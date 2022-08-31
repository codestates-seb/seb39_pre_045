import styled from 'styled-components';
import MarkdownEditor from '../Components/MarkdownEditor';
import { TitleInput } from './WriteQuestion';
import { Btn } from './Login';
import { useState, useRef } from 'react';

export const PageContainer = styled.div`
  display: flex;
  height: max-content;
  margin: 100px 0 100px 250px;
  max-width: 1000px;
  @media only screen and (max-width: 768px) {
    margin: 100px 0 100px 0;
  }
  @media only screen and (max-width: 1200px) {
    flex-direction: column;
    align-items: center;
  }
`;

export const ContentWrapper = styled.div`
  margin-right: 20px;
  max-width: 700px;
  @media only screen and (max-width: 1200px) {
    margin-right: 10px;
  }
`;

export const Wrapper = styled.form`
  @media only screen and (max-width: 767px) {
    margin-left: 10px;
  }
  label {
    font-weight: 600;
    font-size: 20px;
  }
`;

const NoticeWrapper = styled(Wrapper)`
  border: 1.5px solid rgb(237, 228, 189);
  background-color: rgb(251, 247, 226);
  border-radius: 5px;
  height: fit-content;
  margin-bottom: 10px;
  width: ${(props) => props.width || 'auto'};
  p {
    padding: 0 10px;
    font-size: 13px;
  }
`;

export const ListTitle = styled.div`
  padding: 10px 10px 10px 20px;
  background-color: rgba(251, 243, 203, 1);
  border-bottom: 1.5px solid rgb(237, 228, 189);
`;

export const List = styled.li`
  padding: 5px 0;
  font-size: 13px;
`;

const EditQuestion = () => {
  const [title, setTitle] = useState('');
  const editor = useRef();

  const handleSubmitQuestion = (e) => {
    e.preventDefault();
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      console.log(title);
      console.log(editor.current.getInstance().getHTML());
      console.log(editor.current.getInstance().getMarkdown());
    }
  };
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
        <Wrapper onSubmit={handleSubmitQuestion}>
          <label htmlFor="title">Title</label>
          <TitleInput
            required
            id="title"
            onChange={(e) => setTitle(e.target.value)}
          />
          <label required htmlFor="body">
            Body
          </label>
          <MarkdownEditor required id="body" ref={editor} />
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
