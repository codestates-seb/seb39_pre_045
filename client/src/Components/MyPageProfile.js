import styled from 'styled-components';

const Section = styled.div`
  padding: 20px 20px;
  display: flex;
  .greeting {
    padding: 20px 20px;
    font-size: 35px;
    font-weight: 600;
  }
  .status {
    padding-left: 20px;
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
    padding-left: 50px;
    img {
      width: 120px;
      height: 120px;
    }
  }
  @media only screen and (max-width: 1200px) {
    .greeting {
      font-size: 25px;
    }
    .status {
      font-size: 15px;
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
        <div className="greeting">Hello, 닉네임!</div>
        <span className="status">123@stackoverflow.com</span>
      </div>
    </Section>
  );
};

export default MyPageProfile;
