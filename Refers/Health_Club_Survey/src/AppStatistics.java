import java.sql.*;
import java.util.Scanner;


//Statement == sql쿼리문을 보내기위한 객체
public class AppStatistics {
    public void statisticsFunction(Statement statement, Scanner scanner) {
        
        ResultSet resultSet = null;
        String query = "SELECT QUESTION_ID, SUM(ANSWER_ID = 'ANSWER1') AS '(1)매우만족', SUM(ANSWER_ID = 'ANSWER2') AS '(2)만족'," +
         "SUM(ANSWER_ID = 'ANSWER3') AS '(3)보통', SUM(ANSWER_ID = 'ANSWER4') AS '(4)불만', SUM(ANSWER_ID = 'ANSWER5') AS '(5)매우불만'"+
         "FROM survey GROUP BY QUESTION_ID;";
               
        try {

            resultSet = statement.executeQuery(query);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("설문 내역 통계를 조회합니다.");
            System.out.println("-------------------------------------------------------------------------------------\n");

            System.out.println("   \t  (1)매우만족\t    (2)만족\t    (3)보통\t    (4)불만\t  (5)매우불만\t");
            int i = 1;             
            while(resultSet.next()){
                //System.out.print(resultSet.getString("QUESTION_ID") + "\t\t");
                System.out.print("문항"+i+"\t\t");
                System.out.print(resultSet.getString("(1)매우만족") + "\t\t");
                System.out.print(resultSet.getString("(2)만족") + "\t\t");
                System.out.print(resultSet.getString("(3)보통") + "\t\t");
                System.out.print(resultSet.getString("(4)불만") + "\t\t");
                System.out.print(resultSet.getString("(5)매우불만") + "\t\t");
                System.out.println();
                i++;
            }
            System.out.print("\n-------------------------------------------------------------------------------------\n"
            + "통계 조회가 완료되었습니다.\n"
            + "-------------------------------------------------------------------------------------\n\n");

            
        } catch (SQLException e) {
           System.out.println("조회 실패!");
            e.printStackTrace();
        }
    }
}