import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppSearch {
    public void SearchFunction(Statement statement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("설문내역을 확인 하실 수 있습니다. 이름과 비밀번호를 입력해주세요.");
        System.out.print("이름을 입력해주세요 :");
        String name = scanner.next();
        System.out.print("비밀번호를 입력해주세요 :");
        String password = scanner.next();

        String query = "SELECT * FROM user WHERE NAME = '" + name + "' AND PASSWORD = '" + password + "';";
        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.isBeforeFirst()) {
                System.out.println("\n== 로그인에 성공했습니다. ==\n");
                if (name.equals("관리자") && (password.equals("password6"))) {
                    System.out.print("전체설문내역을 출력합니다.");
                    System.out.println("----------------------------------------");
                    query = "SELECT * FROM survey;";
                    ResultSet resultSet1 = statement.executeQuery(query);
                    if (resultSet1.isBeforeFirst()) {
                        while (resultSet1.next()) {
                            System.out.print("USER_ID :" + resultSet1.getString("USER_ID"));
                            System.out.print(", ANSWER_ID :" + resultSet1.getString("ANSWER_ID"));
                            System.out.print(", QUESTION_ID :" + resultSet1.getString("QUESTION_ID"));
                            System.out.println("");
                        }

                    }

                } else if (!name.equals("관리자") && (!password.equals("password6"))) {

                    System.out.print("개인설문내역을 출력합니다.");
                    System.out.println("----------------------------------------");
                    query = "SELECT * FROM survey WHERE USER_ID IN (SELECT USER_ID FROM user WHERE NAME = '" + name
                            + "');";

                    ResultSet resultSet2 = statement.executeQuery(query);
                    if (resultSet2.isBeforeFirst()) {
                        while (resultSet2.next()) {
                            System.out.print("USER_ID :" + resultSet2.getString("USER_ID"));
                            System.out.print(", ANSWER_ID :" + resultSet2.getString("ANSWER_ID"));
                            System.out.print(", QUESTION_ID :" + resultSet2.getString("QUESTION_ID"));
                            System.out.println("");
                        }
                    }
                    else {
                        System.out.println("설문내역이 존재하지 않습니다.");
                    }
                } 
            } else {
                System.out.println("회원정보가 일치하지 않습니다, 다시 시도해주세요.");

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

// 입력받은 이름으로 auth_unique_id 뽑아내는것을 ㅗ바꾸기

// SELECT AUTH_UNIQUE_ID
// FROM user_auth
// where USER_ID IN (SELECT USER_ID
// FROM user
// WHERE NAME IN ('관리자'));