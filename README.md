# 💻 Game-Market
Game Title을 사고 팔수 있는 중고거래 서비스

<br>

# 🛠️ 기술 스택
* **Java 17**
* **Spring Boot 3.2.4**
* **MySQL**
* **Redis**

<br>

# 🧩 시스템 구조

<br>

# 📄 TABLE
![스크린샷 2024-04-22 오후 8 27 00](https://github.com/f-lab-edu/game-market/assets/58434352/587487cd-4a21-42e8-bccf-07da3ea52178)
![스크린샷 2024-04-22 오후 8 27 09](https://github.com/f-lab-edu/game-market/assets/58434352/890e78ee-48e1-4729-84d9-1184e963cb90)

<br>

# 📜 API

### 회원

| 기능   | url        |
|-------|------------|
| 회원 가입 | /user/sign-up  |
| 회원 탈퇴 | /user  |
| 로그인   | /user/log-in   |
| 로그아웃   | /user/log-out   |

### 상품

| 기능   | url        |
|-------|------------|
| 상품 조회 | /product/find  |
| 상품 등록 | /product/create  |
| 상품 삭제 | /product/delete  |
| 상품 수정 | /product/modify  |
| 상품 예약 | /product/reserve  |
| 좋아요   | /product/like  |

### 채팅

| 기능   | url        |
|-------|------------|
| 1:1 채팅 | /chat    |