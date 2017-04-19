package db.admin;


import db.login.Login;
import java.awt.event.*;
import javax.swing.*;

public class Admin extends JFrame {
    private JButton signOutButton;
    private JButton viewFlightButton;
    private JButton deleteFlightButton;
    private JButton addFlightButton;
    private JButton editAirportButton;
    private JLabel nameOfUser;
    private JLabel user;
    
    public Admin() {
        initComponents();
    }

    private void initComponents() {

        nameOfUser = new javax.swing.JLabel();
        viewFlightButton = new javax.swing.JButton();
        deleteFlightButton = new javax.swing.JButton();
        addFlightButton = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        signOutButton = new javax.swing.JButton();
        editAirportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 54));

        nameOfUser.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        nameOfUser.setText("Admin");

        viewFlightButton.setText("View Flights");
        viewFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFlightButtonActionPerformed(evt);
            }
        });

        deleteFlightButton.setText("Delete Flight");
        deleteFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFlightButtonActionPerformed(evt);
            }
        });

        addFlightButton.setText("Add Flight");
        addFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFlightButtonActionPerformed(evt);
            }
        });
        

        user.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        user.setText("User : ");

        signOutButton.setText("Sign out");
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButtonActionPerformed(evt);
            }
        });
        
        editAirportButton.setText("Edit Airport");
        editAirportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAirportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(user)
                .addGap(18, 18, 18)
                .addComponent(nameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editAirportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
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
                .addComponent(viewFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(addFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(deleteFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(editAirportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }

    private void editAirportButtonActionPerformed(ActionEvent evt){
        this.setVisible(false);
        new editAirport().setVisible(true);
    }
    
    private void viewFlightButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        ViewFlight v = new ViewFlight("admin");
        v.setVisible(true);
    }
    
    private void deleteFlightButtonActionPerformed(ActionEvent evt) {                                         
        dispose();
        DeleteFlight df = new DeleteFlight();
        
    }                                        
    private void addFlightButtonActionPerformed(ActionEvent evt) { 
        this.setVisible(false);
        AddFlight af = new AddFlight();
        af.setVisible(true);
        
    }
    
    private void signOutButtonActionPerformed(ActionEvent evt) { 
        this.setVisible(false);
        new Login().setVisible(true);
        
        
    }

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Admin().setVisible(true);
//            }
//        });
//    }    
}
