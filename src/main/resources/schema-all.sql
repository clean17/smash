DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,  -- IDENTITY 는 자동 증가 필드에 사용된다. PK에 부여, 수동으로 값을 입력할 수 없다.// h2DB는 AUTO_INCREMENT 를 사용한다.
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);