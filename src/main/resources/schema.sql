-- R2DBC + H2 스키마 // SERIAL : 자동증가
CREATE TABLE customer (
      id SERIAL PRIMARY KEY
    , first_name VARCHAR(255)
    , last_name VARCHAR(255)
);