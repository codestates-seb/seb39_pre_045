import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import useSideBarStore from '../Store/store-sidebar';
import useLoginSuccessStore from '../Store/store-loginSuccess';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const { setLeftSideBarHidden } = useSideBarStore((state) => state);
  const { loginSuccess, setLoginSuccess } = useLoginSuccessStore(
    (state) => state
  );
  const [errorMessage, setErrorMessage] = useState('');
  const [loginInfo, setLoginInfo] = useState({
    email: '',
    password: '',
  });
  const [searchInfo, setSearchInfo] = useState({
    email: '',
    username: '',
  });
  const navigate = useNavigate();
  const [change, setChange] = useState(true);

  useEffect(() => {
    setLeftSideBarHidden(true);
    if (loginSuccess) {
      navigate('/g');
    }
    return () => {
      setLeftSideBarHidden(false);
    };
  }, []);

  const handleInputValue = (key) => (e) => {
    setLoginInfo({ ...loginInfo, [key]: e.target.value });
    setSearchInfo({ ...searchInfo, [key]: e.target.value });
  };

  const handleForgotPW = () => {
    setChange(!change);
  };

  const handleLoginRequest = (e) => {
    e.preventDefault();
    setErrorMessage('처리중입니다');
    axios
      .post('/members/login', loginInfo)
      .then((res) => {
        if (res.data.data.accessToken) {
          localStorage.setItem('ACCESS_TOKEN', res.data.data.accessToken);
        }
        if (res.data.data.refreshToken) {
          localStorage.setItem('REFRESH_TOKEN', res.data.data.refreshToken);
        }
        localStorage.setItem(
          'USER_INFO',
          JSON.stringify({
            username: res.data.data.username,
            email: res.data.data.email,
            memberId: res.data.data.memberId,
            gender: res.data.data.gender || null,
            age: res.data.data.age || null,
          })
        );
        alert(res.data.data.username + ' 님 환영합니다!');
      })
      .then(() => {
        setLoginSuccess(true);
        setErrorMessage('');
        navigate('/g');
      })
      .catch(() => {
        setErrorMessage('로그인에 실패했습니다');
      });
  };

  const handleRecovery = (e) => {
    e.preventDefault();
    // alert('작성하신 이메일로 임시 비밀번호를 발송하였습니다.');
    setErrorMessage('처리중입니다');
    axios
      .post('/members/recovery', searchInfo)
      .then(() => {
        setErrorMessage('');
        alert('작성하신 이메일로 임시 비밀번호를 발송하였습니다.');
      })
      .catch((err) => {
        setErrorMessage('');
        alert(err.response.data.message || '다시 시도해주세요');
      });
  };

  return (
    <>
      {change ? (
        <Container>
          <Wrapper onSubmit={handleLoginRequest}>
            <label htmlFor="email">Email</label>
            <Input
              required
              id="email"
              type="email"
              onChange={handleInputValue('email')}
            />
            <PasswordDiv>
              <label htmlFor="loginPassword">Password</label>
              <Div color="#3e7bf4" size="11px" onClick={handleForgotPW}>
                Forgot Password?
              </Div>
            </PasswordDiv>
            <Input
              required
              autoComplete="off"
              id="password"
              type="password"
              onChange={handleInputValue('password')}
            />
            <Btn type="submit">Log in</Btn>
            {errorMessage === '' ? null : errorMessage}
          </Wrapper>
        </Container>
      ) : (
        <Container>
          <Wrapper onSubmit={handleRecovery}>
            <button
              type="button"
              className="backToLogin"
              onClick={handleForgotPW}
            >
              Back to login
            </button>
            <label htmlFor="username">Email</label>
            <Input
              required
              id="email"
              type="email"
              onChange={handleInputValue('email')}
            />
            <label htmlFor="username">username</label>
            <Input
              required
              id="username"
              type="text"
              onChange={handleInputValue('username')}
            />
            <Btn type="submit">Confirm</Btn>
            {errorMessage === '' ? null : errorMessage}
          </Wrapper>
        </Container>
      )}
    </>
  );
};

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
  .backToLogin {
    width: fit-content;
    margin-bottom: 20px;
    &:hover {
      cursor: pointer;
    }
  }
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
  &:hover {
    cursor: pointer;
  }
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

export default Login;
