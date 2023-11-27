CREATE TABLE characters(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    jutsu JSON,
    nature_type JSON,
    birthdate VARCHAR(15),
    sex VARCHAR(10),
    age INT,
    height VARCHAR(50),
    weight VARCHAR(50),
    clan VARCHAR(100),
    tools JSON,

    PRIMARY KEY(id)
);