import java.sql.*;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // - MySQL workbench 실행 : JDBC
        // - User/password와 접속 IP:port 접속
        String url = "jdbc:mysql://localhost:3306/health_club";
        String user = "root";
        String password = "*khacademy!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");
            // - query Edit
            Statement statement = connection.createStatement();

            System.out.println("홀리몰리 헬스장 만족도 설문조사\n");
            String input;
            do {
                System.out.print("----------------------------------------------------------------------------------\n"
                        + "P. 설문시작 | S. 통계 | D. 삭제 | C. 회원중복확인 | SR. 검색 | DA. 전체삭제 | Q. 종료\n"
                        + "----------------------------------------------------------------------------------\n"
                        + "선택 : ");
                input = scanner.nextLine();
                switch (input) {
                    case "P":
                        new AppStart().startFunction(statement, scanner, connection);
                        break;
                    case "S":
                        new AppStatistics().statisticsFunction(statement, scanner);
                        break;
                    case "Q":
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    case "D":
                        new AppDelete().DeleteFunction(statement, scanner);
                        break;
                    case "C":
                        new AppCheck().checkFuction(statement, scanner);
                        break;
                    case "SR":

                        break;
                    case "DA":
                        new AppDeleteAll().DeleteAllFunction(statement, scanner);
                        break;
                    default:
                        System.out.println("\n다시 선택하여 주십시오.");
                        break;
                }
            } while (!input.equals("Q"));
        } catch (SQLException exception) {
            System.out.println("DB접속 실패");
            exception.printStackTrace();
        }
    }
}
