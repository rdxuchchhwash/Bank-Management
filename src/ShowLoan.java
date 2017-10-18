import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JOptionPane;
import java.sql.*;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

public class ShowLoan extends JFrame{

	
	JTable table= new JTable();
	JLabel labelLoans = new JLabel("Loan's");
	Connection connection=null;
	JButton btShowDetails = new JButton("Show All Loan's");
	JButton btCancel = new JButton("Cancel");
	JButton btSearch = new JButton("Search");
	JTextField txtSearch = new JTextField();

	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public ShowLoan() {

		
		setBounds(100, 100, 1360, 768);
		setTitle("All Loan's");
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\ShowLoan.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 71, 1194, 588);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		btShowDetails.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btShowDetails.setBounds(600, 670, 170,43);
	    getContentPane().add(btShowDetails);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
	    
		btCancel.setBounds(410 ,670, 170, 43);
		getContentPane().add(btCancel);
		btSearch.setFont(new Font("Agency FB", Font.BOLD, 24));
	    
		btSearch.setBounds(1150 ,670, 120, 43);
		getContentPane().add(btSearch);
		txtSearch.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setColumns(10);
		txtSearch.setBounds(1000, 670, 140, 43);
		getContentPane().add(txtSearch);
	    
		
		
		labelLoans.setForeground(Color.BLACK);
		labelLoans.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelLoans.setBounds(580, -13, 527, 108);
		getContentPane().add(labelLoans);
		

		 connection= DatabaseConnection.dbConnector();
		 showAll();
		
		// performed uml operation on database against the button click
		 
		 btShowDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
					showAll();
				}
			});
		 
		 btSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
					 try
						{
						 	String query ="select * from loan where Account_No=?";
						 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
						 	pst.setString(1,txtSearch.getText());
						 	ResultSet rs=pst.executeQuery();
						 	table.setModel(DbUtils.resultSetToTableModel(rs));
						
						}
					catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,e);
						}
				}
			});
		 
			btCancel.addActionListener(new ActionListener() 
			 {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
			 });
			
			
		
	}
	
	public void showAll()
	{
		 try
			{
			 	String query ="select * from loan";
			 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			 	ResultSet rs=pst.executeQuery();
			 	table.setModel(DbUtils.resultSetToTableModel(rs));
			
			}
		catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
	}
}
