import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';
import axios from 'axios';
import useSortStore from '../Store/store-sort';
import { AlignBtns, SortBtns, MainContainer } from './MainLogout';
import NoResult from '../Components/NoResult';
import Pagination from '../Components/Pagination';
import Loading from '../Components/Loading';
const SearchContainer = styled(MainContainer)`
  .resultQueryDiv {
    padding: 0px 20px 20px;
    font-size: 12px;
    box-sizing: border-box;
    max-width: 850px;
  }
`;
const SearchResult = () => {
  const [ispending, setIsPending] = useState(true);
  const [data, setData] = useState([]);
  const [pageInfo, setPageInfo] = useState({});
  const { setQuery } = useSortStore((state) => state);
  const { setSort } = useSortStore((state) => state);
  const [sortClick, setSortClick] = useState('newest');
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
    axios
      .get(`/questions/search?q=${query}&sort=votes&page=1`)
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
    // axios.get(`/search?q=${query}&sort=${sort}`).then(({data})=>setData(data)).catch(err=>alert('정렬에 실패했습니다'))
    alert(sort);
    setSortClick(sort);
  };

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
        <div className="totalQuestion">{pageInfo.totalElments}results</div>
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
                  <QuestItem key={el.questionId} el={el} />
                ))}
              </ul>
              <Pagination />
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
