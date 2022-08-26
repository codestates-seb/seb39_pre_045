// import { useState } from 'react';
import { useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SignupContainer = styled.div`
  width: 100%;
  margin-top: 50px;
  height: calc(100vh - 50px);
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f1f2f3;
  padding: 10px;
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
  }
  input {
    padding: 8px;
    border: 1px solid #babfc4;
    border-radius: 5px;
    margin-bottom: 15px;
    outline: none;
    position: relative;
    &#signupName {
      background-color: ${(props) =>
        props.nameValid === 'false' ? 'rgba(255, 0, 0, 0.1)' : 'initial'};
    }

    /* & #signupName { */
    :after {
      content: ${(props) =>
        props.nameValid === 'false' ? 'Email cannot be empty.' : ''};
      position: absolute;
      bottom: 0;
      left: 0;
      width: 250px;
      height: 10px;
      font-size: 12px;
      color: black;
    }
    /* } */
    :focus {
      box-shadow: 0px 0px 5px #0a95ff;
    }
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
  const formName = useRef();
  const formEmail = useRef();
  const formPassword = useRef();
  // const [formdata, Setformdata] = useState({
  //   name: '',
  //   email: '',
  //   password: '',
  // });
  const [isvalid, setIsValid] = useState({
    displayName: undefined,
    email: undefined,
    password: undefined,
  });
  const handleSubmit = (e) => {
    e.preventDefault();
    const name = formName.current.value;
    const email = formEmail.current.value;
    const password = formPassword.current.value;
    const newObj = { ...isvalid };
    if (name === '') {
      newObj.displayName = false;
    } else {
      newObj.displayName = true;
    }
    if (email === '') {
      newObj.email = false;
    } else {
      newObj.email = true;
    }
    if (password === '') {
      newObj.password = false;
    } else {
      newObj.email = true;
    }
    setIsValid({ ...newObj });
    alert(isvalid.email);

    return;
  };
  // const handleFormState =()=>{
  //   if
  // }
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
        <SignupForm
          action=""
          nameValid={`${isvalid.displayName}`}
          emailValid={`${isvalid.email}`}
          pwValid={`${isvalid.password}`}
        >
          <label htmlFor="signupName">Display name</label>
          <input id="signupName" type="text" ref={formName} />

          <label htmlFor="signupEmail">Email</label>
          <input
            id="signupEmail"
            type="email"
            ref={formEmail}
            minLength="9"
            maxLength="30"
          />

          <label htmlFor="signupPw">Password</label>
          <input id="signupPw" type="password" ref={formPassword} />
          <div className="agreeCheck">
            <input type="checkbox" name="" id="" />
            어쩌구에 동의하시겠습니까?
          </div>
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
