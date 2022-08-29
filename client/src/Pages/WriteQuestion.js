import styled from 'styled-components';
import { QuestionForm, WrapperDiv } from './EditQuestion';
const WriteWrapper = styled(WrapperDiv)`
  span.desc {
    font-size: 12px;
    margin-bottom: 2px;
  }
`;
const WriteQuestion = () => {
  return (
    <WriteWrapper>
      <h2>Ask a public question</h2>
      <QuestionForm>
        <label htmlFor="askTitle">Title</label>
        <span className="desc">
          Be specific and imagine you’re asking a question to another person
        </span>
        <input type="text" id="askTitle" />
        <label htmlFor="askContent">Body</label>
        <span className="desc">
          Include all the information someone would need to answer your question
        </span>
        <textarea className="askContent"></textarea>
      </QuestionForm>{' '}
      <div className="btns">
        <button className="submitBtn">Review your Question</button>
      </div>
    </WriteWrapper>
  );
};

export default WriteQuestion;
