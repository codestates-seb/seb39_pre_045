import styled from 'styled-components';
import { Li } from '../Components/SideBar';
import MyPageProfile from '../Components/MyPageProfile';
import MyPageQItem from '../Components/MyPageQItem';
import MyPageAItem from '../Components/MyPageAItem';
import MyPageEdit from '../Components/MyPageEdit';
// import { useState } from 'react';

const PageWrapper = styled.div`
  margin-top: 50px;
  display: flex;
  flex-direction: column;
  width: 100%;
  @media only screen and (max-width: 767px) {
    .ul {
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
`;

const MenuContainer = styled.ul`
  padding: 0;
  width: 150px;
`;

const Section = styled.div`
  padding: 20px 0 0 20px;
  display: flex;
`;

const InsideContentWrapper = styled(Section)`
  padding: 20px 0 0 20px;
  display: flex;
  flex-direction: column;
  @media only screen and (max-width: 767px) {
    padding: 20px 0 0 0;
    div {
      width: 85vw;
      margin-left: 0;
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
  // const [selectQ, setSelectQ] = useState('');
  // const [selectA, setSelectA] = useState('');
  // const [selectE, setSelectE] = useState('');
  // const onClickEvent = () => {
  //   setSelectQ(`#ebebeb`);
  // };
  return (
    <PageWrapper>
      <MyPageProfile />
      <Section className="section">
        <MenuContainer className="ul">
          <Li border="none" radius="15px" size="13px">
            Questions
          </Li>
          <Li border="none" radius="15px" size="13px">
            Answers
          </Li>
          <Li border="none" radius="15px" size="13px">
            Edit Profile
          </Li>
        </MenuContainer>
        <InsideContentWrapper>
          <MyPageQItem />
          <MyPageAItem />
          <MyPageEdit />
        </InsideContentWrapper>
      </Section>
    </PageWrapper>
  );
};

export default MyPage;
