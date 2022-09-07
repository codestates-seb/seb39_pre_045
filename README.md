# CodeStates-Seb-Team-045

## ****🏠 소개****


**Stack Overflow 클론 코딩**

- 화면 설계 [Figma prototype](https://www.figma.com/proto/c4txzvN1wokPe9JxDFpmfn/pre-project-stackoverflow-clone-prototype?node-id=28%3A785&scaling=min-zoom&page-id=0%3A1&starting-point-node-id=28%3A785)
- 배포 링크
- 미리 보기

## ****⏲️ 개발 기간****


**22.08.23 ~22.09.06**



## ****🧙 멤버 구성****



**FE**: 이윤진, 김민영

**BE**: 박재환, 양아롬, 나강혁



## ****📌 기술 스택****



**FE**: React v18(create-react-app), styled-component, zustand, react-router, toast-ui, axios

**BE**: Java11, Spring Boot, MySQL

- Spring Boot
    - Spring Data JPA
    - Spring Security
    - Spring REST Docs



## ****📌 주요 기능****



### **반응형 웹 구현**

1. **회원 가입, 로그인, 비밀번호 찾기, 로그아웃**
    - jwt - access, refresh 토큰 생성, 발급 구현
    - spring-boot-mail, thymeleaf를 활용한 html 형식의 이메일 전송 구현
2. **마이 페이지, 회원 정보 수정, 회원 탈퇴**
3. **질문 리스트 조회 • 글 검색 조회, 질문 상세 조회**
    - pagination/sort 구현
4. **질문 • 답변 등록/수정/삭제**
    - 마크 다운 에디터 지원
5. **댓글 등록/수정/삭제**
6. **질문 • 답변 추천/비 추천**
7. **답변 채택**

## ****📌 ERD & API 설계****



### ERD
![image](https://user-images.githubusercontent.com/85007104/188805607-7ed7d609-5162-43c1-80b5-a96734a02f83.png)



### API 명세

추후 첨부

**Spring** **REST Docs**

- Member, Answer, QuestionVote, HomeController, Question - 전체 질문 조회, 질문 검색 부분
https://1drv.ms/u/s!ApUNkUcBCUm4khOCAumpA8hBDzcZ?e=bv4CDX
