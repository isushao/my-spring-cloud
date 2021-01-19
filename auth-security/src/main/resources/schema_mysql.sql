create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint ix_auth_username
        unique (username, authority),
    constraint fk_authorities_users
        foreign key (username) references users (username)
);

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');

create table users
(
    username varchar(50)  not null
        primary key,
    password varchar(500) not null,
    enabled  tinyint(1)   not null
);

INSERT INTO users (username, password, enabled) VALUES ('admin', '{bcrypt}$2a$10$awKax9CGDeRlQ/zZtg3X5uUkT2M9r45IMV1LHzxnADQ9IcgUUzrOS', 1);
INSERT INTO users (username, password, enabled) VALUES ('user', '{bcrypt}$2a$10$awKax9CGDeRlQ/zZtg3X5uUkT2M9r45IMV1LHzxnADQ9IcgUUzrOS', 1);

create table oauth_client_details
(
    client_id               varchar(256)  not null
        primary key,
    resource_ids            varchar(256)  null,
    client_secret           varchar(256)  null,
    scope                   varchar(256)  null,
    authorized_grant_types  varchar(256)  null,
    web_server_redirect_uri varchar(256)  null,
    authorities             varchar(256)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(256)  null
)
    charset = utf8;

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('R2dpxQ3vPrtfgF72', null, '{bcrypt}$2a$10$or6hEP6WKHp8hLLnSrRZG.39U6G0qFcXdirctqxIzrmyW2MTIXwRO', 'user_info', 'authorization_code', 'http://localhost:8082/login/oauth2/code/', 'user_info', null, null, null, 'true');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('su', null, '{bcrypt}$2a$10$awKax9CGDeRlQ/zZtg3X5uUkT2M9r45IMV1LHzxnADQ9IcgUUzrOS', 'order:read', 'authorization_code,password,refresh_token', 'http://www.baidu.com', null, null, null, null, 'true');

create table oauth_code
(
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    code           varchar(255)                        null,
    authentication blob                                null
)
    charset = utf8;

create index code_index
    on oauth_code (code);