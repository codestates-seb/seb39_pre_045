import styled from 'styled-components';
import { Btn, Input } from '../Pages/Login';
import { Content, Title } from '../Pages/MyPage';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import axiosInstance from '../Controller/ApiController';

const MyPageEdit = ({ parsed, setRender }) => {
  const [username, setUsername] = useState(`${parsed.username}`);
  const [password, setPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const navigate = useNavigate();

  const handleSaveProfile = (e) => {
    e.preventDefault();
    const users = { username, prePassword: password };
    if (newPassword !== '') {
      users[newPassword] = newPassword;
    }
    axiosInstance
      .patch(`/members/${parsed.memberId}`, users)
      .then((res) => {
        localStorage.setItem(
          'USER_INFO',
          JSON.stringify({
            ...parsed,
            username: res.data.data.username,
          })
        );
        alert('변경되었습니다!');
        setRender(JSON.parse(localStorage.getItem('USER_INFO')));
        setPassword('');
        setNewPassword('');
      })
      .catch((err) => {
        alert(
          err.response?.data.message
            ? err.response.data.message
            : '다시 시도해주세요. 비밀번호는 필수 입력값입니다.'
        );
        setRender(JSON.parse(localStorage.getItem('USER_INFO')));
      });
  };

  const handleDeleteProfile = (e) => {
    e.preventDefault();
    if (window.confirm('확인을 누르면 회원 정보가 삭제됩니다.')) {
      axios
        .delete(
          `${process.env.REACT_APP_PROXY_URL}/members/${parsed.memberId}`,
          {
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('ACCESS_TOKEN'),
            },
          }
        )
        .then(() => {
          localStorage.clear();
          alert('그동안 이용해주셔서 감사합니다.');
          navigate('/');
        })
        .catch((err) => alert(err.response.data.message));
    } else {
      return;
    }
  };

  return (
    <>
      <Title>Edit Profile</Title>
      <Content>
        <Form>
          <label htmlFor="changeName">Display name</label>
          <Input
            required
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            type="text"
            id="changeName"
          />
          <label htmlFor="pre-password">Password</label>
          <Input
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            autoComplete="off"
            type="password"
            id="pre-password"
          />
          <label htmlFor="new-password">New Password</label>
          <Input
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            autoComplete="off"
            type="password"
            id="new-password"
          />
          <Btn
            bottom="60px"
            width="150px"
            type="submit"
            onClick={handleSaveProfile}
          >
            Save Profile
          </Btn>
        </Form>
        <Span>Delete Profile</Span>
        <RedBtn onClick={handleDeleteProfile}>Delete Profile</RedBtn>
      </Content>
    </>
  );
};

const Form = styled.form`
  display: flex;
  flex-direction: column;
  input {
    width: 300px;
  }
  label {
    font-weight: 600;
  }
`;

const Span = styled.span`
  font-weight: 600;
`;

const RedBtn = styled(Btn)`
  margin-top: 10px;
  background-color: white;
  color: red;
  box-shadow: 0px 0px 3px 1px red inset;
  width: 150px;
  &:hover {
    background-color: rgba(180, 60, 64, 100);
    color: white;
    box-shadow: 0px 0px 1px 2px rgba(230, 0, 0, 0.59) inset;
  }
`;

export default MyPageEdit;
