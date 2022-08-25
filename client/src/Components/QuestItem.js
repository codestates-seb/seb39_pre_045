import styled from 'styled-components';
const Question = styled.li`
  width: 100%;
  box-sizing: border-box;
  padding: 10px;
  max-width: 750px;
  list-style: none;
  font-size: 12px;
  border-top: 1px solid #babfc4;

  .info {
    display: flex;
    flex-direction: ${(props) =>
      props.align === 'horizontal' ? 'row' : 'column'};
    span {
      padding: 3px;
    }
    .answer {
      border: ${(props) =>
        props.answered === 'yes' ? '1px solid #3d8f58' : 'none'};
      border-radius: 3px;
      color: ${(props) => (props.answered === 'yes' ? '#3d8f58' : 'inherit')};
    }
  }
  .question {
    h3.title {
      margin: 0;
      padding: 0;
      a {
        text-decoration: none;
        color: black;
      }
    }
    .content {
      margin: 5px 0;
      padding: 0;
    }
  }
  .userNdate {
    text-align: right;
    span {
      margin: 5px;
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
    <Question align={'horizontal'} answered={test.answer > 0 ? 'yes' : 'no'}>
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
