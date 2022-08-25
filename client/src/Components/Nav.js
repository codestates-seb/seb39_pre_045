import styled from 'styled-components';
const NavHeader = styled.header`
  width: 100%;
  height: 50px;
  background-color: #e1ecf4;
  box-sizing: border-box;
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
    flex: 1 1 10%;
    background-image: url('../image/stckoverflow.png');
    background-position: 0 -500px;
    /* width: 50px;
    height: 50px; */
  }
  .searchHeader {
    flex: 1 1 70%;
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
      padding: 6px;
      border-radius: 5px;
      background-color: azure;
      margin-right: 10px;
      font-size: 14px;
      border: 1px solid hsl(205, 41%, 63%);
      cursor: pointer;
      :last-child {
        color: white;

        background-color: #6bbbf7;
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
      <div className="headerContainer">
        <div className="logo"></div>
        <div className="searchHeader">
          <span className="material-icons md-18">search</span>
          <input type="text" maxLength={30} placeholder="Search..." />
        </div>
        <div className="logInOut">
          <span>Log in</span>
          <span>Sign up</span>
        </div>
      </div>
    </NavHeader>
  );
};
export default Nav;
