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
);

ALTER SEQUENCE farm_id_seq INCREMENT BY 500;
ALTER SEQUENCE chicken_id_seq INCREMENT BY 500;
ALTER SEQUENCE egg_id_seq INCREMENT BY 500;
ALTER SEQUENCE cow_id_seq INCREMENT BY 500;