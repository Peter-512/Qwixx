--Username : postgres
--Password : Student_1234
CREATE DATABASE Qwixx1;



CREATE TABLE IF NOT EXISTS GameSession
(
    gameID             numeric PRIMARY KEY
        CONSTRAINT nn_gameID NOT NULL,
    startTime          time(3)
        CONSTRAINT nn_startTime NOT NULL,
    endTime            time(3)
        CONSTRAINT nn_endTime NOT NULL,
    amountOfRowsLocked numeric,
    duration           time
        CONSTRAINT nn_duration NOT NULL
);

CREATE TABLE IF NOT EXISTS player
(
    nameID varchar(255) PRIMARY KEY,
    gameID numeric,
    CONSTRAINT fk_game_ID
        FOREIGN KEY (gameID)
            REFERENCES GameSession (gameID)
);


CREATE TABLE score
(
    points                      numeric,
    totalDuration               time(3)
        CONSTRAINT nn_totalDuration NOT NULL,
    totalAmountOfNumbersCrossed numeric,
    totalScore                  numeric,
    penaltyPoints               numeric
);

CREATE TABLE IF NOT EXISTS SessionStatistics
(
    gameID                      numeric PRIMARY KEY,
    longestTurn                 numeric,
    shortestTurn                numeric,
    averageTurnDuration         numeric,
    averagePointsEarnedPerTurn  numeric,
    mostPointsEarnedPerTurn     numeric,
    averageNumbersMissedPerTurn numeric,
    mostNumbersMissedPerTurn    numeric,
    mostValuableTurn            numeric,
    nameID                      varchar(255),
    CONSTRAINT fk_game_ID
        FOREIGN KEY (gameID)
            REFERENCES GameSession (gameID),
    CONSTRAINT fk_name_ID
        FOREIGN KEY (nameID)
            REFERENCES player (nameID)
);

CREATE TABLE IF NOT EXISTS GlobalStatistics
(
    gameID                      numeric PRIMARY KEY,
    longestTurn                 numeric,
    shortestTurn                numeric,
    averageTurnDuration         numeric,
    averagePointsEarnedPerTurn  numeric,
    mostPointsEarnedPerTurn     numeric,
    averageNumbersMissedPerTurn numeric,
    mostNumbersMissedPerTurn    numeric,
    averageTotalPoints          numeric
        CONSTRAINT nn_averageTotalPoints NOT NULL,
    mostTotalPoints             numeric
        CONSTRAINT nn_mostTotalPoints NOT NULL,
    leastTotalPoints            numeric
        CONSTRAINT nn_leastTotalPoints NOT NULL,
    mostValuableTurn            numeric,
    nameID                      varchar(255),
    CONSTRAINT fk_game_ID
        FOREIGN KEY (gameID)
            REFERENCES GameSession (gameID),
    CONSTRAINT fk_name_ID
        FOREIGN KEY (nameID)
            REFERENCES player (nameID)
);