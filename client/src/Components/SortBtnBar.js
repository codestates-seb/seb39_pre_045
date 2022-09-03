import styled from 'styled-components';
import useSortStore from '../Store/store-sort';
import axios from 'axios';
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

const SortBtnBar = ({ setData }) => {
  const { setSort, sort } = useSortStore((state) => state);
  const handleSort = (sorting) => {
    axios
      .get(`/questions?page=1&sort=${sort}&filters=`)
      .then(({ data }) => {
        setSort(sorting);
        setData(data.data);
        console.log(data.data);
      })
      .catch((err) => alert(err, '정렬에 실패했습니다'));

    // .catch((err) => {
    //   setData([]);

    //   setTimeout(() => {
    //     setNoResult({ status: 'httpErr', keyword: err.response.status });
    //     setIsPending(false);
    //   }, 200);
    //   console.log(err);
    // });
  };

  return (
    <AlignBtns>
      <SortBtns
        sort={sort === 'newest' && 'active'}
        onClick={() => handleSort('newest')}
      >
        newest
      </SortBtns>
      <SortBtns
        sort={sort === 'votes' && 'active'}
        onClick={() => handleSort('votes')}
      >
        votes
      </SortBtns>
      <SortBtns
        sort={sort === 'answers' && 'active'}
        onClick={() => handleSort('answers')}
      >
        answers
      </SortBtns>
    </AlignBtns>
  );
};
export default SortBtnBar;
