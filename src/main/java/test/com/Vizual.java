package test.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vizual extends Frame {
    JTextField txtLogin, txtPass, txtUrl;
    JButton start, refresh;
    JLabel login, password, jUrl;

    public Vizual() {
        super("AutoTestCashalot");
        setSize(420, 300);
        setLayout(null);
        setBackground(Color.lightGray);

        jUrl = new JLabel("Insert your link:");
        jUrl.setBounds(50, 50, 300, 30);
        add(jUrl);

        txtUrl = new JTextField(15);
        txtUrl.setBounds(50, 80, 300, 30);
        add(txtUrl);



        login = new JLabel("Enter your Login:");
        login.setBounds(50, 110, 300, 30);
        add(login);

        txtLogin = new JTextField(15);
        txtLogin.setBounds(50, 140, 300, 30);
        add(txtLogin);


        password = new JLabel("Enter your Password:");
        password.setBounds(50, 170, 300, 30);
        add(password);

        txtPass = new JPasswordField(15);
        txtPass.setBounds(50, 200, 300, 30);
        add(txtPass);

        start = new JButton("Start");
        start.setBounds(100, 250, 100, 30);
        add(start);
        start.addActionListener(e -> {
           /* WebDriverSettings.url = txtUrl.getText();
            WebDriverSettings.userName = txtLogin.getText();
            WebDriverSettings.userPassword = txtPass.getText();*/
        });

        refresh = new JButton("Refresh");
        refresh.setBounds(210, 250, 100, 30);
        add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUrl.setText("");
                txtLogin.setText("");
                txtPass.setText("");

            }
        });
        setVisible(true);

    }


    public static void main(String[] args) {
        new Vizual();
    }

}
