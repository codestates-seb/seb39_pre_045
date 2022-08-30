import { useEffect } from 'react';
import styled from 'styled-components';
// import axios from 'axios';
import useStore from '../Store/store';

const Container = styled.div`
  background-color: #f1f2f3;
  font-size: 14px;
  margin-top: 50px;
  width: 100vw;
  height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: 600;
`;

export const Wrapper = styled.form`
  display: flex;
  flex-direction: column;
  background: white;
  border-radius: 10px;
  padding: 3em 3em;
  width: ${(props) => props.maxWidth || '288px'};
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);

  @media only screen and (max-width: 767px) {
    width: ${(props) => props.width || '10em'};
    margin-left: ${(props) => props.mobile || '0'};
  }
  @media only screen and (min-width: 768px) and (max-width: 1200px) {
    width: ${(props) => props.width || '220px'};
    margin-left: ${(props) => props.middle || '0'};
  }
`;

const PasswordDiv = styled.div`
  display: flex;
  justify-content: space-between;
  @media only screen and (max-width: 767px) {
    flex-direction: column;
  }
`;

export const Input = styled.input`
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

const Div = styled.div`
  color: ${(props) => props.color || 'black'};
  font-size: ${(props) => props.size || '14px'};
`;

export const Btn = styled.button`
  margin-bottom: ${(props) => props.bottom || 'auto'};
  background: #2495ff;
  padding: 10px 0;
  border: none;
  color: white;
  border-radius: 5px;
  font-weight: 600;
  box-shadow: 0px 0px 5px 1px #0071db inset;
  width: ${(props) => props.width || 'auto'};
  &:hover {
    background-color: #0057a8;
    box-shadow: 0px 0px 1px 2px #b8dcff;
    cursor: pointer;
  }
`;

const Login = () => {
  const { setLoginMode } = useStore((state) => state);
  useEffect(() => {
    setLoginMode(true);
    return () => {
      setLoginMode(false);
    };
  }, []);
  const onSubmitEvent = (e) => {
    e.preventDefault();
  };
  // useEffect(() => {
  //   axios
  //     .post('/test', { name: 'hihihi' })
  //     .then((res) => console.log(res))
  //     .catch((err) => console.log(err));
  // }, []);
  return (
    <Container>
      <Wrapper>
        <label htmlFor="loginEmail">Email</label>
        <Input required id="loginEmail" type="email" />
        <PasswordDiv>
          <label htmlFor="loginPassword">Password</label>
          <Div color="#3e7bf4" size="11px">
            Forgot Password?
          </Div>
        </PasswordDiv>
        <Input required autoComplete="off" id="loginPassword" type="password" />
        <Btn onClick={onSubmitEvent} type="submit">
          Log in
        </Btn>
      </Wrapper>
    </Container>
  );
};

export default Login;
