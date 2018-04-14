package labor.xirpl.spacetyping;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private MediaPlayer player;
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
	public MainMenu() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("SpaceTyping - Classic v0.1 Beta");
		setLocationRelativeTo(null);
		Font fonts = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MorePerfectDOSVGA.ttf"));;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MorePerfectDOSVGA.ttf")));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblSelectWords = new JLabel("Select Words:");
		lblSelectWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectWords.setForeground(Color.LIGHT_GRAY);
		lblSelectWords.setBounds(10, 11, 764, 92);
		fonts = fonts.deriveFont(45f);
		lblSelectWords.setFont(fonts);
		contentPane.add(lblSelectWords);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(10, 111, 764, 2);
		contentPane.add(separator);
		
		JLabel lblIndonesian = new JLabel("Indonesian");
		lblIndonesian.setFont(fonts);
		lblIndonesian.setForeground(Color.LIGHT_GRAY);
		lblIndonesian.setBounds(97, 196, lblIndonesian.getPreferredSize().width,lblIndonesian.getPreferredSize().height);		
		contentPane.add(lblIndonesian);
		
		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setFont(fonts);
		lblEnglish.setForeground(Color.LIGHT_GRAY);
		lblEnglish.setBounds(104, 284, lblEnglish.getPreferredSize().width,lblEnglish.getPreferredSize().height);
		
		lblEnglish.setOpaque(true);
		lblIndonesian.setOpaque(true);
		lblIndonesian.setBackground(Color.BLACK);
		lblEnglish.setBackground(Color.BLACK);
		
		JLabel arrow = new JLabel("=>");
		arrow.setFont(fonts);
		arrow.setForeground(Color.LIGHT_GRAY);
		playMenu("menu.mp3");
		this.addKeyListener(new KeyListener() {
			boolean f = false;
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_UP) {
					f=!f;
					playSound("button.wav");
						lblIndonesian.setBackground(Color.BLUE);
						lblEnglish.setBackground(Color.BLACK);
						arrow.setBounds(40,lblIndonesian.getY(),arrow.getPreferredSize().width,arrow.getPreferredSize().height);
				}
				if(key.getKeyCode() == KeyEvent.VK_DOWN) {
					f=!f;
					playSound("button.wav");
						lblIndonesian.setBackground(Color.BLACK);
						lblEnglish.setBackground(Color.BLUE);
						arrow.setBounds(40,lblEnglish.getY(),arrow.getPreferredSize().width,arrow.getPreferredSize().height);
				}
				if(key.getKeyCode() == KeyEvent.VK_ENTER) {
					if(f) {
						try{
							
							player.stop();
							player = null;
							Play game = new Play(1);
							game.setVisible(true);
							dispose();
						}catch(Exception e) {
							
						}
						
					} else {
						try {
							player.stop();
							player = null;
							Play game = new Play(0);
							game.setVisible(true);
							
							dispose();
						} catch(Exception e) {
							
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		contentPane.add(lblEnglish);
		contentPane.add(arrow);
		
		JLabel lblCopyrightC = new JLabel("Copyright C 2018 XI RPL");
		fonts = fonts.deriveFont(15f);
		lblCopyrightC.setFont(fonts);
		lblCopyrightC.setForeground(Color.LIGHT_GRAY);
		lblCopyrightC.setBounds(10, 536, lblCopyrightC.getPreferredSize().width, lblCopyrightC.getPreferredSize().height);
		contentPane.add(lblCopyrightC);
	
	}
	
	public void playSound(String path) {
		new javafx.embed.swing.JFXPanel();
	    String uriString = new File("Sounds\\"+path).toURI().toString();
	    new MediaPlayer(new Media(uriString)).play();
	}
	
	public void playMenu(String path) {
		new javafx.embed.swing.JFXPanel();
	    String uriString = new File("Sounds\\"+path).toURI().toString();
	    Media med = new Media(uriString);
	    player = new MediaPlayer(med);
	    player.play();
	}
}
