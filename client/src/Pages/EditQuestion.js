import styled from 'styled-components';
import MarkdownEditor from '../Components/MarkdownEditor';
import { TitleInput } from './WriteQuestion';
import { Btn } from './Login';

const PageContainer = styled.div`
  display: flex;
  height: calc(100vh - 300px);
  margin: 100px 0;
  @media only screen and (max-width: 1200px) {
    flex-direction: column;
    align-items: center;
  }
`;

const ContentWrapper = styled.div`
  max-width: 1000px;
  @media only screen and (max-width: 768px) and (max-width: 1200px) {
    width: 99%;
  }
`;

const Wrapper = styled.form`
  max-width: 800px;
  margin-left: 230px;
  @media only screen and (max-width: 768px) and (max-width: 1200px) {
    width: 95%;
  }
  @media only screen and (max-width: 767px) {
    margin: 0 10px;
  }
`;

const NoticeWrapper = styled(Wrapper)`
  border: 1.5px solid rgb(237, 228, 189);
  background-color: rgb(251, 247, 226);
  border-radius: 5px;
  height: fit-content;
  width: ${(props) => props.width || 'inherit'};
  margin: ${(props) => props.margin || '0 0 30px 230px'};
  p {
    padding: 0 10px;
    font-size: 13px;
  }
  @media only screen and (max-width: 1200px) {
    margin: 0 0 10px 230px;
  }
  @media only screen and (max-width: 767px) {
    margin: 0 0 10px 0;
  }
`;

const ListTitle = styled.div`
  padding: 10px;
  background-color: rgba(251, 243, 203, 1);
  border-bottom: 1.5px solid rgb(237, 228, 189);
`;

const List = styled.li`
  padding: 5px 0;
  font-size: 13px;
`;

const EditQuestion = () => {
  return (
    <PageContainer>
      <ContentWrapper className="contentWrapper">
        <NoticeWrapper as="div">
          <p>Your edit will be placed in a queue until it is peer reviewed.</p>
          <p>
            We welcome edits that make the post easier to understand and more
            valuable for readers. Because community members review edits, please
            try to make the post substantially better than how you found it, for
            example, by fixing grammar or adding additional resources and
            hyperlinks.
          </p>
        </NoticeWrapper>
        <Wrapper>
          <label required htmlFor="title">
            Title
          </label>
          <TitleInput id="title" />
          <label required htmlFor="body">
            Body
          </label>
          <MarkdownEditor for="body" />
          <Btn width="100px" type="submit" bottom="30px">
            Edit
          </Btn>
        </Wrapper>
      </ContentWrapper>
      <NoticeWrapper as="div" width="350px" margin="70px 0 10px 30px">
        <ListTitle>How to Edit</ListTitle>
        <ul>
          <List>Correct minor typos or mistakes</List>
          <List>Clarify meaning without changing it</List>
          <List>Add related resources or links</List>
          <List>Always respect the author’s intent</List>
          <List>Don’t use edits to reply to the author</List>
        </ul>
      </NoticeWrapper>
    </PageContainer>
  );
};

export default EditQuestion;
