import MarkdownEditor from '../Components/MarkdownEditor';
import styled from 'styled-components';
import { useRef, useState } from 'react';
import { Input, Wrapper, Btn } from '../Pages/Login';

const WritePage = styled.div`
  height: calc(100vh - 50px);
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  label {
    font-weight: 600;
  }
`;

const TitleInput = styled(Input)`
  width: 100%;
  padding: 0.5em 0;
`;

const P = styled.p`
  font-size: 0.8em;
`;

const WriteQuestion = () => {
  const [title, setTitle] = useState('');
  const editor = useRef();

  const handleRegisterButton = (e) => {
    e.preventDefault();
    console.log(title);
    console.log(editor.current.getInstance().getHTML());
    console.log(editor.current.getInstance().getMarkdown());
  };

  return (
    <WritePage>
      <Wrapper width="500px" maxWidth="800px" mobile="0" middle="200px">
        <label htmlFor="title">Title</label>
        <P>
          Be specific and imagine you’re asking a question to another person
        </P>
        <TitleInput
          type="text"
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <label htmlFor="body">Body</label>
        <P>
          Include all the information someone would need to answer your question
        </P>
        <MarkdownEditor id="body" ref={editor} />
        <Btn width="180px" onClick={handleRegisterButton}>
          Review your Question
        </Btn>
      </Wrapper>
    </WritePage>
  );
};

export default WriteQuestion;
