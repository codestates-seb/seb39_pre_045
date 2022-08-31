import axios from 'axios';
import { useState } from 'react';
import styled from 'styled-components';
const CommentLi = styled.li`
  display: block;
  width: 100%;
  list-style: none;
  font-size: 12px;
  padding: 10px;
  box-sizing: border-box;
  border-top: 1px solid #babfc49b;
  word-break: break-all;
  :last-of-type {
    border-bottom: 1px solid #babfc49b;
  }
  span {
    margin-right: 20px;
    display: inline-block;
    padding: 3px;
  }
  .author {
    color: #00747c;
    background-color: #daeaf7;
  }
  .editNdelete {
    display: inline-block;
    button {
      border: none;
      background-color: transparent;
      cursor: pointer;
      font-weight: 300;
      font-size: 12px;
    }
  }
`;

export const WriteComment = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  box-sizing: border-box;
  /* align-items: center; */
  .close {
    align-self: flex-end;
    border: none;
    font-weight: 700;
    margin-bottom: 3px;
    background-color: transparent;
    cursor: pointer;
  }
  textarea {
    margin: 5px;
    resize: none;
    padding: 5px;
    border: 1px solid #babfc4;
    outline: none;
  }
  .submitComment {
    align-self: flex-end;
    border: none;
    font-weight: 700;
    padding: 5px;
    color: #39739d;
    border: 1px solid hsl(205, 41%, 63%);
    border-radius: 3px;
    background-color: #e1ecf4;
    font-size: 12px;
    cursor: pointer;
    margin: 3px;
  }
`;
const Comment = ({ status, comment }) => {
  const [isOpen, setIsOpen] = useState(false);
  const handleEdit = () => {
    if (window.confirm('댓글을 수정하시겠습니까?')) {
      axios.patch(
        `/${status}/{question-id}/comments/questions/{question-id}/comments/{comment-id}`
      );
    } else {
      return;
    }
  };
  const handleDelete = () => {
    if (window.confirm('댓글을 수정하시겠습니까?')) {
      axios.delete(
        `/${status}/{question-id}/comments/questions/{question-id}/comments/{comment-id}`
      );
    } else {
      return;
    }
  };

  return (
    <CommentLi>
      {isOpen === false ? (
        <>
          <span>{comment.content}</span>
          <span className="author">{comment.author}</span>
          <span>{comment.createdAt}</span>
          <div className="editNdelete">
            <button onClick={() => setIsOpen(!isOpen)}>Edit</button>
            <button onClick={handleDelete}>Delete</button>
          </div>
        </>
      ) : (
        <>
          <WriteComment>
            <button className="close" onClick={() => setIsOpen(!isOpen)}>
              x
            </button>
            <textarea className="editComment" defaultValue={comment.content} />
            <button onClick={handleEdit} className="submitComment">
              Edit
            </button>
          </WriteComment>
        </>
      )}
    </CommentLi>
  );
};

export default Comment;
