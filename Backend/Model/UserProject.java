package Model;

import java.time.LocalDateTime;

public class UserProject {
    private String logon;
    private String password;
    private char sex;
    private String email;
    private String name;
    private LocalDateTime birthDate;

    // Constructor
    public UserProject(String logon, String password, char sex, String email, String name, LocalDateTime birthDate) {
        this.logon = logon;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    // Getters and setters
    public String getLogon() {
        return logon;
    }

    public void setLogon(String logon) {
        this.logon = logon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserProject{" +
                "logon='" + logon + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
