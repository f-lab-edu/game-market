DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_like;

CREATE TABLE `user`
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname    VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    status      BOOLEAN      NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    seller_id       BIGINT       NOT NULL,
    seller_nickname VARCHAR(100) NOT NULL,
    name            VARCHAR(100) NOT NULL,
    price           INT          NOT NULL,
    category        VARCHAR(100) NOT NULL,
    status          BOOLEAN      NOT NULL,
    created_at      DATETIME     NOT NULL,
    updated_at      DATETIME     NOT NULL
);

CREATE INDEX idx_seller_nickname ON product(seller_nickname);
CREATE INDEX idx_name ON product(name);
CREATE INDEX idx_category ON product(category);
CREATE INDEX idx_status ON product(status);
CREATE INDEX idx_create_at ON product(created_at);

CREATE TABLE product_like
(
    product_id      BIGINT       NOT NULL,
    user_id         BIGINT       NOT NULL,
    seller_id       BIGINT       NOT NULL,
    PRIMARY KEY (product_id, user_id, seller_id)
);