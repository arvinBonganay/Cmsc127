package db.admin.airline;

import db.admin.airport.Modify;
import db.admin.editAirport;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAirline extends JFrame {
    private JLabel airlineIdLabel;
    private JTextField airlineIdText;
    private JLabel airlineNameLabel;
    private JTextField airlineNameText;
    private JButton doneButton;
    private JButton nextButton;
    private JLabel title;
    private String airport;
    private String parent;
    
    public AddAirline(String airport, String parent) {
        initComponents();
        this.airport = airport;
        this.parent = parent;
    }

    private void initComponents() {

        title = new JLabel();
        airlineIdLabel = new JLabel();
        airlineNameLabel = new JLabel();
        airlineNameText = new JTextField();
        airlineIdText = new JTextField();
        nextButton = new JButton();
        doneButton = new JButton();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new editAirport().setVisible(true);
            }
        });

        title.setFont(new Font("Times New Roman", 1, 18)); 
        title.setText("Add Airline");

        airlineIdLabel.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airlineIdLabel.setText("Airline ID");

        airlineNameLabel.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airlineNameLabel.setText("AirlineName");

        nextButton.setText("Add Another Airline");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        doneButton.setText("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(airlineNameText, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(airlineNameLabel, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(airlineIdLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(airlineIdText, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(293, Short.MAX_VALUE)
                    .addComponent(doneButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(title)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(airlineIdLabel)
                    .addComponent(airlineIdText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(airlineNameLabel)
                    .addComponent(airlineNameText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(241, Short.MAX_VALUE)
                    .addComponent(doneButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)))
        );

        pack();
    }                        

   
     private Connection connect(){
        String url = "jdbc:sqlite:C:\\Users\\user\\Desktop\\dataBase\\src\\db\\data.db";
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url); 
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    private void add(){
        String airlineId = airlineIdText.getText();
        String airlineName = airlineNameText.getText();
        String sql = "Insert Into airlines values(?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, airlineId);
            stmt.setString(2, airlineName);
            stmt.setString(3, airport);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Airline added");
            if (!parent.equals("editAirport")){
                new Modify().setVisible(true);
            }
            stmt.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void nextButtonActionPerformed(ActionEvent evt) {                                           
        if (airlineIdText.getText().equals("") || airlineNameText.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Incomplete Data", "", JOptionPane.ERROR_MESSAGE);
        } else {
            add();
            dispose();
            new AddAirline(airport, parent).setVisible(true);
        }
    }                                          

    private void doneButtonActionPerformed(ActionEvent evt) {                                           
        if (airlineIdText.getText().equals("") || airlineNameText.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Incomplete Data", "", JOptionPane.ERROR_MESSAGE);
        } else {
            add();
            dispose();
            new editAirport().setVisible(true);
        }
    }                                          

//    public static void main(String args[]) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddAirline("").setVisible(true);
//            }
//        });
//    }
}
