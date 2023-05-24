create table employee
(
    id              bigserial primary key,
    personal_number text    not null unique,
    last_name       text      not null,
    first_name      text      not null,
    middle_name     text,
    position        text,
    login           text      unique,
    email           text,
    status          boolean   not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table project
(
    id          bigserial primary key,
    code        bigint    not null unique,
    name        text      not null,
    description text,
    status      text      not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table task
(
    id          bigserial primary key,
    name        text      not null,
    description text,
    project_id  bigint    not null references project (id),
    employee_id bigint    references employee (id),
    labor_cost  bigint    not null,
    dead_line   date      not null,
    status      text      not null,
    author_id   bigint    not null references employee (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table team
(
    id         bigserial primary key,
    project_id bigint    not null references project (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table team_member
(
    id          bigserial primary key,
    team_id     bigint    not null references team (id),
    employee_id bigint    not null references employee (id),
    role        text      not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);