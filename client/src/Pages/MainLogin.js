import QuestItem from '../Components/QuestItem';
import { AlignBtns, MainContainer } from './MainLogout';
import { useEffect, useState } from 'react';
import NoResult from '../Components/NoResult';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const RightAlignBtns = styled(AlignBtns)`
  align-self: flex-end;
`;

const MainLogin = () => {
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    // axios.get(url).then({data}=>setData(data)).catch(err=>alert('데이터 조회에 실패하였습니다'))
    const test = [];
    for (let i = 0; i < 20; i++) {
      test.push({
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
    // axios.get(`/question?sort=${sort}`).then(({data})=>setData(data)).catch(err=>alert('정렬에 실패했습니다'))
    alert(sort);
  };
  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>Top Questions</h2>
        <button onClick={() => navigate('/write')}>Ask Question</button>
      </div>

      <RightAlignBtns>
        <button onClick={() => handleSort('newest')}>최신순</button>
        <button onClick={() => handleSort('votes')}>추천순</button>
        <button onClick={() => handleSort('answers')}>답변순</button>
      </RightAlignBtns>

      {data.length !== 0 ? (
        <ul>
          {data.map((el) => (
            <>
              <QuestItem el={el} />
            </>
          ))}
        </ul>
      ) : (
        <>
          <NoResult keyword={'err'} status={'data'} />
        </>
      )}
    </MainContainer>
  );
};
export default MainLogin;
