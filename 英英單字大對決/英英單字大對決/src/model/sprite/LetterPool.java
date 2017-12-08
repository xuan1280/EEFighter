package model.sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.factory.SpritePrototypeFactory;

public class LetterPool implements ILetterPool {

	private static LetterPool instance = null;
	private List<Sprite> availableLetters;
	private List<Sprite> inUseLetters;
	private SpritePrototypeFactory prototypeFactory = SpritePrototypeFactory.getInstance();
	private static final byte[] lock = new byte[] {};

	public LetterPool() {
		availableLetters = new ArrayList<>();
		inUseLetters = new ArrayList<>();
		createLetters();
		shuffleLetters();
	}

	public static LetterPool getInstance() {
		if (instance == null)
			instance = new LetterPool();
		return instance;
	}

	private void shuffleLetters() {
		Collections.shuffle(availableLetters);
	}

	private void createLetters() {
		for (int i = 0; i < 2; i++) {
			availableLetters.add(prototypeFactory.createSprite(SpriteName.A));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.B));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.C));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.D));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.E));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.F));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.G));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.H));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.I));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.J));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.K));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.L));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.M));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.N));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.O));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.P));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.Q));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.R));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.S));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.T));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.U));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.V));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.W));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.X));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.Y));
			availableLetters.add(prototypeFactory.createSprite(SpriteName.Z));
		}
	}

	@Override
	public Sprite requireReusable() {
		if (availableLetters.size() > 0) {
			Sprite sprite = availableLetters.get(0);
			availableLetters.remove(0);
			inUseLetters.add(sprite);
			return sprite;
		}
		return null;
	}

	@Override
	public void relaseReusable(Sprite sprite) {
			inUseLetters.remove(sprite);
			availableLetters.add(sprite);
	}

}
