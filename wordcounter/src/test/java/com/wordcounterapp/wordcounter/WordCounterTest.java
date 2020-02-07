package com.wordcounterapp.wordcounter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
 * Test for the Word Counter 
 */ 
public class WordCounterTest {
	
	private WordCounter wordCounter;
	private ITranslator translator;
	
	@Before 
    public void before() throws Exception {
		translator = mock(ITranslator.class);
		wordCounter = new WordCounter(translator);
    }
	
	@Test
	public void testAddWordWithValidInput() {

		when(translator.translate("bake")).thenReturn("bake");
        when(translator.translate("here")).thenReturn("here");
        when(translator.translate("foo")).thenReturn("foo");
		
		wordCounter.addWord("bake"); 
		wordCounter.addWord("here");
		wordCounter.addWord("foo");
		
		assertEquals(1, wordCounter.countWord("bake")); 
		assertEquals(1, wordCounter.countWord("here")); 
		assertEquals(1, wordCounter.countWord("foo")); 
		
		wordCounter.addWord("here"); 
		wordCounter.addWord("bake");
		wordCounter.addWord("bake");
		
		assertEquals(3, wordCounter.countWord("bake")); 
		assertEquals(2, wordCounter.countWord("here")); 
		assertEquals(1, wordCounter.countWord("foo")); 
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddWordWithInValidInput() {
		
		wordCounter.addWord("bake123"); 
	}
	
	@Test
	public void testCountWordWithNonExistingEntry() { 
		
        when(translator.translate("none")).thenReturn("none");

		assertEquals(0, wordCounter.countWord("none")); 
	}
	
	@Test
	public void testAddWordWithDifferentLanguages() {

		when(translator.translate("flower")).thenReturn("flower");
        when(translator.translate("flor")).thenReturn("flower");
        when(translator.translate("blume")).thenReturn("flower");
		
		wordCounter.addWord("flower"); 
		wordCounter.addWord("flor");
		wordCounter.addWord("blume");
		
		assertEquals(3, wordCounter.countWord("flower")); 
		assertEquals(3, wordCounter.countWord("flor")); 
		assertEquals(3, wordCounter.countWord("blume")); 
	}
	
	@After
    public void after() throws Exception {
		wordCounter = null;
    }
}
