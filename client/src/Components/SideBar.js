import styled from 'styled-components';
// import { useState } from 'react';

const Container = styled.nav`
  width: 230px;
  padding: 0;
  height: 100vh;
  border-right: 0.3px solid rgba(77, 77, 77, 0.45);
  ul {
    margin-top: 70px;
  }
  @media only screen and (max-width: 767px) {
    display: none;
  }
`;

const Li = styled.li`
  padding: 8px 6px 8px 0;
  list-style: none;
  color: ${(props) => props.color || '#383938'};
  margin-bottom: ${(props) => props.margin || '0'};
  font-size: ${(props) => props.size || '14px'};
  span {
    font-size: 14px;
    margin-right: 5px;
  }
  &:hover {
    cursor: ${(props) => props.cursor || 'pointer'};
  }
`;

const SideBar = () => {
  // const [isLogin, setIsLogin] = useState(false);
  return (
    <Container>
      <ul>
        <Li margin="10px">Home</Li>
        <Li cursor="default" size="11px" color="#656765">
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
