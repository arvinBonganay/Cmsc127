package db.user;


import db.admin.AddFlight;
import db.admin.ViewFlight;
import db.login.Login;
import java.awt.event.*;
import javax.swing.*;

public class User extends JFrame {
    private JButton signOutButton;
    private JButton viewFlightButton;
    private JButton cancelFlightButton;
    private JButton bookFlightButton;
    private JLabel nameOfUser;
    private JLabel user;
    
    
    public User(String userName) {
        initComponents(userName);
        
    }

    private void initComponents(String userName) {

        nameOfUser = new javax.swing.JLabel();
        viewFlightButton = new javax.swing.JButton();
        cancelFlightButton = new javax.swing.JButton();
        bookFlightButton = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        signOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameOfUser.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        nameOfUser.setText(userName);

        viewFlightButton.setText("View Flights");
        viewFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFlightButtonActionPerformed(evt);
            }
        });

        cancelFlightButton.setText("Cancel my Flight");
        cancelFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelFlightButtonActionPerformed(evt);
            }
        });

        bookFlightButton.setText("Book a Flight");
        bookFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookFlightButtonActionPerformed(evt);
            }
        });

        signOutButton.setText("Sign out");
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                new Login().setVisible(true);
            }
        });


        user.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        user.setText("User : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(user)
                .addGap(18, 18, 18)
                .addComponent(nameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user)
                    .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(viewFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(bookFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(cancelFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }                                        

                                       
    private void viewFlightButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        ViewFlight v = new ViewFlight("admin");
        v.setVisible(true);
    }
    
    private void cancelFlightButtonActionPerformed(ActionEvent evt) {                                         
        this.setVisible(false);
//        DeleteFlight df = new DeleteFlight();
//        df.setVisible(true);
    }                                        
    private void bookFlightButtonActionPerformed(ActionEvent evt) { 
        this.setVisible(false);
        AddFlight af = new AddFlight();
        af.setVisible(true);
        
    }                                        

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User("username").setVisible(true);
            }
        });
    }    
}
