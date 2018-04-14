package labor.xirpl.spacetyping;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Play extends JFrame {

	private JPanel contentPane;
	private JLabel lblWord,lblCPS,lblWordCount,lblScore;
	private MediaPlayer m,mx;
	private Font fonts;
	
	private ArrayList<String> englishWord = new ArrayList<String>();
	private ArrayList<String> currentWord = new ArrayList<String>();
	private ArrayList<JLabel> spaceword = new ArrayList<JLabel>();
	private ArrayList<Integer> cpslist = new ArrayList<Integer>();
	
	private boolean START = false,FAIL = false;
	private String curWord = "";
	private int wordCount = 0,score = 0,charpersecond = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Play frame = new Play(1);
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
	public Play(int lang) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("SpaceTyping - Classic v0.1 Beta [ENG]");
		setLocationRelativeTo(null);
		if(lang==1)
			readText("english.txt");
		if(lang==0) readText("indonesian.txt");
		fonts = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MorePerfectDOSVGA.ttf"));;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MorePerfectDOSVGA.ttf")));
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(10, 493, 764, 57);
		contentPane.add(panel);
		panel.setLayout(null);
		
		fonts = fonts.deriveFont(15f);
		lblWord = new JLabel("Word: ");
		lblWord.setForeground(Color.LIGHT_GRAY);
		lblWord.setBounds(10, 11, 170, 14);
		lblWord.setFont(fonts);
		panel.add(lblWord);
		
		lblScore = new JLabel("Score: 0");
		lblScore.setForeground(Color.LIGHT_GRAY);
		lblScore.setBounds(190, 11, 97, 14);
		lblScore.setFont(fonts);
		panel.add(lblScore);
		
		
		lblCPS = new JLabel("Char per Sec: 0");
		lblCPS.setFont(fonts);
		lblCPS.setForeground(Color.LIGHT_GRAY);
		lblCPS.setBounds(297, 11, 140, 14);
		panel.add(lblCPS);
		
		lblWordCount = new JLabel("Word Count: 0");
		lblWordCount.setBounds(190, 32, 140, 14);
		lblWordCount.setForeground(Color.LIGHT_GRAY);
		lblWordCount.setFont(fonts);
		panel.add(lblWordCount);
		
		fonts=fonts.deriveFont(125f);
		JLabel label = new JLabel("3");
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(fonts);
		label.setBounds(400-label.getPreferredSize().width/2, 250-label.getPreferredSize().height/2, label.getPreferredSize().width, label.getPreferredSize().height);
		Timer t = new Timer();
		playSound("start.wav");
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if(label.getText().equals("Go")) {
					label.setVisible(false);
					START=true;
					play();
					t.cancel();
					t.purge();
					return;
				}
				label.setText((Integer.parseInt(label.getText())-1) + "");
				if(Integer.parseInt(label.getText()) == 0) {
					label.setText("Go");
					label.setBounds(400-label.getPreferredSize().width/2, 250-label.getPreferredSize().height/2, label.getPreferredSize().width, label.getPreferredSize().height);	
				}
				
			}
			
		}, 0,1000);
		
		Random rnd = new Random();
		
		fonts = fonts.deriveFont(18f);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent key) {
				if(START) {
					if(key.getKeyCode() >=65 && key.getKeyCode() <=90) {
						curWord += key.getKeyChar();
					}
					if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						if(curWord.length()>0)
						curWord = curWord.substring(0, curWord.length()-1);
					}
					lblWord.setText("Word: " + curWord);
					int count = 0;
					charpersecond++;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) { }
			
		});
		contentPane.add(label);
	}
	
	public void play() {
		Random rnd = new Random();
			playMenuSound("battle.mp3");
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					int RNG = rnd.nextInt(10)+1;
					for(JLabel ships : spaceword) {
						ships.setBounds(ships.getX()+rnd.nextInt(3)+2,ships.getY(),ships.getPreferredSize().width,ships.getPreferredSize().height);
						if(ships.getX() >= 800) {
							FAIL = true;
							START = false;
						}
						if(FAIL) {
							for(JLabel fail : spaceword) {
								fail.setVisible(false);
							}
							try{
								m.stop();
								m = null;
								mx.stop();
								mx = null;
								FailMenu fm = new FailMenu(lblScore,lblWordCount);
								fm.setVisible(true);
								dispose();
							}catch(Exception e) {
								
							}
							timer.cancel();
							timer.purge();
							return;
						}
					}
					if(currentWord.contains(curWord)) {
						spaceword.get(currentWord.indexOf(curWord)).setVisible(false);
						spaceword.remove(currentWord.indexOf(curWord));
						currentWord.remove(currentWord.indexOf(curWord));
						playSound("explosion.wav");
						lblWordCount.setText("Word Count: " + (wordCount=wordCount+1));
						lblScore.setText("Score: " + (score=score+curWord.length()));
						curWord = "";
						lblWord.setText("Word: ");
					}
					if(RNG == 1 && spaceword.size() <= 20) {
						int idx = rnd.nextInt(englishWord.size());
						JLabel spaceshipLabel = new JLabel(englishWord.get(idx));
						spaceshipLabel.setFont(fonts);
						spaceshipLabel.setForeground(Color.LIGHT_GRAY);
						spaceshipLabel.setBounds(-spaceshipLabel.getPreferredSize().width,rnd.nextInt(450)+spaceshipLabel.getPreferredSize().height,spaceshipLabel.getPreferredSize().width,spaceshipLabel.getPreferredSize().height);
						spaceword.add(spaceshipLabel);
						currentWord.add(englishWord.get(idx));
						contentPane.add(spaceshipLabel);
					}
				}
			}, 0, 100);
			
			Timer cps = new Timer();
			cps.schedule(new TimerTask() {
				public void run() {
					cpslist.add(charpersecond);
					int count = 0;
					for(int i : cpslist) count+=i;
					count/=cpslist.size();
					lblCPS.setText("Char Per Sec: " + count);
					charpersecond = 0;
				}
			}, 0,1000);
	}
	
	public void readText(String path) {
		try {
			File file = new File("Lang\\" + path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				englishWord.add(st);
			}
		} catch(IOException e) {
			
		}
	}
	
	public void playSound(String path) {
		new javafx.embed.swing.JFXPanel();
	    String uriString = new File("Sounds\\"+path).toURI().toString();
	    mx = new MediaPlayer(new Media(uriString));
	    mx.play();
	}
	
	public void playMenuSound(String path) {
		new javafx.embed.swing.JFXPanel();
	    String uriString = new File("Sounds\\"+path).toURI().toString();
	    Media media = new Media(uriString);
	    m = new MediaPlayer(media);
	    m.setOnEndOfMedia(new Runnable() {
	    	public void run() {
	    			m.seek(Duration.ZERO);
	    	}
	    });
	    m.play();
	}
}
