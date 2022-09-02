import styled from 'styled-components';
// import { useState } from 'react';
import { Link } from 'react-router-dom';
import useLoginSuccessStore from '../Store/store-loginSuccess';
import { Li } from './LeftSideBar';

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

const MobileLeftSideBar = ({ openMobileMenu }) => {
  const { loginSuccess } = useLoginSuccessStore();
  const handleMainLoginBtn = () => {
    //if(isLogin) navigate('/g')
    //else{navigate('/login')}
  };

  return (
    <Container display={openMobileMenu ? 'content' : 'none'}>
      <ul>
        <Li margin="10px" onClick={handleMainLoginBtn}>
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
        <Link to="/">
          <Li>
            <span className="material-icons">language</span>
            Questions
          </Li>
        </Link>
        {loginSuccess ? <li>Users</li> : null}
      </ul>
    </Container>
  );
};

export default MobileLeftSideBar;
