import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useRef, useState } from 'react';
import LikeRate from './LikeRate';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const QuestionDiv = styled.div`
  display: flex;
  max-width: 1000px;
  width: 100%;
  height: 100%;
  .test {
    width: 100%;
    margin-left: 30px;
  }
  ul.comment {
    padding: 0;
    max-width: 1000px;
  }
  .addComment {
    border: none;
    background-color: transparent;
    cursor: pointer;
  }
`;
export const InfoBarDiv = styled.div`
  display: flex;
  justify-content: space-between;
  button {
    margin: 0 5px;
    color: #0c0d0e;
    padding: 5px;
    border: none;
    background-color: transparent;
    cursor: pointer;
  }
  .userInfo {
    display: flex;
    flex-direction: column;
    font-size: 12px;
  }
`;

const Question = ({ question }) => {
  const { id } = useParams();
  const comment = useRef();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const handleCommentWrite = () => {
    if (window.confirm('댓글을 등록하시겠습니까?')) {
      axios.post(
        `/questions/{question-id}/comments/questions/{question-id}/comments`
      );
    } else {
      return;
    }
  };
  const handleDelete = () => {
    if (window.confirm('질문을 삭제하시겠습니까?')) {
      axios.delete(`/questions/${id}`).then(({ data }) => {
        console.log(data);
        navigate('/');
      });
    } else {
      return;
    }
  };
  return (
    <QuestionDiv className="wrapper">
      <LikeRate className="rateLike" status={'questions'} />
      <div className="test">
        <MarkdownViewer />
        <InfoBarDiv>
          <div>
            <button onClick={() => navigate(`/questions/${id}`)}>Edit</button>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <div className="userInfo">
            <span>asked {question.createdAt}</span>
            <span>{question.author}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {question.comments.map((el, index) => (
            <Comment comment={el} key={index} status={'questions'} />
          ))}
        </ul>
        <div>
          {isOpen === false ? (
            <button className="addComment" onClick={() => setIsOpen(!isOpen)}>
              add comment...
            </button>
          ) : (
            <WriteComment>
              <span>comment</span>
              <button className="close" onClick={() => setIsOpen(!isOpen)}>
                x
              </button>
              <textarea ref={comment} name="add" className="TextareaComment" />
              <button onClick={handleCommentWrite} className="submitComment">
                submit
              </button>
            </WriteComment>
          )}
        </div>
      </div>
    </QuestionDiv>
  );
};
export default Question;
