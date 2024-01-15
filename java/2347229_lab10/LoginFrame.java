package com.flight.management.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JPasswordField txtPwd;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Login Form");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(65, 55, 90, 43);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(65, 123, 90, 53);
        contentPane.add(lblNewLabel_1);

        txtName = new JTextField();
        txtName.setBounds(188, 52, 205, 46);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtPwd = new JPasswordField();
        txtPwd.setBounds(188, 120, 205, 56);
        contentPane.add(txtPwd);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration?useSSL=false", "root", "kalpana123");

                    String sql = "SELECT * FROM Registerr WHERE username = ? AND password = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, txtName.getText());
                    ps.setString(2, new String(txtPwd.getPassword()));

                    // Execute query and check result
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        // Login successful
                        dispose(); // Close login frame

                        // Create an instance of FlightManagementFrame and show it
                        FlightManagementFrame flightFrame = new FlightManagementFrame();
                        flightFrame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(btnLogin, "Please enter the credentials correctly");
                    }

                    // Close resources
                    rs.close();
                    ps.close();
                    con.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogin.setBounds(59, 221, 85, 32);
        contentPane.add(btnLogin);

        JButton btnClear = new JButton("CLEAR");
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClear.setBounds(198, 221, 85, 32);
        contentPane.add(btnClear);
    }
}
