import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useState } from 'react';
import { InfoBarDiv } from './Question';
import LikeRate from './LikeRate';
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
  const [isOpen, setIsOpen] = useState(false);
  const handleWrite = (e) => {
    e.preventDefault();
  };
  return (
    <AnswerDiv className="wrapper">
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
            <span>asked {answer.createdAt}</span>
            <span>{answer.author}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {answer.comments.map((el, index) => (
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
    </AnswerDiv>
  );
};
export default Answer;
