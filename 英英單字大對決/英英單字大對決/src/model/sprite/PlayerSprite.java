package model.sprite;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlayerSprite extends Sprite{
	private List<Character> letters = new LinkedList<>();
	private int score = 0;

	public PlayerSprite(int w, int h, int biasWithX, int biasWithY, int bodyHeight, int bodyLength,
			SpriteName spriteName, Map<Movement, ImageSequence> imageMap) {
		super(w, h, biasWithX, biasWithY, bodyHeight, bodyLength, spriteName, imageMap);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	@Override
	public synchronized void update() {
		if (status == Status.MOVE) {
			switch (direction) {
			case NORTH:
				xy.move(0, -4);
				break;
			case SOUTH:
				xy.move(0, 4);
				break;
			case WEST:
				xy.move(-4, 0);
				break;
			case EAST:
				xy.move(4, 0);
				break;
			default:
				break;
			}
		}
		if (moveFailed(gameMap)) {
			xy.rollback();
			gameView.onHitWall(this);
		}
	}

	private boolean moveFailed(GameMap gameMap) {
		if (gameMap.outOfMap(this))
			return true;
		for (Sprite terrain : gameMap.getAllTerrains())
			if (terrain.isCollisions(this))
				return true;
		return false;
	}

	public void addLetter(Character letter) {
		letters.add(letter);
	}

	public Character popLetter(Character letter) {
		Character l = null;
		for (int i = letters.size(); i >= 0; i++) 
			if (letters.get(i) == letter) {
				letters.remove(i);
				break;
			}
		return l;
	}

	public void removeAllLetters() {
		while (!letters.isEmpty())
			letters.addAll(letters);
	}
	
	public List<Character> getLetters() {
		return letters;
	}
	
}
