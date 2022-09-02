import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';
import 'prismjs/themes/prism.css';
import Prism from 'prismjs';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight-all.js';
import { Editor } from '@toast-ui/react-editor';
import { forwardRef } from 'react';
import styled from 'styled-components';
// import '@toast-ui/editor/dist/i18n/ko-kr';

const Div = styled.div`
  overflow-x: scroll;
  margin: 10px 0px;
`;

const MarkdownEditor = (props, ref) => {
  return (
    <Div>
      <Editor
        ref={ref}
        initialValue={null || 'please write here'}
        height="500px"
        toolbarItems={[
          ['heading', 'bold', 'italic', 'strike'],
          ['hr', 'quote'],
          ['ul', 'ol', 'indent', 'outdent'],
          ['code', 'codeblock'],
        ]}
        initialEditType="markdown"
        plugins={[[codeSyntaxHighlight, { highlighter: Prism }]]}
      ></Editor>
    </Div>
  );
};

export default forwardRef(MarkdownEditor);
