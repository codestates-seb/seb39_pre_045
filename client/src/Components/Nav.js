import styled from 'styled-components';
import { Link } from 'react-router-dom';
import link from '../image/stackoverflow.png';
const NavHeader = styled.header`
  width: 100%;
  height: 50px;
  background-color: #f8f9f9;
  color: #232629;
  box-sizing: border-box;
  position: fixed;
  z-index: 5;
  .topColored {
    width: 100%;
    height: 3px;
    background-color: orange;
  }
  .headerContainer {
    display: flex;
    padding: 10px 20px;
    max-width: 1200px;
    margin: auto;
    justify-content: space-between;
    align-items: center;
  }
  .logo {
    content: '';
    flex: 1 1 15%;
    background-image: url(${link});
    background-repeat: no-repeat;
    background-position: 0 -500px;
    width: 146px;
    height: 30px;
    cursor: pointer;

    /* width: 50px;
    height: 50px; */
  }
  .searchHeader {
    flex: 1 1 65%;
    display: flex;
    border: 1px solid #babfc4;
    border-radius: 5px;
    background-color: white;

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
      ::placeholder {
        color: #babfc4;
      }
    }
  }
  .logInOut {
    flex: 1 1 20%;
    margin-left: 10px;
    span {
      display: inline-block;
      padding: 5px 7px;
      border-radius: 3px;
      background-color: #e1ecf4;
      margin-right: 10px;
      font-size: 14px;
      color: #39739d;
      border: 1px solid hsl(205, 41%, 63%);
      cursor: pointer;
      &.signupBtn {
        color: white;
        background-color: #0a95ff;
        :hover {
          background-color: #0074cc;
        }
      }
    }
  }
`;

const Nav = () => {
  return (
    <NavHeader className="header">
      <div className="topColored"></div>
      <div className="headerContainer">
        <div className="logo" url={link}>
          {/* <img src={link} alt="" /> */}
        </div>
        <div className="searchHeader">
          <span className="material-icons md-18">search</span>
          <input type="text" maxLength={30} placeholder="Search..." />
        </div>
        <div className="logInOut">
          <Link to={'/login'}>
            <span className="loginBtn">Log in</span>
          </Link>
          <Link to={'/signup'}>
            <span className="signupBtn">Sign up</span>
          </Link>
        </div>
      </div>
    </NavHeader>
  );
};
export default Nav;
