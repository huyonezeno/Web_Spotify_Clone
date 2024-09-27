package Model;
import java.util.Date;

public class User extends ModelBase {
    public String Name;
    public String Email;
    public Date BirthDate;
    public boolean ShareInfo;
    protected String Password;
    protected String State;

    public User(String name, String email, Date birthDate, boolean shareInfo, String password, String state) {
        Name = name;
        Email = email;
        BirthDate = birthDate;
        ShareInfo = shareInfo;
        Password = password;
        State = state;
    }
    public void setPassword() {
        // Get password
        this.Password = Password;
    }
    public void getPassword(String password) {
        // Set password
        this.Password = password;
    }
    public void Isauthenticated() {
        // Check if user is authenticated
    }
}
