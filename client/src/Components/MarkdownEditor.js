import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';
import 'prismjs/themes/prism.css';
import Prism from 'prismjs';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight-all.js';
import { Editor } from '@toast-ui/react-editor';
import { forwardRef } from 'react';
import styled from 'styled-components';

const MarkdownEditor = (props, ref) => {
  return (
    <Div>
      <Editor
        ref={ref}
        height="500px"
        initialValue={props.value}
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

const Div = styled.div`
  overflow-x: scroll;
  margin: 10px 0px;
`;

export default forwardRef(MarkdownEditor);
