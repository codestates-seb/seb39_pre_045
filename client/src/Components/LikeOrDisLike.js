// import { useEffect, useState } from 'react';
// import {useParams} from 'react-router-dom'
// import axios from 'axios';
import styled from 'styled-components';
const RateWrapper = styled.div`
  min-width: 50px;
  max-width: 50px;
  width: 50px;
  height: 100%;
  background-color: black;
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
      <div className="likeCount"></div>
      <button className="dislike" onClick={handleLike}></button>
    </RateWrapper>
  );
};
export default LikeOrDislike;
