import styled from 'styled-components';

const MyPageProfile = ({ parsed }) => {
  return (
    <Section>
      <Img
        alt="profile"
        src="https://cdn.pixabay.com/photo/2022/08/18/09/20/houses-7394390__480.jpg"
      />
      <div>
        <div className="greeting">Hello, {parsed.username}!</div>
        <span className="status">
          <span className="material-icons">mail_outline</span>
          {parsed.email}
        </span>
        <span className="status">
          {parsed.gender === 'female' ? (
            <span className="material-icons">female</span>
          ) : (
            <span className="material-icons">male</span>
          )}
        </span>
        <span className="status">
          <span className="material-icons">cake</span> {parsed.age}
        </span>
      </div>
    </Section>
  );
};

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
  .material-icons {
    font-size: 20px;
  }
  @media only screen and (max-width: 767px) {
    padding-left: 0;
    img {
      width: 100px;
      height: 100px;
    }
  }
  .material-icons {
    padding-right: 5px;
    font-size: 17px;
    color: gray;
    vertical-align: bottom;
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
export default MyPageProfile;
