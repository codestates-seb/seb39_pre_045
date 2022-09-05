import { ReactComponent as Spinner } from '../image/loading.svg';
import styled from 'styled-components';

const Loading = () => {
  return (
    <Div>
      <Title>Loading...</Title>
      <Spinner />
    </Div>
  );
};

const Div = styled.div`
  height: calc(100vh - 350px);
  margin: 100px 250px;
`;

const Title = styled.div`
  text-align: center;
  font-size: 20px;
  color: gray;
`;

export default Loading;
