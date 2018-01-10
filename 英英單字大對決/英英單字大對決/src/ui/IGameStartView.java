package ui;

import java.util.List;

import model.Question;
import model.sprite.Sprite;

public interface IGameStartView {
	void onNextQuestion(Question question);

	void onAnswerCorrectCleanLettersView();

	void showPlayerScore(String player, int Score);

	void onPlayerEatLetter(String string, int score, List<Character> letter);

	void onPlayerPopedLetter(String string, int score, List<Character> letter);
}
