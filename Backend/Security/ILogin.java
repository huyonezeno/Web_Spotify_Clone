package Security;

import Model.User;
import Model.UserProjection;
import Model.UserProject;

public interface ILogin {
    Token login(UserProjection user);  // Điều chỉnh để dùng lớp User thay cho UserProjection
    boolean logout(String logon);
    User createUser(UserProject user);  // Điều chỉnh để nhận đối tượng User
}
