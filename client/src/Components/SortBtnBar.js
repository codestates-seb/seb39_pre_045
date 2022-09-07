import styled from 'styled-components';
import useSortStore from '../Store/store-sort';
import defAxios from '../Controller/default';
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

const SortBtnBar = ({ setIsPending, setNoResult }) => {
  const { setSort, sort, setPagination, setData, setPageInfo } = useSortStore(
    (state) => state
  );
  const handleSort = async (sorting) => {
    setSort(sorting);
    setPagination(1);
    setIsPending(true);
    await defAxios
      .get(`/questions?page=1&sort=${sorting}&filters=`)
      .then(({ data }) => {
        setPageInfo(data.pageInfo);
        setData(data.data);
        setIsPending(false);
      })
      .catch((err) => {
        setTimeout(() => {
          setNoResult({ status: 'httpErr', keyword: err.response.status });
          setIsPending(false);
        }, 200);
        alert('정렬에 실패했습니다');
      });
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
