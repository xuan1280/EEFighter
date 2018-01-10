package model.words;

import factory.ComponentAbstractFactory;

public class WordProductionLineFactoryImp extends WordProductionLineFactory {

	public WordProductionLineFactoryImp(ComponentAbstractFactory componentAbstractFactory) {
		super(componentAbstractFactory);
		// TODO Auto-generated constructor stub
	}
	
	public void addWordToWordRepository(String wordTxt) {
		Word word = new Word(wordTxt);
		onPreparingWord(word);
		onGetWordSound(word);
		onSavingWord(word);
	}
	
	@Override
	public void onPreparingWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetWordSound(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSavingWord(Word word) {
		// TODO Auto-generated method stub
		
	}

}
