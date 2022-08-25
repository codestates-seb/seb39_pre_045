import styled from 'styled-components';

const Container = styled.div`
  border-radius: 10px;
  background: rgb(241, 242, 243);
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  @media only screen and (max-width: 767px) {
    background-color: aliceblue;
    div {
      background-color: red;
    }
  }
`;

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  background: white;
  border-radius: 10px;
  padding: 3em;
`;

const Input = styled.input`
  &:focus {
    outline: 0.5px solid skyblue;
    box-shadow: 1px 1px blue;
  }
`;

const Login = () => {
  return (
    <Container>
      <Wrapper>
        Email
        <Input type="email" />
        Password
        <Input type="password" />
      </Wrapper>
      <button>Log in</button>
    </Container>
  );
};

export default Login;
