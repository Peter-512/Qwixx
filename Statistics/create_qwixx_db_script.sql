--Username : postgres
--Password : Student_1234
-- CREATE DATABASE Qwixx1;

CREATE TABLE IF NOT EXISTS game_session
(
    game_id               numeric PRIMARY KEY
        CONSTRAINT NOT NULL,
    start_time            time(3)
        CONSTRAINT NOT NULL,
    end_time              time(3)
        CONSTRAINT NOT NULL,
    amount_of_rows_locked numeric,
    duration              time
        CONSTRAINT NOT NULL
);

CREATE TABLE IF NOT EXISTS player
(
    name_id varchar(255) PRIMARY KEY,
    game_id numeric,
    CONSTRAINT fk_game_ID
        FOREIGN KEY (game_id)
            REFERENCES game_session (game_id)
);


CREATE TABLE score
(
    points                      numeric,
    total_duration               time(3)
        CONSTRAINT NOT NULL,
    total_amount_of_numbers_crossed numeric,
    total_score                  numeric,
    penalty_points               numeric,
    game_id numeric,
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game_session (game_id)

);

CREATE TABLE IF NOT EXISTS session_statistics
(
    longest_turn                 numeric,
    shortest_turn                numeric,
    average_turn_duration         numeric,
    average_points_earned_per_turn  numeric,
    most_points_earned_per_turn     numeric,
    average_numbers_missed_per_turn numeric,
    most_numbers_missed_per_turn    numeric,
    most_valuable_turn            numeric
);

CREATE TABLE IF NOT EXISTS global_statistics
(
    longest_turn                 numeric,
    shortest_turn                numeric,
    average_turn_duration         numeric,
    average_numbers_missed_per_turn numeric,
    most_points_earned_per_turn     numeric,
    averageNumbersMissedPerTurn numeric,
    most_numbers_missed_per_turn    numeric,
    average_total_points          numeric
        CONSTRAINT  NOT NULL,
    most_total_points             numeric
        CONSTRAINT  NOT NULL,
    least_total_points            numeric
        CONSTRAINT  NOT NULL,
    most_valuable_turn            numeric

)
