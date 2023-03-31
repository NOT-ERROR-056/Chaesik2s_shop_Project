# 개인 맞춤형 채식 쇼핑몰 웹 애플리케이션, **채식이들 🥗**

# v2 업데이트 내용
- 메인 페이지
   - 현재 메인 페이지의 채식주의자 유형 정보 명시 (플로팅 배너 선택, 회원, 비회원마다 상이한 유형에 따라 달라지는 메인 페이지를 확실하게 인지)
   - 플로팅 배너의 글자 및 박스 크기 증가
   - 전체 제품 list에서 각 제품에 섭취 가능 채식유형 태그 추가 (변화를 확실하게 인지)
   - Header 안의 요소들을 크기와 위치 조정
- 제품 상세 페이지
   -  페이지 UI 스타일 수정
   - ChatGPT API 를 적용한 AI 대화형 큐레이션 기능 추가
- 장바구니, 주문 도메인 API 에러 수정
   - 서버 코드 리팩토링으로, 차이가 발생한 장바구니, 주문 API 규격 통일
   - 장바구니 및 주문 내역의 총 가격 데이터 유실 에러 해결
   - 장바구니 안에 내역이 없을 때 주문 방지 오류 해결
   - 장바구니에서 제품 재고를 초과하여 주문했을 때, 주문되는 현상 에러 해결
   - 장바구니 내의 제품 구매시, 장바구니 내역 전체 삭제 기능 구현 

<br>

# 버전 관련 기타 정보
### 수행 기간 : 23.03
### 수행 멤버 : @Si-Hyeak-KANG 
### v2 배포 : Github Action, CI/CD

<br>

# 참고

* 프론트엔드, 백엔드 전체 영역을 혼자 수행했습니다. 혹시나 코드를 읽는데 어려움이 있을시 연락주세요!
* 앞으로도 [채식이들]을 꾸준히 업데이트 하겠습니다.
* 리액트(React) 코드 리뷰 기여 : @Hong-mike 

## ChatGPT API 기반 AI 대화형 큐레이션 기능

<img width="512" alt="스크린샷 2023-03-30 오후 10 50 01" src="https://user-images.githubusercontent.com/79829085/229088147-4367484a-29d4-41e5-a4e0-cd20e9c759ed.png">

## 제품 상세 페이지 UI 수정

<img width="512" alt="스크린샷 2023-03-30 오후 10 48 45" src="https://user-images.githubusercontent.com/79829085/229088265-34b772a5-1bb7-4023-a422-0fc1608c7c2e.png">

<img width="512" alt="스크린샷 2023-03-30 오후 10 50 14" src="https://user-images.githubusercontent.com/79829085/229088367-3ab2e555-2141-4bdf-9425-e96bc734ce14.png">

## 메인 페이지
<img width="512" alt="스크린샷 2023-03-30 오후 10 47 33" src="https://user-images.githubusercontent.com/79829085/229088981-d1a68694-2472-4761-ad3e-3a507fea7edb.png">
