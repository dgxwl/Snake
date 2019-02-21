package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size;
	
	public Snake() {
		Node node1 = new Node(25, 25, Direction.L);
		head = node1;
		Node node2 = new Node(26, 25, Direction.L);
		node1.next = node2;
		node2.pre = node1;
		Node node3 = new Node(27, 25, Direction.L);
		node2.next = node3;
		node3.pre = node2;
		tail = node3;
		size = 3;
	}
	
	public void addToTail() {
		Node node;
		switch (tail.direction) {
		case U:
			node = new Node(tail.col, tail.row + 1, tail.direction);
			node.pre = tail;
			tail.next = node;
			tail = node;
			break;
		case D:
			node = new Node(tail.col, tail.row - 1, tail.direction);
			node.pre = tail;
			tail.next = node;
			tail = node;
			break;
		case L:
			node = new Node(tail.col + 1, tail.row, tail.direction);
			node.pre = tail;
			tail.next = node;
			tail = node;
			break;
		case R:
			node = new Node(tail.col - 1, tail.row, tail.direction);
			node.pre = tail;
			tail.next = node;
			tail = node;
			break;
		}
		size++;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		for (Node n = head; n != null; n = n.next) {
			g.drawRect(n.col * World.BLOCK_SIZE, n.row * World.BLOCK_SIZE, World.BLOCK_SIZE, World.BLOCK_SIZE);
		}
	}
	
	public Direction getHeadDirection() {
		return head.direction;
	}
	
	public void toLeft() {
		head.direction = Direction.L;
	}
	public void toRight() {
		head.direction = Direction.R;
	}
	public void toUp() {
		head.direction = Direction.U;
	}
	public void toDown() {
		head.direction = Direction.D;
	}
	
	public void isEat(Food food) {
		if (head.col == food.getCol() && head.row == food.getRow()) {
			addToTail();
			changeFoodPlace(food);
		}
	}
	
	public boolean isDead() {
		if (head.col < 0 || head.col >= World.COLS || head.row < 0 || head.row >= World.ROWS) {
			return true;
		}
		for (Node n = head.next; n != null; n = n.next) {
			if (head.col == n.col && head.row == n.row) {
				return true;
			}
		}
		return false;
	}
	
	public void changeFoodPlace(Food food) {
		Random random = new Random();
		boolean ok = true;
		int c;
		int r;
		do {
			c = random.nextInt(World.COLS);
			r = random.nextInt(World.ROWS);
			for (Node n = head; n != null; n = n.next) {
				if (c == n.col && r == n.row) {
					ok = false;
				}
			}
		} while (!ok);
		food.setCol(c);
		food.setRow(r);
	}
	
	public void update() {
		switch (head.direction) {
		case U:
			tail.direction = head.direction;
			tail.col = head.col;
			tail.row = head.row - 1;
			moveProcess();
			break;
		case D:
			tail.direction = head.direction;
			tail.col = head.col;
			tail.row = head.row + 1;
			moveProcess();
			break;
		case L:
			tail.direction = head.direction;
			tail.col = head.col - 1;
			tail.row = head.row;
			moveProcess();
			break;
		case R:
			tail.direction = head.direction;
			tail.col = head.col + 1;
			tail.row = head.row;
			moveProcess();
			break;
		}
	}
	
	private void moveProcess() {
		tail.next = head;
		head.pre = tail;
		head = tail;
		tail = tail.pre;
		tail.next = null;
	}
	
	class Node {
		private int col;
		private int row;
		private Direction direction;
		private Node pre;
		private Node next;
		
		public Node(int col, int row, Direction direction) {
			this.col = col;
			this.row = row;
			this.direction = direction;
		}
	}
}
