import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.lang.Math;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 30; // Set a unit for a game's object
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 150;
	
	final int x[] = new int[GAME_UNITS]; // x coordinates of the snake's body
	final int y[] = new int[GAME_UNITS]; // y coordinates of the snake's body
	int bodyParts = 6; // The snake starts with 8 parts of body
	char direction; // The snakes starts by going RIGHT
	boolean running = false;
	
	Timer timer;
	Random random;
	Prey newPrey;
		
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Set the size for the panel
		this.setBackground(Color.black); // Set color for the panel
		this.setFocusable(true);
		this.addKeyListener(new KeyInput());
		newPrey = new Prey();
		x[0] = 0;
		y[0] = 0;
		startGame();
	}
	
	
	public void startGame() {
		running = true;
		timer = new Timer(DELAY, this); // Controls the speed of the game
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (running){
			newPrey.drawPrey(g);
			for (int i = 0; i < this.bodyParts; i++) {
				if (i == 0) {
					g.setColor(new Color(0, 100, 8));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					if ((100+20*i) > 255) {
						g.setColor(new Color(0, 255, 8));
					}
					else {
						g.setColor(new Color(0, 100 + 20*i, 8));
					}
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
		}
		else {
			gameOver(g);
		}
	}
	
	
	public void move() {
		for (int i = this.bodyParts; i > 0; i--) { // Update everything except the head of the snake
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		if (x[0] > newPrey.getPreyX() && direction != 'R') {
			x[0] -= UNIT_SIZE;
			direction = 'L';
		}
		else if (x[0] < newPrey.getPreyX() && direction != 'L') {
			x[0] += UNIT_SIZE;
			direction = 'R';
		}
		else if (y[0] > newPrey.getPreyY() && direction != 'D') {
			y[0] -= UNIT_SIZE;
			direction = 'U';
		}
		else if (y[0] < newPrey.getPreyY() && direction != 'U') {
			y[0] += UNIT_SIZE;
			direction = 'D';
		}
	}
	
	
	public void checkPrey() {	
		// Checks if the prey collides with the snake's body
		for (int i = this.bodyParts; i > 0; i--) {
			if (x[i] == newPrey.getPreyX() && y[i] == newPrey.getPreyY()) {
				running = false;
			}
		}
	}
	
	public void checkCollisions() {
		
		// Checks if the snake bites itself
		for (int i = this.bodyParts; i > 0; i--) {
			if (x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		
		// Checks of the snake touches the borders
		if (x[0] < 0 || x[0] > SCREEN_WIDTH - UNIT_SIZE) {
			running = false;
		}
		if (y[0] < 0 || y[0] > SCREEN_HEIGHT - UNIT_SIZE) {
			running = false;
		}

		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) { // Display the GAME OVER screen
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
	}
	
	public void startMenu(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.ITALIC, 25));
		g.drawString("1. Easy", 0, g.getFont().getSize());
		g.drawString("2. Medium", UNIT_SIZE, g.getFont().getSize() + UNIT_SIZE);
		g.drawString("3. Hard", 2*UNIT_SIZE, g.getFont().getSize() + 2*UNIT_SIZE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (running) {
			this.move();
			this.checkPrey();
			this.checkCollisions();
		}
		repaint(); // If the game is no longer running, call repaint
	}
}
