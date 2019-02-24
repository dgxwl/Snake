package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int COLS = 40;
	public static final int ROWS = 30;
	public static final int BLOCK_SIZE = 15;
	public static final int WIDTH = COLS * BLOCK_SIZE;
	public static final int HEIGHT = ROWS * BLOCK_SIZE;
	
	private Snake snake = new Snake();
	private Food food = new Food(13, 13);
	
	public void start() {
		KeyAdapter l = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (snake.getHeadDirection() == Direction.D) {
						return ;
					}
					snake.toUp();
					break;
				case KeyEvent.VK_DOWN:
					if (snake.getHeadDirection() == Direction.U) {
						return ;
					}
					snake.toDown();
					break;
				case KeyEvent.VK_LEFT:
					if (snake.getHeadDirection() == Direction.R) {
						return ;
					}
					snake.toLeft();
					break;
				case KeyEvent.VK_RIGHT:
					if (snake.getHeadDirection() == Direction.L) {
						return ;
					}
					snake.toRight();
					break;
				}
			}
		};
		this.addKeyListener(l);
	}
	
	public void update() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				snake.update();
				snake.isEat(food);
				if (snake.isDead()) {
					System.exit(0);
				}
				
				repaint();
			}
		}, 0, 100);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		for (int i = 1; i < ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, WIDTH, BLOCK_SIZE * i);
		}
		for (int i = 1; i < COLS; i++) {
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, HEIGHT);
		}
		
		food.draw(g);
		snake.draw(g);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("snake");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		World world = new World();
		frame.add(world);
		frame.setVisible(true);
		
		world.start();
		world.requestFocus();
		world.update();
	}
}
