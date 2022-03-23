import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Members extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTxtBox;
	private JList<String> memberListBox;
	private JButton showListBtn;
	private JLabel lblAge;
	private JTextField ageTxtBox;
	private JLabel lblNewLabel_2;
	private JTextField addressTxtBox;
	private JLabel lblNewLabel_3;
	private JTextField memberShipStatusTxtBox;
	private JLabel lblNewLabel_4;
	private JTextField loyaltyTxtBox;
	private JLabel lblNewLabel_5;
	private JTextField rentedMoviesTxtBox;
	private JButton addBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton returnBtn;
	private String memberFileLocation = "D:\\Bachelor of Computing Systems\\Semester 2\\ISCG5421 - Programming Principles & Practice\\Assignment\\Members.txt";
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private ArrayList<Member> memberList = new ArrayList<Member>();
	
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Members frame = new Members();
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
	public Members() {
		setTitle("Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(240, 41, 52, 31);
		contentPane.add(lblNewLabel);
		
		nameTxtBox = new JTextField();
		nameTxtBox.setColumns(10);
		nameTxtBox.setBounds(408, 46, 199, 20);
		contentPane.add(nameTxtBox);
		
		memberListBox = new JList<String>();
		memberListBox.setBounds(10, 11, 190, 330);
		contentPane.add(memberListBox);
		
		showListBtn = new JButton("Show List");
		showListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listModel.removeAllElements();
					showMemberList();
				} catch (IOException ef) {
					ef.printStackTrace();
				}
			}
		});
		showListBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showListBtn.setBounds(54, 352, 109, 25);
		contentPane.add(showListBtn);
		
		lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(240, 83, 52, 31);
		contentPane.add(lblAge);
		
		ageTxtBox = new JTextField();
		ageTxtBox.setColumns(10);
		ageTxtBox.setBounds(408, 88, 79, 20);
		contentPane.add(ageTxtBox);
		
		lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(240, 125, 94, 31);
		contentPane.add(lblNewLabel_2);
		
		addressTxtBox = new JTextField();
		addressTxtBox.setColumns(10);
		addressTxtBox.setBounds(408, 130, 224, 20);
		contentPane.add(addressTxtBox);
		
		lblNewLabel_3 = new JLabel("Membership Status:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(240, 167, 146, 31);
		contentPane.add(lblNewLabel_3);
		
		memberShipStatusTxtBox = new JTextField();
		memberShipStatusTxtBox.setColumns(10);
		memberShipStatusTxtBox.setBounds(408, 172, 116, 20);
		contentPane.add(memberShipStatusTxtBox);
		
		lblNewLabel_4 = new JLabel("Loyalty Points:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(240, 212, 132, 31);
		contentPane.add(lblNewLabel_4);
		
		loyaltyTxtBox = new JTextField();
		loyaltyTxtBox.setColumns(10);
		loyaltyTxtBox.setBounds(408, 217, 79, 20);
		contentPane.add(loyaltyTxtBox);
		
		lblNewLabel_5 = new JLabel("Movies Rented:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(240, 259, 116, 31);
		contentPane.add(lblNewLabel_5);
		
		rentedMoviesTxtBox = new JTextField();
		rentedMoviesTxtBox.setColumns(10);
		rentedMoviesTxtBox.setBounds(408, 264, 79, 20);
		contentPane.add(rentedMoviesTxtBox);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Add Member?", "Add", JOptionPane.YES_NO_OPTION) == 0) {
					String name = nameTxtBox.getText();
					int age = Integer.parseInt(ageTxtBox.getText());
					String address = addressTxtBox.getText();
					String memberShipStatus = memberShipStatusTxtBox.getText();
					int loyaltyPoints = Integer.parseInt(loyaltyTxtBox.getText());
					int rentedMovies = Integer.parseInt(rentedMoviesTxtBox.getText());
					
					addMember(name, age, address, memberShipStatus, loyaltyPoints, rentedMovies);
					listModel.addElement(nameTxtBox.getText());
					
					File memberListFile = new File (memberFileLocation);
					try {
						if (!memberListFile.exists()) {
							memberListFile.createNewFile();
						}
						
						FileWriter fileWriter = new FileWriter(memberListFile, true);
						BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
						bufferWriter.write(name + "," + age + "," + address + "," + memberShipStatus + "," + loyaltyPoints + "," + rentedMovies + "\n");
						bufferWriter.close();
					} catch (IOException ef) {
						ef.printStackTrace();
					}
					
					nameTxtBox.setText("");
					ageTxtBox.setText("");
					addressTxtBox.setText("");
					memberShipStatusTxtBox.setText("");
					loyaltyTxtBox.setText("");
					rentedMoviesTxtBox.setText("");
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBtn.setBounds(240, 352, 88, 25);
		contentPane.add(addBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Update member?", "Update", JOptionPane.YES_NO_OPTION) == 0) {
					String name = nameTxtBox.getText();
					int age = Integer.parseInt(ageTxtBox.getText());
					String address = addressTxtBox.getText();
					String memberShipStatus = memberShipStatusTxtBox.getText();
					int loyaltyPoints = Integer.parseInt(loyaltyTxtBox.getText());
					int rentedMovies = Integer.parseInt(rentedMoviesTxtBox.getText());
					
					listModel.removeAllElements();
					updateMember(index, name, age, address, memberShipStatus, loyaltyPoints, rentedMovies);
					
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
				}
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateBtn.setBounds(338, 352, 88, 25);
		contentPane.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Delete member?", "Delete", JOptionPane.YES_NO_OPTION) == 0) {
					
					listModel.removeAllElements();
					memberList.remove(index);
					
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
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteBtn.setBounds(436, 352, 88, 25);
		contentPane.add(deleteBtn);
		
		returnBtn = new JButton("Return");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menuForm = new MainMenu();
				menuForm.setVisible(true);
				dispose();
			}
		});
		returnBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnBtn.setBounds(552, 352, 109, 25);
		contentPane.add(returnBtn);
	}
	
	// Add Member Method
	private void addMember(String name, int age, String address, String memberStatus, int loyalty, int rentedMovies) {
		Member newMember = new Member(name, address, age,  rentedMovies, loyalty, memberStatus);
		memberList.add(newMember);
		
		// Event Listener
		memberListBox.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent arg) {
			        index = memberListBox.locationToIndex(arg.getPoint());
			        nameTxtBox.setText(memberList.get(index).getName());
			        addressTxtBox.setText(memberList.get(index).getAddress());
			        int age =	(memberList.get(index).getAge());
		            ageTxtBox.setText("" + age);
		            int rentedMovies =	(memberList.get(index).getMoviesRented());
			        rentedMoviesTxtBox.setText("" + rentedMovies);
				    int loyalty = (memberList.get(index).getLoyaltyPoints());
			        loyaltyTxtBox.setText("" + loyalty);
			        memberShipStatusTxtBox.setText(memberList.get(index).getMembershipStatus());
			   }
		});
	}
	
	// Update Member Method
	private void updateMember(int index, String name, int age, String address, String memberStatus, int loyalty, int rentedMovies) {
		Member updateMember = new Member(name, address, age, rentedMovies, loyalty, memberStatus);
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
	
		listModel.removeAllElements();
		
        // Adding List model to the arrayList
		for (Member member : memberList) {
			listModel.addElement(member.getName());
			memberListBox.setModel(listModel);
        }
     }
}
