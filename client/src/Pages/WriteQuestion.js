import MarkdownEditor from '../Components/MarkdownEditor';
import styled from 'styled-components';
import { useRef, useState } from 'react';
import { Input, Wrapper, Btn } from '../Pages/Login';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const WriteQuestion = () => {
  const header = `Bearer ${localStorage.getItem('ACCESS_TOKEN')}`;
  axios.defaults.headers = {
    'Content-Type': 'application/json',
    Authorization: header,
  };
  const [title, setTitle] = useState('');
  const editor = useRef();
  const navigate = useNavigate();
  const handleSubmitQuestion = (e) => {
    e.preventDefault();
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      axios
        .post('/questions', {
          title,
          questionContent: editor.current.getInstance().getMarkdown(),
        })
        .then((res) => navigate(`/questions/${res.data.data.questionId}`))
        .catch((err) => console.log(err));
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
        <MarkdownEditor required id="body" ref={editor} />
        <Btn width="180px">Review your Question</Btn>
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
