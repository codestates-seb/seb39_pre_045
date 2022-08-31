import styled from 'styled-components';
import MarkdownEditor from '../Components/MarkdownEditor';
import MarkdownViewer from '../Components/MarkdownViewer';
import { Btn } from './Login';
import {
  PageContainer,
  ContentWrapper,
  Wrapper,
  ListTitle,
  List,
} from './EditQuestion';
import { useRef } from 'react';

const QuestionWrapper = styled.div`
  @media only screen and (max-width: 767px) {
    margin-left: 15px;
  }
  .title {
    margin-top: 20px;
    font-size: 19px;
    color: #2578ad;
    font-weight: 500;
  }
`;

const NoticeWrapper = styled(Wrapper)`
  border: 1.5px solid rgb(237, 228, 189);
  background-color: rgb(251, 247, 226);
  border-radius: 5px;
  height: fit-content;
  width: ${(props) => props.width || 'auto'};
  p {
    padding: 0 10px;
    font-size: 13px;
  }
`;

const EditAnswer = () => {
  const editor = useRef();

  const handleSubmitAnswer = (e) => {
    e.preventDefault();
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
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
        <QuestionWrapper>
          <div className="title">원문 질문 제목</div>
          <MarkdownViewer margin="10px 0" />
        </QuestionWrapper>
        <Wrapper onSubmit={handleSubmitAnswer}>
          <label htmlFor="answer">Answer</label>
          <MarkdownEditor required for="body" ref={editor} />
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

export default EditAnswer;
