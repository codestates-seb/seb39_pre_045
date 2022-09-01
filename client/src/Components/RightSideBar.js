import { NoticeWrapper, ListTitle } from '../Pages/EditQuestion';
import styled from 'styled-components';
import { ReactComponent as Logo } from '../image/rightsidebar.svg';

const RightSideContainer = styled.div`
  margin-left: 10px;
`;

const SideNoticeWrapper = styled(NoticeWrapper)`
  font-size: 12px;
  margin-bottom: 20px;
  .material-icons,
  .number {
    font-size: 13px;
    padding: 1px 5px 0 7px;
  }
`;

const ItemWrapper = styled.div`
  display: flex;
  padding: 10px 10px;
  .logo {
    padding: 1px 5px 0 6px;
    min-width: 15px;
  }
`;

const Words = styled.div`
  margin-left: 10px;
`;

const MailBox = styled(NoticeWrapper)`
  border: 1px solid hsl(210, 8%, 90%);
  background-color: white;
  font-size: 14px;
  .svg-icon {
    padding-right: 5px;
    vertical-align: bottom;
  }
  b {
    padding: 4px;
  }
  li {
    margin: 10px 30px;
    font-size: 12px;
  }
`;

const RigthtSideBar = () => {
  return (
    <RightSideContainer>
      <SideNoticeWrapper width="290px">
        <ListTitle weight>The Overflow Blog</ListTitle>
        <ItemWrapper>
          <div className="material-icons">edit</div>
          <Words>
            What companies lose when they track worker productivity (Ep. 478)
          </Words>
        </ItemWrapper>
        <ItemWrapper>
          <div className="material-icons">edit</div>
          <Words>
            Functional programming is an ideal fit for developing blockchains
          </Words>
        </ItemWrapper>
        <ListTitle border weight>
          Featured on Meta
        </ListTitle>
        <ItemWrapper>
          <div className="material-icons">chat_bubble_outline</div>
          <Words>
            Announcing the Stack Overflow Student Ambassador Program
          </Words>
        </ItemWrapper>
        <ItemWrapper>
          <div className="material-icons">chat_bubble_outline</div>
          <Words>Google Analytics 4 (GA4) upgrade</Words>
        </ItemWrapper>
        <ItemWrapper>
          <Logo className="logo" />
          <Words>Staging Ground Workflow: Question Lifecycle</Words>
        </ItemWrapper>
        <ItemWrapper>
          <Logo className="logo" />
          <Words>The [option] tag is being burninated</Words>
        </ItemWrapper>
        <ItemWrapper>
          <Logo className="logo" />
          <Words>
            Collectives Update: WSO2 launches, and Google Go sunsets
          </Words>
        </ItemWrapper>
        <ListTitle border weight>
          Hot Meta Posts
        </ListTitle>
        <ItemWrapper>
          <div className="number">15</div>
          <Words>Consolidating Python version-specific tag</Words>
        </ItemWrapper>
      </SideNoticeWrapper>
      <MailBox width="290px">
        <ListTitle black>
          <svg
            aria-hidden="true"
            className="svg-icon iconMail"
            width="18"
            height="18"
            viewBox="0 0 18 18"
          >
            <path d="m1 6 8 5 8-5V4L9 9 1 4c0-1.1.9-2 2-2h12c1.09 0 2 .91 2 2v10c0 1.09-.91 2-2 2H3c-1.09 0-2-.91-2-2V6Z"></path>
          </svg>
          Love this site?
        </ListTitle>
        <p>
          {`Get the`}
          <b>weekly newsletter!</b>
          {`In it, you'll get:`}
        </p>
        <ul>
          <li>{`The week's top questions and answers`}</li>
          <li>Important community announcements</li>
          <li>Questions that need answers</li>
        </ul>
      </MailBox>
    </RightSideContainer>
  );
};

export default RigthtSideBar;
