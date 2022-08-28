/*eslint-disable*/
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';
export const MainContainer = styled.div`
  width: 100%;
  margin-top: 50px;
  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  margin: 50px 0 0 230px;
  /* padding: 20px; */
  .pageDesc {
    padding: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 850px;
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
    max-width: 850px;
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
  button {
    background-color: white;
    border: none;
    cursor: pointer;
  }
`;

const MainLogout = () => {
  const [data, setData] = useState([]);
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

  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>All Questions</h2>
        <button>Ask Question</button>
      </div>
      <div className="totalNbtns">
        <div className="totalQuestion">22,932,174 questions</div>
        {/* <AlignBtns>
          <button>최신순</button>
          <button>추천순</button>
        </AlignBtns> */}
      </div>
      <ul>
        {data.map((el) => (
          <>
            <QuestItem el={el} />
          </>
        ))}
      </ul>
    </MainContainer>
  );
};
export default MainLogout;
