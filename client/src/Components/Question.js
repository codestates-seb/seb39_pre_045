import styled from 'styled-components';
import MarkdownViewer from '../Components/MarkdownViewer';
import Comment, { WriteComment } from './Comment';
import { useRef, useState } from 'react';
import LikeRate from './LikeRate';
import { useNavigate, useParams } from 'react-router-dom';
import reIssue from '../reIssue';

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

const Question = ({ datas, setData }) => {
  const { id } = useParams();
  const comment = useRef();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
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
      reIssue
        .post(`/questions/${id}/comments`, postData, { headers })
        .then(({ data }) => {
          alert('댓글 등록에 성공했습니다');
          setData({
            ...datas,
            questionComments: [...datas.questionComments, data.data],
          });
          setIsOpen(false);
        });
    } else {
      return;
    }
  };
  const handleDelete = (e) => {
    if (window.confirm('질문을 삭제하시겠습니까?')) {
      e.preventDefault();

      reIssue
        .delete(`/questions/${id}`, { headers })
        .then(() => {
          alert('질문 삭제가 완료되었습니다.');
          navigate(`/`);
        })
        .catch((err) => {
          console.log(err);
          alert('질문 삭제를 실패했습니다.');
        });
    } else {
      return;
    }
  };

  return (
    <QuestionDiv className="wrapper">
      <LikeRate
        className="rateLike"
        status={'questions'}
        originData={datas}
        id={id}
      />
      <div className="test">
        <MarkdownViewer margin={'20px auto'} value={datas.questionContent} />
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
              {new Date(datas.createdAt).toLocaleString('en-US', {
                weekday: 'short',
                year: 'numeric',
                month: 'short',
                day: 'numeric',
              })}
            </span>
            <span>{datas.questionUsername}</span>
          </div>
        </InfoBarDiv>
        <ul className="comment">
          {datas.questionComments.length !== 0 &&
            datas.questionComments.map((el, index) => (
              <Comment
                originData={datas}
                data={el}
                key={index}
                id={id}
                status={'questions'}
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
