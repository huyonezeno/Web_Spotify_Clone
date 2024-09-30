package Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class User {
    public String Name;
    public String Email;
    public Date BirthDate;
    public boolean ShareInfo;
    protected String Password;
    protected String State;

    public User(String name, String email, Date birthDate, boolean shareInfo, String password, String state) {
        this.Name = name;
        this.Email = email;
        this.BirthDate = birthDate;
        this.ShareInfo = shareInfo;
        this.Password = password;
        this.State = state;
    }
    
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

    public void setPassword() {
        // Get password
        this.Password = Password;
    }
    
    public void getPassword(String password) {
        // Set password
        this.Password = password;
    }

    public void setState() {
        // Set state
        this.State = State;
    }
    
    public String getState() {
        // Get state
        return State;   
    }
    
    public Date getBirthDate(int day, int month, int year) {
        // Get birth date
        String date = year + "-" + month + "-" + day;
        Date birthDate = Date.valueOf(date);
        return birthDate;
    }
    
    public void setState(String state) {
        // Set state
        this.State = state;
    }
    
    public String getDomain() {
        return Email.split("@")[1];
    }
    
    public String getEmailName() {
        return Email.split("@")[0];
    }
    
    public static boolean isEmail(String email) {
        return email.contains("@") && email.contains(".") && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static void insertToDatabaseUser(String username, String domain, String password) {
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
}
