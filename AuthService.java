public interface AuthService {

    String getNickandLoginPass(String login, String password);
    boolean registration(String login, String password, String nick);
    boolean changNick(String backNick, String newNick);
}
