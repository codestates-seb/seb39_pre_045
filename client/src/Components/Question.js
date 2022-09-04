import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useRef, useState } from 'react';
import LikeRate from './LikeRate';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import useDetaulQuestion from '../Store/store-detailquestion';

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
    align-items: flex-end;
  }
`;

const Question = ({ data }) => {
  const { id } = useParams();
  const comment = useRef();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const { detailData, setDatailData } = useDetaulQuestion((state) => state);
  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('ACCESS_TOKEN')}`,
  };
  const handleCommentWrite = (e) => {
    e.preventDefault();
    if (window.confirm('댓글을 등록하시겠습니까?')) {
      const postData = {
        question: {
          questionId: id,
        },
        memberId: 1,
        questionCommentContent: comment.current.value,
      };
      // console.log(postData);
      axios
        .post(`/questions/${id}/comments`, postData, { headers })
        .then(({ data }) => {
          console.log('등록 성공이닷!', data.data);
          setDatailData({
            ...detailData,
            questionComments: [...setDatailData.questionComments, data.data],
          });
          navigate(`/questions/${id}`);
        });
    } else {
      return;
    }
  };
  const handleDelete = (e) => {
    if (window.confirm('질문을 삭제하시겠습니까?')) {
      e.preventDefault();
      console.log(id);
      // axios.delete(`/questions/${id}`).then(({ data }) => {
      //   console.log(data);
      //   // navigate('/');
      // });
    } else {
      return;
    }
  };

  return (
    <QuestionDiv className="wrapper">
      <LikeRate className="rateLike" status={'questions'} />
      <div className="test">
        <MarkdownViewer
          margin={'20px auto'}
          value={detailData.questionContent}
        />
        <InfoBarDiv>
          <div>
            <button onClick={() => navigate(`/questions/edit/${id}`)}>
              Edit
            </button>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <div className="userInfo">
            <span>
              asked{' '}
              {new Date(data.createdAt).toLocaleString('en-US', {
                weekday: 'short',
                year: 'numeric',
                month: 'short',
                day: 'numeric',
              })}
            </span>
            <span>{data.questionUserName}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {data.questionComments.length !== 0 &&
            data.questionComments.map((el, index) => (
              <Comment data={el} key={index} id={id} status={'questions'} />
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
