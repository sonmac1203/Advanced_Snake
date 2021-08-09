import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	Prey newPrey = new Prey();

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			Prey.preyX -= GamePanel.UNIT_SIZE;
			break;
		case KeyEvent.VK_RIGHT:
			Prey.preyX += GamePanel.UNIT_SIZE;
			break;
		case KeyEvent.VK_DOWN:
			Prey.preyY += GamePanel.UNIT_SIZE;
			break;
		case KeyEvent.VK_UP:
			Prey.preyY -= GamePanel.UNIT_SIZE;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
