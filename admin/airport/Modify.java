package db.admin.airport;


import db.admin.airline.AddAirline;
import db.admin.airline.DeleteAirline;
import db.admin.editAirport;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Modify extends JFrame {
    private JLabel jLabel7;
    private JComboBox<String> airportChoice;
    private JButton addAirlineButton;
    private JTextField airportNameText;
    private JButton confirmButton;
    private JTextField countryText;
    private JButton deleteAirlineButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextField jTextField1;
    private JTextField jTextField3;
    
    
    public Modify() {
        initComponents();
        String sql = "Select airportid from airports";
        List<String> l = new ArrayList<>();
        try (Connection con = connect();
             Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                l.add(rs.getString("airportId"));
            }
            String choices[] = new String[l.size()];
            choices = l.toArray(choices);
            airportChoice.setModel(new DefaultComboBoxModel<>(choices));            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    
    
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jLabel3 = new JLabel();
        countryText = new JTextField();
        jLabel4 = new JLabel();
        jTextField3 = new JTextField();
        jLabel5 = new JLabel();
        airportNameText = new JTextField();
        jLabel6 = new JLabel();
        addAirlineButton = new JButton();
        deleteAirlineButton = new JButton();
        confirmButton = new JButton();
        airportChoice = new JComboBox<>();
        jLabel7 = new JLabel();


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new editAirport().setVisible(true);
            }
        });


        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Modify Airport");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Country :");

        jTextField1.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("Change To :");

//        countryText.setEditable(TRUE);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("Airport Name :");

        jTextField3.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("Change To :");

//        airportNameText.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Airlines :");

        addAirlineButton.setText("Add Airline");
        addAirlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addAirlineButtonActionPerformed(evt);
            }
        });

        deleteAirlineButton.setText("Delete Airline");
        deleteAirlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteAirlineButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm Changes");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("Airport");
        
        airportChoice.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(airportNameText, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countryText, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(airportChoice, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(addAirlineButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(deleteAirlineButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(181, 181, 181))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(airportChoice, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(countryText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(airportNameText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(addAirlineButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAirlineButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
    
    private void addAirlineButtonActionPerformed(ActionEvent evt) {                                                 
        new AddAirline(airportChoice.getSelectedItem().toString(), "modify").setVisible(true);
    }                                                

    private void deleteAirlineButtonActionPerformed(ActionEvent evt) {                                                    
        new DeleteAirline(airportChoice.getSelectedItem().toString()).setVisible(true);
    }                                                   

    private void confirmButtonActionPerformed(ActionEvent evt) {                                              
        String country = countryText.getText();
        String airportName = countryText.getText();
        
        String airport = "airportChoice.getSelectedItem().toString()";
        if (country.trim().equals("")){
            String sql = "Update airports set airportName = \"" + airportName + "\" where airportID == \"" + airport + "\"";
            update(sql);
        } else if (airportName.trim().equals("")){
            String sql = "Update airports set airportName = \"" + airportName + "\" where airportID == \"" + airport + "\"";
            update(sql);
        } else {
            String sql1 =  "Update airports set airportName = \"" + airportName + "\" where airportID == \"" + airport + "\"";
            String sql2 = "Update airports set airportName = \"" + airportName + "\" where airportID == \"" + airport + "\"";
            update(sql1);
            update(sql2);
        }
        JOptionPane.showMessageDialog(null, "Changes have been saved");
    }
    
    private void update(String sql){
        try (Connection con = connect();
             Statement stmt = con.createStatement()){
            stmt.executeUpdate(sql);
            con.close();
            stmt.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Modify().setVisible(true);
//            }
//        });
//    }

    

}
