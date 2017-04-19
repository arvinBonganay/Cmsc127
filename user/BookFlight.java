package db.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BookFlight extends javax.swing.JFrame {
    
    private javax.swing.JButton bookFlightButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> searchType;
    private String prevSearch;
    private int userId;
    private String username;

    public BookFlight(int userId, String username) {
        initComponents();
        showTable("Select * From Flights");
        prevSearch = "";
        this.username = username;
        this.userId = userId;
    }
    
    private void showTable(String sql){
        
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            java.util.List<String> l = new ArrayList<>();
            while (rs.next()) {
                l.add(String.valueOf(rs.getInt("flightNum")));
                l.add(rs.getString("airlineId"));
                l.add(rs.getString("airportId"));
                l.add(rs.getString("destinationFrom"));
                l.add(rs.getString("destinationTo"));
                l.add(rs.getString("date"));
                l.add(rs.getString("pilot"));
                l.add(rs.getString("airplane"));
            }
            
            if (l.isEmpty()){
                JOptionPane.showMessageDialog(this, "No results Found");
                searchField.setText(prevSearch);
            } else {
                prevSearch = searchField.getText();
                int row = l.size() / 8;

                String[][] data = new String[row][8];
                for (int i = 0; i < row; i++){

                    data[i][0] = l.get(8 * i);
                    data[i][1] = l.get(8 * i + 1);
                    data[i][2] = l.get(8 * i + 2);
                    data[i][3] = l.get(8 * i + 3);
                    data[i][4] = l.get(8 * i + 4);
                    data[i][5] = l.get(8 * i + 5);
                    data[i][6] = l.get(8 * i + 6);
                    data[i][7] = l.get(8 * i + 7);
                }
                
                String column[] = {"FlightNum", "AirlineId", "AirportId", "DestinationFrom", "DestinationTo",  "Date", "Pilot", "Airplane"};

                jTable1.setModel(new DefaultTableModel(data, column));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  1");
        }
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
    

    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        searchType = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        bookFlightButton = new javax.swing.JButton();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    new User(username, userId).setVisible(true);
            }
        });

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        searchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "FlightNum ", "AirlineId", "AirportId", "Date", "DestinationTo", "DestinationFrom", "Pilot" , "Airplane" }));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        bookFlightButton.setText("Book a Flight");
        bookFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookFlightButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(searchType, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(bookFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        String search = searchField.getText();
        String type = searchType.getSelectedItem().toString();
        if (type.equals("All")){
            showTable("Select * From flights");
            prevSearch = "";
            searchField.setText("");
        } else {
            String sql = "Select * From flights Where " + type + " == \"" + search + "\"";
            showTable(sql);
        }
    }                                            

    private void bookFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        try {
            int flightNum = Integer.valueOf(JOptionPane.showInputDialog(null, "Enter Flight Number", "Book a flight", JOptionPane.PLAIN_MESSAGE));
            if (!existBooking(flightNum)){
                JOptionPane.showMessageDialog(null, "Flight does not exist", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (exist(flightNum)){
               JOptionPane.showMessageDialog(null, "You already booked for this flight", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
     
            add(flightNum);
            JOptionPane.showMessageDialog(null, "Successfully booked flight");
            dispose();
            new User(username, userId).setVisible(true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }              
    
    private boolean existBooking(int flightNum){
        String sql = "Select flightNum from flights";
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
             while(rs.next()){
                 if (rs.getInt("flightNum") == flightNum){
                     return true;
                 }
             } return false;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    private boolean exist(int flightNum){
        String sql = "Select * from bookedflights  \n" +
                "where userid == \"" + userId + "\"";
        try (Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                if (rs.getInt("flightNum") == flightNum){
                    return true;
                }
            } 
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    private void add(int flightNum){
        String sql = "Insert into bookedFlights values ( " + userId + " ," + flightNum + ")";
        try (Connection con = connect();
             Statement stmt = con.createStatement()){
            stmt.executeUpdate(sql);
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
            
    }

}
