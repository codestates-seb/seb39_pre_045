import MarkdownEditor from './MarkdownEditor';
import styled from 'styled-components';
import { Btn } from '../Pages/Login';
import { Wrapper } from '../Pages/EditQuestion';
import { useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../Controller/ApiController';

const DetailPageAnswerEditor = (props) => {
  const { setData, originData } = props;
  const [isWritingMode, setIsWritingMode] = useState(false);
  const [display, setDisplay] = useState('content');
  const [message, setMessage] = useState('');
  const { id } = useParams();
  const editor = useRef();
  const QUESTION_ID = id;

  const handlePostAnswer = (e) => {
    e.preventDefault();
    setMessage('처리중입니다');
    axiosInstance
      .post(`/answers`, {
        question: {
          questionId: QUESTION_ID,
        },
        answerContent: editor.current.getInstance().getMarkdown(),
      })
      .then(({ data }) => {
        setMessage('');
        editor.current.getInstance().setHTML('');
        setData({
          ...originData,
          answers: [...originData.answers, data.data],
        });
      })
      .catch((err) => {
        alert(err.response?.data.message || '다시 시도해주세요');
        setMessage('');
      });
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
        <MarkdownEditor ref={editor} value={'please write here'} />
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
        <span>{message}</span>
      </Wrapper>
    </Container>
  );
};

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

export default DetailPageAnswerEditor;
