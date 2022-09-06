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
  RedBtn,
} from './EditQuestion';
import { useEffect, useRef } from 'react';
import useDetailQuestion from '../Store/store-detailquestion';
import { useNavigate, useParams } from 'react-router-dom';
import axiosInstance from '../Controller/ApiController';

const EditAnswer = () => {
  const { detailData } = useDetailQuestion((state) => state);
  const editor = useRef();
  const { id } = useParams();
  const navigate = useNavigate();
  const QUESTION_ID = detailData.questionId;
  const ANSWER_ID = id;

  useEffect(() => {
    if (detailData?.answers !== undefined) {
      const currentAnswer = detailData.answers.filter((el) => {
        return el.answerId === parseInt(id);
      });
      const value = currentAnswer[0].answerContent;
      editor.current.getInstance().setMarkdown(value, true);
    } else {
      alert('잘못된 접근입니다. 뒤로가기를 눌러주세요.');
    }
  }, []);

  const handleSubmitAnswer = (e) => {
    e.preventDefault();
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      axiosInstance
        .patch(`/answers/${ANSWER_ID}`, {
          question: {
            questionId: QUESTION_ID,
          },
          answerId: ANSWER_ID,
          answerContent: editor.current.getInstance().getMarkdown(),
        })
        .then(() => {
          window.scrollTo(0, 1000);
          navigate(`/questions/${QUESTION_ID}`);
        })
        .catch((err) =>
          alert(err.response.data.message || '다시 시도해주세요')
        );
    }
  };

  const handleClickCancel = () => {
    navigate(`/`);
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
          <div className="title">{detailData.title}</div>
          <MarkdownViewer margin="10px 0" value={detailData.questionContent} />
        </QuestionWrapper>
        <Wrapper onSubmit={handleSubmitAnswer}>
          <label htmlFor="answer">Answer</label>
          <MarkdownEditor required for="body" ref={editor} />
          <Btn width="100px" type="submit" bottom="30px">
            Edit
          </Btn>
          <RedBtn type="button" onClick={handleClickCancel}>
            Cancel
          </RedBtn>
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

export default EditAnswer;
