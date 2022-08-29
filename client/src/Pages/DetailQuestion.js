// import { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import axios from 'axios'
import styled from 'styled-components';
const DetailQuestion = () => {
  // const id = useParams()
  // const [data, setData] = useState([]);
  // useEffect(() => {
  //   axios.get(`/questions/${id}`).then(({data})=>setData(data)).catch(err=>alert('상세조회에 실패하였습니다.');
  // }, []);
  const DivWrapper = styled.div`
    width: 100%;
    margin-top: 50px;
    min-height: calc(100vh - 50px);
    display: flex;
    flex-direction: column;
    margin: 50px 0 0 230px;
    padding: 20px;
    background-color: white;
    box-sizing: border-box;
  `;
  const data = {
    question: {
      id: Math.round(Math.random() * 113412304985),
      title: 'How to place the ~/.composer/vendor/bin directory in your PATH?',
      author: 'which1ispink',
      createdAt: '20220425',
      view: 12341234,
      content: `I'm on Ubuntu 14.04 and I've been trying all possible methods to install Laravel to no avail. Error messages everything I try. I'm now trying the first method in the quickstart documentation, that is, via Laravel Installer, but it says to "Make sure to place the ~/.composer/vendor/bin directory in your PATH so the Laravel executable is found when you run the Laravel command in your terminal." so my question is, how do I do that? This may be a simple question but I'm really frustrated and would appreciate any help.`,
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
        author: 'Michiel',
        content: `585

        To put this folder on the PATH environment variable type
        
        export PATH="$PATH:$HOME/.composer/vendor/bin"
        This appends the folder to your existing PATH, however, it is only active for your current terminal session.
        
        If you want it to be automatically set, it depends on the shell you are using. For bash, you can append this line to $HOME/.bashrc using your favorite editor or type the following on the shell
        
        echo 'export PATH="$PATH:$HOME/.composer/vendor/bin"' >> ~/.bashrc
        In order to check if it worked, logout and login again or execute
        
        source ~/.bashrc
        on the shell.
        
        PS: For other systems where there is no ~/.bashrc, you can also put this into ~/.bash_profile
        
        PSS: For more recent laravel you need to put $HOME/.config/composer/vendor/bin on the PATH.
        
        PSSS: If you want to put this folder on the path also for other shells or on the GUI, you should append the said export command to ~/.profile (cf. https://help.ubuntu.com/community/EnvironmentVariables).`,
        createdAt: '20220825',
        modifiedAt: 20220624,
        view: 12341234,

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
        author: 'Michiel',
        content: `585

        To put this folder on the PATH environment variable type
        
        export PATH="$PATH:$HOME/.composer/vendor/bin"
        This appends the folder to your existing PATH, however, it is only active for your current terminal session.
        
        If you want it to be automatically set, it depends on the shell you are using. For bash, you can append this line to $HOME/.bashrc using your favorite editor or type the following on the shell
        
        echo 'export PATH="$PATH:$HOME/.composer/vendor/bin"' >> ~/.bashrc
        In order to check if it worked, logout and login again or execute
        
        source ~/.bashrc
        on the shell.
        
        PS: For other systems where there is no ~/.bashrc, you can also put this into ~/.bash_profile
        
        PSS: For more recent laravel you need to put $HOME/.config/composer/vendor/bin on the PATH.
        
        PSSS: If you want to put this folder on the path also for other shells or on the GUI, you should append the said export command to ~/.profile (cf. https://help.ubuntu.com/community/EnvironmentVariables).`,
        createdAt: '20220825',
        modifiedAt: 20220624,
        view: 12341234,

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
    <DivWrapper>
      <h2>{data.question.title}</h2>
      <div>
        <span>{data.question.createdAt}</span>
        <span>{data.question.modifiedAt}</span>
        <span>{data.question.view}</span>
      </div>
      <div className="wrapper"></div>
      <div></div>
      {/* 여기에다가 질문 컴포넌트   */}
      {/* 질문안에 감싼상태의 댓글컴포넌트 맵돌리기 */}
    </DivWrapper>
  );
};
export default DetailQuestion;
