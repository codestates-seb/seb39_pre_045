import axios from 'axios';
import { useState, useRef } from 'react';

import styled from 'styled-components';
import link from '../image/stackoverflow.png';
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
    margin-right: 10px;
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
      display: inline-block;
      border: none;
      background-color: transparent;
      cursor: pointer;
      &.editBtn {
        background: url(${link}) no-repeat -21px -341px;
        width: 12px;
        height: 12px;
        margin: 3px 5px;
      }
      &.deleteBtn {
        background: url(${link}) no-repeat -40px -319px;
        width: 15px;
        height: 15px;
      }
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
const Comment = ({ status, data, id }) => {
  const [isOpen, setIsOpen] = useState(false);
  const content = useRef();
  const handleEdit = async (e) => {
    e.preventDefault();
    if (window.confirm('댓글을 수정하시겠습니까?')) {
      if (content.current.value === '') {
        return;
      }
      const patchData =
        status === 'questions'
          ? {
              memberId: 1,
              questionCommentId: data.questionCommentId,
              question: {
                questionId: id,
              },
              questionCommentContent: content.current.value,
            }
          : {
              memberId: 1,
              answerCommentId: data.answerCommentId,
              answer: {
                answerId: id,
              },
              answerCommentContent: content.current.value,
            };

      await axios
        .patch(
          `/${status}/${id}/comments/${
            status === 'questions'
              ? data.questionCommentId
              : data.answerCommentId
          }`,
          patchData
        )
        .then(({ data }) => console.log(data.data))
        .catch((err) => {
          alert('댓글수정에 실패하였습니다');
          console.log(err);
        });
    } else {
      return;
    }
  };
  const handleDelete = () => {
    if (window.confirm('댓글을 삭제하시겠습니까?')) {
      axios
        .delete(
          `/${status}/${id}/comments/${
            status === 'questions'
              ? data.questionCommentId
              : data.answerCommentId
          }`
        )
        .then(({ data }) => {
          alert('댓글 삭제');
          console.log(data.data);
        })
        .catch((err) => {
          alert('댓글 삭제 실패하였습니다');
          console.log(err);
        });
    } else {
      return;
    }
  };
  return (
    <CommentLi>
      {isOpen === false ? (
        <>
          {status === 'questions' ? (
            <span>{data.questionCommentContent}</span>
          ) : (
            <span>{data.answerCommentContent}</span>
          )}
          {status === 'questions' ? (
            <span className="author">{data.questionCommentUsername}</span>
          ) : (
            <span className="author">{data.answerCommentUsername}</span>
          )}

          <div className="editNdelete">
            <button
              className="editBtn"
              onClick={() => setIsOpen(!isOpen)}
            ></button>
            <button className="deleteBtn" onClick={handleDelete}></button>
          </div>
        </>
      ) : (
        <>
          <WriteComment>
            <button className="close" onClick={() => setIsOpen(!isOpen)}>
              x
            </button>
            <textarea
              ref={content}
              className="editComment"
              defaultValue={
                status === 'questions'
                  ? data.questionCommentContent
                  : data.answerCommentContent
              }
            />
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
