// import { useState } from 'react';
import { useRef } from 'react';
import styled from 'styled-components';

const SignupContainer = styled.div`
  width: 100%;
  margin-top: 50px;
  height: calc(100vh - 50px);
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f1f2f3;
  /* 이거 전체 배경색으로 바꿔야함 */
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
      transform: translate(-300px);
    }
  }
  @media screen and (max-width: 786px) {
    display: none;
  }
`;
const SignupForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: white;
  width: 250px;
  padding: 20px;
  border-radius: 10px;
  animation: rightslide 0.5s forwards 1 ease-out;
  label {
    margin-bottom: 5px;
    font-weight: 600;
    color: #0c0d0e;
  }
  input {
    padding: 8px;
    border: 1px solid #babfc4;
    border-radius: 5px;
    margin-bottom: 5px;
    outline: none;
    :focus {
      box-shadow: 0px 0px 5px #0a95ff;
    }
  }
  div.recap {
    width: 100%;
    height: 100px;
    background-color: beige;
    margin-bottom: 20px;
  }
  div.agreeCheck {
    margin-bottom: 10px;
    font-size: 14px;
  }
  button {
    border: none;
    background-color: #0a95ff;
    color: white;
    border-radius: 5px;
    padding: 10px;
    cursor: pointer;
    :hover {
      background-color: #0074cc;
    }
  }
  @keyframes rightslide {
    to {
      transform: translate(0px);
    }
    from {
      transform: translate(300px);
    }
  }
`;
const Signup = () => {
  const name = useRef();
  const email = useRef();
  const password = useRef();
  // const [formdata, Setformdata] = useState({
  //   name,
  //   email,
  //   password,
  // });

  const submitSignup = (e) => {
    e.preventDefault();
    return;
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
          <input id="signupName" type="text" ref={name} />

          <label htmlFor="signupEmail">Email</label>
          <input id="signupEmail" type="email" ref={email} />

          <label htmlFor="signupPw">Password</label>
          <input id="signupPw" type="password" ref={password} />
          <div className="recap">리캡쳐자리</div>
          <div className="agreeCheck">
            <input type="checkbox" name="" id="" />
            어쩌구에 동의하시겠습니까?
          </div>
          <button onClick={submitSignup}>Sign up</button>
        </SignupForm>
      </div>
    </SignupContainer>
  );
};
export default Signup;
