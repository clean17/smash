-- hsqldb 사용시
-- DROP TABLE people IF EXISTS;
--
-- CREATE TABLE people  (
--     person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,  -- IDENTITY 는 자동 증가 필드에 사용된다. PK에 부여, 수동으로 값을 입력할 수 없다.// h2DB는 AUTO_INCREMENT 를 사용한다.
--     first_name VARCHAR(20),
--     last_name VARCHAR(20)
-- );

-- serial 은 Postgre 의 자동증가 전략, h2 에서도 사용가능
drop table BOOKINGS if exists;
create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);