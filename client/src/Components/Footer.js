import styled from 'styled-components';
import link from '../image/stackoverflow.png';

const Footer = () => {
  return (
    <StyledFooter>
      <Container>
        <Logo />
        <Container padding="0">
          <MenuItems className="menu">
            <MenuItems>STACK OVERFLOW</MenuItems>
            <Item>Questions</Item>
            <Item>Help</Item>
          </MenuItems>
          <MenuItems className="menu">
            <MenuItems>PRODUCTS</MenuItems>
            <Item>Teams</Item>
            <Item>Advertising</Item>
            <Item>Collectives</Item>
            <Item>Talent</Item>
          </MenuItems>
          <MenuItems className="menu">
            <MenuItems>COMPANY</MenuItems>
            <Item>About</Item>
            <Item>Press</Item>
            <Item>Work Here</Item>
            <Item>Legal</Item>
            <Item>Privacy Policy</Item>
            <Item>Terms of Service</Item>
            <Item>Contact Us</Item>
            <Item>Cookie Settings</Item>
            <Item>Cookie Policy</Item>
          </MenuItems>
          <MenuItems className="menu">
            <MenuItems>STACK EXCHANGE NETWORK</MenuItems>
            <Item>Technology</Item>
            <Item>Culture & recreation</Item>
            <Item>Life & arts</Item>
            <Item>Science</Item>
            <Item>Professional</Item>
            <Item margin="35px">Business</Item>
            <Item>API</Item>
            <Item>Data</Item>
          </MenuItems>
          <MenuItems>
            <Item>Blog</Item>
            <Item>Facebook</Item>
            <Item>Twitter</Item>
            <Item>LinkedIn</Item>
            <Item>Instagram</Item>
          </MenuItems>
        </Container>
      </Container>
    </StyledFooter>
  );
};

const StyledFooter = styled.footer`
  background-color: rgb(41, 42, 45);
  height: 350px;
  width: 100%;
  position: relative;
  bottom: 0;
  z-index: 1000;
  color: hsl(210, 8%, 75%);
  @media only screen and (max-width: 767px) {
    height: 300px;
  }
`;

const Container = styled.div`
  display: flex;
  padding: ${(props) => props.padding || '50px 20px 0 20px'};
  justify-content: center;
  @media only screen and (max-width: 767px) {
    flex-direction: column;
  }
  @media only screen and (min-width: 768px) {
    .menu {
      margin: 0 30px;
    }
  }
`;

const Logo = styled.div`
  background-image: url(${link});
  background-repeat: no-repeat;
  background-position: 0 -500px;
  width: 30px;
  height: 50px;
`;

const MenuItems = styled.div`
  margin: 0 3vw 1em 0;
  font-size: 14px;
  font-weight: bold;
`;

const Item = styled.div`
  font-size: 12px;
  font-weight: 500;
  /* margin-bottom: ${(props) => props.margin || '10px'}; */
  margin: 0 10px 10px 0;
  @media only screen and (max-width: 767px) {
    display: none;
  }
`;
export default Footer;
