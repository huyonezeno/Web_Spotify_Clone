package Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Login {
    protected static boolean checkPassword(String username, String domain, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối tới MySQL database
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/spotify", "root", "Hoang11204");

            // Câu lệnh SQL để kiểm tra username, domain và password
            String sqlSelect = "SELECT username, domain, password FROM email_info WHERE username = ? AND domain = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, domain);
            preparedStatement.setString(3, password);

            resultSet = preparedStatement.executeQuery();

            // Kiểm tra nếu có kết quả trả về tức là đăng nhập thành công
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public static void CheckLogin() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        // login with email
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String userPassword = sc.nextLine();
        sc.close();

        String emailUsername = email.split("@")[0];
        String emailDomain = email.split("@")[1];

        if(User.checkDuplicate(emailUsername, emailDomain)) {
            if (checkPassword(emailUsername, emailDomain, userPassword)) {
                System.out.println("Login successfully");
            } else {
                System.out.println("Login failed");
            }
        } else {
            System.out.println("Email not found");
        }
        sc.close();
    }
}
