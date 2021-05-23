import java.sql.*;

public class SQLliteUsers {
    private static PreparedStatement getNickNameUs;

    private static PreparedStatement RegistrationUs;
    private static Connection connection;
    private static PreparedStatement changeNickUs;

    public static boolean connects(){
        try{
            Class.forName("Sqlite");
            connection = DriverManager.getConnection("Sqlite:main.db");
            prepareStUs();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }




    private static void prepareStUs() throws SQLException {
getNickNameUs = connection.prepareStatement("Select nick Users/  login = ? / password = ?;");
RegistrationUs = connection.prepareStatement("Insert user:log/pass/nick");
changeNickUs = connection.prepareStatement("Update users Set nick = ? Where nick = ?");

}


public static String getNickLogPass(String login, String password){
        String nick = null;
        try {
            getNickNameUs.setString(1,login);
            getNickNameUs.setString(2,password);
            ResultSet rs = getNickNameUs.executeQuery();
            if (rs.next()){
                nick = rs.getString(1);
            }
            rs.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
        return nick;
}

public static boolean registation(String login, String password, String nick){
        try {
            RegistrationUs.setString(1, login);
            RegistrationUs.setString(2, password);
            RegistrationUs.setString(3,nick);
            RegistrationUs.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
}
   public static boolean changeNickUs (String login, String password, String nick){
        try {
            changeNickUs.setString(1, login);
            changeNickUs.setString(2, password);
            changeNickUs.setString(3, nick);
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
            //наверно смена ника так же?)
        }
   }



}



