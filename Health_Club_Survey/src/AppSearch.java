import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AppSearch {
    public void SearchFunction(Statement statement,Scanner scanner) {
    
        System.out.println("설문내역 확인을 위한 이름과 비밀번호를 입력해주세요.");
        System.out.println();
        System.out.print("- 이름을 입력해주세요 : ");
        String name = scanner.next();
        System.out.print("- 비밀번호를 입력해주세요 : ");
        String password = scanner.next();
        System.out.println();


        String query = "SELECT * FROM user WHERE NAME = '" + name + "' AND PASSWORD = '" + password + "';";
        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.isBeforeFirst()) {
                System.out.println("\n=== 로그인에 성공했습니다. ===\n");
                if (name.equals("관리자") && (password.equals("password6"))) {
                    System.out.println("전체설문내역을 출력합니다.\n");
                    System.out.println("-------------------------------------------------------------------------------------\n");
                    String[] userName = new String[]{"홍길동", "박보검","강형욱","오은영","강형욱"};
                    String[] userID = new String[]{"USER1", "USER2","USER3","USER4","USER5"};

                    for(int i=0; i<userName.length; i++){
                        query = "SELECT user.NAME, user.USER_ID, answer.ANSWER, survey.QUESTION_ID, question.QUESTION FROM survey " +
                    " INNER JOIN user on user.USER_ID = survey.USER_ID INNER JOIN question ON question.QUESTION_ID =" + 
                    "survey.QUESTION_ID INNER JOIN answer ON answer.ANSWER_ID = survey.ANSWER_ID WHERE NAME = '"+userName[i] +"' AND survey.USER_ID = '"+ userID[i] +"';";
                    
                    ResultSet resultSet1 = statement.executeQuery(query);
                    
                    if (resultSet1.isBeforeFirst()) {
                        System.out.print(userName[i] + "님의 설문내역을 출력합니다." + "\n");
                        System.out.println("-------------------------------------------------------------------------------------");
                        while (resultSet1.next()) {
                            System.out.print("설문내용 : " + resultSet1.getString("question.QUESTION") + "\n");
                            System.out.print("답항 : " + resultSet1.getString("answer.ANSWER"));
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------------------\n");
                    }
                    }
                } else if (!name.equals("관리자") && (!password.equals("password6"))) {

                   
                    System.out.print(name + "님의 설문내역을 출력합니다." + "\n");
                    System.out.println("-------------------------------------------------------------------------------------");
                    query = "SELECT user.NAME, user.USER_ID, answer.ANSWER, survey.QUESTION_ID, question.QUESTION FROM survey " +
                     " INNER JOIN user on user.USER_ID = survey.USER_ID INNER JOIN question ON question.QUESTION_ID = survey.QUESTION_ID INNER JOIN answer ON answer.ANSWER_ID = survey.ANSWER_ID WHERE NAME = '"+name +"';";

                    ResultSet resultSet2 = statement.executeQuery(query);
                    if (resultSet2.isBeforeFirst()) {
                        System.out.println(name + "님의 설문내역입니다." + "\n");
                        while (resultSet2.next()) {                           
                            System.out.print("설문내용 :" + resultSet2.getString("question.QUESTION") + "\n");
                            System.out.print("답항 : " + resultSet2.getString("answer.ANSWER")+"\n");
                            System.out.println("");
                        }
                    }
                    else {
                        System.out.println("참여하신 설문이 없습니다.");
                    }
                } 
            } else {
                System.out.println("회원정보가 일치하지 않습니다. 메인으로 돌아갑니다.");

            }
            System.out.println();
            //버퍼 제거 ㅠ
            scanner.nextLine();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
