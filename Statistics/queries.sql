SELECT turn_number, action_number, points_earned, amount_of_numbers_missed, passed_turn
FROM player
         JOIN player_session USING ( player_id )
         JOIN turn USING ( session_id )
         JOIN action USING ( turn_id )
WHERE player_id = 5;

SELECT SUM( points_earned )
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
WHERE name LIKE '%Toni%';
SELECT DISTINCT game_id
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
         JOIN game_session USING ( game_id )
WHERE name LIKE '%Toni%';
SELECT SUM( points_earned )
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
         JOIN game_session USING ( game_id )
WHERE game_id IN (SELECT DISTINCT game_id
                  FROM game_session
                           JOIN player_session USING ( game_id )
                           JOIN player USING ( player_id )
                  WHERE name LIKE '%Toni%')
  AND name = 'Skynet';

SELECT SUM( points_earned ) > (SELECT SUM( points_earned )
                               FROM action
                                        JOIN turn USING ( turn_id )
                                        JOIN player_session USING ( session_id )
                                        JOIN player USING ( player_id )
                                        JOIN game_session USING ( game_id )
                               WHERE game_id IN (SELECT DISTINCT game_id
                                                 FROM game_session
                                                          JOIN player_session USING ( game_id )
                                                          JOIN player USING ( player_id )
                                                 WHERE name LIKE '%Toni%')
                                 AND name = 'Skynet') AS player_won
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
WHERE name LIKE '%Toni%';


SELECT AVG( amount_of_numbers_missed ), turn_number
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
WHERE name = 'Piet'
  AND NOT passed_turn
  AND points_earned != -5
GROUP BY turn_number;

SELECT AVG( amount_of_numbers_missed )
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
WHERE name = 'Piet'
  AND NOT passed_turn
  AND points_earned != -5;

SELECT SUM( nums_missed ) / COUNT( nums_missed )
FROM (SELECT SUM( amount_of_numbers_missed )
      FROM action
               JOIN turn USING ( turn_id )
               JOIN player_session USING ( session_id )
               JOIN player USING ( player_id )
      WHERE name = 'Piet'
        AND NOT passed_turn
        AND points_earned != -5
      GROUP BY turn_number) AS nums_missed;

SELECT amount_of_numbers_missed, turn_number, action_number
FROM action
         JOIN turn USING ( turn_id )
         JOIN player_session USING ( session_id )
         JOIN player USING ( player_id )
WHERE name = 'Piet'
  AND NOT passed_turn
  AND points_earned != -5;


SELECT game_id, name, total_score, is_win, start_time
FROM game_session
         JOIN player_session USING ( game_id )
         JOIN player USING ( player_id )
         JOIN score USING ( session_id );


SELECT *
FROM game_session
         JOIN player_session USING ( game_id )
         JOIN player USING ( player_id )
         JOIN score USING ( session_id )
         JOIN turn USING ( session_id )
         JOIN action USING ( turn_id );


SELECT name,
       REVERSE( LOWER( REPLACE( name, ' ', '' ) ) )                                    AS password,
       CASE WHEN name IN ( 'Peter', 'Nova', 'Brandon' ) THEN 'Admin' ELSE 'Player' END AS user_role
FROM player;
