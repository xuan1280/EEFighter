package model.words;

import factory.ComponentAbstractFactory;

public abstract class WordProductionLineFactory {
	protected String soundPath = "sounds/vocabulary";
	protected TTS tts;
	protected Crawler crawler;
	protected WordRepository wordRepository;
	
	public WordProductionLineFactory(ComponentAbstractFactory componentAbstractFactory) {
		wordRepository = componentAbstractFactory.getWordRepository();  
		crawler = componentAbstractFactory.getCrawler();
		tts = componentAbstractFactory.getTts();
	}
	
	public abstract void onPreparingWord(Word word);
	public abstract void onGetWordSound(Word word);
	public abstract void onSavingWord(Word word);
	
}
