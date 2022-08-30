import styled from 'styled-components';
import { Btn, Input } from '../Pages/Login';
import { Content, Title } from '../Pages/MyPage';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Form = styled.form`
  display: flex;
  flex-direction: column;
  input {
    width: 300px;
  }
  label,
  span {
    font-weight: 600;
  }
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

const MyPageEdit = () => {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [checkPassword, setCheckPassword] = useState('');
  const navigate = useNavigate();

  const onSubmitEvent = (e) => {
    e.preventDefault();
    navigate('/'), { replace: true };
  };
  return (
    <>
      <Title>Edit Profile</Title>
      <Content>
        <Form>
          <label htmlFor="changeName">Display name</label>
          <Input
            required
            value={name}
            onChange={(e) => setName(e.target.value)}
            type="text"
            id="changeName"
          />
          <label htmlFor="new-message">Status Message</label>
          <Input type="text" id="new-message" />
          <label htmlFor="new-password">Password</label>
          <Input
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            autoComplete="off"
            type="password"
            id="new-password"
          />
          <label htmlFor="check-password">Check Password</label>
          <Input
            required
            value={checkPassword}
            onChange={(e) => setCheckPassword(e.target.value)}
            autoComplete="off"
            type="password"
            id="check-password"
          />
          <Btn
            bottom="60px"
            width="150px"
            type="submit"
            onClick={onSubmitEvent}
          >
            Save Profile
          </Btn>
          <span>Delete Profile</span>
          <RedBtn onClick={onSubmitEvent}>Delete Profile</RedBtn>
        </Form>
      </Content>
    </>
  );
};

export default MyPageEdit;
