import { useState } from 'react';
import styled from 'styled-components';

const AlignBtns = styled.div`
  border: 1px solid #babfc49b;
  border-radius: 3px;
`;
const SortBtns = styled.button`
  font-size: 12px;

  border: none;
  color: #0c0d0e;
  background-color: ${(props) => (props.sort ? '#babfc49b' : 'transparent')};
  cursor: pointer;
  padding: 8px;
  border-right: 1px solid #babfc49b;
  transition: background-color ease-in-out 0.5s;
  :last-child {
    border-right: none;
  }
`;

const SortBtnBar = () => {
  const [sortClick, setSortClick] = useState('newest');
  const handleSort = (sort) => {
    // axios.get(`/question?sort=${sort}`).then(({data})=>{
    //   setData(data)
    //   setSortClick(sort)
    // }).catch(err=>alert('정렬에 실패했습니다'))
    setSortClick(sort);
  };

  return (
    <AlignBtns>
      <SortBtns
        sort={sortClick === 'newest' && 'active'}
        onClick={() => handleSort('newest')}
      >
        newest
      </SortBtns>
      <SortBtns
        sort={sortClick === 'votes' && 'active'}
        onClick={() => handleSort('votes')}
      >
        votes
      </SortBtns>
      <SortBtns
        sort={sortClick === 'answers' && 'active'}
        onClick={() => handleSort('answers')}
      >
        answers
      </SortBtns>
    </AlignBtns>
  );
};
export default SortBtnBar;
