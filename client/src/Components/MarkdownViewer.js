import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import { Viewer } from '@toast-ui/react-editor';
import styled from 'styled-components';

const Div = styled.div`
  margin: 50px 0;
  height: 100px;
  border: 1px solid red;
  overflow-y: scroll;
`;

const MarkdownViewer = () => {
  const words = `
  ## 뷰어 체크중
  잘 **나**오나
  # hi
  dd\`hi\`dd
  ddddd
  please write here

***
백틱 앞에 백슬래시 추가해야함
\`\`\`;
  ㅇㅇㅇㅇ
  \`\`\`
  `;
  return (
    <Div>
      <Viewer initialValue={words} />
    </Div>
  );
};

export default MarkdownViewer;
