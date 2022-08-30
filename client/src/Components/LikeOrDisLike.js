// import { useEffect, useState } from 'react';
// import {useParams} from 'react-router-dom'
// import axios from 'axios';
import link from '../image/stackoverflow.png';
import styled from 'styled-components';
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
    background: url(${link}) no-repeat -5px -178px;
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
    background: url(${link}) no-repeat -5px -224px;
    width: 35px;
    height: 15px;
    padding: 10px;
    border: none;
    cursor: pointer;
  }
`;
const LikeOrDislike = () => {
  // const id = useParams()
  // const [like, setLike]=useState({like:0,count:0,dislike:0})
  // useEffect(()=>{
  //   axios.get(`/question/${id}/`).then(({data})=>setLike(data).catch(err=>console.log(err))
  // },[])
  const handleLike = () => {
    console.log('test');
  };
  return (
    <RateWrapper className="likeNdislike">
      <button className="like" onClick={handleLike}></button>
      <div className="likeCount">356</div>
      <button className="dislike" onClick={handleLike}></button>
    </RateWrapper>
  );
};
export default LikeOrDislike;
