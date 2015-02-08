package cmp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextArea;

import javax.swing.JTextField;

import java.awt.event.MouseMotionAdapter;
import java.awt.Panel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class AppWindow {

	private JFrame frame;
	private Board board;
	private ImageIcon xIcon, oIcon;
	private JButton[] buttons = new JButton[9];
	private final JButton newGame = new JButton ("");
	private int count = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	private void renderBoard () {
		if (board.isEndOfGame()==1) return;
		
		int[][] grid = board.getBoard();
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (grid[i][j]==0) {
					buttons [i*3+j].setIcon(xIcon);
				}
				else if (grid[i][j]==1) {
					buttons [i*3+j].setIcon(oIcon);
				}
				else {
					buttons [i*3+j].setIcon(null);
				}
			}
		}
		
		//Check for winner
		int winner = board.getWinner();
		if (winner!=-1) {
			System.out.println ("Winner is "+winner);
			JOptionPane.showMessageDialog(null, "Winner is: Player " + (winner+1));
			board.setEndOfGame();
		}
		
		if (board.isDraw()==1 && winner==-1) {
			board.setEndOfGame();
			JOptionPane.showMessageDialog(null, "Game ended: draw");
			return;
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setLayout(null);
		frame.setBackground(new Color(224, 255, 255));
		frame.setBounds(500, 200, 255, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String dir = System.getProperty("user.dir");
		xIcon = new ImageIcon (dir+"\\X.png");
		oIcon = new ImageIcon (dir+"\\O.jpg");
		board = new Board();
		
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				buttons[i*3+j] = new JButton("");
				buttons[i*3+j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int index = Arrays.asList(buttons).indexOf((JButton)arg0.getSource());
						board.makeMove(index/3, index%3);
						renderBoard();
					}
				});
				buttons[i*3+j].setBounds(j*80, i*80, 80, 80);
				frame.getContentPane().add(buttons[i*3+j]);
			}
		}
		
		newGame.setBounds(0, 240, 240, 30);
		newGame.setText("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board = new Board();
				renderBoard();
			}
		});
		
		frame.getContentPane().add(newGame);
	}

}
