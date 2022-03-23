import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(163, 56, 52, 29);
		contentPane.add(lblNewLabel);
		
		JButton membersFormBtn = new JButton("Members");
		membersFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Members memberForm = new Members();
				memberForm.setVisible(true);
				dispose();
			}
		});
		membersFormBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		membersFormBtn.setBounds(124, 115, 140, 29);
		contentPane.add(membersFormBtn);
		
		JButton customersFormBtn = new JButton("Customers");
		customersFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customers customerForm = new Customers();
				customerForm.setVisible(true);
				dispose();
			}
		});
		customersFormBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customersFormBtn.setBounds(124, 183, 140, 29);
		contentPane.add(customersFormBtn);
		
		JButton rentMovieBtn = new JButton("Rent Movies");
		rentMovieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentMovie rentMovieForm = new RentMovie();
				rentMovieForm.setVisible(true);
				dispose();
			}
		});
		rentMovieBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rentMovieBtn.setBounds(124, 258, 140, 29);
		contentPane.add(rentMovieBtn);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginForm = new Login();
				loginForm.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogOut.setBounds(7, 5, 98, 29);
		contentPane.add(btnLogOut);
	}

}
