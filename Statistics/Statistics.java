package Statistics;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class Statistics {
    //TODO: gameid (gamesession), playername (nameid) (Player), score (Player), avg_turnduration, turnduration, longest/shortest turn, penalty_points (scorecard), amount penalty,
    //TODO: most vs least value points, isCrossed


    //GLOBAL:   avg
    //SESSION:

    public static void main(String[] args) {

//    }
//    public Statistics() {
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
                                        
                                        
                    CREATE TABLE score
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
                                        
                    CREATE TABLE IF NOT EXISTS global_statistics
                    (
                        longest_turn                    numeric,
                        shortest_turn                   numeric,
                        average_turn_duration           numeric,
                        average_numbers_missed_per_turn numeric,
                        most_points_earned_per_turn     numeric,
                        averageNumbersMissedPerTurn     numeric,
                        most_numbers_missed_per_turn    numeric,
                        average_total_points            numeric
                            CONSTRAINT nn_average_total_points NOT NULL,
                        most_total_points               numeric
                            CONSTRAINT nn_most_total_points NOT NULL,
                        least_total_points              numeric
                            CONSTRAINT nn_least_total_points NOT NULL,
                        most_valuable_turn              numeric
                                        
                    );
                                        
                    CREATE TABLE IF NOT EXISTS turn (
                        turn_number numeric PRIMARY KEY,
                        turn_start_time numeric,
                        turn_end_time numeric,
                        turn_duration numeric
                    );""");
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE IF NOT EXISTS game_session " +
//                    "(gameID numeric PRIMARY KEY CONSTRAINT nn_gameID NOT NULL, " +
//                    "startTime timestamp(3) CONSTRAINT nn_startTime NOT NULL, " +
//                    "endTime timestamp(3) CONSTRAINT nn_endTime NOT NULL);"); // creates table
//            statement.execute("CREATE SEQUENCE IF NOT EXISTS id_incr START 1;"); //CREATE SEQUENCE FOR game_id
//            // statement.execute("insert into INT_leaderboard values (nextval('id_incr'),'Debil',5,date_trunc('second', now()));");
//            //statement.execute("create sequence if not exists rank start with 1;");
//            //statement.execute("alter sequence rank restart with 1;");
//
//            statement.execute("CREATE TABLE IF NOT EXISTS player(" +
//                    "gameID numeric," +
//                    "CONSTRAINT fk_game_ID FOREIGN KEY (gameID) REFERENCES game_session(gameID)," +
//                    "nameID varchar(255) PRIMARY KEY," +
//                    "score numeric CONSTRAINT nn_score NOT NULL," +
//                    "TotalTime timestamp(3) CONSTRAINT nn_totalTime NOT NULL," +
//                    "GameOver boolean" +
//                    ");");
//
//            statement.execute("CREATE TABLE SessionStatistics(" +
//                    "gameID numeric PRIMARY KEY," +
//                    "averageDuration numeric," +
//                    "averageScore numeric," +
//                    "nameID varchar(255)," +
//                    "averageTimeSpent numeric," +
//                    "winPercentage numeric," +
//                    "moveCountAverage numeric," +
//                    "CONSTRAINT fk_game_ID FOREIGN KEY(gameID) REFERENCES game_session(gameID)," +
//                    "CONSTRAINT fk_name_ID" +
//                    "   FOREIGN KEY(nameID)" +
//                    "       REFERENCES player(nameID)" +
//                    ");");
//
//            statement.execute("CREATE TABLE GlobalStatistics(" +
//                    "gameID numeric PRIMARY KEY," +
//                    "averageTotal numeric," +
//                    "highestTotal numeric," +
//                    "lowestTotal numeric," +
//                    "averageDuration numeric," +
//                    "averageScore numeric," +
//                    "nameID varchar(255)," +
//                    "averageTimeSpent numeric," +
//                    "winPercentage numeric," +
//                    "moveCountAverage numeric," +
//                    "CONSTRAINT fk_game_ID FOREIGN KEY(gameID) REFERENCES game_session(gameID)," +
//                    "CONSTRAINT fk_name_ID" +
//                    "   FOREIGN KEY(nameID)" +
//                    "       REFERENCES player(nameID)" +
//                    ");");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }

//    public void putStatistics(String playerName, long startTime, long endTime, int Score, long turnDuration, int turnNumber, boolean gameOver) {
//        long totalTime = endTime - startTime;
//
//        try {
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/qwixx",
//                    "postgres",
//                    "Student_1234");
//            Statement statement = connection.createStatement();
//            statement.execute("INSERT INTO game_session (gameid, starttime, endtime) values (nextval('id_incr', " + startTime + "," + endTime + ")");
//            statement.execute("INSERT INTO player (nameid, score, totaltime, iswin, turn_duration)" + playerName + Score + totalTime + gameOver + turnDuration);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


}


