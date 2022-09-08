import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';

import useSortStore from '../Store/store-sort';
import { MainContainer } from './MainLogout';
import NoResult from '../Components/NoResult';
import Pagination from '../Components/Pagination';
import Loading from '../Components/Loading';
import SortBtnBar from '../Components/SortBtnBar';
import defAxios from '../Controller/default';

const SearchContainer = styled(MainContainer)`
  flex: 1 1 1;
  margin: 50px 0 0 230px;
  width: 100%;
  .resultQueryDiv {
    padding: 0px 20px 20px;
    font-size: 12px;
    box-sizing: border-box;
    max-width: 850px;
  }
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;
const SearchResult = () => {
  const [ispending, setIsPending] = useState(true);
  const {
    setQuery,
    setSort,
    setPagination,
    setPageInfo,
    setData,
    pageInfo,
    data,
  } = useSortStore((state) => state);
  // const { setSort } = useSortStore((state) => state);
  const navigate = useNavigate();
  const location = useLocation();
  const query = decodeURI(location.search.slice(3));
  const [noResult, setNoResult] = useState({
    status: 'search',
    keyword: query,
  });
  useEffect(() => {
    setQuery(query);
    setSort('votes');
    defAxios
      .get(`/questions/search?q=${query}&sort=votes&page=1`)
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
    <SearchContainer>
      <div className="pageDesc">
        <h2>Search Results</h2>
        <button onClick={() => navigate('/write')}>Ask Question</button>
      </div>
      <div className="resultQueryDiv">
        Results for {query}
        <br /> query Search options not deleted
      </div>
      <div className="totalNbtns">
        <div className="totalQuestion">{pageInfo.totalElements} results</div>
        <SortBtnBar setData={setData} />
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
                  <QuestItem key={el.questionId} el={el} />
                ))}
              </ul>
              <Pagination
                setIsPending={setIsPending}
                setNoResult={setNoResult}
                status={'search'}
              />
            </>
          ) : (
            <NoResult keyword={noResult.keyword} status={noResult.status} />
          )}
        </>
      )}
    </SearchContainer>
  );
};
export default SearchResult;
