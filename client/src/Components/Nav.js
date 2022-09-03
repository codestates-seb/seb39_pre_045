import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import link from '../image/stackoverflow.png';
import { useRef, useState } from 'react';
import useLoginSuccessStore from '../Store/store-loginSuccess';
// import axios from 'axios'
const NavHeader = styled.header`
  width: 100%;
  height: 50px;
  background-color: #f8f9f9;
  color: #232629;
  box-sizing: border-box;
  position: fixed;
  z-index: 5;
  box-shadow: 0px 2px 5px #b0bec5;
  top: 0;
  ::after {
    content: '';
    position: absolute;
    width: 100%;
    height: 3px;
    background-color: orange;
    top: 0;
  }
  .headerContainer {
    display: flex;
    padding-right: 5px;
    max-width: 1200px;
    margin: 10px auto;
    justify-content: space-between;
    align-items: center;
    height: 30px;
  }
  .logo {
    content: '';
    margin-top: -5px;
    margin-right: 20px;
    margin-left: 5px;
    background-image: url(${link});
    background-repeat: no-repeat;
    background-position: 0 -500px;
    min-width: 146px;
    height: 30px;
    cursor: pointer;

    /* width: 50px;
    height: 50px; */
  }
  .searchHeader {
    display: flex;
    border: 1px solid #babfc4;
    border-radius: 5px;
    background-color: white;
    width: 50%;
    span {
      flex: 1 1 5%;
      text-align: center;
      line-height: 30px;
      color: #babfc4;
      font-size: 24px;
    }
    input {
      flex: 1 1 95%;
      padding: 5px;
      border: none;
      outline: none;
      box-sizing: border-box;
      ::placeholder {
        color: #babfc4;
      }
    }
  }
  .logInOut {
    /* flex: 1 1 35%; */
    margin-left: 10px;
    display: flex;
    align-items: center;
    span {
      display: inline-block;
      padding: 5px 7px;
      border-radius: 3px;
      background-color: #e1ecf4;
      margin-left: 10px;
      font-size: 14px;
      color: #39739d;
      border: 1px solid hsl(205, 41%, 63%);
      cursor: pointer;
      &.signupBtn {
        color: white;
        background-color: #0a95ff;
        margin-right: 0;
        box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
        :hover {
          background-color: #0074cc;
        }
      }
    }
    button.logout {
      display: inline-block;
      padding: 7px;
      border-radius: 3px;

      margin-left: 10px;
      font-size: 14px;

      border: 1px solid hsl(205, 41%, 63%);
      cursor: pointer;
      color: white;
      background-color: #0a95ff;
      margin-right: 0;
      box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
      :hover {
        background-color: #0074cc;
      }
    }
    button.material-icons {
      padding: 0;
      margin: 0;
      border: none;
      background-color: initial;
      font-size: 24px;
      width: 30px;
      font-weight: 600;
      display: none;
    }
  }
  @media screen and (max-width: 768px) {
    .headerContainer {
      justify-content: space-between;
      .logo {
        width: 30px;
        height: 30px;
        min-width: 25px;
        max-width: 25px;
      }
      .searchHeader {
        /* flex: 1 1 50%; */
        position: absolute;
        margin: 5px 5%;
        top: 50px;
        width: 90%;
        display: ${(props) => (props.display === 'false' ? 'none' : 'flex')};
        padding: 5px;
        :focus {
          box-shadow: 0px 0px 5px #0a95ff;
        }
      }
      .logInOut {
        /* flex: 1 1 50%; */
        margin-left: 5px;
        .material-icons {
          display: inline-block;
        }
      }
    }
  }
`;

const Nav = ({ handleMobileMenuOpen }) => {
  const [isblock, setIsblock] = useState(false);
  const searchVal = useRef();
  const navigate = useNavigate();
  const { loginSuccess, setLoginSuccess } = useLoginSuccessStore(
    (state) => state
  );

  // const [searchVal, setSearchVal] = useState('');
  const showBlock = () => {
    setIsblock(!isblock);
    searchVal.current.value = '';
  };
  const handleSearch = (e) => {
    if (e.key === 'Enter') {
      if (e.target.value === '') {
        return;
      }
      // setSearchVal(e.target.value);
      // alert(searchVal);
      const result = e.target.value;
      e.target.value = '';
      navigate(`/search?q=${result}`);
    }
  };
  const handleLogout = () => {
    // axios.post(url,data).then(({data})=>{
    //   //로그온 정보 싹다 날리기. 해당 헤더 바꾸기위해서 useState설정
    //   useNavigate('/')}).catch(err=>alert('로그아웃에 실패하였습니다'))
    setLoginSuccess(false);
    alert('test');
  };
  return (
    <NavHeader className="header" display={`${isblock}`}>
      <div className="headerContainer">
        <Link to={'/'}>
          <div className="logo" url={link}></div>
        </Link>

        <div className="searchHeader">
          <span className="material-icons md-18">search</span>
          <input
            type="text"
            maxLength={30}
            placeholder="Search..."
            ref={searchVal}
            onKeyUp={handleSearch}
          />
        </div>
        <div className="logInOut">
          <button onClick={handleMobileMenuOpen} className="material-icons">
            menu_open
          </button>
          <button className="material-icons" onClick={showBlock}>
            search
          </button>
          {loginSuccess === false ? (
            <>
              <Link to={'/login'}>
                <span className="loginBtn">Log in</span>
              </Link>
              <Link to={'/signup'}>
                <span className="signupBtn">Sign up</span>
              </Link>
            </>
          ) : (
            <>
              <Link to={'/mypage'}>
                <span className="loginBtn">My Page</span>
              </Link>
              <button className="logout" onClick={handleLogout}>
                Log Out
              </button>
            </>
          )}
        </div>
      </div>
    </NavHeader>
  );
};
export default Nav;
