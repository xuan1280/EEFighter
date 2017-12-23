package model.sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Question;

public class LetterManager {
	private List<Sprite> letters = new ArrayList<>();
	private LetterCreateListener letterCreateListener;
	private LetterPool letterPool;
	private GameMap gameMap;
	private PlayerSprite player1;
	private PlayerSprite player2;
	private boolean windowClosed;
	private boolean gameOver;
	
	public LetterManager(GameMap gameMap, LetterPool letterPool, PlayerSprite player1, PlayerSprite player2) {
		this.letterPool = letterPool;
		this.gameMap = gameMap;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void setLetterCreateListener(LetterCreateListener letterCreateListener) {
		this.letterCreateListener = letterCreateListener;
	}
	
	public void onNextQuestion(Question question) {
		letterPool.shuffleObjects(question);
	}
	
	public void createLetter() {
		new Thread() {
			public void run() {
				while (!windowClosed && !gameOver) {
					try {
						Thread.sleep(1750);
						Sprite sprite = getLetter();
						letters.add(sprite);
						letterCreateListener.onCreateLetters(letters);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	private Sprite getLetter() {
		Sprite sprite = letterPool.get();
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(gameMap.getWidth());
			y = random.nextInt(gameMap.getHeight());
		} while (hasLetter(x, y) || !hasGrass(x, y) || hasNearByPlayer(x, y));
		sprite.setXY(x * GameMap.ITEM_SIZE, y * GameMap.ITEM_SIZE);
		return sprite;
	}
	
	private boolean hasNearByPlayer(int x, int y) {
		int range = GameMap.ITEM_SIZE * 2;
		int X = x * GameMap.ITEM_SIZE;
		int Y = y * GameMap.ITEM_SIZE;
		int player1X = player1.getX();
		int player1Y = player1.getY();
		int player2X = player2.getX();
		int player2Y = player2.getY();
		System.out.printf("l:%d, %d\np1:%d, %d\np2:%d, %d\n", X, Y, player1X, player1Y, player2X, player2Y);
		if ((X > player1X - range && X < player1X + range && Y > player1Y - range && Y < player1Y + range))
			return true;
		if (X > player2X - range && X < player2X + range && Y > player2Y - range && Y < player2Y + range)
			return true;
		return false;
	}
	
	private boolean hasLetter(int x, int y) {
		for (Sprite letter : letters)
			if (letter.getX() == x * GameMap.ITEM_SIZE && letter.getY() == y * GameMap.ITEM_SIZE)
				return true;
		return false;
	}
	
	private boolean hasGrass(int x, int y) {
		return gameMap.getSprite(x, y).getSpriteName() == SpriteName.GRASS;
	}
	
	public void releaseLetter(Sprite sprite) {
		letterPool.release(sprite);
	}
	
	public void releaseLetters(List<Sprite> sprites) {
		for (Sprite sprite : sprites) 
			letterPool.release(sprite);
	}
	
	public void windowClosed() {
		windowClosed = true;
	}
	
	public void gameOver() {
		gameOver = true;
	}
	
	public interface LetterCreateListener {
		void onCreateLetters(List<Sprite> letters);	
	}
	
}
