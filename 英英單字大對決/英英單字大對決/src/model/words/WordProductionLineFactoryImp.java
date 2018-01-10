package model.words;

import factory.ComponentAbstractFactory;

public class WordProductionLineFactoryImp extends WordProductionLineFactory {

	public WordProductionLineFactoryImp(ComponentAbstractFactory componentAbstractFactory) {
		super(componentAbstractFactory);
	}
	
	@Override
	public Word onPreparingWord(Word word) throws WordNotExistException {
		return crawler.crawlWordAndGetSentence(word.getWord());
	}

	@Override
	public Word onGetWordSound(Word word) throws TTSException {
		String soundPath = tts.saveWordTTS(path, word.getWord());
		word.setSoundPath(soundPath);
		return word;
	}

}
