import styled from 'styled-components';
const NavHeader = styled.header`
  width: 100%;
  height: 50px;
  background-color: aqua;
  .headerContainer {
    display: flex;
    width: 100%;
    padding: 10px;
    max-width: 1200px;
    margin: auto;
    justify-content: space-between;
    align-items: center;
  }
  .logo {
    flex: 1 1 10%;
    background-color: blue;
  }
  .searchHeader {
    flex: 1 1 70%;
    background-color: red;
    display: flex;
    border: 2px solid black;
    border-radius: 5px;
    span {
      flex: 1 1 5%;
      text-align: center;
      line-height: 30px;
    }
    input {
      flex: 1 1 95%;
      padding: 5px;
    }
  }
  .logInOut {
    flex: 1 1 20%;
    text-align: right;
    span {
      padding: 5px;
      border-radius: 5px;
      background-color: azure;
      margin-right: 10px;
    }
  }
`;

const Nav = () => {
  return (
    <NavHeader className="header">
      <div className="headerContainer">
        <div className="logo">여기로고</div>
        <div className="searchHeader">
          <span className="material-icons md-18">search</span>
          <input type="text" maxLength={30} />
        </div>
        <div className="logInOut">
          <span>login</span>
          <span>sign-up</span>
        </div>
      </div>
    </NavHeader>
  );
};
export default Nav;
