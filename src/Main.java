import java.awt.print.Book;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knigi","myuser","19304045");
        DriverConnectivity dc = new DriverConnectivity(con);
//        boolean cond = true;
//        while (cond) {
//            System.out.println("1.Prebaraj Kniga\n2.Vnesi Kniga\n3.'D' ZA IZLEZ");
//            Scanner sc = new Scanner(System.in);
//            String input = sc.nextLine().toLowerCase();
//
//            if(input.equals("1")){
//                prebaraj(dc);
//            }if(input.equals("2")){
//                dc.insertBook(kreirajKniga());
//            }if(input.equals("d")){
//                cond =false;
//            }
//        }
//
        Registration reg = new Registration();
        Login log = new Login();
        con.close();
    }

    public static Books kreirajKniga(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Vnesi podatoci za knigata");
        String ime,avtor,izdavatel,godinaNaIzd;
        int invBr,cena;
        System.out.println("Ime na knigata:");
        ime = sc.nextLine();
        System.out.println("Avtor:");
        avtor = sc.nextLine();
        System.out.println("Izdavatel:");
        izdavatel = sc.nextLine();
        System.out.println("Godina na izdavanje");
        godinaNaIzd = sc.nextLine();
        System.out.println("Cena (VO MKD)");
        cena = sc.nextInt();
        System.out.println("Inventaren broj");
        invBr = sc.nextInt();
        Books book = new Books(invBr,ime,avtor,izdavatel,godinaNaIzd,cena);
        System.out.println(book);
        return book;

    }

}