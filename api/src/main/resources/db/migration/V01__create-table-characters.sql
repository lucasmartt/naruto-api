CREATE TABLE characters(
    id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    jutsu JSON,
    nature_type JSON,
    birthdate VARCHAR(15),
    sex VARCHAR(10),
    clan VARCHAR(100),
    tools JSON,

    PRIMARY KEY(id)
);