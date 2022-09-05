import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useRef, useState } from 'react';
import { InfoBarDiv } from './Question';
import LikeRate from './LikeRate';
import { useNavigate } from 'react-router-dom';

import reIssue from '../reIssue';

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

const Answer = ({ data, originData, setData, idx }) => {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const id = data.answerId;
  const comment = useRef();

  const handleCommentWrite = (e) => {
    e.preventDefault();
    if (window.confirm('댓글을 등록하시겠습니까?')) {
      const postData = {
        answer: {
          answerId: id,
        },
        memberId: 1,
        answerCommentContent: comment.current.value,
      };
      reIssue
        .post(`/answers/${id}/comments`, postData)
        .then(({ data }) => {
          // setDetailData({...detailData,answers.answerComment})// 보류
          alert('댓글 등록에 성공했습니다');
          setIsOpen(false);
          setData({
            ...originData,
            answers: originData.answers.map((el) => {
              if (el.answerId === id) {
                el.answerComments = [...el.answerComments, data.data];
              }
              return el;
            }),
          });
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
    if (data.adopted) {
      alert('채택된 답변은 삭제할 수 없습니다');
      return;
    }
    if (window.confirm('답변을 삭제하시겠습니까?')) {
      reIssue.delete(`/answers/${id}`).then(({ data }) => {
        setData({
          ...originData,
          answers: originData.answers.filter((el) => el.answerId !== id),
        });
        console.log(data);
        alert('답변이 삭제되었습니다');
        // setDetailData({});보류
      });
    } else {
      return;
    }
  };

  return (
    <AnswerDiv className="wrapper" idx={idx}>
      <LikeRate
        className="rateLike"
        status={'answers'}
        id={id}
        originData={originData}
      />
      <div className="test">
        <MarkdownViewer margin={'20px auto'} value={data.answerContent} />
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
              <Comment
                originData={originData}
                data={el}
                id={id}
                key={index}
                status={'answers'}
                setData={setData}
              />
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
