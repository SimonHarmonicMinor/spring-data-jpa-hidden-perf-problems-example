CREATE TABLE farm
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT
);

CREATE TABLE cow
(
    id      BIGSERIAL PRIMARY KEY,
    name    TEXT,
    farm_id BIGINT REFERENCES farm (id)
);

CREATE TABLE chicken
(
    id      BIGSERIAL PRIMARY KEY,
    name    TEXT,
    farm_id BIGINT REFERENCES farm (id)
);

CREATE TABLE egg
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT,
    chicken_id BIGINT REFERENCES chicken (id)
)