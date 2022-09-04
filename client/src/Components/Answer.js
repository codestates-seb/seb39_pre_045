import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useRef, useState } from 'react';
import { InfoBarDiv } from './Question';
import LikeRate from './LikeRate';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const AnswerDiv = styled.div`
  display: flex;
  max-width: 1000px;
  width: 100%;
  height: 100%;
  margin-bottom: 50px;

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
  border-top: ${(props) => props.idx !== 0 && '1px solid #babfc49b'};
`;

const Answer = ({ data, idx }) => {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const id = data.answerId;
  const comment = useRef();
  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('ACCESS_TOKEN')}`,
  };
  const handleCommentWrite = (e) => {
    e.preventDefault();
    console.log('err');
    if (window.confirm('댓글을 등록하시겠습니까?')) {
      const postData = {
        answer: {
          answerId: id,
        },
        memberId: 1,
        answerCommentContent: comment.current.value,
      };
      console.log(postData);
      axios
        .post(`/answers/${id}/comments`, postData, { headers })
        .then(({ data }) => {
          alert('등록 성공이닷!', data.data);
        })
        .catch((err) => {
          alert('등록에 실패했습니다');
          console.log(err);
        });
    } else {
      return;
    }
  };

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

  return (
    <AnswerDiv className="wrapper" idx={idx}>
      <LikeRate className="rateLike" status={'answers'} />
      <div className="test">
        <MarkdownViewer margin={'20px auto'} />
        <InfoBarDiv>
          <div>
            <button onClick={() => navigate(`/answers/edit/${id}`)}>
              Edit
            </button>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <div className="userInfo">
            <span>
              answered{' '}
              {new Date(data.createdAt).toLocaleString('en-US', {
                weekday: 'short',
                year: 'numeric',
                month: 'short',
                day: 'numeric',
              })}
            </span>
            <span>{data.answerUsername}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {data.answerComments.length !== 0 &&
            data.answerComments.map((el, index) => (
              <Comment data={el} id={id} key={index} status={'answers'} />
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
              <textarea ref={comment} className="TextareaComment" />
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
