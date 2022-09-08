import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import useLoginSuccessStore from '../Store/store-loginSuccess';
import { Li } from './LeftSideBar';

const MobileLeftSideBar = ({ openMobileMenu, setOpenMobileMenu }) => {
  const { loginSuccess } = useLoginSuccessStore((state) => state);
  const navigate = useNavigate();

  const handleClickHome = () => {
    navigate('/g');
    setOpenMobileMenu(!openMobileMenu);
  };
  const handleClickQuestion = () => {
    navigate('/');
    setOpenMobileMenu(!openMobileMenu);
  };
  const handleClickMyPage = () => {
    navigate('/mypage');
    setOpenMobileMenu(!openMobileMenu);
  };

  return (
    <Container display={openMobileMenu ? 'content' : 'none'}>
      <ul>
        <Li margin="10px" onClick={handleClickHome}>
          Home
        </Li>
        <Li
          cursor="default"
          size="11px"
          color="#656765"
          bg="#f1f2f3"
          border="none"
        >
          PUBLIC
        </Li>
        <Li onClick={handleClickQuestion}>
          <span className="material-icons">language</span>
          Questions
        </Li>
        {loginSuccess ? <Li onClick={handleClickMyPage}>My page</Li> : null}
      </ul>
    </Container>
  );
};

const Container = styled.nav`
  background-color: #f1f2f3;
  width: 230px;
  padding: 0;
  position: fixed;
  top: 0;
  z-index: 4;
  ul {
    margin-top: 70px;
  }
  a {
    text-decoration: none;
    &:visited {
      color: inherit;
    }
  }
  .material-icons {
    vertical-align: -20%;
    font-size: 17px;
  }
  @keyframes openMenu {
    0% {
      transform: translate3d(0, -100%, 0);
    }
    to {
      transform: translateZ(0);
    }
  }
  display: ${(props) => props.display || 'content'};
  left: 50%;
  height: 400px;
  border: 1px solid rgba(77, 77, 77, 0.45);
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  animation: openMenu 1s;
  @media only screen and (min-width: 768px) {
    display: none;
  }
`;

export default MobileLeftSideBar;
