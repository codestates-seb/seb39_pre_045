/*eslint-disable*/
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import NoResult from '../Components/NoResult';
import QuestItem from '../Components/QuestItem';
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
  const [sortClick, setSortClick] = useState('newest');
  const navigate = useNavigate();
  useEffect(() => {
    // axios.get(`/question?page=1`).then(({data})=>setData(data)).catch(err=>alert('데이터 조회에 실패하였습니다'))
    const test = [];
    for (let i = 1; i <= 20; i++) {
      test.push({
        id: i,
        vote: Math.round(Math.random() * 100),
        answer: Math.round(Math.random() * 4),
        view: Math.round(Math.random() * 4000),
        title:
          'Selecting Multiple Values from a Dropdown List Across Multiple Columns',
        content:
          'I am trying to select multiple options from a dropdown list across multiple columns. I have taken the widely used script for one coloumn and converted it but it is having some negative effects across ...',
        author: 'Clare Vallely',
        time: 2022,
        chosen: Math.round(Math.random() * 5) > 3 ? true : false,
      });
    }
    setData(test);
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
        <div className="totalQuestion">22,932,174 questions</div>
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
      {data.length !== 0 ? (
        <ul>
          {data.map((el) => (
            <>
              <QuestItem el={el} key={el.id} />
            </>
          ))}
        </ul>
      ) : (
        <>
          <NoResult keyword={'no data'} status={'data'} />
        </>
      )}
    </MainContainer>
  );
};
export default MainLogout;
