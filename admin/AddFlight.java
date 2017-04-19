
package db.admin;

import db.admin.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFlight extends JFrame {

    private JButton addButton;
    private JLabel airplane;
    private JComboBox<String> airplaneBox;
    private JLabel airport;
    private JComboBox<String> airportBox;
    private JLabel capacity;
    private JComboBox<String> capacityBox;
    private JLabel date;
    private JComboBox<String> dateBox;
    private JLabel destFrom;
    private JComboBox<String> destFromBox;
    private JLabel destTo;
    private JComboBox<String> destToBox;
    private JLabel title;
    
    public AddFlight() {
        initComponents();
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
    
    
    private void initComponents() {

        title = new JLabel();
        airport = new JLabel();
        airplane = new JLabel();
        destFrom = new JLabel();
        destTo = new JLabel();
        capacity = new JLabel();
        airportBox = new JComboBox<>();
        airplaneBox = new JComboBox<>();
        destFromBox = new JComboBox<>();
        destToBox = new JComboBox<>();
        capacityBox = new JComboBox<>();
        addButton = new JButton();
        date = new JLabel();
        dateBox = new JComboBox<>();

        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new Admin().setVisible(true);
            }
        });

        title.setFont(new Font("Times New Roman", 1, 18)); // NOI18N
        title.setText("Add Flight");

        airport.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airport.setText("Airport");
    
        airplane.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airplane.setText("Airplane");

        destFrom.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        destFrom.setText("Destination From");

        destTo.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        destTo.setText("Destination To");

        capacity.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        capacity.setText("Capacity");

        airportBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "Airport 1", "Airport 2", "Airport 3" }));
    
        airplaneBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "Airplane 1", "Airplane 2", "Airplane 3" }));

        destFromBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "Country 1", "Country 2", "Country 3" }));

        destToBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "Country 1", "Country 2", "Country 3" }));

        capacityBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "100", "150", "200" }));

        addButton.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        date.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        date.setText("Date");

        dateBox.setModel(new DefaultComboBoxModel<>(new String[] { "Choose...", "Time 1", "Time 2", "Time 3" }));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(destFrom)
                            .addComponent(capacity)
                            .addComponent(airport)
                            .addComponent(airplane)
                            .addComponent(destTo)
                            .addComponent(date))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(airplaneBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(destFromBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(destToBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(airportBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(capacityBox, GroupLayout.Alignment.TRAILING, 0, 168, Short.MAX_VALUE)
                            .addComponent(dateBox, GroupLayout.Alignment.TRAILING, 0, 168, Short.MAX_VALUE))
                        .addGap(123, 123, 123))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(airport)
                    .addComponent(airportBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(airplane)
                    .addComponent(airplaneBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(destFrom)
                    .addComponent(destFromBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(destTo)
                    .addComponent(destToBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(capacity)
                    .addComponent(capacityBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(dateBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
        );
        setResizable(false);
        pack();
    }

    private void addButtonActionPerformed(ActionEvent evt) {                                          
        if (airportBox.getSelectedIndex() == 0  ||
            airplaneBox.getSelectedIndex() == 0 ||
            destToBox.getSelectedIndex() == 0   || 
            destFromBox.getSelectedIndex() == 0 ||
            capacityBox.getSelectedIndex() == 0 ||
            dateBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Incomplete data", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String sql = "Insert Into flights (airport, airplane, destinationFrom, destinationTo, date, capacity) "
                    + "Values (?, ?, ?, ?, ?, ?)";

            try (Connection con = connect();
                 PreparedStatement pstmt = con.prepareStatement(sql)){
                
                pstmt.setString(1, airportBox.getSelectedItem().toString());
                pstmt.setString(2, airplaneBox.getSelectedItem().toString());
                pstmt.setString(3, destFromBox.getSelectedItem().toString());
                pstmt.setString(4, destToBox.getSelectedItem().toString());
                pstmt.setString(5, dateBox.getSelectedItem().toString());
                pstmt.setInt(6, Integer.valueOf(capacityBox.getSelectedItem().toString()));
              
                pstmt.executeUpdate();
                con.close();
                pstmt.close();
                JOptionPane.showMessageDialog(null, "Successfully added flight");
                int answer = JOptionPane.showConfirmDialog(this, "Do you want to add another flight?", "" , JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.NO_OPTION){
                    this.dispose();
                    new Admin().setVisible(true);
                } else {
                    airplaneBox.setSelectedIndex(0);
                    airportBox.setSelectedIndex(0);
                    destFromBox.setSelectedIndex(0);
                    destToBox.setSelectedIndex(0);
                    capacityBox.setSelectedIndex(0);
                    dateBox.setSelectedIndex(0);
                }
                
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }                                         

//    public static void main(String args[]) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddFlight().setVisible(true);
//            }
//        });
//    }
}
