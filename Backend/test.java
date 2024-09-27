import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class test {

    // Hàm kết nối tới MySQL và chèn dữ liệu vào bảng email_info
    public static void insertIntoUser(String username, String domain, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Kết nối tới MySQL database, schema 'spotify'
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/spotify", "root", "Hoang11204");

            if (connection != null) {
                System.out.println("Connected to MySQL database 'Spotify'");

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
            else {
                System.out.println("not conne");
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

    // Hàm kiểm tra Mat khau
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

    // Hàm kiểm tra định dạng email
    public static boolean isEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    // Hàm đăng ký tài khoản mới
    public static void SignUp() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        // sign up with email
        System.out.println("Sign up with email");
        String Email = sc.nextLine();
        System.out.print("Create your password: ");
        String password = sc.nextLine();

        System.out.println("Tell us about yourself");
        System.out.print("Name: ");
        String name = sc.nextLine();
        
        System.out.print("Date of birth: ");
        int DayOfMonth = sc.nextInt();
        int Month = sc.nextInt();
        int Year = sc.nextInt();

        //format lại dob
        String DateOfBirth = Year + "-" + Month + "-" + DayOfMonth;
        Date BirthDate = Date.valueOf(DateOfBirth);

        if(isEmail(Email)){
            String username = Email.split("@")[0];
            String domain = Email.split("@")[1];
            if (!checkDuplicate(username, domain)) {
                insertIntoUser(username, domain, password);
                System.out.println("Account created successfully");
            } else {
                System.out.println("Email already linked to an account");
            }
        } else {
            System.out.println("Invalid email");
        }
        sc.close();
    }

    public static void Login() {

        java.util.Scanner sc = new java.util.Scanner(System.in);
        // login with email
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String userPassword = sc.nextLine();
        sc.close();

        String emailUsername = email.split("@")[0];
        String emailDomain = email.split("@")[1];

        if(checkDuplicate(emailUsername, emailDomain)) {
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

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("Welcome to Spotify. Choose an option: ");
        System.out.println("1. Sign up");
        System.out.println("2. Login");

        int option = sc.nextInt();
        
        if (option == 1) {
            SignUp();
        }
        else if (option == 2) {
            sc.nextLine();
        } else {
            System.out.println("Invalid option");
        }

        // insertIntoUser("Hoangtv", "gmail.com", "123456");

        sc.close();
    }
}
