import MarkdownEditor from '../Components/MarkdownEditor';
import styled from 'styled-components';
import { useRef, useState } from 'react';
import { Input, Wrapper, Btn } from '../Pages/Login';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../Controller/ApiController';
import { RedBtn } from './EditQuestion';

const WriteQuestion = () => {
  const [title, setTitle] = useState('');
  const [message, setMessage] = useState('');
  const editor = useRef();
  const navigate = useNavigate();

  const handleClickCancel = () => {
    navigate(`/`);
  };

  const handleSubmitQuestion = (e) => {
    e.preventDefault();
    setMessage('처리중입니다');
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      axiosInstance
        .post('/questions', {
          title,
          questionContent: editor.current.getInstance().getMarkdown(),
        })
        .then((res) => {
          setMessage('');
          navigate(`/questions/${res.data.data.questionId}`);
        })
        .catch((err) => {
          setMessage('');
          alert(
            err.response?.data.message
              ? err.response.data.message
              : '다시 시도해주세요'
          );
        });
    }
  };

  return (
    <WritePage>
      <Wrapper
        width="500px"
        maxWidth="700px"
        mobile="0"
        middle="200px"
        onSubmit={handleSubmitQuestion}
      >
        <label htmlFor="title">Title</label>
        <P>
          Be specific and imagine you’re asking a question to another person
        </P>
        <TitleInput
          required
          type="text"
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <label htmlFor="body">Body</label>
        <P>
          Include all the information someone would need to answer your question
        </P>
        <MarkdownEditor
          required
          id="body"
          ref={editor}
          value={'please write here'}
        />
        <div>
          <Btn width="180px">Review your Question</Btn>
          <RedBtn type="button" onClick={handleClickCancel}>
            Cancel
          </RedBtn>
        </div>
        <span>{message}</span>
      </Wrapper>
    </WritePage>
  );
};
export const WritePage = styled.div`
  height: max-content;
  margin: 100px 0;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  label {
    font-weight: 600;
  }
`;

export const TitleInput = styled(Input)`
  width: 100%;
  padding: 0.5em 0;
`;

const P = styled.p`
  font-size: 0.8em;
`;

export default WriteQuestion;
