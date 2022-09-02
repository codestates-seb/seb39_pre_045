import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import NoResult from '../Components/NoResult';
import QuestItem from '../Components/QuestItem';
import Loading from '../Components/Loading';
import Pagination from '../Components/Pagination';
export const MainContainer = styled.div`
  width: 100%;
  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  margin: 50px 0 0 230px;
  max-width: 850px;
  /* 이거민영님한테물어보기 */
  /* padding: 20px; */
  .pageDesc {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    /* max-width: 850px; */
    h2 {
      padding: 0 10px;
      font-weight: 500;
    }
    button {
      border: none;
      background-color: #0a95ff;
      color: white;
      border-radius: 5px;
      padding: 10px;
      cursor: pointer;
      user-select: none;
      box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
      :hover {
        background-color: #0074cc;
      }
    }
  }
  .totalNbtns {
    display: flex;
    justify-content: space-between;
    /* max-width: 850px; */
    padding: 0 10px;
    .totalQuestion {
      padding: 0 10px;
      font-size: 16px;
    }
  }
  ul {
    padding: 0;
  }
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;
export const AlignBtns = styled.div`
  border: 1px solid #babfc49b;
  border-radius: 3px;
`;
export const SortBtns = styled.button`
  font-size: 12px;
  border: none;
  color: #0c0d0e;
  background-color: ${(props) => (props.sort ? '#babfc49b' : 'transparent')};
  cursor: pointer;
  padding: 8px;
  border-right: 1px solid #babfc49b;
  transition: background-color ease-in-out 0.5s;
  :last-child {
    border-right: none;
  }
`;

const MainLogout = () => {
  const [data, setData] = useState([]);
  const [pageInfo, setPageInfo] = useState({});
  const [sortClick, setSortClick] = useState('newest');
  const [ispending, setIsPending] = useState(true);
  const navigate = useNavigate();
  const [noResult, setNoResult] = useState({
    status: 'data',
    keyword: 'no data',
  });
  useEffect(() => {
    axios
      .get(`questions?page=1&sort=newest&filters=`)
      .then(({ data }) => {
        setData(data.data !== undefined ? data.data : []);
        setPageInfo(data.pageInfo);
      })
      .catch((err) => {
        setData([]);
        setTimeout(() => {
          setNoResult({ status: 'httpErr', keyword: err.response.status });
          setIsPending(false);
        }, 200);
        console.log(err);
      });
  }, []);
  const handleSort = (sort) => {
    // axios.get(`/question?sort=${sort}`).then(({data})=>{
    //   setData(data)
    //   setSortClick(sort)
    // }).catch(err=>alert('정렬에 실패했습니다'))
    setSortClick(sort);
  };
  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>All Questions</h2>
        <button onClick={() => navigate('/write')}>Ask Question</button>
      </div>
      <div className="totalNbtns">
        <div className="totalQuestion">{pageInfo.totalElements} questions</div>
        <AlignBtns>
          <SortBtns
            sort={sortClick === 'newest' && 'active'}
            onClick={() => handleSort('newest')}
          >
            newest
          </SortBtns>
          <SortBtns
            sort={sortClick === 'votes' && 'active'}
            onClick={() => handleSort('votes')}
          >
            votes
          </SortBtns>
          <SortBtns
            sort={sortClick === 'answers' && 'active'}
            onClick={() => handleSort('answers')}
          >
            answers
          </SortBtns>
        </AlignBtns>
      </div>
      {ispending === true ? (
        <>
          <Loading />
        </>
      ) : (
        <>
          {data.length !== 0 ? (
            <>
              <ul>
                {data.map((el) => (
                  <>
                    <QuestItem el={el} key={el.questionId} />
                  </>
                ))}
              </ul>
              <Pagination />
            </>
          ) : (
            <>
              <NoResult keyword={noResult.keyword} status={noResult.status} />
            </>
          )}
        </>
      )}
    </MainContainer>
  );
};
export default MainLogout;
