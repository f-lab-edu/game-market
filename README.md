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

### 상품

| 기능    | METHOD | URL           |
|-------|--------|---------------|
| 상품 등록 | POST   | /product/     |
| 상품 삭제 | DELETE | /product/{id} |
| 상품 조회 | GET    | /product      |
| 상품 수정 | PATCH  | /product/{id} |
| 상품 예약 | ---    | ---           |

### 좋아요

| 기능          | METHOD | URL                  |
|-------------|--------|----------------------|
| 상품 좋아요 등록   | POST   | /like/product/{id}   |
| 상품 좋아요 삭제   | DELETE | /like/product/{id}   |
| 상품 좋아요 조회   | GET    | /like/product/       |

### 회원

| 기능    | METHOD | URL            |
|-------|--------|----------------|
| 회원 가입 | POST   | /user/sign-up  |
| 회원 탈퇴 | PATCH  | /user/sign-off |
| 로그인   | POST   | /user/sign-in  |
| 로그아웃  | POST   | /user/sing-out |
| 회원 수정 | PATCH  | /user/update   |

### 채팅

| 기능    | METHOD | URL  |
|-------|------------|------|
| 1:1 채팅 | /chat    | ---  |
