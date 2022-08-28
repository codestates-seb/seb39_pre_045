import styled from 'styled-components';
// import { useState } from 'react';

const Container = styled.nav`
  background-color: #f1f2f3;
  width: 230px;
  padding: 0;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  border-right: 0.3px solid rgba(77, 77, 77, 0.45);
  z-index: 4;
  ul {
    margin-top: 70px;
  }
  @keyframes openMenu {
    0% {
      transform: translate3d(0, -100%, 0);
    }
    to {
      transform: translateZ(0);
    }
  }
  @media only screen and (max-width: 767px) {
    display: ${(props) => props.display || 'content'};
    left: 50%;
    height: 400px;
    border: 1px solid rgba(77, 77, 77, 0.45);
    box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
      0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
    animation: openMenu 1s;
  }
  @media only screen and (min-width: 768px) and (max-width: 1200px) {
    width: 210px;
  }
`;

export const Li = styled.li`
  padding: 8px 0px 8px 10px;
  list-style: none;
  color: ${(props) => props.color || '#383938'};
  margin-bottom: ${(props) => props.margin || '0'};
  font-size: ${(props) => props.size || '14px'};
  background-color: ${(props) => props.selected || 'auto'};
  border-radius: ${(props) => props.radius || '0'};
  span {
    font-size: 14px;
    margin-right: 5px;
  }
  &:hover {
    cursor: ${(props) => props.cursor || 'pointer'};
    background-color: ${(props) => props.bg || '#ebebeb'};
    border-right: ${(props) => props.border || '3px solid orange'};
  }
`;

const SideBar = ({ openMobileMenu }) => {
  // const [isLogin, setIsLogin] = useState(false);

  return (
    <Container display={openMobileMenu ? 'content' : 'none'}>
      <ul>
        <Li margin="10px">Home</Li>
        <Li
          cursor="default"
          size="11px"
          color="#656765"
          bg="#f1f2f3"
          border="none"
        >
          PUBLIC
        </Li>
        <Li>
          <span className="material-icons">language</span>Questions
        </Li>
        {/* {isLogin ? <li>Users</li> : null} */}
      </ul>
    </Container>
  );
};

export default SideBar;
