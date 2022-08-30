import styled from 'styled-components';
import { Li } from '../Components/SideBar';
import MyPageProfile from '../Components/MyPageProfile';
import MyPageQItem from '../Components/MyPageQItem';
import MyPageAItem from '../Components/MyPageAItem';
import MyPageEdit from '../Components/MyPageEdit';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const PageWrapper = styled.div`
  margin: 80px 0 0 30%;
  display: flex;
  flex-direction: column;
  width: 100%;
  height: calc(100vh - 350px);
  @media only screen and (max-width: 767px) {
    margin: 50px 0 0 20px;
    .menuContainer {
      display: flex;
      flex-direction: row;
      width: 250px;
      justify-content: space-between;
    }
    .section {
      flex-direction: column;
    }
    li {
      padding-right: 10px;
    }
  }
  @media only screen and (min-width: 768px) and (max-width: 1200px) {
    .menuContainer {
      padding-left: 30px;
    }
    div {
      width: 90%;
    }
  }
`;

const MenuContainer = styled.ul`
  padding: 0;
  width: 120px;
`;

const Section = styled.div`
  padding: 20px 0 0 20px;
  display: flex;
`;

const InsideContentWrapper = styled(Section)`
  padding: 20px;
  display: flex;
  flex-direction: column;
  @media only screen and (max-width: 767px) {
    padding: 20px 0 0 0;
    div {
      width: 85%;
    }
  }
`;

export const Title = styled.div`
  font-size: 20px;
  font-weight: 500;
`;

export const Content = styled.div`
  margin: 10px 10px 30px 0;
  border-radius: 5px;
  border: 1px solid #e0e0e0;
  max-width: 1000px;
  min-height: 200px;
  width: 50vw;
  padding: 20px;
  display: flex;
  flex-direction: column;
`;

const MyPage = () => {
  const [selectQ, setSelectQ] = useState(true);
  const [selectA, setSelectA] = useState(false);
  const [selectE, setSelectE] = useState(false);
  const navigate = useNavigate();
  const handleClickQuestions = () => {
    navigate('?questions');
    setSelectQ(true);
    setSelectA(false);
    setSelectE(false);
  };
  const handleClickAnswers = () => {
    navigate('?answers');
    setSelectQ(false);
    setSelectA(true);
    setSelectE(false);
  };
  const handleClickEdit = () => {
    navigate('?edit');
    setSelectQ(false);
    setSelectA(false);
    setSelectE(true);
  };
  return (
    <PageWrapper>
      <MyPageProfile />
      <Section className="section">
        <MenuContainer className="menuContainer">
          <Li
            onClick={handleClickQuestions}
            border="none"
            radius="15px"
            size="13px"
          >
            Questions
          </Li>
          <Li
            onClick={handleClickAnswers}
            border="none"
            radius="15px"
            size="13px"
          >
            Answers
          </Li>
          <Li onClick={handleClickEdit} border="none" radius="15px" size="13px">
            Edit Profile
          </Li>
        </MenuContainer>
        <InsideContentWrapper>
          {selectQ ? <MyPageQItem /> : null}
          {selectA ? <MyPageAItem /> : null}
          {selectE ? <MyPageEdit /> : null}
        </InsideContentWrapper>
      </Section>
    </PageWrapper>
  );
};

export default MyPage;
