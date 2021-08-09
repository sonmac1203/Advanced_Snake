import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Prey {

	
	public static int preyX;
	public static int preyY;
	
	Prey(){
		preyX = GamePanel.SCREEN_WIDTH/2;
		preyY = GamePanel.SCREEN_HEIGHT/2;
	}
	
	public int getPreyX() {
		return preyX;
	}
	
	public int getPreyY() {
		return preyY;
	}
	

	public void drawPrey(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(preyX, preyY, GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
	}
}
