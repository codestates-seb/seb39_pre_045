// import { useState } from 'react';
import { useRef, useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import useStore from '../Store/store';

const SignupContainer = styled.div`
  width: 100%;
  margin-top: 50px;
  height: calc(100vh - 50px);
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f1f2f3;
  padding: 10px;
  box-sizing: border-box;
  /* 이거 전체 배경색으로 바꿔야함 */
  .alreadyAcc {
    width: 250px;
    text-align: center;
    padding: 10px;
    font-size: 14px;
    a {
      margin-left: 5px;
      text-decoration: none;
      color: #0a95ff;
    }
  }
`;
const SignupDesc = styled.div`
  margin-right: 30px;
  animation: slide 0.5s forwards 1 ease-out;
  h2 {
    padding: 0;
    font-size: 20px;
  }
  .sentence {
    display: flex;
    align-items: center;
    .material-icons {
      padding: 2px;
      font-size: 20px;
      color: #0a95ff;
    }
    p {
      font-size: 14px;
    }
  }
  @keyframes slide {
    to {
      transform: translate(0px);
    }
    from {
      transform: translate(-100px);
    }
  }
  @media screen and (max-width: 768px) {
    display: none;
  }
`;
const SignupForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: white;
  width: 250px;
  padding: 30px 20px 50px;
  border-radius: 10px;
  animation: rightslide 0.5s forwards 1 ease-out;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  label {
    margin-bottom: 5px;
    font-weight: 600;
    color: #0c0d0e;
    font-size: 14px;
    :last-of-type {
      position: relative;
      ::after {
        content: '※ optional';
        font-size: 10px;
        width: 100px;
        height: 12px;
        position: absolute;
        left: 30px;
        top: 3px;
        font-style: italic;
      }
    }
  }
  input {
    padding: 8px;
    border: 1px solid #babfc4;
    border-radius: 5px;
    outline: none;
    position: relative;

    :focus {
      box-shadow: 0px 0px 5px #0a95ff;
    }
  }
  .alert {
    font-size: 12px;
    color: red;
    height: 15px;
    font-weight: 700;
  }
  fieldset {
    border: none;
    padding: 0;
    margin-bottom: 10px;
    legend {
      padding: 0;
      margin-bottom: 5px;
      font-weight: 600;
      color: #0c0d0e;
      font-size: 14px;
      position: relative;
      ::after {
        content: '※ optional';
        font-size: 10px;
        width: 100px;
        height: 12px;
        position: absolute;
        left: 50px;
        top: 3px;
        font-style: italic;
      }
    }
    input {
      margin-left: 0;
    }
    input[value='male'] {
      margin-left: 20px;
    }
  }
  div.agreeCheck {
    margin-bottom: 10px;
    font-size: 14px;
  }
  button {
    margin-top: 15px;
    border: none;
    background-color: #0a95ff;
    color: white;
    border-radius: 5px;
    padding: 10px;
    cursor: pointer;
    box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
    :hover {
      background-color: #0074cc;
    }
  }
  @keyframes rightslide {
    to {
      transform: translate(0px);
    }
    from {
      transform: translate(100px);
    }
  }
  @media screen and (max-width: 768px) {
    animation: scale 0.5s forwards 1 ease-out;
    @keyframes scale {
      to {
        transform: scale(1);
      }
      from {
        transform: scale(0.5);
      }
    }
  }
`;
const Signup = () => {
  const { setLoginMode } = useStore((state) => state);
  const checkName = useRef();
  const checkEmail = useRef();
  const checkPw = useRef();
  // const [user, setuser] = useState({});
  const [formData, SetFormData] = useState({
    username: '',
    email: '',
    password: '',
    gender: null,
    age: null,
  });

  useEffect(() => {
    setLoginMode(true);
    return () => {
      setLoginMode(false);
    };
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      formData.username === '' ||
      formData.email === '' ||
      formData.password === ''
    ) {
      alert('입력하지 않은 정보가 있습니다');
      return;
    } else {
      //통신 자리
      // axios.post(url,formData).then(({data})=>{함수를 만들어야하나? useNavigate써서 리다이렉트 }).catch(err=>alert('회원가입에 실패하였습니다')
    }
    return;
  };
  const handleFormState = (e) => {
    console.log(e.target.value);
    console.log(e.target.name, 'name');
    SetFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const checkisInvalid = (ref, value, min, max) => {
    if (ref === checkEmail) {
      if (value === '') {
        ref.current.textContent = '※빈칸을 채워주세요';
      } else {
        if (value.includes('@') === false) {
          ref.current.textContent = '※올바른 이메일 주소를 입력해주세요';
        } else {
          ref.current.textContent = '';
        }
      }
    } else {
      if (value === '') {
        ref.current.textContent = '※빈칸을 채워주세요';
      } else if ((value !== '' && value.length < min) || value.length > max) {
        ref.current.textContent = `※최소 ${min}자 최대 ${max}자로 설정해주세요`;
      } else {
        ref.current.textContent = '';
      }
    }
  };
  return (
    <SignupContainer>
      <SignupDesc className="desc">
        <h2>Join the Stack Overflow community</h2>
        <div>
          <div className="sentence">
            <span className="material-icons">live_help</span>
            <p>Get unstuck — ask a question</p>
          </div>
          <div className="sentence">
            <span className="material-icons">check</span>
            <p> Unlock new privileges like voting and commenting</p>
          </div>
          <div className="sentence">
            <span className="material-icons">local_offer</span>

            <p> Save your favorite tags, filters, and jobs</p>
          </div>
          <div className="sentence">
            <span className="material-icons">emoji_events</span>
            <p> Earn reputation and badges</p>
          </div>
        </div>
      </SignupDesc>
      <div>
        {/* <div>에센에스로긴</div> */}
        <SignupForm action="">
          <label htmlFor="signupName">Display name</label>
          <input
            id="signupName"
            type="text"
            onChange={handleFormState}
            name="username"
            onBlur={() => checkisInvalid(checkName, formData.username, 2, 8)}
          />
          <span className="alert" ref={checkName}></span>
          <label htmlFor="signupEmail">Email</label>
          <input
            id="signupEmail"
            type="email"
            minLength="9"
            name="email"
            maxLength="30"
            onChange={handleFormState}
            onBlur={() => checkisInvalid(checkEmail, formData.email, 9, 30)}
          />
          <span className="alert" ref={checkEmail}></span>
          <label htmlFor="signupPw">Password</label>
          <input
            id="signupPw"
            name="password"
            type="password"
            onChange={handleFormState}
            onBlur={() => checkisInvalid(checkPw, formData.password, 4, 8)}
          />
          <span className="alert" ref={checkPw}></span>
          <fieldset className="genderFieldset">
            <legend>Gender</legend>
            <input type="radio" value="female" name="gender" />
            female
            <input type="radio" value="male" name="gender" />
            male
          </fieldset>
          <label htmlFor="age">Age</label>
          <input type="number" id="age" min="8" max="100" />

          {/* <div className="agreeCheck">
            <input type="checkbox" name="" id="" />
            어쩌구에 동의하시겠습니까?
          </div> */}
          <button onClick={handleSubmit}>Sign up</button>
        </SignupForm>

        <div className="alreadyAcc">
          Already have an account?
          <Link to={'/login'}>Log in</Link>
        </div>
      </div>
    </SignupContainer>
  );
};
export default Signup;
