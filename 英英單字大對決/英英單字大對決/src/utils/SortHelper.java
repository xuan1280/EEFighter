package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortHelper {

	public static List<Character> sortPlayerLetters(String answer, List<Character> letters){
		List<Character> tempAnswer = new ArrayList<>();
		List<Character> tempAnswerCopy = new ArrayList<>();
		for (int i = 0; i < answer.length(); i++) {
			tempAnswer.add(answer.charAt(i));
			tempAnswerCopy.add(answer.charAt(i));
		}
		List<Character> match = new ArrayList<>();
		List<Character> nonsenses = new ArrayList<>();
		
		for (int j = 0; j < letters.size(); j++) {
			Character letter = Character.valueOf(letters.get(j));
			if (tempAnswer.contains(letter)) {
				match.add(letter);
				tempAnswer.remove(letter);
			}
			else 
				nonsenses.add(letter);
		}
		
		Collections.sort(match, (letter1, letter2) -> {
			int index1 = tempAnswerCopy.indexOf(letter1);
			int index2 = tempAnswerCopy.indexOf(letter2);
			if (index1 < index2)
				tempAnswerCopy.remove(index1);
			return index1 - index2;
		});
		
		StringBuilder stringBuilder = new StringBuilder();
		for (Character character : match)
			stringBuilder.append(character);
		for (Character character : nonsenses)
			stringBuilder.append(character);
		
		for (int i = 0; i < stringBuilder.length(); i++)
			letters.add(stringBuilder.charAt(i));
		
		return letters;
	}
	
}
