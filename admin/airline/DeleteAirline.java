package db.admin.airline;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DeleteAirline extends javax.swing.JFrame {
    
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> searchType;
    private String prevSearch;
    private String airport;

    public DeleteAirline(String airport) {
        initComponents();
        this.airport = airport;
        showTable("Select * From Airlines where airportId == \"" + airport + "\"");
        prevSearch = "";
    }
    
    private void showTable(String sql){
        
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            java.util.List<String> l = new ArrayList<>();
            while (rs.next()) {
                l.add(rs.getString("airlineId"));
                l.add(rs.getString("airlineName"));
                l.add(rs.getString("airportId"));
            }
            if (l.isEmpty()){
                JOptionPane.showMessageDialog(this, "No results Found");
                searchField.setText(prevSearch);
            } else {
                prevSearch = searchField.getText();
                int row = l.size() / 3;

                String[][] data = new String[row][3];
                for (int i = 0; i < row; i++){

                    data[i][0] = l.get(3 * i);
                    data[i][1] = l.get(3 * i + 1);
                    data[i][2] = l.get(3* i + 2);
                    
                }
                
                String column[] = {"Airline ID", "Airline Name", "Airport ID"};

                jTable1.setModel(new DefaultTableModel(data, column));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  1");
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();
        searchType = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                    new Admin().setVisible(true);
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

        searchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "AirlineID ", "AirlineName"}));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
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
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            showTable("Select * From Airlines where airportId == \"" + airport + "\"");
            prevSearch = "";
            searchField.setText("");
        } else {
            String sql = "Select * From Airlines Where  airportid == \"" + airport + "\" and "+ type + " == \"" + search + "\"";
            showTable(sql);
        }
        
    }                        
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String search = searchField.getText();
        String type = searchType.getSelectedItem().toString();
        
        String message = "Delete Airline/s ";
        String sql = "";
        if (search.equals("") || type.equals("All")){
            sql +=  "Select airlineId From Airlines Where  airportid == \"" + airport + "\"";
        } else{
            sql += "Select airlineId From Airlines Where  airportid == \"" + airport + "\" and " + type + " == \"" + search + "\"";
        }
        
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                message += rs.getString("airlineId") + ", ";
                
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        int x = JOptionPane.showConfirmDialog(null, "are you sure you want to " + message, "", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.NO_OPTION){
            
        } else {
            String sql2 = "";
            if (search.equals("") || type.equals("All")){
                sql2 += "Delete From Airlines Where  airportid == \"" + airport + "\"";
            } else{
                sql2 += "Delete From Airlines Where  airportid == \"" + airport + "\" and " + type + " == \"" + search + "\"";
            }
            try (Connection con = connect();
                 Statement stmt = con.createStatement() ){
                 stmt.executeUpdate(sql2);
                 System.out.println(sql2);
                 JOptionPane.showMessageDialog(this, "Successfully deleted airlines");
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
            this.dispose();
//            new Admin().setVisible(true);
        }
    }                                            

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DeleteAirline("NAIA").setVisible(true);
//            }
//        });
//    }
}
