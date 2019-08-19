import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class InputHandler implements KeyListener {
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = true;
		}
		
		if (keyCode == KeyEvent.VK_S) {
			
			Game.player.goingDown = true;

		}
		
		if (keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = true;
		}
		
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = true;
		}
		
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = false;
		}
		
		if (keyCode == KeyEvent.VK_S) {
			
			Game.player.goingDown = false;

		}
		
		if (keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = false;
		}
		
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = false;
		}
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
