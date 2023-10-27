import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyFrame extends JFrame implements ActionListener {

    JButton izbrishiKniga;
    JPanel panel = new JPanel();
    JScrollPane sp;
    JTable j;
    JLabel labelIme,labelAvtor,labelGodina,labelIzdavatel,labelCena,labelInvBroj;
    JTextField fieldIme, fieldAvtor,fieldGodina,fieldIzdavatel,fieldCena,fieldInvBroj;
    JButton btn = new JButton("Vnesi kniga");
    String[] columnNames = {"Inventaren Broj", "Ime","Avtor","Izdavatel","Godina na izdavanje","Cena"};
    DefaultTableModel tableModel;
    DriverConnectivity dc;
    Connection con;
    public MyFrame() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knigi","myuser","19304045");
        this.dc= new DriverConnectivity(con);
        labelIme = new JLabel("Ime");
        labelAvtor = new JLabel("Avtor");
        labelGodina = new JLabel("Godina");
        labelIzdavatel = new JLabel("Izdavatel");
        labelCena = new JLabel("Cena");
        labelInvBroj = new JLabel("Inventaren Broj");

        fieldIme = new JTextField();
        fieldAvtor = new JTextField();
        fieldGodina = new JTextField();
        fieldIzdavatel = new JTextField();
        fieldCena = new JTextField();
        fieldInvBroj = new JTextField();

        izbrishiKniga = new JButton("Izbrishi kniga");
        izbrishiKniga.setBounds(500,140,130,30);
        izbrishiKniga.setFocusable(false);
        izbrishiKniga.addActionListener(this);
        panel.add(izbrishiKniga);

        btn.setBounds(350,140,130,30);
        btn.setFocusable(false);
        btn.addActionListener(this);
        panel.add(btn);

        labelIme.setBounds(50,80,100,20);
        labelAvtor.setBounds(175,80,100,20);
        labelGodina.setBounds(290,80,100,20);
        labelIzdavatel.setBounds(350,80,100,20);
        labelCena.setBounds(475,80,100,20);
        labelInvBroj.setBounds(550,80,100,20);

        fieldIme.setBounds(50,100,100,20);
        fieldAvtor.setBounds(175,100,100,20);
        fieldGodina.setBounds(290,100,50,20);
        fieldIzdavatel.setBounds(350,100,100,20);
        fieldCena.setBounds(475,100,50,20);
        fieldInvBroj.setBounds(550,100,100,20);

        tableModel = new DefaultTableModel(dc.getBooks(),columnNames);
        j =new JTable(tableModel){
            public boolean editCellAt(int row,int colmun,java.util.EventObject e){
                return false;
            }
            public boolean isCellEditable(int row, int colum){
                return false;
            }

        };
        j.getTableHeader().setReorderingAllowed(false);
        j.setAutoResizeMode(5);
        j.setBounds(25,190,650,200);;
        j.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sp = new JScrollPane(j);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBounds(25,190,650,200);
        panel.add(sp);
        panel.add(fieldIme);
        panel.add(fieldAvtor);
        panel.add(fieldGodina);
        panel.add(fieldIzdavatel);
        panel.add(fieldCena);
        panel.add(fieldInvBroj);

        panel.add(labelIme);
        panel.add(labelAvtor);
        panel.add(labelGodina);
        panel.add(labelIzdavatel);
        panel.add(labelCena);
        panel.add(labelInvBroj);
        panel.setLayout(null);
        panel.setSize(this.getSize());
        this.add(panel);
        this.setSize(700,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btn)){
            try {
                if(fieldInvBroj.getText().trim().equals("") || fieldIme.getText().trim().equals("")||fieldAvtor.getText().trim().equals("") ||fieldIzdavatel.getText().trim().equals("") || fieldGodina.getText().trim().equals("")||fieldCena.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(this,"GRESHEN VNES\nPROVERETE ZA PRAZNI POLINJA ","ERROR",JOptionPane.INFORMATION_MESSAGE);

                }else {
                    dc.insertBook(new Books(Integer.parseInt(fieldInvBroj.getText()), fieldIme.getText(), fieldAvtor.getText(), fieldIzdavatel.getText(), fieldGodina.getText(), Integer.parseInt(fieldCena.getText())));
                    tableModel.setDataVector(dc.getBooks(), columnNames);
                    j.repaint();
                    fieldAvtor.setText("");
                    fieldIme.setText("");
                    fieldGodina.setText("");
                    fieldIzdavatel.setText("");
                    fieldCena.setText("");
                    fieldInvBroj.setText("");
                }
            }catch (SQLException err){
                JOptionPane.showMessageDialog(this,"PROBAVTE DA VNESITE VEKJE POSTOECHKA KNIGA","ERROR",JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if(e.getSource().equals(izbrishiKniga)){
            try {
                int broj = Integer.parseInt(JOptionPane.showInputDialog(this,"Vnesi inventaren broj na knigata",null));
                String message = dc.getBook(broj);
                int result = JOptionPane.showConfirmDialog(null, "DOKOLKU PRITISNETE YES NA PROZORECOT KNIGATA KJE BIDE IZBRISHANA OD BAZATA NA PODATOCI");
                if(result ==JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(this,message,"USPESHNA OPERACIJA",JOptionPane.INFORMATION_MESSAGE);
                    dc.deleteBook(broj);
                    tableModel.setDataVector(dc.getBooks(), columnNames);
                    j.repaint();
                }


            }catch(SQLException s){
                System.out.println(s);
                JOptionPane.showMessageDialog(this,"NEPOSTOECHKA KNIGA","ERROR",JOptionPane.INFORMATION_MESSAGE);
            }



        }
    }
}
