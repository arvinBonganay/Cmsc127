
package db.admin.airport;
import db.admin.airline.AddAirline;
import db.admin.editAirport;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AddAirport extends JFrame {
    private JTextField airportIdText;
    private JLabel airportNameLabel;
    private JTextField airportNameText;
    private JLabel airportidLabel;
    private JLabel countryLabel;
    private JTextField countryText;
    private JButton nextButton;
    private JLabel title;
    public AddAirport() {
        initComponents();
    }

   
    private void initComponents() {

   
        title = new JLabel();
        airportidLabel = new JLabel();
        airportNameLabel = new JLabel();
        countryLabel = new JLabel();
        airportNameText = new JTextField();
        airportIdText = new JTextField();
        countryText = new JTextField();
        nextButton = new JButton();

        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new editAirport().setVisible(true);
            }
        }); 

        title.setFont(new Font("Times New Roman", 1, 18)); // NOI18N
        title.setText("Add Airport");

        airportidLabel.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airportidLabel.setText("Airport ID");

        airportNameLabel.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        airportNameLabel.setText("Airport Name");

        countryLabel.setFont(new Font("Times New Roman", 0, 16)); // NOI18N
        countryLabel.setText("Country");

        nextButton.setText("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(airportNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countryLabel, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(airportidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(airportIdText, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(airportNameText, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addComponent(countryText, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(countryLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(airportidLabel)
                            .addComponent(airportIdText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(airportNameLabel)
                            .addComponent(airportNameText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(countryText, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
    
    private void nextButtonActionPerformed(ActionEvent evt) {   
        String airportId = airportIdText.getText();
        String airportName = airportNameText.getText();
        String country = countryText.getText();
        if (airportId.equals("") || airportName.equals("") || country.equals("")){
            JOptionPane.showMessageDialog(null, "Incomplete Data", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String sql = "Insert Into airports values (? ,? , ?)";
            try (Connection con = connect();
                 PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setString(1, airportId);
                stmt.setString(2, airportName);
                stmt.setString(3, country);
                con.close();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Airport added");
                dispose();
                new AddAirline(country, "editAirport").setVisible(true);
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
    }  
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAirport().setVisible(true);
            }
        });
    }

}
