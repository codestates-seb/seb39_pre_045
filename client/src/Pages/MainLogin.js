import QuestItem from '../Components/QuestItem';
import { MainContainer } from './MainLogout';
const MainLogin = () => {
  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>Top Questions</h2>
        <button>Ask Question</button>
      </div>
      <ul>
        <QuestItem />
        <QuestItem />
        <QuestItem />
        <QuestItem />
      </ul>
    </MainContainer>
  );
};
export default MainLogin;
