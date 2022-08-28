import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';
// import axios from 'axios'
import { MainContainer } from './MainLogout';

const SearchContainer = styled(MainContainer)`
  .resultQueryDiv {
    padding: 0px 20px 20px;
    font-size: 12px;
  }
`;
const SearchResult = () => {
  const [data, setData] = useState([]);

  const location = useLocation();
  useEffect(() => {
    // axios.get(`url${location.pathname}${location.search}`),then(({data})=>setData(data)).catch(err=>alert('검색에 실패했습니다.'))
    console.log(`url${location.pathname}${location.search}`);
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
  return (
    <SearchContainer>
      <div className="pageDesc">
        <h2>Search Results</h2>
        <button>Ask Question</button>
      </div>
      <div className="resultQueryDiv">
        Results for {location.search.slice(3)}
        <br /> query Search options not deleted
      </div>
      <div className="totalNbtns">
        <div className="totalQuestion">500 result 검색결과 총갯수</div>
        {/* <AlignBtns>
          <button>최신순</button>
          <button>추천순</button>
        </AlignBtns> */}
      </div>
      <ul>
        {data.map((el) => (
          <QuestItem key={el.id} el={el} />
        ))}
      </ul>
    </SearchContainer>
  );
};
export default SearchResult;
