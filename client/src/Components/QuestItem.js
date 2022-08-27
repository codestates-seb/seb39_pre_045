/*eslint-disable*/

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
  return (
    <Question answered={el.answer > 0 ? 'yes' : 'no'} chosen={`${el.chosen}`}>
      <div className="info">
        <span>{el.vote} votes</span>
        <span className="answer">{el.answer} answers</span>
        <span>{el.view} views</span>
      </div>
      <div className="question">
        <h3 className="title">
          <a href="/">{el.title}</a>
        </h3>
        <p className="content">{el.content}</p>
        <div className="userNdate">
          <span>{el.author}</span>
          <span>{el.time}</span>
        </div>
      </div>
    </Question>
  );
};
export default QuestItem;
