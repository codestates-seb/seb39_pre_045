// import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Question from '../Components/Question';
import Answer from '../Components/Answer';
import styled from 'styled-components';
import DetailPageAnswerEditor from '../Components/DetailPageAnswerEditor';
import { useEffect, useState } from 'react';
import Loading from '../Components/Loading';
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
        font-weight: 400;
        font-size: 22px;
        color: #3b4045;
        margin: 10px;
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
      span {
        margin-right: 20px;
      }
    }
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
// const data = {
//   questionId: Math.round(Math.random() * 113412304985),
//   title: 'How to place the ~/.composer/vendor/bin directory in your PATH?',
//   questionContent: `I'm on Ubuntu 14.04 and I've been trying all possible methods to install Laravel to no avail. Error messages everything I try. I'm now trying the first method in the quickstart documentation, that is, via Laravel Installer, but it says to "Make sure to place the ~/.composer/vendor/bin directory in your PATH so the Laravel executable is found when you run the Laravel command in your terminal." so my question is, how do I do that? This may be a simple question but I'm really frustrated and would appreciate any help.`,
//   questionUserName: 'which1ispink',
//   createdAt: '20220425',
//   modifiedAt: '20220623',
//   view: 12341234,
//   checkAdopted: true,
//   totalVotes: 34,

//   questionComments: [
//     {
//       questionCommentId: 1,
//       questionCommentUsername: 'dbswlsdbswls',
//       questionCommentContent: '아마 이거 테스트?',
//     },
//     {
//       questionCommentId: 1,
//       questionCommentUsername: 'dbswlsdbswls',
//       questionCommentContent: '아마 이거 테스트?',
//     },
//     {
//       questionCommentId: 1,
//       questionCommentUsername: 'dbswlsdbswls',
//       questionCommentContent: '아마 이거 테스트?',
//     },
//     {
//       questionCommentId: 1,
//       questionCommentUsername: 'dbswlsdbswls',
//       questionCommentContent:
//         '아마 이거asdasdasdadsadadadadasdadsadadasdgrejtyslkfhfgfhfjtjfhfshfhfhfdhdsdfsdfsdfsdasdsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssfsdfdfsddfhdfhdfh 테스트?',
//     },
//   ],

//   answers: [
//     {
//       answerId: Math.round(Math.random() * 113412304985),
//       answerUserName: 'Michiel',
//       answerContent: `585

//       To put this folder on the PATH environment variable type

//       export PATH="$PATH:$HOME/.composer/vendor/bin"
//       This appends the folder to your existing PATH, however, it is only active for your current terminal session.

//       If you want it to be automatically set, it depends on the shell you are using. For bash, you can append this line to $HOME/.bashrc using your favorite editor or type the following on the shell

//       echo 'export PATH="$PATH:$HOME/.composer/vendor/bin"' >> ~/.bashrc
//       In order to check if it worked, logout and login again or execute

//       source ~/.bashrc
//       on the shell.

//       PS: For other systems where there is no ~/.bashrc, you can also put this into ~/.bash_profile

//       PSS: For more recent laravel you need to put $HOME/.config/composer/vendor/bin on the PATH.

//       PSSS: If you want to put this folder on the path also for other shells or on the GUI, you should append the said export command to ~/.profile (cf. https://help.ubuntu.com/community/EnvironmentVariables).`,
//       createdAt: '20220825',
//       modifiedAt: 20220624,
//       totalvotes: 1234,
//       adopted: false,

//       answerComments: [
//         {
//           answerCommentId: 1,
//           answerCommentUsername: 'dbswlsdbswls',
//           answerCommentContent: '아마 이거 테스트?',
//         },
//         {
//           answerCommentId: 2,
//           answerCommentUsername: 'dbswlsdbswls',
//           answerCommentContent: '아마 이거 테스트?',
//         },
//         {
//           answerCommentId: 3,
//           answerCommentUsername: 'dbswlsdbswls',
//           answerCommentContent: '아마 이거 테스트?',
//         },
//         {
//           answerCommentId: 4,
//           answerCommentUsername: 'dbswlsdbswls',
//           answerCommentContent:
//             '아마 이거asdasdasdadsadadadadasdadsadadasdgrejtyslkfhfgfhfjtjfhfshfhfhfdhdsdfsdfsdfsdasdsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssfsdfdfsddfhdfhdfh 테스트?',
//         },
//       ],
//     },
//   ],
// };

const DetailQuestion = () => {
  const { id } = useParams();
  const [ispending, setIsPending] = useState(true);
  const ids = 2;
  const navigate = useNavigate();
  console.log(id);
  const [data, setData] = useState({});

  useEffect(() => {
    axios
      .get(`/questions/${ids}`)
      .then(({ data }) => {
        console.log(`/questions/${ids}`);
        console.log(data.data);
        setData(data.data);
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
              <h2>{data.title}</h2>
              <button onClick={() => navigate('/write')}>Ask Question</button>
            </div>
            <div className="dateNview">
              <span>
                {new Date(data.createdAt).toLocaleString('en-US', options)}
              </span>
              {data.modifiedAt === undefined || (
                <span>
                  {new Date(data.createdAt).toLocaleString('en-US', options)}
                </span>
              )}
              <span>{data.view}</span>
            </div>
          </div>
          <Question
            data={{
              createdAt: data.createdAt,
              totalVotes: data.totalVotes,
              questionUserName: data.questionUserName,
              questionComments: data.questionComments,
            }}
          />
          <div>2 Answers</div>
          {data.answers.length !== 0 &&
            data.answers.map((el, index) => (
              <Answer data={el} key={el.answerId} idx={index} />
            ))}
          <DetailPageAnswerEditor />
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
