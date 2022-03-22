import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainInterface extends JFrame {
	
	JDesktopPane desktop=new JDesktopPane();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface frame = new MainInterface();
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
	public MainInterface() {
		
    	Toolkit tool=Toolkit.getDefaultToolkit();
    	Dimension size=tool.getScreenSize();

		setTitle("Bangladesh Cricket Board");
		setIconImage(Toolkit.getDefaultToolkit().getImage(".img\\Logo.png"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(size);
		contentPane = new JPanel();
		desktop.setBackground(new Color(0, 102, 102));
		
		getContentPane().add(desktop, BorderLayout.CENTER);
		desktop.setLayout(null);
		
		JButton btnAddNewM = new JButton("Add Member");
		btnAddNewM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new AddMember());
			}
		});
		btnAddNewM.setBounds(1099, 80, 251, 39);
		btnAddNewM.setForeground(new Color(255, 255, 255));
		btnAddNewM.setBackground(new Color(51, 0, 0));
		desktop.add(btnAddNewM);
		
		JButton btnCollectFees = new JButton("Collect Fees");
		btnCollectFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new FeeCollection());
			}
		});
		btnCollectFees.setBounds(1099, 154, 251, 39);
		btnCollectFees.setForeground(Color.WHITE);
		btnCollectFees.setBackground(new Color(51, 0, 0));
		desktop.add(btnCollectFees);
		
		JButton btnTeams = new JButton("Teams");
		btnTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new MakeTeam());
			}
		});
		btnTeams.setBounds(1099, 226, 251, 39);
		btnTeams.setForeground(Color.WHITE);
		btnTeams.setBackground(new Color(51, 0, 0));
		desktop.add(btnTeams);
		
		JButton btnArrangeMatch = new JButton("Arrange Match");
		btnArrangeMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ArrangeMatch());
			}
		});
		btnArrangeMatch.setBounds(1099, 298, 251, 39);
		btnArrangeMatch.setForeground(Color.WHITE);
		btnArrangeMatch.setBackground(new Color(51, 0, 0));
		desktop.add(btnArrangeMatch);
		
		JButton btnAddExpenditure = new JButton("Add Expenditure");
		btnAddExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new AddExpenditure());
			}
		});
		btnAddExpenditure.setBounds(1099, 371, 251, 39);
		btnAddExpenditure.setForeground(Color.WHITE);
		btnAddExpenditure.setBackground(new Color(51, 0, 0));
		desktop.add(btnAddExpenditure);
		
		JButton btnAllExpenditure = new JButton("Match Results");
		btnAllExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new MatchResult());
			}
		});
		btnAllExpenditure.setBounds(1099, 442, 251, 39);
		btnAllExpenditure.setForeground(Color.WHITE);
		btnAllExpenditure.setBackground(new Color(51, 0, 0));
		desktop.add(btnAllExpenditure);
		
		JLabel lblV = new JLabel("Victoria Cricket Club");
		lblV.setBackground(new Color(51, 0, 0));
		lblV.setBounds(292, 11, 395, 35);
		desktop.add(lblV);
		lblV.setForeground(new Color(255, 255, 255));
		lblV.setFont(new Font("Stencil", Font.PLAIN, 32));
		
		JLabel lblCricketClubManagement = new JLabel("Cricket Club Management System");
		lblCricketClubManagement.setBounds(300, 57, 354, 14);
		desktop.add(lblCricketClubManagement);
		lblCricketClubManagement.setForeground(new Color(204, 204, 255));
		lblCricketClubManagement.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCricketClubManagement.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnAllExpenditure_1 = new JButton("All Expenditure");
		btnAllExpenditure_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new AddExpenditure());
			}
		});
		btnAllExpenditure_1.setForeground(Color.WHITE);
		btnAllExpenditure_1.setBackground(new Color(51, 0, 0));
		btnAllExpenditure_1.setBounds(1099, 513, 251, 39);
		desktop.add(btnAllExpenditure_1);
		
		JButton button = new JButton("All Expenditure");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new SearchResult());
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 0, 0));
		button.setBounds(1099, 589, 251, 39);
		desktop.add(button);
		
		JMenuBar mainMenu = new JMenuBar();
		setJMenuBar(mainMenu);
		
		JMenu mnSystemn = new JMenu("Systemn");
		mainMenu.add(mnSystemn);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnSystemn.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnSystemn.add(mntmExit);
		
		JMenu addMenu = new JMenu("Add");
		mainMenu.add(addMenu);
		
		JMenuItem menuItem = new JMenuItem("Add Member");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new AddMember());
			}
		});
		addMenu.add(menuItem);
		
		JMenuItem mntmCollectFees = new JMenuItem("Collect Fees");
		mntmCollectFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new FeeCollection());
			}
		});
		
		JMenuItem mntmAddPlayers = new JMenuItem("Add Players");
		mntmAddPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new AddPlayers());
			}
		});
		addMenu.add(mntmAddPlayers);
		addMenu.add(mntmCollectFees);
		
		JMenuItem playersItem = new JMenuItem("Teams");
		playersItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new MakeTeam());
			}
		});
		addMenu.add(playersItem);
		
		JMenuItem mntmArrangeMatch = new JMenuItem("Arrange Match");
		mntmArrangeMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ArrangeMatch());
			}
		});
		addMenu.add(mntmArrangeMatch);
		
		JMenuItem mntmAddExpenditure = new JMenuItem("Add Expenditure");
		mntmAddExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new AddExpenditure());
			}
		});
		addMenu.add(mntmAddExpenditure);
		
		JMenu reportMenu = new JMenu("Reports");
		mainMenu.add(reportMenu);
		
		JMenuItem searchPlayerItem = new JMenuItem("All Expenditure");
		searchPlayerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new SearchResult());
			}
		});
		reportMenu.add(searchPlayerItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Match Results");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desktop.add(new MatchResult());
			}
		});
		reportMenu.add(menuItem_1);
		contentPane.setLayout(null);
		setVisible(true);
	}
}
