import styled from 'styled-components';
import MarkdownEditor from '../Components/MarkdownEditor';
import { TitleInput } from './WriteQuestion';
import { Btn } from './Login';
import { useState, useRef, useEffect } from 'react';
import axiosInstance from '../Controller/ApiController';
import { useNavigate, useParams } from 'react-router-dom';
import useDetaulQuestion from '../Store/store-detailquestion';

const EditQuestion = () => {
  const { detailData } = useDetaulQuestion((state) => state);
  const [title, setTitle] = useState(detailData.title);
  const { id } = useParams();
  const editor = useRef();
  const navigate = useNavigate();
  const QUESTION_ID = id;
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    if (detailData?.questionId === undefined) {
      alert('잘못된 접근입니다. 취소 버튼을 눌러주세요.');
    }
  }, []);

  const handleClickCancel = () => {
    navigate(`/questions/${QUESTION_ID}`);
  };

  const handleSubmitQuestion = (e) => {
    e.preventDefault();
    setErrorMessage('처리중입니다');
    if (editor.current.getInstance().getMarkdown() === 'please write here') {
      alert('내용을 입력해주세요');
    } else {
      axiosInstance
        .patch(`/questions/${QUESTION_ID}`, {
          questionId: parseInt(QUESTION_ID),
          title,
          questionContent: editor.current.getInstance().getMarkdown(),
        })
        .then(() => {
          window.scrollTo(0, 1000);
          setErrorMessage('');
          navigate(`/questions/${QUESTION_ID}`);
        })
        .catch((err) => {
          setErrorMessage('');
          alert(err.response.data.message || '다시 시도해주세요');
        });
    }
  };
  return (
    <PageContainer>
      <ContentWrapper className="contentWrapper">
        <NoticeWrapper as="div" margin>
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
            defaultValue={detailData.title}
            id="title"
            onChange={(e) => setTitle(e.target.value)}
          />
          <label required htmlFor="body">
            Body
          </label>
          <MarkdownEditor
            required
            id="body"
            ref={editor}
            value={detailData.questionContent}
          />
          <Btn width="100px" type="submit" bottom="30px">
            Edit
          </Btn>
          <RedBtn type="button" onClick={handleClickCancel}>
            Cancel
          </RedBtn>
          <span>{errorMessage}</span>
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

export const RedBtn = styled.button`
  margin-left: 10px;
  background-color: white;
  color: red;
  box-shadow: 0px 0px 3px 1px red inset;
  width: 100px;
  padding: 10px 0;
  border: none;
  border-radius: 5px;
  font-weight: 600;
  &:hover {
    background-color: rgba(180, 60, 64, 100);
    color: white;
    box-shadow: 0px 0px 1px 2px rgba(230, 0, 0, 0.59) inset;
    cursor: pointer;
  }
`;

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

export const NoticeWrapper = styled.div`
  border: 1px solid hsl(47, 65%, 84%);
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
  background-color: hsl(47, 87%, 94%);
  border-radius: 5px;
  height: fit-content;
  margin-bottom: ${(props) => (props.margin ? '10px' : '0')};
  width: ${(props) => props.width || 'auto'};
  p {
    padding: 0 10px;
    font-size: 13px;
  }
  @media only screen and (max-width: 767px) {
    margin-left: 10px;
  }
`;

export const ListTitle = styled.div`
  padding: 10px 10px 10px 20px;
  background-color: ${(props) =>
    props.black ? 'hsl(210, 8%, 97.5%)' : 'hsl(47, 83%, 91%)'};
  border-bottom: ${(props) =>
    props.black
      ? '1.5px solid hsl(210,8%,90%)'
      : '1.5px solid hsl(47, 65%, 84%)'};
  border-top: ${(props) =>
    props.border ? '1px solid hsl(210,8%,90%)' : 'none'};
  font-weight: ${(props) => (props.weight ? '600' : 'auto')};
  color: ${(props) => (props.black ? 'hsl(210, 8%, 35%)' : 'black')};
`;

export const List = styled.li`
  padding: 5px 0;
  font-size: 13px;
`;

export default EditQuestion;
