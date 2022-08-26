import styled from 'styled-components';

const Section = styled.div`
  padding: 20px 0 0 20px;
  display: flex;
  div {
    font-size: 40px;
    padding: 10px;
    font-weight: 600;
  }
  span {
    padding: inherit;
    font-size: 20px;
  }
  @media only screen and (max-width: 767px) {
    padding-left: 0;
    img {
      width: 100px;
      height: 100px;
    }
  }
  @media only screen and (min-width: 768px) and (max-width: 1200px) {
    padding-left: 100px;
    span {
      font-size: 15px;
    }
    img {
      width: 110px;
      height: 110px;
    }
  }
`;

const Img = styled.img`
  object-fit: cover;
  width: 150px;
  height: 150px;
  box-shadow: 1px 2px 7px 3px hsla(0, 0%, 0%, 0.1);
  border-radius: 5px;
`;
const MyPageProfile = () => {
  return (
    <Section>
      <Img
        alt="profile"
        src="https://cdn.pixabay.com/photo/2022/08/18/09/20/houses-7394390__480.jpg"
      />
      <div>
        <div>Hello, 닉네임!</div>
        <span>상태메시지 넣을까요...</span>
      </div>
    </Section>
  );
};

export default MyPageProfile;
