import { useState } from 'react';
import styled from 'styled-components';

const PaginationDiv = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  margin: 10px auto 30px;
`;
const PaginationBtn = styled.button`
  font-size: 16px;
  border: 1px solid #d6d9dc;
  background-color: ${(props) =>
    props.active === 'true' ? '#f48225' : 'transparent'};
  color: ${(props) => (props.active === 'true' ? 'white' : 'initial')};
  padding: 5px;
  margin: 5px;
  :hover {
    background-color: ${(props) => props.active === 'false' && '#babfc4'};
  }
  transition: background-color ease-in-out 0.3s, color ease-in-out 0.3s;
`;

const Pagination = () => {
  const initialNum = 5;
  // prop로 총길이
  const [num, setNum] = useState(1);
  const page = [];
  const handlePagination = (e) => {
    setNum(Number(e.target.textContent));
  };
  for (let i = 1; i <= initialNum; i++) {
    page.push(i);
  }

  return (
    <PaginationDiv>
      {page.map((el, index) => (
        <PaginationBtn
          onClick={handlePagination}
          active={el === num ? 'true' : 'false'}
          key={index}
        >
          {el}
        </PaginationBtn>
      ))}
    </PaginationDiv>
  );
};
export default Pagination;
