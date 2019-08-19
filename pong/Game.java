import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	JFrame frame; // window of the game
	
	
	public final int WIDTH = 400;
	public final int HEIGHT = WIDTH / 9 * 9;
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Pong";
	static boolean gameRunning = false;
	
	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	InputHandler ih;
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	int p1Score, p2Score;
	public Game() {
		frame = new JFrame();
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		
		ih = new InputHandler(this);
		player = new PlayerPaddle(10, 50);
		ai = new AIPaddle(getWidth() - 20, 50);
		ball = new Ball(getWidth() /2, getHeight() / 2);
	}
	
	
	public void run() {
		while(gameRunning) {
			tick();
			render();
			
			try {
				Thread.sleep(7);
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}
	
	public void tick() {
		player.tick(this);
		ai.tick(this);
		ball.tick(this);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.drawString("Player 1: " + p1Score, 0, 10);
		g.drawString("Player 2: " + p2Score, getWidth() - 70, 10);
		player.render(g);
		ai.render(g);
		ball.render(g);
		
		g.dispose();
		
		bs.show();
		
	}
	

	
}
