import styled from 'styled-components';
import MarkdownEditor from '../Components/MarkdownEditor';
import MarkdownViewer from '../Components/MarkdownViewer';
import { Btn } from './Login';
import {
  PageContainer,
  ContentWrapper,
  Wrapper,
  NoticeWrapper,
  ListTitle,
  List,
} from './EditQuestion';
import { useRef } from 'react';
import axios from 'axios';

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

const EditAnswer = () => {
  const editor = useRef();
  const QUESTION_ID = 10;
  const ANSWER_ID = 1;

  const handleSubmitAnswer = (e) => {
    e.preventDefault();
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      axios
        .patch(`/answers/${ANSWER_ID}`, {
          memberId: 1,
          question: {
            questionId: QUESTION_ID,
          },
          answerId: ANSWER_ID,
          answerContent: editor.current.getInstance().getMarkdown(),
        })
        .then((res) => console.log(res))
        .catch((err) => Error(err));
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
