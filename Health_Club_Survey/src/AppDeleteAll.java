import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppDeleteAll {
    public void DeleteAllFunction(Statement statement, Scanner scanner) {
        // 커맨드 받기.
        String name;
        String password;

        while(true){
            System.out.print("- 이름을 입력해주세요 : ");
            name = scanner.next();
            System.out.print("- 비밀번호를 입력해주세요 : ");
            password = scanner.next();
            System.out.println();

            if(name.equals("관리자")&&password.equals("password6")){
                break;
            }else if(!name.equals("관리자")){
                System.out.println("전체삭제는 관리자만 가능합니다! 개인 설문내역 삭제는 D 메뉴를 이용해주세요. :) ");
            }else{
                System.out.println("관리자정보가 일치하지 않습니다. 다시 시도해주세요.");
            }
        }


        if (name.equals("관리자") && password.equals("password6")) {

            String query = "SELECT * FROM user WHERE NAME = '" + name + "' AND PASSWORD = '" + password + "';";

            try {

                ResultSet resultSet = statement.executeQuery(query);
                // ResultSet resultSet2 = statement.executeQuery(query);

                if (resultSet.isBeforeFirst()) {

                    System.out.println("\n=== 로그인에 성공했습니다. ===\n");
                    System.out.print("전체설문내역을 삭제하시겠습니까? [Y/N] : ");
                    String yORn = scanner.next();
                    System.out.println();
                    boolean flag = true;
                    while (flag) {

                        if (yORn.equals("Y")) {
                            query = "DELETE FROM survey;";
                            statement.execute(query);

                            query = "SELECT * FROM survey;";
                            ResultSet rst = statement.executeQuery(query);

                            if (!rst.isBeforeFirst()) {

                                System.out.println("설문내역이 전체삭제되었습니다.");
                                flag = false;
                            }
                        } else if (yORn.equals("N")) {
                            System.out.println("메인으로 돌아갑니다.");
                            flag = false;
                        } else {
                            System.out.println("다시 입력해주세요.");
                            System.out.print("개인설문내역을 삭제하시겠습니까? [Y/N] : ");
                            yORn = scanner.next();                           
                        }
                    }
                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        //버퍼 제거 ㅠ
        System.out.println();
        scanner.nextLine();
    }

}

// if(!str1.equals("AA")){
// System.out.println("AA가 아닙니다");
// }