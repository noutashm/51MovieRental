import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxtBox;
	private JPasswordField passwordTxtBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Membership System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(135, 128, 82, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(135, 188, 82, 31);
		contentPane.add(lblPassword);
		
		usernameTxtBox = new JTextField();
		usernameTxtBox.setBounds(267, 135, 156, 20);
		contentPane.add(usernameTxtBox);
		usernameTxtBox.setColumns(10);
		
		JLabel lblEntertainmentPlusMovie = new JLabel("Entertainment Plus Movie Rental Store");
		lblEntertainmentPlusMovie.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEntertainmentPlusMovie.setBounds(111, 11, 409, 47);
		contentPane.add(lblEntertainmentPlusMovie);
		
		JLabel lblCustomerMembershipSystem = new JLabel("Customer Membership System");
		lblCustomerMembershipSystem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomerMembershipSystem.setBounds(206, 56, 225, 36);
		contentPane.add(lblCustomerMembershipSystem);
		
		JButton loginBtn = new JButton("Log In");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameTxtBox.getText().equals("admin") && passwordTxtBox.getText().equals("admin")) {
					MainMenu mainMenu = new MainMenu();
					mainMenu.setVisible(true);
				    dispose();
				} else {
					JOptionPane.showMessageDialog(null,"Username or Password is Invalid!");
				}
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginBtn.setBounds(206, 253, 109, 25);
		contentPane.add(loginBtn);
		
		passwordTxtBox = new JPasswordField();
		passwordTxtBox.setBounds(267, 195, 156, 20);
		contentPane.add(passwordTxtBox);
	}
}
