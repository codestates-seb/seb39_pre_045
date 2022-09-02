import QuestItem from '../Components/QuestItem';
import { AlignBtns, SortBtns, MainContainer } from './MainLogout';
import { useEffect, useState } from 'react';
import NoResult from '../Components/NoResult';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Loading from '../Components/Loading';

const RightAlignBtns = styled(AlignBtns)`
  align-self: flex-end;
`;

const MainLogin = () => {
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  const [sortClick, setSortClick] = useState('newest');
  const [ispending, setIsPending] = useState(true);
  const [noResult, setNoResult] = useState({
    status: 'data',
    keyword: 'no data',
  });
  useEffect(() => {
    axios
      .get(`questions?page=1&sort=newest&filters=`)
      .then(({ data }) => {
        setData(data.data !== undefined ? data.data : []);
      })
      .catch((err) => {
        setData([]);
        setTimeout(() => {
          setNoResult({ status: 'httpErr', keyword: err.response.status });
          setIsPending(false);
        }, 200);
        console.log(err);
      });
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
    setSortClick(sort);
  };
  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>Top Questions</h2>
        <button onClick={() => navigate('/write')}>Ask Question</button>
      </div>

      <RightAlignBtns>
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
      </RightAlignBtns>
      {ispending === true ? (
        <>
          <Loading />
        </>
      ) : (
        <>
          {' '}
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
              <NoResult keyword={noResult.keyword} status={noResult.status} />
            </>
          )}
        </>
      )}
    </MainContainer>
  );
};
export default MainLogin;
