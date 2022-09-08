import styled from 'styled-components';

const NoResultDiv = styled.div`
  width: 100%;
  height: 500px;
  max-width: 850px;
  display: flex;
  box-sizing: border-box;
  justify-content: center;
  align-items: center;
  h2 {
    font-size: 30px;
    position: relative;
    font-weight: 500;
    /* color: #6a737c; */
    user-select: none;
    z-index: 0;
    ::after {
      content: '😢';
      position: absolute;
      opacity: 0.3;
      top: -100px;
      left: 50%;
      transform: translateX(-50%);
      font-size: 100px;
      z-index: -1;
    }
  }
`;

const NoResult = ({ keyword, status }) => {
  let sentence = '';
  if (status === 'search') {
    sentence = `We couldn't find anything for "${keyword}"`;
  } else if (status === 'data') {
    sentence = `Sorry, Data doesn't exist : ${keyword}`;
  } else if (status === 'httpErr') {
    sentence = `An Unexpected Network Error Occurred : ${keyword}`;
  } else {
    sentence = 'Page not found';
  }
  return (
    <NoResultDiv>
      <h2>{sentence}</h2>
    </NoResultDiv>
  );
};
export default NoResult;
