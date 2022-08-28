import QuestItem from '../Components/QuestItem';
import { MainContainer } from './MainLogout';
import { useEffect, useState } from 'react';
const MainLogin = () => {
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
        <h2>Top Questions</h2>
        <button>Ask Question</button>
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
export default MainLogin;
