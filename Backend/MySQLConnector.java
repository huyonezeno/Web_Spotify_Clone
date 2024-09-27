import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MySQLConnector {

    // Hàm kết nối tới MySQL và chèn dữ liệu vào bảng email_info
    public static void insertIntoEmailInfo(String username, String domain, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Kết nối tới MySQL database, schema 'spotify'
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/Spotify", "root", "Hoang11204");

            if (connection != null) {
                System.out.println("Connected to MySQL database 'spotify'");

                // Câu lệnh SQL để chèn dữ liệu vào bảng email_info
                String sqlInsert = "INSERT INTO email_info (username, domain, password) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, domain);
                preparedStatement.setString(3, password);

                // Thực thi câu lệnh
                preparedStatement.executeUpdate();
                System.out.println("Record inserted successfully into email_info");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm kiểm tra email đã tồn tại trong database chưa
    public static boolean checkDuplicate(String username, String domain) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối tới MySQL database
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/spotify", "root", "Hoang11204");

            // Câu lệnh SQL để kiểm tra username và domain
            String sqlSelect = "SELECT username, domain FROM email_info WHERE username = ? AND domain = ?";
            preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, domain);

            resultSet = preparedStatement.executeQuery();

            // Kiểm tra xem có bản ghi nào trả về hay không
            if (resultSet.next()) {
                return true; // Email đã tồn tại
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

    // Hàm kiểm tra đăng nhập
    public static boolean checkPassword(String username, String domain, String password) {
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

    public static void main(String[] args) {
        // Chèn dữ liệu vào bảng email_info nếu không trùng lặp
        java.util.Scanner sc = new Scanner(System.in);

        String username = sc.nextLine();
        String domain = username.split("@")[0];
        String password = sc.nextLine();

        if (!checkDuplicate(username, domain)) {
            insertIntoEmailInfo(username, domain, password);
        } else {
            System.out.println("Duplicate email");
        }

        // Kiểm tra đăng nhập
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();


        if(checkDuplicate(username, domain)) {
            System.out.print("Enter your password: ");
            String userPassword = scanner.nextLine();
            scanner.close();

            String emailUsername = email.split("@")[0];
            String emailDomain = email.split("@")[1];

            if (checkPassword(emailUsername, emailDomain, userPassword)) {
                System.out.println("Login successfully");
            } else {
                System.out.println("Login failed");
            }
        }
        else {
            System.out.println("not in database");
        }
        sc.close();
    }
}
