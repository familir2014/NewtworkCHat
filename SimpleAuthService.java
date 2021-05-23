import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService{

    private class UserData{
        String login;
        String password;
        String nick;

        public UserData(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
    private List<UserData> users =new  ArrayList<>();

    public SimpleAuthService() {
        users = new ArrayList<>();
        users.add(new UserData("eee", "eee", "eee"));
        users.add(new UserData("rrr", "rrr", "rrr"));
        users.add(new UserData("ttt", "ttt", "ttt"));
        int i;
        for (i = 0; i < 10; i++) ;
        users.add(new UserData("Login" + i, "Pass" + i, "Nick" + i));

    }

    @Override
    public String getNickandLoginPass(String login, String password) {
        for (UserData u: users){
            if (u.login.equals(login) && u.login.equals(password)){
                return u.nick;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nick) {
        return false;
    }
}

