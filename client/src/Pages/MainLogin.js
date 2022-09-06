import QuestItem from '../Components/QuestItem';
import { MainContainer, Div } from './MainLogout';
import { useEffect, useState } from 'react';
import NoResult from '../Components/NoResult';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Loading from '../Components/Loading';
import useSortStore from '../Store/store-sort';
import SortBtnBar from '../Components/SortBtnBar';
import styled from 'styled-components';
import RightSideBar from '../Components/RightSideBar';

const MainLoginDiv = styled(MainContainer)`
  .btns {
    display: flex;
    justify-content: flex-end;
  }
`;

const MainLogin = () => {
  const navigate = useNavigate();
  const [ispending, setIsPending] = useState(true);
  const [noResult, setNoResult] = useState({
    status: 'data',
    keyword: 'no data',
  });
  const { data, setData, setSort, setPageInfo } = useSortStore(
    (state) => state
  );
  useEffect(() => {
    axios
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
      setSort('newest');
      setData([]);
      setPageInfo({});
    };
  }, []);

  return (
    <Div>
      <MainLoginDiv>
        <div className="pageDesc">
          <h2>Top Questions</h2>
          <button onClick={() => navigate('/write')}>Ask Question</button>
        </div>
        <div className="btns">
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
              <ul>
                {data.map((el) => (
                  <QuestItem el={el} key={el.questionId} />
                ))}
              </ul>
            ) : (
              <>
                <NoResult keyword={noResult.keyword} status={noResult.status} />
              </>
            )}
          </>
        )}
      </MainLoginDiv>
      <RightSideBar />
    </Div>
  );
};
export default MainLogin;
