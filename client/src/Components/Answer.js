import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useState } from 'react';
import { InfoBarDiv } from './Question';
import LikeRate from './LikeRate';
import { useNavigate } from 'react-router-dom';
import { axios } from 'axios';

const AnswerDiv = styled.div`
  display: flex;
  max-width: 1000px;
  width: 100%;
  height: 100%;

  .rateLike {
    height: 100%;
  }
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

const Answer = ({ answer }) => {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const id = 1234;
  const handleDelete = () => {
    if (window.confirm('답변을 삭제하시겠습니까?')) {
      // axios.delete(`/answers/${id}`).then(({ data }) => {
      //   console.log(data);
      //   navigate('/');
      // });
    } else {
      return;
    }
  };
  const handleCommentWrite = () => {
    if (window.confirm('댓글을 등록하시겠습니까?')) {
      axios.post(`/answers/{answer-id}/comments`);
    } else {
      return;
    }
  };
  return (
    <AnswerDiv className="wrapper">
      <LikeRate className="rateLike" status={'answers'} />
      <div className="test">
        <MarkdownViewer />
        <InfoBarDiv>
          <div>
            <button onClick={() => navigate(`/answers/${id}`)}>Edit</button>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <div className="userInfo">
            <span>asked {answer.createdAt}</span>
            <span>{answer.author}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {answer.comments.map((el, index) => (
            <Comment comment={el} key={index} status={'answers'} />
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
              <textarea id="editComment" />
              <button onClick={handleCommentWrite} className="submitComment">
                Edit
              </button>
            </WriteComment>
          )}
        </div>
      </div>
    </AnswerDiv>
  );
};
export default Answer;
