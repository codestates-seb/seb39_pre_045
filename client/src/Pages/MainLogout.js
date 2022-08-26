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
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;

const MainLogout = () => {
  return (
    <MainContainer>
      <div className="pageDesc">
        <h2>All Questions</h2>
        <button>Ask Question</button>
      </div>

      <QuestItem />
      <QuestItem />
      <QuestItem />
      <QuestItem />
    </MainContainer>
  );
};
export default MainLogout;
