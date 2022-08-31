import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';
// import axios from 'axios'
import { MainContainer, AlignBtns } from './MainLogout';
import NoResult from '../Components/NoResult';
const SearchContainer = styled(MainContainer)`
  .resultQueryDiv {
    padding: 0px 20px 20px;
    font-size: 12px;
    box-sizing: border-box;
    max-width: 850px;
  }
`;
const SearchResult = () => {
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  const location = useLocation();
  const query = decodeURI(location.search.slice(3));
  useEffect(() => {
    //axios.get(`/search?q=${query}&sort=votes`).then(({data})=>setData(data)).catch(err=>alert('검색에 실패했습니다.'))
    const test = [];
    for (let i = 0; i < 20; i++) {
      test.push({
        id: Math.round(Math.random() * 456138313941535),
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
    // axios.get(`/search?q=${query}&sort=${sort}`).then(({data})=>setData(data)).catch(err=>alert('정렬에 실패했습니다'))
    alert(sort);
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
        <div className="totalQuestion">500 results</div>
        <AlignBtns>
          <button onClick={() => handleSort('newest')}>최신순</button>
          <button onClick={() => handleSort('votes')}>추천순</button>
          <button onClick={() => handleSort('answers')}>답변순</button>
        </AlignBtns>
      </div>
      {data.length === 0 ? (
        <ul>
          {data.map((el) => (
            <QuestItem key={el.id} el={el} />
          ))}
        </ul>
      ) : (
        <NoResult keyword={query} status={'search'} />
      )}
    </SearchContainer>
  );
};
export default SearchResult;
