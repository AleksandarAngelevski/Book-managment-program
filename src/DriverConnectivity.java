import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverConnectivity {
    private  String userName = "";
    private  String password = "";
    private  String url = "";
    private Connection con;
    public DriverConnectivity( Connection con) throws  Exception{
        this.con = con;
    }
    public String[][] getBooks() throws SQLException{
        ArrayList<List<String>> s =new ArrayList<List<String>>();
        Statement st = con.createStatement();
        String query = "select * from knigi";
        ResultSet rs = st.executeQuery(query);
        String[][] sarr = new String[0][0];

        while (rs.next()){
            String invenBr = String.valueOf(rs.getInt("inventarenBroj"));
            String ime = rs.getString("Ime");
            String avtor = rs.getString("Avtor");
            String izd = rs.getString("Izdavatel");
            String godIzd = rs.getString("GodinaNaIzdavanje");
            String cena = String.valueOf(rs.getInt("Cena"));
            s.add(List.of(invenBr,ime,avtor,izd,godIzd,cena));
        }
        sarr = s.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return sarr;




    }
    public String  getBook(int broj) throws SQLException {
            Statement st = con.createStatement();
            String query = "select * from knigi where inventarenBroj = "+broj+"";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String ime = rs.getString("Ime");
            String avtor = rs.getString("Avtor");
            int invBroj = rs.getInt("inventarenBroj");
            return "Izbrana e knigata " +ime+" od "+avtor+" so broj:"+invBroj;
    }
    public void insertBook(Books book) throws SQLException{
        String query = "insert into knigi values ("+book.getInventarenBroj()+",'"+book.getIme()+"','"+book.getAvtor()+"','"+book.getIzdavatel()+"','"+book.getGodinaNaIzdavanje()+"',"+book.getCena()+");";
        PreparedStatement st = con.prepareStatement(query);
        int count = st.executeUpdate(query);
        System.out.println(count+ " row/s affected");
    }
    public void deleteBook(int broj)throws SQLException{
        Statement st = con.createStatement();
        String query = "delete from knigi where inventarenBroj = "+broj+";";
        int count  = st.executeUpdate(query);
        if(count==0){
            throw new SQLException();
        }
    }

}
