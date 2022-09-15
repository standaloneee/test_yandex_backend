create table "item"
(
    id       uuid primary key,
    url      varchar(255),
    date     DATE,
    parent_id uuid,
    type     varchar(20),
    size     BIGINT,
    FOREIGN KEY (parent_id) REFERENCES item (id)
);
