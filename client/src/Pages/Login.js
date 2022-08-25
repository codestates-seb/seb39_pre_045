import styled from 'styled-components';

const Container = styled.div`
  background-color: #f1f2f3;
  font-size: 14px;
  width: 100%;
  margin-top: 50px;
  height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: 600;
  @media only screen and (max-width: 767px) {
    form {
      width: 10em;
    }
    div {
      flex-direction: column;
    }
  }
  @media only screen and (min-width: 768px) {
    width: 100vw;
  }
`;

const Wrapper = styled.form`
  display: flex;
  flex-direction: column;
  background: white;
  border-radius: 10px;
  padding: 3em 3em;
  width: 288px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
`;

const PasswordDiv = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Input = styled.input`
  border-radius: 5px;
  border: 1px solid #babfc4;
  height: 32px;
  padding: 5px 9px;
  margin: 10px 0 20px 0;
  &:focus {
    outline: 0.5px solid skyblue;
    box-shadow: 0px 0px 2px 4px rgb(222, 233, 246);
  }
`;

const Span = styled.div`
  color: ${(props) => props.color || 'black'};
  font-size: ${(props) => props.size || '14px'};
`;

const Btn = styled.button`
  background: #2495ff;
  padding: 10px 0;
  border: none;
  color: white;
  border-radius: 5px;
  font-weight: 600;
  box-shadow: 0px 0px 5px 1px #0071db inset;
  &:hover {
    background-color: #0057a8;
    box-shadow: 0px 0px 1px 2px #b8dcff;
    cursor: pointer;
  }
`;

const Login = () => {
  return (
    <Container>
      <Wrapper>
        Email
        <Input type="email" />
        <PasswordDiv>
          <Span>Password</Span>
          <Span color="#3e7bf4" size="11px">
            Forgot Password?
          </Span>
        </PasswordDiv>
        <Input type="password" />
        <Btn>Log in</Btn>
      </Wrapper>
    </Container>
  );
};

export default Login;
