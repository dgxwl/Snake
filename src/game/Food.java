package game;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	private int col;
	private int row;
	
	public Food(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(col * World.BLOCK_SIZE, row * World.BLOCK_SIZE, World.BLOCK_SIZE, World.BLOCK_SIZE);
	}
}
