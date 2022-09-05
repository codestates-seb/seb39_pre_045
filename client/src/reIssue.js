import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const reIssue = axios.create();
reIssue.interceptors.request.use(function (config) {
  const token = localStorage.getItem('ACCESS_TOKEN');
  if (!token) {
    config.headers['Authorization'] = null;
    return config;
  }
  if (token) {
    config.headers['Authorization'] = `Bearer ${localStorage.getItem(
      'ACCESS_TOKEN'
    )}`;
    return config;
  }
});

//AccessToken이 만료됐을때 처리
reIssue.interceptors.response.use(
  function (response) {
    return response;
  },
  async function (err) {
    const originalConfig = err.config;

    if (err.response.status === 403) {
      // const accessToken = originalConfig.headers['Authorization'];
      const access = localStorage.getItem('ACCESS_TOKEN');
      const refresh = localStorage.getItem('REFRESH_TOKEN');
      const ddaa = {
        accessToken: access,
        refreshToken: refresh,
      };
      const headers = {
        'Content-Type': 'application/json',
      };
      try {
        console.log('ddd');
        const data = await axios
          .post('/members/reissue', ddaa)
          .then(({ data }) => {
            localStorage.setItem('ACCESS_TOKEN', data.data.accessToken);
            localStorage.setItem('REFRESH_TOKEN', data.data.refreshToken);
          });
        return await reIssue.request(originalConfig);
      } catch (err) {
        console.log('토큰 갱신 에러');
      }
      return Promise.reject(err);
    }
    return Promise.reject(err);
  }
);

export default reIssue;
