create table lottery_number_statistics
(
    id           bigint not null
        constraint lottery_number_statistics_pkey
            primary key,
    drawn_count  integer,
    drawn_number integer,
    game_type    varchar(255)
);

create table six_of_fourtynine_combinations
(
    id       integer not null
        constraint six_of_fourtynine_combinations_pkey
            primary key,
    checksum integer,
    number1  integer,
    number2  integer,
    number3  integer,
    number4  integer,
    number5  integer,
    number6  integer
);

create table drawn_numbers
(
    uuid               uuid not null
        constraint drawn_numbers_pkey
            primary key,
    drawn_date         timestamp,
    drawn_id           varchar(255),
    game_seventy_seven integer,
    super_number       integer,
    super_six_number   integer,
    owner              integer
        constraint fk_drawn_numbers_six_of_fourtynine_combinations_id
            references six_of_fourtynine_combinations
);

create table six_of_fourtynine_combinations_statistics
(
    id          integer not null
        constraint six_of_fourtynine_combinations_statistics_pkey
            primary key,
    drawn_count integer,
    owner       integer
        constraint fk_sofncs_six_of_fourtynine_combinations_id
            references six_of_fourtynine_combinations
);

create table tickets
(
    uuid               uuid not null
        constraint tickets_pkey
            primary key,
    drawn_id           varchar(255),
    game_seventy_seven integer,
    owner              varchar(255),
    super_number       integer,
    super_six_number   integer
);

create table boxes
(
    uuid         uuid not null
        constraint boxes_pkey
            primary key,
    game_type    varchar(255),
    index        integer,
    win_category varchar(255),
    numbers_id   integer
        constraint fk_boxes_six_of_fourtynine_combinations_id
            references six_of_fourtynine_combinations,
    ticket_uuid  uuid
        constraint fk_boxes_tickets_uuid
            references tickets
);