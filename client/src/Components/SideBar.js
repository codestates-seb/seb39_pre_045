import styled from 'styled-components';
// import { useState } from 'react';

const Container = styled.nav`
  width: 164px;
`;
const Li = styled.li`
  padding: 8px 6px 8px 0;
  list-style: none;
`;

const SideBar = () => {
  // const [isLogin, setIsLogin] = useState(false);
  return (
    <Container>
      <ul>
        <Li>Home</Li>
        <Li>Public</Li>
        <Li>Questions</Li>
        {/* {isLogin ? <li>Users</li> : null} */}
      </ul>
    </Container>
  );
};

export default SideBar;
