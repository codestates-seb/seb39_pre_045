import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import { Viewer } from '@toast-ui/react-editor';
import styled from 'styled-components';

const MarkdownViewer = (props) => {
  const words = `<p>글자</p>`;
  return (
    <Div margin={props.margin}>
      <Viewer initialValue={props.value || words} />
    </Div>
  );
};

const Div = styled.div`
  margin: ${(props) => props.margin || '50px 0'};
  min-height: 300px;
  overflow-y: scroll;
`;
export default MarkdownViewer;
