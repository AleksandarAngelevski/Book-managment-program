import javax.swing.*;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class Registration {
    public Registration(){

    }

    public void checkPassword(String username, String password, Login ova)throws Exception{
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knigi","myuser","19304045");
            Statement st = con.createStatement();
            String query = "select * from userID where username = '"+username+"';";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String passw = rs.getString("hashedPassword");
            String salt = rs.getString("salt");
            if(hashPassword(password,salt).equals(passw)){
                new MyFrame();
                System.out.println("AAAA");
                ova.dispose();

            }else {
                System.out.println("Wrong password");
            }
            con.close();
        }
        catch (SQLException s){
            System.out.println("INVALID USERNAME");
        }
    }
    private String hashPassword(String psw,String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest((psw+salt).getBytes());
        BigInteger no = new BigInteger(1,messageDigest);
        String hashPsw = no.toString(16);
        while (hashPsw.length()<32){
            hashPsw ="0"+hashPsw;
        }

        return hashPsw;
    }
    private String getSalt() {
        byte[] salt =new byte[16];
        Random rand = new SecureRandom();
        rand.nextBytes(salt);
        return Arrays.toString(salt);
    }
    public void storeInformation(String username,String psw) throws Exception{
        String stringSalt = getSalt();
        String password = hashPassword(psw,stringSalt);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knigi","myuser","19304045");
        Statement st = con.createStatement();
        DriverConnectivity dc = new DriverConnectivity(con);
        String query = "insert into userID values('"+username+"','"+password+"','"+stringSalt+"');";
        int count =st.executeUpdate(query);
        con.close();
    }
}