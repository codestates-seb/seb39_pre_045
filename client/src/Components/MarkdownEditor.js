import '@toast-ui/editor/dist/toastui-editor.css';
import { forwardRef } from 'react';
import { Editor } from '@toast-ui/react-editor';
import styled from 'styled-components';
// import '@toast-ui/editor/dist/i18n/ko-kr';

const Div = styled.div`
  overflow-x: scroll;
  margin-bottom: 10px;
`;

const MarkdownEditor = (props, ref) => {
  return (
    <Div>
      <Editor
        ref={ref}
        initialValue="please write here"
        height="500px"
        toolbarItems={[
          ['heading', 'bold', 'italic', 'strike'],
          ['hr', 'quote'],
          ['ul', 'ol', 'indent', 'outdent'],
          ['code', 'codeblock'],
        ]}
      ></Editor>
    </Div>
  );
};

export default forwardRef(MarkdownEditor);
