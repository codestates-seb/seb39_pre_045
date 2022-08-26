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
    flex: 1 1 10%;
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
      color: ${(props) => (props.answered === 'yes' ? '#3d8f58' : 'inherit')};
    }
  }
  .question {
    flex: 1 1 90%;
    h3.title {
      margin: 0;
      padding: 0;
      a {
        text-decoration: none;
        color: #00747c;
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
  @media screen and (max-width: 820px) {
    display: flex;
    flex-direction: column;
    padding: 10px 10px 10px 5px;
    .info {
      flex-direction: row;
      align-items: flex-start;
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
const QuestItem = () => {
  const test = {
    vote: 2,
    answer: Math.round(Math.random() * 1),
    view: 13,
    title:
      'Selecting Multiple Values from a Dropdown List Across Multiple Columns',
    content:
      'I am trying to select multiple options from a dropdown list across multiple columns. I have taken the widely used script for one coloumn and converted it but it is having some negative effects across ...',
    author: 'Clare Vallely',
    time: 2022,
  };

  return (
    <Question answered={test.answer > 0 ? 'yes' : 'no'}>
      <div className="info">
        <span>{test.vote} votes</span>
        <span className="answer">{test.answer} answers</span>
        <span>{test.view} views</span>
      </div>
      <div className="question">
        <h3 className="title">
          <a href="/">{test.title}</a>
        </h3>
        <p className="content">{test.content}</p>
        <div className="userNdate">
          <span>{test.author}</span>
          <span>{test.time}</span>
        </div>
      </div>
    </Question>
  );
};
export default QuestItem;
