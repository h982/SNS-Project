# SSAFY PJT I 원하는 운동의 그룹을 찾아 인증을 통해 서로 운동자극을 받는 SNS 혼자 뭐하니?

## 목차

- [프로젝트 소개](#프로젝트-소개)
- [텍스트 ... ](#프로젝트-소개)
- [프로젝트 명세](#프로젝트-명세)
  - [개발 환경](#개발-환경)
  - [Design Resources](#design-resources)
  - [핵심 라이브러리](#핵심-라이브러리)
    <br>

## 프로젝트 소개

- 서비스명 : 혼자 뭐하니?
- 소재 : 팀 매칭과 SNS
- 원하는 그룹을 찾고 서로의 운동욕구를 자극하는 SNS 프로그램
  - 원하는 그룹을 찾아 가입할 수 있다.
  - 그룹을 통해 팀원들과 같이 운동할 수 있는 기회를 제공한다.
  - SNS를 통해 서로 소통하며 첼린지를 통해 서로의 운동욕구를 자극한다.
  - 결재시스템과 추천시스템을 활용해 차별화된 서비스를 제공한다.
<br>

## 프로젝트 명세

### 개발 환경

---
## :video_game: 기술 스택

### 💻Back-End

  <details>
      <summary>Back 자세히 살펴보기 🔥</summary>
      <ul>
        <li>기술스택 ⚙</li>
      </ul>
      <ul>
          <li>Spring-Boot : 2.3.9</li>
          <li>Spring-Boot-Data-JPA</li>
          <li>spring-boot-starter-mail</li>
          <li>spring-boot-starter-validation</li>
          <li>spring-boot-starter-jdbc</li>
          <li>openvidu-java-client : 2.17.0</li>
          <li>spring-boot-starter-websocket</li>
          <li>spring-cloud-starter-aws : 2.2.5</li>
          <li>jjwt : 0.9.1</li>
          <li>lombok</li>
          <li>mysql : 8.0.22</li>
      </ul>
      <ul>
        <li>라이브러리 📚</li>
      </ul>
  </details>

### ✨Front-End

<details>
    <summary>Front 자세히 살펴보기 🌈</summary>
    <ul>
        <li>기술스택 ⚙</li>
    </ul>   
    <ul>
        <li>JS, HTML, CSS</li>
        <li>SCSS</li>
        <li>Vue.js @2.6.11</li>
    </ul>
    <li>--------------------------------------------------------------------------------------</li>
     <ul>
        <li>라이브러리 📚</li>
    </ul>   
    <ul>
        <li>axios @0.21.1 : Promise 기반 HTTP 클라이언트</li>
        <li>eslint & prettier @6.7.2 : 협업을 위한 formatter 라이브러리</li>
        <li>node-sass @5.0.0 : SASS/SCSS 적용을 위한 라이브러리</li>
        <li>sass-loader @10.1.1 : SASS/SCSS 적용을 위한 라이브러리</li>
    </ul>
</details>


### 🌏Infra

  <details>
      <summary>개발, CI/CD 자세히 살펴보기 🔥</summary>
      <ul>
          <li>AWS EC2 - Deploy Server</li>
          <li>AWS S3 - Image(File) Server</li>
          <li>Docker</li>
            -<li>Mysql - DB Server</li>
            -<li>Jenkins </li>
          <li>GitLab</li>
      </ul>
  </details>

-------------------------------------------------

#### Front-end / Android

- **Framework** : Vue.js 
- **지원 환경** : Web / Mobile 
- **담당자** : 진해진, 차범희, 이조순
  <br>

#### Back-end

- **Framework** : Spring boot 
- **Database** : MySQL 
- **담당자** : 최준오, 양동현, 이호황
  <br>

#### Design

- **Framework 사용** : O 
  - [Vuetify](https://vuetifyjs.com/)
  - [Sass](https://sass-lang.com/)
- **Design Tool 사용** : 
- **담당자** : 진해진, 차범희
  <br>

--------------------
### Design Resources
**외부 템플릿 또는 에셋** (이미지 또는 링크 첨부)

- 무료 이미지, 아이콘, 폰트 등은 제외
- [EldinZaimovic](https://eldin.space/) (무료)
  - **사용 기능** : 디자인 전반 적용
    <br>


-------------------
### 핵심 라이브러리
기본 제공하는 라이브러리 외 핵심 기능 구현에 사용한 라이브러리가 있다면 작성해주세요.  
- **Amazon S3**

  - **링크** : https://aws.amazon.com/ko/s3/
  - **소개** : AWS에서 제공하는 Store 라이브러리
  - **사용 기능** : 이미지 파일 저장 
  - **담당자** : 이호황

- **WebSocket**

  - **링크** : https://developer.mozilla.org/ko/docs/Web/API/WebSocket
  - **소개** : Rest API 소켓 연결을 할 수 있게 해주는 라이브러리
  - **사용 기능** : 소켓 연결, 통신
  - **담당자** : 양동현

- **카카오 API**

  - **링크** : https://developers.kakao.com/docs/latest/ko/local/dev-guide
  - **소개** : 카카오에서 제공하는 API
  - **사용 기능** : 주소 검색 API -> 자기 주변 그룹 검색에 이용
  - **담당자** : 이조순

