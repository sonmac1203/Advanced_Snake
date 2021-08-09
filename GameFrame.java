import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame() {	
		this.add(new GamePanel());
		this.setTitle("Snake"); // Add a title for the game
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // Make the frame appear at the center of the screen
	}
}
