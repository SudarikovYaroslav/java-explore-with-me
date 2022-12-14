CREATE TABLE IF NOT EXISTS apps
(
    app_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app_name varchar(512)                            NOT NULL,
    PRIMARY KEY (app_id)
);

CREATE TABLE IF NOT EXISTS hits
(
    hit_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app_id     BIGINT REFERENCES apps (app_id)         NOT NULL,
    uri        varchar(512)                            NOT NULL,
    ip         varchar(15)                             NOT NULL,
    time_stamp TIMESTAMP WITHOUT TIME ZONE,
    event_id   BIGINT,
    PRIMARY KEY (hit_id)
);