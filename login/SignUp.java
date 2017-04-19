package db.login;

import db.login.Login;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;



public class SignUp extends JFrame {
    
    private JButton jButton3;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPasswordField jPasswordField2;
    private JPasswordField jPasswordField3;
    private JTextField jTextField2;
    private JTextField jTextField3;
    
    public SignUp() {
        initComponents();
    }

    
    private void initComponents() {

        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jPasswordField2 = new JPasswordField();
        jPasswordField3 = new JPasswordField();
        jLabel6 = new JLabel();
        jButton3 = new JButton();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new Login().setVisible(true);
            }
        });
        
        jLabel3.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("Username");

        jLabel5.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("Confirm Password");

        jTextField2.setFont(new Font("Times New Roman", 0, 14)); // NOI18N
    
        jTextField3.setFont(new Font("Times New Roman", 0, 14)); // NOI18N
       
        jPasswordField2.setFont(new Font("Times New Roman", 0, 14)); // NOI18N
    
        jPasswordField3.setFont(new Font("Times New Roman", 0, 14)); // NOI18N
    
        jLabel6.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Password");

        jButton3.setText("Sign Up");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPasswordField3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPasswordField2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        setResizable(false);
        pack();
    }
    
     private Connection connect(){
        String url = "jdbc:sqlite:data.db";
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url); 
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }

    private void jButton3ActionPerformed(ActionEvent evt) {                                         
        String password = new String(jPasswordField2.getPassword());
        String password2 = new String(jPasswordField3.getPassword());
        // incomplete data
        if (jTextField2.getText().equals("") || jTextField3.getText().equals("") ||
            password.equals("") || password2.equals("")){
            JOptionPane.showMessageDialog(this, "Incomplete data", "", JOptionPane.ERROR_MESSAGE);
        }
        // password doesn't match
        else if (!password.equals(password2)){
            JOptionPane.showMessageDialog(null, "Password does not Match", "", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String sql = "INSERT INTO ACCOUNTS (name, username, password) VALUES (?, ?, ?)";
            try (Connection con = connect();
                PreparedStatement pstmt = con.prepareStatement(sql)){
                pstmt.setString(1, jTextField3.getText());
                pstmt.setString(2, jTextField2.getText());
                pstmt.setString(3, new String(jPasswordField2.getPassword()));
                pstmt.executeUpdate();
                con.close();
                pstmt.close();
            } catch (SQLException e){
                String message = e.getMessage();
                int start  = message.indexOf("(");
                int end  = message.indexOf(")");
                String errMessage = message.substring(start + 1, end);
                System.out.println(message);
                JOptionPane.showMessageDialog(null, errMessage, "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Successful Sign Up");
            this.dispose();
            new Login().setVisible(true);
        }
    }                                        

}
