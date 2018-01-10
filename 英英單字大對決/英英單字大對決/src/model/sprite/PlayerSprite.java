package model.sprite;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Stack;

import utils.SortHelper;

public class PlayerSprite extends Sprite{
	private List<Character> letters = new LinkedList<Character>();
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

	public void addLetter(String answer, Sprite sprite) {
		letters.add(sprite.getSpriteName().toString().charAt(0));
		letters = SortHelper.sortPlayerLetters(answer, getLetters());
		// TODO ±Æ§Ç

		// letters.push(sprite);
	}

	private boolean indexIsAlreadyChange(String gottenLetter, String questionLetter) {
		return gottenLetter.equals(questionLetter) ? true : false;
	}

	public Character popLetter() {
		if (!letters.isEmpty())
			return letters.pop();
		return null;
	}

	public void removeAllLetters() {
		while (!letters.isEmpty())
			popLetter();
	}

//	public List<Sprite> getLetters() {
//		for (Sprite sprite : letters) {
//			System.out.println(sprite.spriteName.toString());
//		}
//		return letters;
//	}
	
	public List<Character> getLetters() {
		return letters;
	}

}
