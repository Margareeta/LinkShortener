CREATE database links_db;
CREATE table links
(
    short_link varchar(50) primary key,
    full_link  varchar(200),
    created_at timestamp,
    call_counter int

);
