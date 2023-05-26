# 개인 맞춤형 채식 쇼핑몰 웹 애플리케이션, **채식이들 🥗**

<a href="http://chaesik2s.s3-website.ap-northeast-2.amazonaws.com/">
  <img width="150" height="30" src="https://img.shields.io/badge/(v2)채식이들 배포 링크-6ba544?style=for-the-badge&logo=one&logoColor=black"></a>

<a href="http://chaesik2s.shop/">
  <img width="150" height="30" src="https://img.shields.io/badge/(v1)채식이들 배포 링크-ffffff?style=for-the-badge&logo=one&logoColor=black"></a>
  
<a href="https://www.youtube.com/watch?v=7s14aXx1vXM">
  <img width="175" height="30" src="https://img.shields.io/badge/채식이들 유튜브 영상-ffffff?style=for-the-badge&logo=youtube&logoColor=black"></a>
  
<a href="https://codestates.notion.site/39-Team-cbcc6beff32a4ba1bfac9e8a12cf41ad">
    <img width="150" height="30" src="https://img.shields.io/badge/채식이들 팀 노션-ffffff?style=for-the-badge&logo=notion&logoColor=black"></a>
  
<a href="https://not-error-064.tistory.com/">
  <img width="160" height="30" src="https://img.shields.io/badge/채식이들 팀 블로그-ffffff?style=for-the-badge&logo=tistory&logoColor=black"></a>

<br>

<img width="1200" alt="스크린샷 2022-10-10 오후 5 57 07" src="https://user-images.githubusercontent.com/95335294/194830808-1122b293-33c3-4e61-aab9-f92db5d0622b.png">

<br>

## 채식이들 버전 이력

### v2.0.0

> 자세한 내용은 [TAG] 를 확인해주세요.

[업데이트 내용 요약]
* __ChatGPT 기반 AI 대화형 큐레이션 기능 개발__
* __비회원 메인 페이지에 제품 미출력 에러 해결__
* __장바구니에 제품 주문시, 장바구니 내역 삭제 기능 구현__
* 제품 재고 수량에 맞는 품절, 품절임박 태그 추가 / 장바구니의 제품이 품절일 경우 품절 표시
* 코드 리팩토링 및 API 통신 트러블 슈팅

[배포 링크]

* V2 비용 문제로 배포 임시 중단

> __이유 :__ <br>
> 초기 AWS EC2 프리 티어 제품(t2.micro)으로 배포하려 했으나, 너무 심한 Time delay로 인해 실패 <br>
> 따라서 빠른 배포와 임시 오픈을 목표로 등급을 높여서 배포 <br>
> 30일이 지났을 때, 감당하기 힘든 비용 발생 <br>

> __느낀점 :__ <br>
> '최소한의 비용으로 최대한의 성능을 만들어야 하는 것'이 백엔드 개발자의 중요한 역할이라고 뼈저리게 느꼈다.

[배포 예정]
* 비용 절감을 위해 클라우드 서버가 자동 슬립 모드로 설정하는 방법을 학습하고 적용
* (단점) 링크를 클릭할 때 서비스가 깨어나기 때문에 첫 시작이 느림
* 하지만 실제로 트래픽이 발생하는 서비스가 아니기 때문에 이와 같은 배포 전략을 선택


---

### v1.2.0

[배포링크] http://chaesik2s.shop/

* 채식주의자 유형에 따른 차별적인 제품 제공 기능
* 쇼핑 서비스 기능(장바구니, 주문 등)
* AWS 3 tier architecture(EC2,RDS,S3)

<br>

## ▪️ 프로젝트 기간

v1 : `2022.09.07 ~ 2022.10.12`

v2 : `2023.03 ~ 2023.04`

<br>

## ▪️ 프로젝트 설명

 `핵심 팀 목표 : 고객이 겪는 문제를 해결하는 서비스를 만들자`

### ▫️ 기획 배경

* 소비자들의 채식 유형에 해당하는 제품을 편하게 구매할 수 있도록 제공하고자 함

### ▫️ 핵심 기능

* 본인이 설정한 **채식 유형**에 맞는 **식제품 노출** 기능

<img width="800" alt="스크린샷 2022-10-10 오후 8 10 04" src="https://user-images.githubusercontent.com/95335294/194853396-37863935-c13e-4e51-b492-0c087e5bb570.png">

### ▫️ 서비스 특징

* 플렉시테리언, 폴로-페스코, 페스코, 폴로, 락토-오보, 락토, 오보, 비건, 프루테리언 등 채식 유형에 맞게 상품 데이터 분류
* 대체품, 야채류 제품만 나열되어 있는 기존 채식 쇼핑몰과 다른 차별점 보유
* UI//UX 부분에 채식 유형 정보를 알 수 있도록 제작하여 사용자 경험 극대화
* 채식에 대한 정확한 인식으로 다양한 채식 유형의 소비자들에게 편한 쇼핑 서비스 제공

