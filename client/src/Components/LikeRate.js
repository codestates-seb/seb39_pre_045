// import { useEffect, useState } from 'react';
import reIssue from '../reIssue';
import link from '../image/stackoverflow.png';
import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useState } from 'react';
const RateWrapper = styled.div`
  min-width: 55px;
  max-width: 55px;
  width: 55px;
  height: 100%;
  margin: auto;
  padding: 20px 0;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;

  .like {
    background: url(${link}) no-repeat
      ${(props) =>
        props.checkLike === 'true' ? '-45px -176px' : '-5px -177px'};
    width: 35px;
    height: 15px;
    padding: 10px;
    border: none;

    cursor: pointer;
  }
  .likeCount {
    padding: 10px;
    margin: 10px 0 10px -5px;
    font-size: 18px;
    box-sizing: border-box;
    font-weight: 400;
    color: #6a737c;
    /* text-align: center; */
    box-sizing: border-box;
  }
  .dislike {
    background: url(${link}) no-repeat
      ${(props) =>
        props.checkDislike === 'true' ? '-45px -224px' : '-5px -224px'};
    width: 35px;
    height: 15px;
    padding: 10px;
    border: none;
    cursor: pointer;
  }
  .isadopted {
    background: url(${link}) no-repeat
      ${(props) =>
        props.isadopted === 'true' ? '-48px -273px' : '-8px -273px'};

    width: 30px;
    height: 30px;
    border: none;
    margin-top: 15px;
    cursor: pointer;
  }
`;
const LikeRate = ({ status, originData, id }) => {
  const { id: ids } = useParams();

  const username = window.localStorage.getItem('USER_INFO')
    ? JSON.parse(window.localStorage.getItem('USER_INFO')).username
    : 'x';

  const [like, setLike] = useState({
    like: 0,
    dislike: 0,
    totalVotes:
      status === 'questions'
        ? originData.totalVotes
        : originData.answers.filter((el) => el.answerId === id)[0].totalVotes,
  });

  const [adopted, setAdopted] = useState(
    status === 'answers'
      ? `${originData.answers.filter((el) => el.answerId === id)[0].adopted}`
      : false
  );
  const handleLike = (e) => {
    if (like.like === 1 || like.dislike === 1) {
      return;
    } else {
      if (e.target.name === 'like') {
        reIssue
          .post(`/${status}/${id}/up`, '')
          .then(({ data }) => {
            setLike({ ...like, like: 1, totalVotes: data.data.total });
          })

          .catch((err) => {
            alert(err.response.data.message);
          });
      } else {
        reIssue
          .post(`/${status}/${id}/down`, '')
          .then(({ data }) =>
            setLike({ ...like, dislike: 1, totalVotes: data.data.total })
          )
          .catch((err) => alert(err.response.data.message));
      }
    }
  };
  const handleAdopted = () => {
    if (adopted === true) {
      return;
    }
    reIssue
      .post(`/answers/${id}/adopt/${ids}`, '')
      .then(() => {
        setAdopted(true);
      })
      .catch((err) => console.log(err));
  };
  return (
    <RateWrapper
      checkLike={like.like === 1 ? 'true' : 'false'}
      checkDislike={like.dislike === -1 ? 'true' : 'false'}
      isadopted={`${adopted}`}
      className="likeNdislike"
    >
      <button className="like" name="like" onClick={handleLike}></button>
      <div className="likeCount">
        {like.totalVotes > 999
          ? `${(like.totalVotes / 1000).toFixed(1)}k`
          : like.totalVotes}
      </div>
      <button className="dislike" name="dislike" onClick={handleLike}></button>

      {adopted === 'true' ? (
        <>
          <button className="isadopted"></button>
        </>
      ) : username === originData.questionUsername ? (
        status === 'answers' && (
          <button onClick={handleAdopted} className="isadopted"></button>
        )
      ) : (
        <></>
      )}
    </RateWrapper>
  );
};
export default LikeRate;
