import { useEffect } from 'react';
import styled from 'styled-components';
import defAxios from '../Controller/default';
import useSortStore from '../Store/store-sort';
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

const Pagination = ({ status, setIsPending, setNoResult }) => {
  const {
    pagination,
    setPagination,
    sort,
    query,
    setData,
    pageInfo,
    setPageInfo,
  } = useSortStore((state) => state);
  const page = [];
  for (let i = 1; i <= pageInfo.totalPages; i++) {
    page.push(i);
  }
  // const { setPagination } = useSortStore((state) => state);
  // const { sort } = useSortStore((state) => state);
  // const { query } = useSortStore((state) => state);
  // const [num, setNum] = useState(1);

  const handlePagination = async (e) => {
    if (status === 'question') {
      defAxios
        .get(
          `/questions?page=${Number(
            e.target.textContent
          )}&sort=${sort}&filters=`
        )
        .then(({ data }) => {
          setPageInfo(data.pageInfo);
          setData(data.data);
          setPageInfo(data.pageInfo);

          setIsPending(false);
          window.scrollTo(0, 0);
        })
        .catch((err) => {
          setData([]);
          setTimeout(() => {
            setNoResult({ status: 'httpErr', keyword: err.response.status });
            setIsPending(false);
          }, 200);
          console.log(err);
        });
    } else {
      defAxios
        .get(
          `/search?q=${query}&page=${Number(
            e.target.textContent
          )}&sort=${sort}&`
        )
        .then(({ data }) => {
          setPageInfo(data.pageInfo);
          setData(data.data);
          setPageInfo(data.pageInfo);

          setIsPending(false);
          window.scrollTo(0, 0);
        })
        .catch((err) => {
          setData([]);
          setTimeout(() => {
            setNoResult({ status: 'httpErr', keyword: err.response.status });
            setIsPending(false);
          }, 200);
          console.log(err);
        });
    }

    setPagination(Number(e.target.textContent));
  };

  useEffect(() => {
    // console.log(pagination);
    // setPagination('votes');
  }, [pagination]);
  return (
    <PaginationDiv>
      {page.map((el, index) => (
        <PaginationBtn
          onClick={handlePagination}
          active={el === pagination ? 'true' : 'false'}
          key={index}
        >
          {el}
        </PaginationBtn>
      ))}
    </PaginationDiv>
  );
};
export default Pagination;
