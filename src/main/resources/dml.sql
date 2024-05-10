INSERT INTO `user` (nickname, email, password, status, created_at, updated_at) VALUES
('abc', 'abc@naver.com', '$2a$10$Z0bb.ObvF1kNnQTohs7E7.2vCWyq2MojzVKD1RneJNbn5oKwp0RE2', TRUE, NOW(), NOW()),
('abcd', 'abcd@naver.com', '$2a$10$Z0bb.ObvF1kNnQTohs7E7.2vCWyq2MojzVKD1RneJNbn5oKwp0RE2', TRUE, NOW(), NOW());
-- login: {"email": "abc@naver.com", "password": "qwer1234!@"}

INSERT INTO product (seller_id, seller_nickname, name, price, category, status, created_at, updated_at) VALUES
(1, 'abc', 'godOfWar3', 1200, 'ACTION', TRUE, NOW(), NOW()),
(2, 'abcd', 'forzaHorizon5', 800, 'RACING', TRUE, NOW(), NOW());

