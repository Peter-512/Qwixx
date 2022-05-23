--Username : postgres
--Password : Student_1234

-- FIXME rename all tables to plural

DROP TABLE IF EXISTS action;

DROP TABLE IF EXISTS session_statistics;

DROP TABLE IF EXISTS global_statistics;

DROP TABLE IF EXISTS turn;

DROP TABLE IF EXISTS score;

DROP TABLE IF EXISTS player_session;

DROP TABLE IF EXISTS game_session;

DROP TABLE IF EXISTS player;


CREATE TABLE game_session
(
    duration INT NOT NULL,
    game_id  INT GENERATED ALWAYS AS IDENTITY
        CONSTRAINT game_session_pk
            PRIMARY KEY
);

CREATE TABLE player
(
    player_id SERIAL
        CONSTRAINT player_pk
            PRIMARY KEY,
    name      VARCHAR(30) NOT NULL
);

CREATE TABLE player_session
(
    session_id     SERIAL
        CONSTRAINT player_session_pk
            PRIMARY KEY,
    game_id        INT
        CONSTRAINT player_session_game_session_game_id_fk
            REFERENCES game_session,
    player_id      INT
        CONSTRAINT player_session_player_player_id_fk
            REFERENCES player,
    starting_first BOOL NOT NULL
);

CREATE TABLE turn
(
    turn_id       SERIAL
        CONSTRAINT turn_pk
            PRIMARY KEY,
    session_id    INT
        CONSTRAINT turn_player_session_session_id_fk
            REFERENCES player_session ( session_id ),
    turn_duration INT NOT NULL
);

CREATE TABLE action
(
    action_id                SERIAL
        CONSTRAINT action_pk
            PRIMARY KEY,
    turn_id                  INT
        CONSTRAINT action_turn_turn_id_fk
            REFERENCES turn,
    amount_of_numbers_missed INT NOT NULL,
    passed_turn              BOOL,
    points_earned            INT
);


CREATE TABLE score
(
    score_id                        SERIAL
        CONSTRAINT score_pk
            PRIMARY KEY,
    session_id                      INT
        CONSTRAINT score_player_session_session_id_fk
            REFERENCES player_session ( session_id ),
    total_amount_of_numbers_crossed INT NOT NULL,
    total_points                    INT NOT NULL,
    penalty_points                  INT NOT NULL,
    total_score                     INT NOT NULL,
    red_points                      INT NOT NULL,
    yellow_points                   INT NOT NULL,
    blue_points                     INT NOT NULL,
    green_points                    INT NOT NULL
);
COMMENT ON COLUMN score.total_points IS 'All points excluding penalty points';
COMMENT ON COLUMN score.total_score IS 'total_points - penalty_points';

CREATE TABLE session_statistics
(
    game_id                         INT
        CONSTRAINT session_statistics_pk
            PRIMARY KEY,
    most_valuable_turn              INT NOT NULL
        CONSTRAINT session_statistics_turn_turn_id_fk
            REFERENCES turn,
    average_turn_duration           INT NOT NULL,
    longest_turn_duration           INT NOT NULL,
    shortest_turn_duration          INT NOT NULL,
    average_points_per_turn         INT NOT NULL,
    most_points_per_turn            INT NOT NULL,
    average_numbers_missed_per_turn INT NOT NULL,
    most_numbers_missed_per_turn    INT NOT NULL
);
