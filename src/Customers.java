import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Customers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> customerListBox;
	private JTextField ageTxtBox;
	private JTextField nameTxtBox;
	private JTextField addressTxtBox;
	private JTextField rentedMoviesTxtBox;
	private String customerFileLocation = "D:\\Bachelor of Computing Systems\\Semester 2\\ISCG5421 - Programming Principles & Practice\\Assignment\\Customers.txt";
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customers frame = new Customers();
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
	public Customers() {
		setTitle("Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		customerListBox = new JList<String>();
		customerListBox.setBounds(10, 11, 190, 330);
		contentPane.add(customerListBox);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(240, 41, 52, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(240, 98, 52, 31);
		contentPane.add(lblAge);
		
		ageTxtBox = new JTextField();
		ageTxtBox.setColumns(10);
		ageTxtBox.setBounds(408, 103, 79, 20);
		contentPane.add(ageTxtBox);
		
		nameTxtBox = new JTextField();
		nameTxtBox.setColumns(10);
		nameTxtBox.setBounds(408, 46, 199, 20);
		contentPane.add(nameTxtBox);
		
		addressTxtBox = new JTextField();
		addressTxtBox.setColumns(10);
		addressTxtBox.setBounds(408, 165, 224, 20);
		contentPane.add(addressTxtBox);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(240, 160, 94, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Movies Rented:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(240, 220, 119, 31);
		contentPane.add(lblNewLabel_5);
		
		rentedMoviesTxtBox = new JTextField();
		rentedMoviesTxtBox.setColumns(10);
		rentedMoviesTxtBox.setBounds(408, 225, 79, 20);
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
		returnBtn.setBounds(552, 352, 109, 25);
		contentPane.add(returnBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Delete customer?", "Delete", JOptionPane.YES_NO_OPTION) == 0) {
					
					listModel.removeAllElements();
					customerList.remove(index);
					
					PrintWriter writer = null;
					try {
						writer = new PrintWriter(customerFileLocation);
					} catch (FileNotFoundException ef) {
						ef.printStackTrace();
					}
					
					for (Customer customer : customerList) {
						listModel.addElement(customer.getName());
						writer.print(customer.getName() + "," + customer.getAge() +"," + customer.getAddress() + "," + customer.getMoviesRented() + "\n");
					}
					
					writer.close();
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteBtn.setBounds(436, 352, 88, 25);
		contentPane.add(deleteBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Update customer?", "Update", JOptionPane.YES_NO_OPTION) == 0) {
					String name = nameTxtBox.getText();
					int age = Integer.parseInt(ageTxtBox.getText());
					String address = addressTxtBox.getText();
					int rentedMovies = Integer.parseInt(rentedMoviesTxtBox.getText());
					
					listModel.removeAllElements();
					updateCustomer(index, name, age, address, rentedMovies);
					
					PrintWriter writer = null;
					try {
						writer = new PrintWriter(customerFileLocation);
					} catch (FileNotFoundException ef) {
						ef.printStackTrace();
					}
					
					for (Customer customer : customerList) {
						listModel.addElement(customer.getName());
						writer.print(customer.getName() + "," + customer.getAge() +"," + customer.getAddress() + "," + customer.getMoviesRented() + "\n");
					}
					
					writer.close();
				}
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateBtn.setBounds(338, 352, 88, 25);
		contentPane.add(updateBtn);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Add Customer?", "Add", JOptionPane.YES_NO_OPTION) == 0) {
					String name = nameTxtBox.getText();
					int age = Integer.parseInt(ageTxtBox.getText());
					String address = addressTxtBox.getText();
					int rentedMovies = Integer.parseInt(rentedMoviesTxtBox.getText());
					
					addCustomer(name, age, address, rentedMovies);
					listModel.addElement(nameTxtBox.getText());
					
					File customerListFile = new File (customerFileLocation);
					try {
						if (!customerListFile.exists()) {
							customerListFile.createNewFile();
						}
						
						FileWriter fileWriter = new FileWriter(customerListFile, true);
						BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
						bufferWriter.write(name + "," + age + "," + address + "," + rentedMovies + "\n");
						bufferWriter.close();
					} catch (IOException ef) {
						ef.printStackTrace();
					}
					
					nameTxtBox.setText("");
					ageTxtBox.setText("");
					addressTxtBox.setText("");
					rentedMoviesTxtBox.setText("");
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBtn.setBounds(240, 352, 88, 25);
		contentPane.add(addBtn);
		
		JButton showListBtn = new JButton("Show List");
		showListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listModel.removeAllElements();
					showCustomerList();
				} catch (IOException ef) {
					ef.printStackTrace();
				}
			}
		});
		showListBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showListBtn.setBounds(54, 352, 109, 25);
		contentPane.add(showListBtn);
	}
	
		// Add Customer Method
		private void addCustomer(String name, int age, String address, int rentedMovies) {
			Customer newCustomer = new Customer(name, age, address, rentedMovies);
			customerList.add(newCustomer);
			
			// Event Listener
			customerListBox.addMouseListener(new MouseAdapter() {
				   public void mouseClicked(MouseEvent arg) {
				        index = customerListBox.locationToIndex(arg.getPoint());
				        nameTxtBox.setText(customerList.get(index).getName());
				        int age =	(customerList.get(index).getAge());
				        ageTxtBox.setText("" + age);
				        addressTxtBox.setText(customerList.get(index).getAddress());
			            int rentedMovies =	(customerList.get(index).getMoviesRented());
				        rentedMoviesTxtBox.setText("" + rentedMovies);
				       }
			});
		}
		
		// Update Customer Method
		private void updateCustomer(int index, String name, int age, String address, int rentedMovies) {
			Customer updateCustomer = new Customer(name, age, address, rentedMovies);
			customerList.set(index, updateCustomer);
		}
		
		// Show Customer List Method
		public void showCustomerList() throws IOException {BufferedReader bufferedReader = new BufferedReader( new FileReader(customerFileLocation));
			try {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
			        String [] lineSplit = line.split(",");
			        if (lineSplit.length > 1) {
			        addCustomer(lineSplit[0], Integer.parseInt(lineSplit[1]), lineSplit[2], Integer.parseInt(lineSplit[3]));
			    }}
			} finally {
				bufferedReader.close();
			}
		
			listModel.removeAllElements();
			
	        // Adding List model to the arrayList
			for (Customer customer : customerList) {
				listModel.addElement(customer.getName());
				customerListBox.setModel(listModel);
	        }
	    }
}
