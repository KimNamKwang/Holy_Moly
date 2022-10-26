import java.sql.*;
import java.util.Scanner;


public class AppStart {

    public void startFunction(Statement statement, Scanner scanner, Connection connection){
                    System.out.print("\n- 이름을 입력하세요 : ");
                    String loginName = scanner.nextLine();
                    System.out.print("- 비밀번호를 입력하세요 : ");
                    String loginPassword = scanner.nextLine();
                    // - SELECT * FROM users ;
                    String query = "SELECT * FROM user WHERE NAME = '"+loginName+"' AND PASSWORD = '"+loginPassword+"';";
                    ResultSet resultSet;
                    try {
                        resultSet = statement.executeQuery(query);
                        //ResultSet resultSet2 = statement.executeQuery(query);
                        if(resultSet.isBeforeFirst()) {
                            System.out.println("\n== 설문을 시작합니다. ==\n");
                            query = "SELECT * FROM question;";
                            resultSet = statement.executeQuery(query);
                            int num;
                            String answer = "";
                            
                            while(resultSet.next()) {
                                String QUESTION = resultSet.getString("QUESTION");
                                String QUESTIONID = resultSet.getString("QUESTION_ID");
                                System.out.println(QUESTION);
                                System.out.println("(1)매우만족 (2)만족 (3)보통 (4)불만 (5)매우불만");
                                System.out.print("답) ");
                                num = scanner.nextInt();

                                switch(num) {
                                    case 1 :
                                        answer = "(1)매우만족";
                                        break;
                                    case 2 :
                                        answer = "(2)만족";
                                        break;
                                    case 3 :
                                        answer = "(3)보통";
                                        break;
                                    case 4 :
                                        answer = "(4)불만";
                                        break;
                                    case 5 :
                                        answer = "(5)매우불만";
                                        break;
                                    default :
                                        System.out.println("잘못된 숫자입니다.");
                                }
                                // 오류 해결을 위한 새로운 Statement
                                Statement statement2 = connection.createStatement();
                                
                                // ANSWER_ID값 담기
                                query = "SELECT ANSWER_ID FROM answer WHERE ANSWER = '"+ answer +"';";
                                ResultSet resultSet2 = statement2.executeQuery(query);
                                resultSet2.next();
                                String ANSWER = resultSet2.getString("ANSWER_ID");
                                
                                // USER_ID 값 담기
                                query = "SELECT USER_ID FROM user WHERE NAME = '" + loginName + "';";
                                resultSet2 = statement2.executeQuery(query);
                                resultSet2.next();
                                String USERID = resultSet2.getString("USER_ID");

                                // INSERT 구문
                                query = "INSERT INTO survey (QUESTION_ID,ANSWER_ID,USER_ID) VALUES( '" + QUESTIONID + "', '" + ANSWER + "', '" + USERID + "' );";
                                statement2.execute(query);
                            }
                                //버퍼 제거 ㅠ
                                scanner.nextLine();
                                System.out.print("---------------------------------\n"
                                + "설문이 성공적으로 저장되었습니다.\n"
                                + "---------------------------------\n\n");
                                
                        }else {
                            System.out.println("-------------------------------\n"
                                        +"회원정보가 없습니다.\n"
                                        +"-------------------------------");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    }
    
}
