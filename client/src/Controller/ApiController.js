import axios from 'axios';

const TOKEN = localStorage.getItem('ACCESS_TOKEN');
const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_PROXY_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + TOKEN,
  },
});

axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const {
      config,
      response: { status },
    } = error;
    const originalRequest = config;
    if (status === 403) {
      const accessToken = localStorage.getItem('ACCESS_TOKEN');
      const refreshToken = localStorage.getItem('REFRESH_TOKEN');
      try {
        const { data } = await axios({
          method: 'post',
          url: '/members/reissue',
          data: { accessToken, refreshToken },
        });
        const newAccessToken = data.data.accessToken;
        const newRefreshToken = data.data.refreshToken;
        originalRequest.headers = {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + newAccessToken,
        };
        localStorage.setItem('ACCESS_TOKEN', newAccessToken);
        localStorage.setItem('REFRESH_TOKEN', newRefreshToken);
        return await axios(originalRequest);
      } catch (err) {
        new Error(err);
      }
    } else {
      return Promise.reject(error);
    }
  }
);

export default axiosInstance;
