import styled from 'styled-components';
import QuestItem from '../Components/QuestItem';
const MainContainer = styled.div`
  width: 100%;
  margin-top: 50px;
  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  padding: 20px;
`;

const MainLogout = () => {
  return (
    <MainContainer>
      All Questions
      <QuestItem />
      <QuestItem />
      <QuestItem />
      <QuestItem />
    </MainContainer>
  );
};
export default MainLogout;
