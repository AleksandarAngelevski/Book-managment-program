

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Login extends JFrame implements ActionListener {
    Connection con;
    JButton login = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField username, password;
    JLabel user,psw;
    public Login(){
       con =con;
        login.addActionListener(this);
        login.setBounds(75,180,150,30);
        panel.add(login);

        user = new JLabel("Username");
        psw = new JLabel("Password");
        user.setBounds(75,50,150,30);
        psw.setBounds(75,110,150,30);
        panel.add(user);
        panel.add(psw);

        username = new JTextField();
        password = new JPasswordField();
        username.setBounds(75,80,150,30);
        password.setBounds(75,140,150,30);
        panel.add(username);
        panel.add(password);

        panel.setSize(this.getSize());
        this.add(panel);

        panel.setLayout(null);
        this.setSize(300,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(login)){
            String usr = username.getText();
            String psw = password.getText();
            Registration reg =new Registration();
            try {
                reg.checkPassword(usr,psw,this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
