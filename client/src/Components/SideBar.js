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
  @media only screen and (max-width: 767px) {
    display: none;
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

const SideBar = () => {
  // const [isLogin, setIsLogin] = useState(false);
  return (
    <Container>
      <ul>
        <Li margin="10px">Home</Li>
        <Li
          cursor="default"
          size="11px"
          color="#656765"
          bg="white"
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
