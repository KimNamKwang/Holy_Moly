import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AppDelete {
    
    public void DeleteFunction(Statement statement, Scanner scanner) {
        // 커맨드 받기.

        String query;
        ResultSet resultSet;
        String name;
        String password;
        while(true){
            System.out.print("- 이름을 입력해주세요 : ");
            name = scanner.next();
            System.out.print("- 비밀번호를 입력해주세요 : ");
            password = scanner.next();
            query = "SELECT * FROM user WHERE NAME = '" + name + "' AND PASSWORD = '" + password + "';";

            try {
                resultSet = statement.executeQuery(query);
                if(!resultSet.isBeforeFirst()){
                    System.out.println("\n-------------------------------------------------------------------------------------"+
                    "회원정보가 일치하지 않습니다, 다시 시도해주세요."
                    +"-------------------------------------------------------------------------------------\n");
                }
                else{
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        try {

            // ResultSet resultSet2 = statement.executeQuery(query);

            if (resultSet.isBeforeFirst()) {

                System.out.println("\n=== 로그인에 성공했습니다. ===\n");
                System.out.print("개인설문내역을 삭제하시겠습니까? [Y/N] : ");
                String yORn = scanner.next();
                System.out.println();
                boolean flag = true;

                while (flag) {

                    if (yORn.equals("Y")) {

                        query = "DELETE FROM survey WHERE USER_ID IN (SELECT USER_ID FROM user WHERE NAME = '" + name
                                + "'AND password ='" + password + "');";
                        statement.execute(query);

                        query = "SELECT * FROM survey WHERE USER_ID IN (SELECT USER_ID FROM user WHERE NAME = '" + name
                                + "')";
                        ResultSet rst = statement.executeQuery(query);
                        // Scanner로 입력받은 USER_NAME과 USER_ID를 어떻게 엮을지 고민.-> 했으나 극뽁-! ->한 줄 알았는데 FAIL->했으나
                        // 실패는 성공의 오까상

                        if (!rst.isBeforeFirst()) {

                            System.out.println(name + "님의 설문내역이 삭제되었습니다.");
                            flag = false;

                        }

                    } else if (yORn.equals("N")) {
                        System.out.println("\n메인으로 돌아갑니다.");
                        flag = false;
                    } else {
                        System.out.println("다시 입력해주세요.\n");
                        System.out.print("개인설문내역을 삭제하시겠습니까? [Y/N] : ");
                        yORn = scanner.next();

                    }
                }
            }
            System.out.println();
            //버퍼 제거 ㅠ
            scanner.nextLine();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
