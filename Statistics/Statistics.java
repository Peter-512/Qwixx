package Statistics;

import App.src.model.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class Statistics {
    //TODO: gameid (gamesession), playername (nameid) (Player), score (Player), avg_turnduration, turnduration, longest/shortest turn, penalty_points (scorecard), amount penalty,
    //TODO: most vs least value points, isCrossed


    //GLOBAL:   avg
    //SESSION:
    public Statistics() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/qwixx",
                    "postgres",
                    "Student_1234");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS game_session " +
                    "(gameID numeric PRIMARY KEY CONSTRAINT nn_gameID NOT NULL, " +
                    "startTime timestamp(3) CONSTRAINT nn_startTime NOT NULL, " +
                    "endTime timestamp(3) CONSTRAINT nn_endTime NOT NULL);"); // creates table
            statement.execute("create sequence if not exists id_incr start 1;"); //CREATE SEQUENCE FOR game_id
            // statement.execute("insert into INT_leaderboard values (nextval('id_incr'),'Debil',5,date_trunc('second', now()));");
            //statement.execute("create sequence if not exists rank start with 1;");
            //statement.execute("alter sequence rank restart with 1;");

            statement.execute("CREATE TABLE IF NOT EXISTS player(" +
                    "gameID numeric," +
                    "CONSTRAINT fk_game_ID FOREIGN KEY (gameID) REFERENCES game_session(gameID),"+
                    "nameID varchar(255) PRIMARY KEY," +
                    "score numeric CONSTRAINT nn_score NOT NULL," +
                    "totalTime timestamp(3) CONSTRAINT nn_totalTime NOT NULL," +
                    "isWin boolean" +
                    ");");

            statement.execute("CREATE TABLE statistics(" +
                    "gameID numeric PRIMARY KEY," +
                    "averageDuration numeric," +
                    "averageScore numeric," +
                    "nameID varchar(255)," +
                    "averageTimeSpent numeric," +
                    "winPercentage numeric," +
                    "moveCountAverage numeric," +
                    "CONSTRAINT fk_game_ID FOREIGN KEY(gameID) REFERENCES game_session(gameID)," +
                    "CONSTRAINT fk_name_ID" +
                    "   FOREIGN KEY(nameID)" +
                    "       REFERENCES player(nameID)" +
                    ");");

            statement.execute("CREATE TABLE globalStatistics(" +
                    "gameID numeric PRIMARY KEY," +
                    "averageTotal numeric,"+
                    "highestTotal numeric,"+
                    "lowestTotal numeric,"+
                    "averageDuration numeric," +
                    "averageScore numeric," +
                    "nameID varchar(255)," +
                    "averageTimeSpent numeric," +
                    "winPercentage numeric," +
                    "moveCountAverage numeric," +
                    "CONSTRAINT fk_game_ID FOREIGN KEY(gameID) REFERENCES game_session(gameID)," +
                    "CONSTRAINT fk_name_ID" +
                    "   FOREIGN KEY(nameID)" +
                    "       REFERENCES player(nameID)" +
                    ");");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }

    public void putStatistics(String playerName, long startTime, long endTime, int Score, long turnDuration, int turnNumber, boolean isWin){
        long totalTime = endTime-startTime;

        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/qwixx",
                    "postgres",
                    "Student_1234");
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO game_session (gameid, starttime, endtime) values (nextval('id_incr', "+startTime+","+endTime+")");
            statement.execute("INSERT INTO player (nameid, score, totaltime, iswin, turn_duration)" + playerName + Score + totalTime + isWin + turnDuration);

        } catch (SQLException e){
            e.printStackTrace();
        }



    }

}

//    GameSession;
//            NumberField; //isCrossed
//            Player;
//            Row; //maybe
//            Score;
//            ScoreCard;
//            Turn;
//
//
//            }
