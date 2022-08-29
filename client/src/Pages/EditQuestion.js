import styled from 'styled-components';

export const WrapperDiv = styled.div`
  width: 100%;
  margin-top: 50px;
  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  margin: 50px 0 0 230px;
  padding: 20px;
  background-color: #f1f2f3;
  h2 {
    font-weight: 600;
    font-size: 28px;
  }
  button {
    display: inline-block;
    padding: 10px;
    margin-top: 15px;
    border: none;
    background-color: #0a95ff;
    color: white;
    border-radius: 5px;
    cursor: pointer;
    box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
    :hover {
      background-color: #0074cc;
    }
    &.cancel {
      margin-left: 10px;
      color: #0074cc;
      background-color: transparent;
      :hover {
        background-color: #def0ff;
      }
    }
  }
  @media screen and (max-width: 768px) {
    margin: 50px auto;
  }
`;

export const QuestionForm = styled.form`
  display: flex;
  flex-direction: column;
  background-color: white;
  padding: 20px;
  max-width: 850px;
  border-radius: 3px;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  textarea {
    padding: 8px;
    border: 1px solid #babfc4;
    border-radius: 5px;
    outline: none;
    position: relative;
    height: 250px;
    margin-top: 5px;

    :focus {
      box-shadow: 0px 0px 5px #0a95ff;
    }
  }
  label {
    font-weight: 600;
    color: #0c0d0e;
    font-size: 16px;
    margin-bottom: 3px;
  }
  input {
    padding: 8px;
    border: 1px solid #babfc4;
    border-radius: 5px;
    outline: none;
    position: relative;
    margin-top: 5px;
    margin-bottom: 15px;

    :focus {
      box-shadow: 0px 0px 5px #0a95ff;
    }
  }
`;

const EditQuestion = () => {
  return (
    <WrapperDiv>
      <h2>Edit a question</h2>
      <QuestionForm>
        <label htmlFor="askTitle">Title</label>
        <input type="text" id="askTitle" />
        <label htmlFor="askContent">Body</label>
        <textarea className="askContent"></textarea>
      </QuestionForm>
      <div className="btns">
        <button>saveEdit</button>
        <button className="cancel">cancel</button>
      </div>
    </WrapperDiv>
  );
};

export default EditQuestion;
