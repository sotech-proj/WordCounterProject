package com.wordcounterapp.wordcounter;

import java.util.HashMap;

/** 
 * This Word Counter adds English and Non-English words and maintains a count of how many times a word has been added
 */ 
public class WordCounter {
	
	private final HashMap<String, Integer> wordToCount = new HashMap<String, Integer>(); 
	private final ITranslator translator;
	
	public WordCounter(ITranslator translator) {
		this.translator = translator;
	}
	
	/**
	 * This method adds a word to the word counter
	 * @param word the word to be added 
	 */
	public void addWord(String word) {
		
		validateInput(word);
		
		//we assume that trying to translate an English word will return the same word 
		//and trying to translate a non-English word will return the English word 
		String englishWord = translator.translate(word);
		
		wordToCount.put(englishWord.toLowerCase(), wordToCount.getOrDefault(englishWord.toLowerCase(), 0) + 1);
	}
	
	/**
	 * This method counts how many time a word has been added
	 * @param word the word to look up
	 * @return the number of times the word has been added
	 */
	public int countWord(String word) {
		validateInput(word);
		String englishWord = translator.translate(word);
		return wordToCount.getOrDefault(englishWord.toLowerCase(), 0);
	}
	
	private void validateInput(String word) {
		if(!word.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Non-alphabetic characters are not allowed");
		}
	}
}

