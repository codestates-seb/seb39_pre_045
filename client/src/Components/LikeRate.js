// import { useEffect, useState } from 'react';
// import axios from 'axios';
import link from '../image/stackoverflow.png';
import styled from 'styled-components';
// import { useParams } from 'react-router-dom';
import { useState } from 'react';
const RateWrapper = styled.div`
  min-width: 55px;
  max-width: 55px;
  width: 55px;
  height: 100%;
  margin: auto;
  padding: 20px 0;
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
    width: 55px;
    padding: 10px;
    margin: 10px 0;
    font-size: 18px;
    box-sizing: border-box;
    font-weight: 400;
    color: #6a737c;
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
`;
const LikeRate = () => {
  // const LikeRate = ({ status }) => {
  // const { id } = useParams();
  // 아마 이거아닐듯 questionId일듯
  const [like, setLike] = useState({
    like: 0,
    totalVotes: 0,
    dislike: 0,
    total: 0,
  });
  // useEffect(()=>{
  //   axios.get(`/${status}/${id}/`).then(({data})=>setLike({...like,..data}).catch(err=>console.log(err))
  // 이거아닐듯 props로 내려올듯
  // },[])
  const handleLike = (e) => {
    console.log(like);
    if (like.like === 1 || like.dislike === 1) {
      // 로직 수정해야함.!
      return;
    } else {
      if (e.target.name === 'like') {
        // axios
        //   .post(`/${status}/${id}/up`)
        //   .then(({ data }) => setLike({ ...like, like: 1, ...data }))
        //   .catch((err) => alert(err));

        setLike({ ...like, like: 1, total: like.total + 1 });
        console.log(like);
      } else {
        // axios
        //   .post(`/${status}/${id}/down`)
        //   .then(({ data }) => setLike({ ...like, dislike: 1, ...data }))
        //   .catch((err) => alert(err));
        setLike({ ...like, dislike: 1, total: like.total - 1 });
      }
    }
  };
  return (
    <RateWrapper
      checkLike={like.total === 1 ? 'true' : 'false'}
      checkDislike={like.total === -1 ? 'true' : 'false'}
      className="likeNdislike"
    >
      <button className="like" name="like" onClick={handleLike}></button>
      <div className="likeCount">356</div>
      <button className="dislike" name="dislike" onClick={handleLike}></button>
    </RateWrapper>
  );
};
export default LikeRate;
