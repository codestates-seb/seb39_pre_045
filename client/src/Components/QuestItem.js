import { Link } from 'react-router-dom';
import styled from 'styled-components';
const Question = styled.li`
  width: 100%;
  box-sizing: border-box;
  padding: 10px 10px 10px 20px;
  /* 패딩바꿔야함 */
  max-width: 850px;
  list-style: none;
  font-size: 14px;
  border-top: 1px solid #babfc4;
  display: flex;
  word-break: break-all;
  .info {
    flex: 1 1 15%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-end;
    margin-right: 10px;
    color: #0c0d0e;
    span {
      padding: 3px;
      text-align: right;
    }
    .answer {
      border: ${(props) =>
        props.answered === 'yes' ? '1px solid #3d8f58' : 'none'};
      border-radius: 3px;
      color: ${(props) =>
        props.answered === 'yes'
          ? props.chosen === 'true'
            ? 'white'
            : '#3d8f58'
          : 'inherit'};
      background-color: ${(props) =>
        props.chosen === 'true' ? '#3d8f58' : 'inherit'};
    }
  }
  .question {
    flex: 1 1 90%;
    h3.title {
      margin: 0;
      padding: 0;
      font-weight: 400;
      a {
        text-decoration: none;
        color: #0074cc;
        font-size: 16px;
      }
    }
    .content {
      margin: 5px 0;
      padding: 0;
      color: #384045;
    }
  }
  .userNdate {
    text-align: right;
    span {
      margin: 5px;
      font-size: 12px;
      &.username {
        color: #00747c;
        background-color: #daeaf7;
        padding: 3px;
        border-radius: 3px;
      }
    }
  }
  @media screen and (max-width: 768px) {
    display: flex;
    flex-direction: column;
    .info {
      flex-direction: row;
      align-items: flex-start;
    }
  }
`;
const QuestItem = ({ el }) => {
  const options = {
    weekday: 'short',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  };
  return (
    <Question
      answered={el.answers.length > 0 ? 'yes' : 'no'}
      chosen={`${el.checkAdopted}`}
    >
      <div className="info">
        <span>{el.totalVotes} votes</span>
        <span className="answer">{el.answers.length} answers</span>
        <span>
          {el.view > 999 ? `${(el.view / 1000).toFixed(1)}k` : el.view} views
        </span>
      </div>
      <div className="question">
        <h3 className="title">
          <Link to={`/questions/${el.questionId}`}>{el.title}</Link>
        </h3>
        <p className="content">
          {el.questionContent.length > 100
            ? el.questionContent.slice(0, 100)
            : el.questionContent}
        </p>
        <div className="userNdate">
          <span className="username">{el.questionUsername}</span>
          <span>
            asked {new Date(el.createdAt).toLocaleString('en-US', options)}
          </span>
        </div>
      </div>
    </Question>
  );
};
export default QuestItem;
