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
  return (
    <NoResultDiv>
      {status === 'search' ? (
        <h2>{`We couldn't find anything for "${keyword}"`}</h2>
      ) : (
        <h2>{`Sorry, Data doesn't exist : ${keyword}`}</h2>
      )}
    </NoResultDiv>
  );
};
export default NoResult;
