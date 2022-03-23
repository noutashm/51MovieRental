import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Color;

public class RentMovie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> memberListBox;
	private JTextField nameTxtBox;
	private JTextField memberStatusTxtBox;
	private JTextField loyaltyTxtBox;
	private JTextField rentedMoviesTxtBox;
	private JTextField priceTxtBox;
	private JTextField discountTxtBox;
	private JTextField totalTxtBox;
	private String memberFileLocation = "D:\\Bachelor of Computing Systems\\Semester 2\\ISCG5421 - Programming Principles & Practice\\Assignment\\Members.txt";
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private ArrayList<Member> memberList = new ArrayList<Member>();
	
	private int index;
	private int loyaltyPoints;
	private int numOfDVD;
	private int moviesRented;
	private double price;
	private double discount;
	private double total;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentMovie frame = new RentMovie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RentMovie() {
		try {
			showMemberList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setTitle("Rent Movies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(210, 11, 439, 327);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setEnabled(false);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(240, 41, 52, 31);
		contentPane.add(lblNewLabel);
		
		nameTxtBox = new JTextField();
		nameTxtBox.setEditable(false);
		nameTxtBox.setColumns(10);
		nameTxtBox.setBounds(408, 46, 199, 20);
		contentPane.add(nameTxtBox);
		
		JLabel lblNewLabel_3 = new JLabel("Membership Status:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(240, 99, 146, 31);
		contentPane.add(lblNewLabel_3);
		
		memberStatusTxtBox = new JTextField();
		memberStatusTxtBox.setEditable(false);
		memberStatusTxtBox.setColumns(10);
		memberStatusTxtBox.setBounds(408, 104, 116, 20);
		contentPane.add(memberStatusTxtBox);
		
		JLabel lblNewLabel_4 = new JLabel("Loyalty Points:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(240, 156, 132, 31);
		contentPane.add(lblNewLabel_4);
		
		loyaltyTxtBox = new JTextField();
		loyaltyTxtBox.setEditable(false);
		loyaltyTxtBox.setColumns(10);
		loyaltyTxtBox.setBounds(408, 161, 79, 20);
		contentPane.add(loyaltyTxtBox);
		
		JLabel lblNewLabel_5 = new JLabel("Movies Rented:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(240, 215, 116, 31);
		contentPane.add(lblNewLabel_5);
		
		rentedMoviesTxtBox = new JTextField();
		rentedMoviesTxtBox.setEditable(false);
		rentedMoviesTxtBox.setColumns(10);
		rentedMoviesTxtBox.setBounds(408, 220, 79, 20);
		contentPane.add(rentedMoviesTxtBox);
		
		JButton returnBtn = new JButton("Return");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menuForm = new MainMenu();
				menuForm.setVisible(true);
				dispose();
			}
		});
		returnBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnBtn.setBounds(538, 349, 109, 25);
		contentPane.add(returnBtn);
		
		memberListBox = new JList<String>();
		memberListBox.setBounds(10, 11, 190, 330);
		contentPane.add(memberListBox);
		memberListBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg) {
				index = memberListBox.locationToIndex(arg.getPoint());
			    nameTxtBox.setText(memberList.get(index).getName());
		        int rentedMovies =	(memberList.get(index).getMoviesRented());
			    rentedMoviesTxtBox.setText("" + rentedMovies);
				int loyalty = (memberList.get(index).getLoyaltyPoints());
			    loyaltyTxtBox.setText("" + loyalty);
			    memberStatusTxtBox.setText(memberList.get(index).getMembershipStatus());
			}
		});
		
		for (Member member : memberList) {
	        listModel.addElement(member.getName());
	        memberListBox.setModel(listModel);
	   }
		
		JSpinner numOfDVDSpinner = new JSpinner();
		numOfDVDSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numOfDVD = (Integer)numOfDVDSpinner.getValue();
			}
		});
		numOfDVDSpinner.setBounds(408, 282, 79, 20);
		contentPane.add(numOfDVDSpinner);
		
		JButton rentBtn = new JButton("Rent");
		rentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateCost();
				updateLoyaltyPoints();
				checkDVD();
				
				listModel.removeAllElements();
				updateMember(index);
				
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(memberFileLocation);
				} catch (FileNotFoundException ef) {
					ef.printStackTrace();
				}
				
				for (Member member : memberList) {
					listModel.addElement(member.getName());
					writer.print(member.getName() + "," + member.getAge() +"," + member.getAddress() + "," + member.getMembershipStatus() + "," + member.getLoyaltyPoints() + "," + member.getMoviesRented() + "\n");
				}
				
				writer.close();
				
				numOfDVDSpinner.setEnabled(false);
				panel.setEnabled(true);
				panel.setVisible(true);
			}
		});
		rentBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rentBtn.setBounds(419, 349, 109, 25);
		contentPane.add(rentBtn);
		
		JLabel lblNewLabel_5_1 = new JLabel("Number of DVDs");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(240, 275, 116, 31);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Price:");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2.setBounds(136, 190, 46, 31);
		panel.add(lblNewLabel_5_2);
		
		priceTxtBox = new JTextField();
		priceTxtBox.setEditable(false);
		priceTxtBox.setColumns(10);
		priceTxtBox.setBounds(220, 196, 79, 20);
		panel.add(priceTxtBox);
		
		discountTxtBox = new JTextField();
		discountTxtBox.setEditable(false);
		discountTxtBox.setColumns(10);
		discountTxtBox.setBounds(220, 238, 79, 20);
		panel.add(discountTxtBox);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Discount:");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1.setBounds(136, 232, 76, 31);
		panel.add(lblNewLabel_5_2_1);
		
		totalTxtBox = new JTextField();
		totalTxtBox.setEditable(false);
		totalTxtBox.setColumns(10);
		totalTxtBox.setBounds(220, 284, 79, 20);
		panel.add(totalTxtBox);
		
		JLabel lblNewLabel_5_2_1_1 = new JLabel("Total:");
		lblNewLabel_5_2_1_1.setForeground(Color.RED);
		lblNewLabel_5_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_1.setBounds(136, 278, 50, 31);
		panel.add(lblNewLabel_5_2_1_1);
		
		JLabel lblNewLabel_5_2_1_1_1 = new JLabel("Members Discount");
		lblNewLabel_5_2_1_1_1.setForeground(Color.RED);
		lblNewLabel_5_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_1_1.setBounds(150, 23, 137, 31);
		panel.add(lblNewLabel_5_2_1_1_1);
		
		JLabel lblNewLabel_5_2_1_2 = new JLabel("Silver Members");
		lblNewLabel_5_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2.setBounds(48, 65, 113, 31);
		panel.add(lblNewLabel_5_2_1_2);
		
		JLabel lblNewLabel_5_2_1_2_1 = new JLabel("Gold Members");
		lblNewLabel_5_2_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2_1.setBounds(242, 65, 113, 31);
		panel.add(lblNewLabel_5_2_1_2_1);
		
		JLabel lblNewLabel_5_2_1_2_2 = new JLabel("10%");
		lblNewLabel_5_2_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2_2.setBounds(48, 91, 113, 31);
		panel.add(lblNewLabel_5_2_1_2_2);
		
		JLabel lblNewLabel_5_2_1_2_3 = new JLabel("+1 Loyalty Points");
		lblNewLabel_5_2_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2_3.setBounds(48, 119, 134, 31);
		panel.add(lblNewLabel_5_2_1_2_3);
		
		JLabel lblNewLabel_5_2_1_2_4 = new JLabel("15%");
		lblNewLabel_5_2_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2_4.setBounds(242, 91, 113, 31);
		panel.add(lblNewLabel_5_2_1_2_4);
		
		JLabel lblNewLabel_5_2_1_2_5 = new JLabel("+2 Loyalty Points");
		lblNewLabel_5_2_1_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1_2_5.setBounds(242, 119, 137, 31);
		panel.add(lblNewLabel_5_2_1_2_5);
		
		JButton clearPanelBtn = new JButton("<-");
		clearPanelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel.setEnabled(false);
				numOfDVDSpinner.setEnabled(true);
			}
		});
		clearPanelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearPanelBtn.setBounds(10, 291, 58, 25);
		panel.add(clearPanelBtn);
	}
	
		// Add Member Method
		public void addMember(String name, int age, String address, String memberStatus, int loyalty, int rentedMovies) {
			Member newMember = new Member(name, address, age,  rentedMovies, loyalty, memberStatus);
			memberList.add(newMember);
		}
		
		// Update Royalty Points Method
	    public void updateLoyaltyPoints() {
	    	
			if (memberStatusTxtBox.getText().equals("Silver")) {
				int abc = (numOfDVD * 1);
				int pointsText = memberList.get(index).getLoyaltyPoints();
				loyaltyPoints = pointsText + abc ;
			} else if (memberStatusTxtBox.getText().equals("Gold")) {
				int abc = (numOfDVD * 2);
				int pointsText = (memberList.get(index).getLoyaltyPoints());
				loyaltyPoints = pointsText + abc ;
			}
		}
	  
	    //Free DVD every 5 movies rented for gold members
	    public void checkDVD() {
		    int oldMoviesRented = (memberList.get(index).getMoviesRented());
		    moviesRented = oldMoviesRented + numOfDVD;
		    if(moviesRented % 5 == 0 && memberStatusTxtBox.getText().equals("Gold")){
		    	 JOptionPane.showMessageDialog(null,"Congratulations! You get a free DVD!");
		    }
		}

	    // Calculate Price/Discount/Cost
		public void calculateCost() {
		    price = (numOfDVD * 5);
	        priceTxtBox.setText("" + price);
	        
	        // get discount
			if (memberStatusTxtBox.getText().equals("Silver")) {
				 discount = (price * .10);
				discountTxtBox.setText("" + discount);
				}
			else if (memberStatusTxtBox.getText().equals("Gold")) {
				     discount = (price * .15);
				     discountTxtBox.setText("" + discount);
				}
			
			// get total
			total = price - discount ;
			totalTxtBox.setText("" + total);
			}
		
		// method to update the loyalty points and movies rented		
		private void updateMember(int index) {
			Member updateMember = new Member(memberList.get(index).getName(), memberList.get(index).getAddress(), memberList.get(index).getAge(), moviesRented, loyaltyPoints, memberList.get(index).getMembershipStatus());
			memberList.set(index, updateMember);
		}
		
		// Show Member List Method
		public void showMemberList() throws IOException {BufferedReader bufferedReader = new BufferedReader( new FileReader(memberFileLocation));
			try {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
			        String [] lineSplit = line.split(",");
			        if (lineSplit.length > 1) {
			        addMember(lineSplit[0], Integer.parseInt(lineSplit[1]), lineSplit[2], lineSplit[3], Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[5]));
			    }}
			} finally {
				bufferedReader.close();
			}
	    }
}
