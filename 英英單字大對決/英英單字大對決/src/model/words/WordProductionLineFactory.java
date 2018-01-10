package model.words;

import factory.ComponentAbstractFactory;

public abstract class WordProductionLineFactory {
	protected String path = "sounds/vocabulary";
	private WordRepository wordRepository;
	protected TTS tts;
	protected Crawler crawler;
	
	public WordProductionLineFactory(ComponentAbstractFactory componentAbstractFactory) {
		wordRepository = componentAbstractFactory.getWordRepository();  
		crawler = componentAbstractFactory.getCrawler();
		tts = componentAbstractFactory.getTts();
	}
	
	public void addWordToWordRepository(String wordTxt) throws WordNotExistException, TTSException {
		Word word = new Word(wordTxt);
		word = onPreparingWord(word);
		word = onGetWordSound(word);
		onSavingWord(word);
	}
	
	public abstract Word onPreparingWord(Word word) throws WordNotExistException;
	public abstract Word onGetWordSound(Word word) throws TTSException;
	
	public void onSavingWord(Word word) {
		wordRepository.addWord(word);
	}
	
}
