import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import NoResult from '../Components/NoResult';
import QuestItem from '../Components/QuestItem';
import Loading from '../Components/Loading';
import Pagination from '../Components/Pagination';
import useSortStore from '../Store/store-sort';
import SortBtnBar from '../Components/SortBtnBar';
import RightSideBar from '../Components/RightSideBar';
import defAxios from '../Controller/default';
export const MainContainer = styled.div`
  width: 100%;
  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  flex: 1 1 80%;
  max-width: 850px;
  .pageDesc {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
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
    padding: 0 10px;
    .totalQuestion {
      padding: 0 10px;
      font-size: 16px;
    }
  }
  ul {
    padding: 0;
    margin-bottom: 50px;
    min-height: calc(100vh - 400px);
  }
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;
export const Div = styled.div`
  display: flex;
  margin: 50px 0 0 230px;
  width: 100%;
  max-width: 1200px;
  min-height: calc(100vh - 50px);
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;
const MainLogout = () => {
  // const [data, setData] = useState([]);
  // const [pageInfo, setPageInfo] = useState({});
  const [ispending, setIsPending] = useState(true);
  const navigate = useNavigate();
  const [noResult, setNoResult] = useState({
    status: 'data',
    keyword: 'no data',
  });
  const { setPagination, data, setData, setSort, setPageInfo, pageInfo } =
    useSortStore((state) => state);
  useEffect(() => {
    defAxios
      .get(`/questions?page=1&sort=newest&filters=`)
      .then(({ data }) => {
        setData(data.data !== undefined ? data.data : []);
        setPageInfo(data.pageInfo);
        setIsPending(false);
      })
      .catch((err) => {
        setData([]);
        setTimeout(() => {
          setNoResult({ status: 'httpErr', keyword: err.response.status });
          setIsPending(false);
        }, 200);
        console.log(err);
      });
    return () => {
      setPagination(1);
      setSort('newest');
      setData([]);
      setPageInfo({});
    };
  }, []);

  return (
    <Div>
      <MainContainer>
        <div className="pageDesc">
          <h2>All Questions</h2>
          <button onClick={() => navigate('/write')}>Ask Question</button>
        </div>
        <div className="totalNbtns">
          <div className="totalQuestion">
            {pageInfo.totalElements} questions
          </div>
          <SortBtnBar
            setData={setData}
            setIsPending={setIsPending}
            setNoResult={setNoResult}
          />
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
                    <QuestItem el={el} key={el.questionId} />
                  ))}
                </ul>
                <Pagination
                  setIsPending={setIsPending}
                  setNoResult={setNoResult}
                  status={'question'}
                />
              </>
            ) : (
              <>
                <NoResult keyword={noResult.keyword} status={noResult.status} />
              </>
            )}
          </>
        )}
      </MainContainer>
      <RightSideBar />
    </Div>
  );
};
export default MainLogout;
