import java.sql.*;
import java.util.Scanner;

public class AppCheck {

    public void checkFuction(Statement statement, Scanner scanner){
        System.out.print("- 이름을 입력하세요 : ");
        String checkName = scanner.nextLine();
        System.out.print("- 핸드폰 번호를 입력하세요 : ");
        String checkPhoneNumber = scanner.nextLine();

        String query = "SELECT NAME,PHONE_NUMBER FROM user WHERE NAME = "+ "\"" + checkName + "\""  +" AND PHONE_NUMBER = " + "\""+ checkPhoneNumber + "\"" + ";";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);

            if(resultSet.next() == false){
                System.out.println("\n-------------------------------------------------------------------------------------");
                System.out.println("회원정보가 없습니다. 메인으로 돌아갑니다.");
                System.out.println("-------------------------------------------------------------------------------------");
            }
            else{
                System.out.println("\n-------------------------------------------------------------------------------------");
                System.out.println("이미 가입된 회원입니다.");
                System.out.println("-------------------------------------------------------------------------------------");
            }
            System.out.println(); 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
