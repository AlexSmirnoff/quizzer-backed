create table auth.users
(
    id       bigint primary key generated always as identity,
    username text not null,
    password text not null,
    UNIQUE (username)
);

create table auth.granted_authorities
(
    user_id bigint references auth.users(id),
    granted_authority text not null,
    UNIQUE (user_id, granted_authority)
);