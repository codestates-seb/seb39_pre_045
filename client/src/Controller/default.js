import axios from 'axios';

const defAxios = axios.create({
  baseURL: process.env.REACT_APP_PROXY_URL,
});

export default defAxios;
