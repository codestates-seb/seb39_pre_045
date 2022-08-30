import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useState } from 'react';
import LikeRate from './LikeRate';

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
  span {
    margin: 0 10px;
    a {
      text-decoration: none;
      color: #0c0d0e;
    }
  }
  .userInfo {
    display: flex;
    flex-direction: column;
    font-size: 12px;
  }
`;

const Question = ({ question }) => {
  const [isOpen, setIsOpen] = useState(false);
  const handleWrite = (e) => {
    e.preventDefault();
  };
  return (
    <QuestionDiv className="wrapper">
      <LikeRate className="rateLike" />
      <div className="test">
        <MarkdownViewer />
        <InfoBarDiv>
          <div>
            <span>
              <a href="/">Edit</a>
            </span>
            <span>
              <a href="/">Delete</a>
            </span>
          </div>
          <div className="userInfo">
            <span>asked {question.createdAt}</span>
            <span>{question.author}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {question.comments.map((el, index) => (
            <Comment comment={el} key={index} />
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
              <button onClick={handleWrite} className="submitComment">
                Edit
              </button>
            </WriteComment>
          )}
        </div>
      </div>
    </QuestionDiv>
  );
};
export default Question;
