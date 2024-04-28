DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    user_id    BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원 식별자',
    nick_name  VARCHAR(100) NOT NULL COMMENT '회원 닉네임',
    email      VARCHAR(255) NOT NULL COMMENT '회원 이메일',
    password   VARCHAR(255) NOT NULL COMMENT '비밀번호',
    status     BIT(1)       NOT NULL COMMENT '회원 상태',
    created_at DATETIME     NOT NULL COMMENT '생성 시간',
    updated_at DATETIME     NOT NULL COMMENT '수정 시간'
);