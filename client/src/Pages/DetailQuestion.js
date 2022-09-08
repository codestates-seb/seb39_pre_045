// import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Question from '../Components/Question';
import Answer from '../Components/Answer';
import styled from 'styled-components';
import DetailPageAnswerEditor from '../Components/DetailPageAnswerEditor';
import { useEffect, useState } from 'react';
import Loading from '../Components/Loading';
import useDetailQuestion from '../Store/store-detailquestion';
import defAxios from '../Controller/default';
const DivWrapper = styled.div`
  width: 100%;

  min-height: calc(100vh - 50px);
  display: flex;
  flex-direction: column;
  max-width: 850px;
  margin: 50px 0 0 230px;
  padding: 70px 20px;
  background-color: white;
  box-sizing: border-box;
  color: #232629;
  .questionDesc {
    border-bottom: 1px solid #babfc49b;
    .titleNbtn {
      max-width: 1000px;
      display: flex;
      justify-content: space-between;
      align-items: center;

      h2 {
        width: 80%;
        font-weight: 500;
        font-size: 22px;
        color: #3b4045;
        margin: 10px;
        word-break: break-all;
      }
      button {
        border: none;
        background-color: #0a95ff;
        color: white;
        border-radius: 5px;
        padding: 10px;
        cursor: pointer;
        user-select: none;
        box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4);
        :hover {
          background-color: #0074cc;
        }
      }
    }

    .dateNview {
      margin: 10px;
      font-size: 14px;
      span {
        margin-right: 20px;
      }
    }
  }

  .totalAnswers {
    padding: 30px 0;
    font-size: 18px;
    font-weight: 400;
  }
  @media screen and (max-width: 768px) {
    margin: 50px auto;
    .titleNbtn {
      flex-direction: column-reverse;
      button {
        align-self: flex-end;
      }
    }
  }
`;

const DetailQuestion = () => {
  const { id } = useParams();
  const [ispending, setIsPending] = useState(true);
  const navigate = useNavigate();
  const { setDetailData } = useDetailQuestion((state) => state);
  const [data, setData] = useState({});

  useEffect(() => {
    defAxios
      .get(`/questions/${id}`)
      .then(({ data }) => {
        setData(data.data);
        setDetailData(data.data);
        setIsPending(false);
        window.scrollTo(0, 0);
      })
      .catch((err) => alert(err, '상세조회에 실패하였습니다.'));
  }, []);
  const options = {
    weekday: 'short',
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  };
  return (
    <DivWrapper>
      {ispending === false ? (
        <>
          <div className="questionDesc">
            <div className="titleNbtn">
              <h2 className="title">{data.title}</h2>
              <button onClick={() => navigate('/write')}>Ask Question</button>
            </div>
            <div className="dateNview">
              <span>
                Asked{' '}
                {new Date(data.createdAt).toLocaleString('en-US', options)}
              </span>
              {data.modifiedAt === undefined || (
                <span>
                  {new Date(data.modifiedAt).toLocaleString('en-US', options)}
                </span>
              )}
              <span>
                {data.view > 999
                  ? `${(data.view / 1000).toFixed(1)}k`
                  : data.view}{' '}
                views
              </span>
            </div>
          </div>
          <Question datas={data} setData={setData} />
          <div className="totalAnswers">{data.answers.length} Answers</div>
          {data.answers.length !== 0 &&
            data.answers.map((el, index) => (
              <Answer
                originData={data}
                data={el}
                key={el.answerId}
                idx={index}
                setData={setData}
              />
            ))}
          <DetailPageAnswerEditor originData={data} setData={setData} />
        </>
      ) : (
        <>
          <Loading />
        </>
      )}
    </DivWrapper>
  );
};
export default DetailQuestion;