<br>

## 👨🏻‍💻 Member

|[<img src="https://avatars.githubusercontent.com/u/79829085?v=4" width="160px;" alt=""/>](https://github.com/Si-Hyeak-KANG) |[<img src="https://avatars.githubusercontent.com/u/98000922?v=4" width="160px" >](https://github.com/Juniverse)|[<img src="https://avatars.githubusercontent.com/u/95335294?v=4" width="160px">](https://github.com/hongmj37)|[<img src="https://avatars.githubusercontent.com/u/98211110?v=4" width="160px" >](https://github.com/HYUNSUK331)|
|:---:|:---:|:---:|:---:|
|[BE 강시혁](https://github.com/Si-Hyeak-KANG) |[BE 황윤준](https://github.com/YunJuniverse)|[BE 홍민정](https://github.com/hongmj37) |[BE 이현석](https://github.com/HYUNSUK331)|
|🌟Team Leader|BE_CTO| GitHub 책임자 |배포 담당자|

|[<img src="https://avatars.githubusercontent.com/u/61141988?v=4" width="160px;" alt=""/>](https://github.com/Hong-sk) |[<img src="https://avatars.githubusercontent.com/u/94218285?v=4" width="160px">](https://github.com/git-daun)|[<img src="https://avatars.githubusercontent.com/u/94212747?v=4" width="160px" >](https://github.com/NR0617)|
|:---:|:---:|:---:|
|[FE 홍성권](https://github.com/Hong-sk) |[FE 정다운](https://github.com/git-daun) |[FE 오나래](https://github.com/NR0617)|
|FE_CTO|팀블로그 관리|회의록 |

<br>

## 🫧 Skill

### ▪️ Frontend

<img width="800" alt="스크린샷 2022-10-10 오후 8 09 19" src="https://user-images.githubusercontent.com/95335294/194853665-2b0631fb-cc23-4926-80b7-0027cf7ef43e.png">

<br>

### ▪️ Backend 

<img width="800" alt="스크린샷 2022-10-10 오후 6 58 01" src="https://user-images.githubusercontent.com/95335294/194841207-ce338cc0-affd-4dc6-96a4-71c435d56147.png">

<br>

## 💌 Collaboration Tools
<p>
  <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
   <br>
  <img src="https://img.shields.io/badge/discord-4d377b?style=for-the-badge&logo=discord&logoColor=white">
  <img src="https://img.shields.io/badge/google calendar-ffffff?style=for-the-badge&logo=googlecalendar&logoColor=0052CC">
</p>

<br>

## 🗓 Schedule

<img width="800" alt="스크린샷 2022-10-10 오후 6 12 46" src="https://user-images.githubusercontent.com/95335294/194833392-e2e80799-6152-4e2d-af85-cf9b011d5ffd.png">

<br>

## 📝 Version

<img width="800" alt="스크린샷 2022-10-10 오후 6 14 00" src="https://user-images.githubusercontent.com/95335294/194833583-9fa2f005-185f-48b0-94c7-a7baeff797cd.png">

<br>

## 🏆 Strategy & Rule

### ▪️ Commit Message Rule

| 태그이름     | 설명                               |
|----------|----------------------------------|
| feat     | 새로운 기능 추가                        |
| bug      | 버그 수정                            |
| docs     | 문서 수정                            |
| test     | 테스트 코드를 생성하거나 수정                 |
| refactor | 코드 리팩토링                          |
| style    | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| design   | CSS 등 UI 수정                      |

(1) 커밋 메시지는 브랜치를 토대로 작성합니다.

(2) git commit 수행 시, 자동으로 커밋 템플릿 화면이 뜹니다.

(3) 해당 형식에 맞게 기록해주세요.

<br>

### ▪️ Branch strategy

<img width="800" alt="스크린샷 2022-10-10 오후 8 09 42" src="https://user-images.githubusercontent.com/95335294/194853537-8f4d6c55-d1fe-4181-9adf-d6213f5140f3.png">

(1) 각 개발자분들은 본 repository를 clone 합니다.

(2) Jira에서 이슈가 할당되면 이슈를 진행중으로 바꿔주세요.

(3) 진행중으로 이슈 상태를 바꾸면 자동으로 이슈에 해당하는 브랜치가 생성됩니다.

(4) pull을 받고 브랜치를 checkout 시켜주세요.

(5) 작업을 진행하고 완료되면, 생성된 브랜치에 push 를 합니다.

(6) push한 작업을 토대로 팀 코드리뷰를 진행합니다.

(7) FE는 dev/fe로 BE는 dev/be로 pull request 를 합니다.
    (Pull request 시, 자동으로 템플릿이 뜨니 형식에 맞게 기록해주세요.)
