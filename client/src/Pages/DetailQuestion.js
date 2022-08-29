// import { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import axios from 'axios'
const DetailQuestion = () => {
  // const id = useParams()
  // const [data, setData] = useState([]);
  // useEffect(() => {
  //   axios.get(`/questions/${id}`).then(({data})=>setData(data)).catch(err=>alert('상세조회에 실패하였습니다.');
  // }, []);
  const data = {
    question: {
      id: Math.round(Math.random() * 113412304985),
      title: '테스트중이에용',
      author: 'dbswls',
      createdAt: '20220425',
      view: 12341234,
      content: '이것도 테스트중',
      comments: [
        {
          author: 'dbswlsdbswls',
          content: '아마 이거 테스트?',
          createdAt: '20220825',
        },
        {
          author: 'dbswlsdbswls',
          content: '아마 이거 테스트?',
          createdAt: '20220825',
        },
        {
          author: 'dbswlsdbswls',
          content: '아마 이거 테스트?',
          createdAt: '20220825',
        },
        {
          author: 'dbswlsdbswls',
          content: '아마 이거 테스트?',
          createdAt: '20220825',
        },
        {
          author: 'dbswlsdbswls',
          content: '아마 이거 테스트?',
          createdAt: '20220825',
        },
      ],
    },
    answers: [
      {
        id: Math.round(Math.random() * 113412304985),
        title: '테스트중이에용',
        author: 'dbswls',
        createdAt: '20220425',
        view: 12341234,
        content: '이것도 테스트중',
        comments: [
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
        ],
      },
      {
        id: Math.round(Math.random() * 113412304985),
        author: 'dbswls',
        createdAt: 20220425,
        modifiedAt: 20220624,
        view: 12341234,
        content: '이것도 테스트중',
        comments: [
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
          {
            author: 'dbswlsdbswls',
            content: '아마 이거 테스트?',
            createdAt: '20220825',
          },
        ],
      },
    ],
  };
  return (
    <div
      style={{
        width: '100%',
        marginTop: '50px',
        minHeight: 'calc(100vh - 50px)',
        display: 'flex',
        flexDirection: 'column',
        margin: '50px 0 0 230px',
        padding: '20px',
        backgroundColor: 'white',
      }}
    >
      <h2>{data.question.title}</h2>
      <div>
        <span>{data.question.createdAt}</span>
        <span>{data.question.modifiedAt}</span>
        <span>{data.question.view}</span>
      </div>
      {/* 여기에다가 질문 컴포넌트   */}
      {/* 질문안에 감싼상태의 댓글컴포넌트 맵돌리기 */}
    </div>
  );
};
export default DetailQuestion;
