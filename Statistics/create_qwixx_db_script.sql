--Username : postgres
--Password : Student_1234
-- CREATE DATABASE Qwixx1;
drop DATABASE qwixx1;

create DATABASE qwixx1;

CREATE TABLE IF NOT EXISTS game_session
(
    game_id               numeric PRIMARY KEY
        CONSTRAINT
            nn_game_id NOT NULL,
    start_time            time(3)
        CONSTRAINT nn_starttime NOT NULL,
    end_time              time(3)
        CONSTRAINT nn_endtime NOT NULL,
    amount_of_rows_locked numeric,
    duration              time
        CONSTRAINT nn_duration NOT NULL
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
        CONSTRAINT nn_total_time NOT NULL,
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
        CONSTRAINT nn_average_total_points NOT NULL,
    most_total_points             numeric
        CONSTRAINT nn_most_total_points NOT NULL,
    least_total_points            numeric
        CONSTRAINT nn_least_total_points NOT NULL,
    most_valuable_turn            numeric

)
