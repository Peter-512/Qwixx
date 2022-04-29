package Statistics;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class Statistics {


    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/qwixx1",
                    "postgres",
                    "Student_1234");
            Statement statement = connection.createStatement();
            statement.execute("""
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
                    );        CREATE SEQUENCE IF NOT EXISTS id_incr START 1;
                                        
                                        
                    CREATE SEQUENCE IF NOT EXISTS rank START WITH 1;
                    ALTER SEQUENCE rank RESTART WITH 1;
                                        
                                        
                                        
                    CREATE TABLE IF NOT EXISTS player
                    (
                        name_id varchar(255) PRIMARY KEY,
                        game_id numeric,
                        CONSTRAINT fk_game_ID
                            FOREIGN KEY (game_id)
                                REFERENCES game_session (game_id)
                    );
                                        
                                        
                    CREATE TABLE If NOT EXISTS score
                    (
                        points                          numeric,
                        total_duration                  time(3)
                            CONSTRAINT nn_total_time NOT NULL,
                        total_amount_of_numbers_crossed numeric,
                        total_score                     numeric,
                        penalty_points                  numeric,
                        game_id                         numeric,
                        CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game_session (game_id)
                                        
                    );
                                        
                    CREATE TABLE IF NOT EXISTS session_statistics
                    (
                        longest_turn                    numeric,
                        shortest_turn                   numeric,
                        average_turn_duration           numeric,
                        average_points_earned_per_turn  numeric,
                        most_points_earned_per_turn     numeric,
                        average_numbers_missed_per_turn numeric,
                        most_numbers_missed_per_turn    numeric,
                        most_valuable_turn              numeric
                    );
                                        
                    CREATE TABLE IF NOT EXISTS turn (
                        turn_number numeric PRIMARY KEY,
                        turn_start_time numeric,
                        turn_end_time numeric,
                        turn_duration numeric
                    );""");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }

}


