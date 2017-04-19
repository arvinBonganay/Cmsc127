package db.login;


import db.admin.Admin;
import db.user.User;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JTextField usernameTextField;
    
    public Login() {
        initComponents();
    }
    
    private void initComponents() {

        loginButton = new JButton();
        signUpButton = new JButton();
        usernameTextField = new JTextField();
        jLabel1 = new JLabel();
        passwordField = new JPasswordField();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("Log in");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        signUpButton.setText("Sign Up");

        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); 
        jLabel1.setText("Password");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Username");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 123, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                            .addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
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
    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // log in
        String userName1 = usernameTextField.getText();
        String password1 = new String(passwordField.getPassword());

        String sql = "Select userid, username, password From accounts Where username == ? AND password == ?";
        try (Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userName1);
            pstmt.setString(2, password1);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String userName2 = rs.getString("username");
                String password2 = rs.getString("password");
                int userId = rs.getInt("userid");
                if (userName2.equals(userName1) && password1.equals(password2)){
                    // log in succesful
                    JOptionPane.showMessageDialog(null, "Log In successful");
                    if (userName2.equals("admin")){
                        this.dispose();
                        new Admin().setVisible(true);
                    } else {
                        this.dispose();
                        new User(userName2, userId).setVisible(true);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Account does not exist", "", JOptionPane.ERROR_MESSAGE);
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }                                           


    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // open sign up window
        this.setVisible(false);
        SignUp s = new SignUp();
        s.setVisible(true);
        usernameTextField.setText("");
        usernameTextField.setText("");
        passwordField.setText("");
        passwordField.setText("");
        
        
    }                                            
}
