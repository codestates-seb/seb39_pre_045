import MarkdownEditor from './MarkdownEditor';
import styled from 'styled-components';
import { Btn } from '../Pages/Login';
import { Wrapper } from '../Pages/EditQuestion';
import { useRef, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Container = styled.div`
  margin-top: 50px;
  border-top: 0.8px solid lightgray;
  padding: 10px 0;
  font-size: 20px;
`;

const Div = styled.div`
  padding: 10px 0;
`;

const NoticeWrapper = styled(Wrapper)`
  border: 1.5px solid rgb(237, 228, 189);
  background-color: rgb(251, 247, 226);
  border-radius: 5px;
  height: fit-content;
  margin: 30px 0;
  font-size: 14px;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  display: ${(props) => props.display};
`;

const CloseBtn = styled.div`
  margin: 20px 0;
  height: fit-content;
  background: none;
  border: none;
  font-size: 20px;
  &:hover {
    cursor: pointer;
  }
`;

const DetailPageAnswerEditor = () => {
  const [isWritingMode, setIsWritingMode] = useState(false);
  const [display, setDisplay] = useState('content');
  const editor = useRef();
  const navigate = useNavigate();
  const QUESTION_ID = 10;

  const handlePostAnswer = (e) => {
    e.preventDefault();
    axios
      .post(`/answers`, {
        memberId: 1,
        question: {
          questionId: QUESTION_ID,
        },
        answerContent: editor.current.getInstance().getHTML(),
      })
      .then(() => navigate(`/questions/${QUESTION_ID}`))
      .catch((err) => Error(err));
  };
  const handleNoticeWrapper = () => {
    setIsWritingMode(true);
  };
  const handleCloseNoticeWrapper = () => {
    setDisplay('none');
  };
  return (
    <Container>
      <Div>Your Answer</Div>
      <Wrapper onSubmit={handlePostAnswer} onClick={handleNoticeWrapper}>
        <MarkdownEditor ref={editor} />
        {isWritingMode ? (
          <NoticeWrapper as="div" display={display}>
            <div>
              <p>Thanks for contributing an answer to Stack Overflow!</p>
              <ul>
                <li>
                  Please be sure to answer the question. Provide details and
                  share your research!
                </li>
              </ul>
              <p>But avoid …</p>
              <ul>
                <li>
                  Asking for help, clarification, or responding to other
                  answers.
                </li>
                <li>
                  Making statements based on opinion; back them up with
                  references or personal experience.
                </li>
              </ul>
              <p>To learn more, see our tips on writing great answers.</p>
            </div>
            <CloseBtn role="button" onClick={handleCloseNoticeWrapper}>
              X
            </CloseBtn>
          </NoticeWrapper>
        ) : null}
        <Btn type="submit" width="130px">
          Post Your Answer
        </Btn>
      </Wrapper>
    </Container>
  );
};

export default DetailPageAnswerEditor;
