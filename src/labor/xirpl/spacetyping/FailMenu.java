package labor.xirpl.spacetyping;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FailMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FailMenu frame = new FailMenu(new JLabel(), new JLabel());
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
	public FailMenu(JLabel score, JLabel wordCount) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("SpaceTyping - Classic v0.1 Beta - Game Over");
		setLocationRelativeTo(null);
		
		Font fonts = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MorePerfectDOSVGA.ttf"));;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(300, 100, 200, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		fonts = fonts.deriveFont(20f);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(fonts);
		lblScore.setForeground(Color.LIGHT_GRAY);
		lblScore.setBounds(10, 11, 180, 40);
		panel.add(lblScore);
		
		JLabel label = new JLabel(score.getText());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(fonts);
		label.setBounds(10, 50, 180, 40);
		panel.add(label);
		
		JLabel lblWordCount = new JLabel("Word Count");
		lblWordCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordCount.setForeground(Color.LIGHT_GRAY);
		lblWordCount.setFont(fonts);
		lblWordCount.setBounds(10, 101, 180, 40);
		panel.add(lblWordCount);
		
		JLabel label_1 = new JLabel(wordCount.getText());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(null);
		label_1.setBounds(10, 138, 180, 40);
		panel.add(label_1);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setForeground(Color.LIGHT_GRAY);
		lblMainMenu.setBackground(Color.LIGHT_GRAY);
		lblMainMenu.setBounds(300, 311, 200, 29);
		fonts = fonts.deriveFont(30f);
		lblMainMenu.setFont(fonts);
		lblMainMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
					dispose();
				}catch(Exception e) {
					
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		contentPane.add(lblMainMenu);
	}
}
