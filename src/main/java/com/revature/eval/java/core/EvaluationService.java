package com.revature.eval.java.core;

import java.time.*;
import java.time.temporal.Temporal;
import java.util.*;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * This solution was given.
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * Use a regular expression to split the phrase into a group of words by removing
	 * all characters that are not letters, and grouping letters together (forming a "word").
	 * Iterate through the word array and look at the first character of each word.  Use a 
	 * StringBuilder to append these characters together, then return the final result
	 * as an uppercase String.
	 */
	public String acronym(String phrase) {
		String[] words = phrase.split("[^A-Za-z]+");
		StringBuilder result = new StringBuilder();
		for(String word : words) {
			result.append(word.charAt(0));
		}
		return result.toString().toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	
	/*
	 * William's Notes:
	 * Create a new method to insert all triangle sides into a Set, and then evaluate the set size.
	 * Set size should always be 1, 2 or 3.  Each value has one corresponding valid triangle type,
	 * except for Isosceles (which also considers Equilateral triangles as Isosceles).
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (getNumberOfUniqueSides() == 1);
		}

		public boolean isIsosceles() {
			return (getNumberOfUniqueSides() <= 2);
		}

		public boolean isScalene() {
			return (getNumberOfUniqueSides() == 3);
		}
		
		private int getNumberOfUniqueSides() {
			Set<Double> triangleSides = new HashSet<>();
			triangleSides.add(sideOne);
			triangleSides.add(sideTwo);
			triangleSides.add(sideThree);
			return triangleSides.size();
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * Change the string into a char array and iterate through it.
	 * Call method getScrabbleLetterScore which simply looks through a
	 * switch statement and returns the appropriate value for each char.
	 */
	public int getScrabbleScore(String string) {
		int result = 0;
		char[] word = string.toCharArray();
		for (char c : word){
			result += getScrabbleLetterScore(c);
		}
		return result;
	}
	
	public int getScrabbleLetterScore(char c) {
		c = Character.toUpperCase(c);
		switch(c) {
			case 'A':
			case 'E':
			case 'I':
			case 'O':
			case 'U':
			case 'L':
			case 'N':
			case 'R':
			case 'S':
			case 'T':
				return 1;
			case 'D':
			case 'G':
				return 2;
			case 'B':
			case 'C':
			case 'M':
			case 'P':
				return 3;
			case 'F':
			case 'H':
			case 'V':
			case 'W':
			case 'Y':
				return 4;
			case 'K':
				return 5;
			case 'J':
			case 'X':
				return 8;
			case 'Q':
			case 'Z':
				return 10;
		}
		return -1;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	
	/*
	 * William's Notes:
	 * Use a regular expression to strip all non-digits from the input string.
	 * If there is exactly one country code extra in the result string, strip it.
	 * If the result string is the wrong length, throw an error.
	 * This method is quite generous with extra invalid characters, it only cares that 
	 * the digits create a valid number.
	 */
	public String cleanPhoneNumber(String string) {
		string = string.replaceAll("\\D", "");

		if(string.length() == 11 && string.charAt(0) == '1') {
			string = string.substring(1);
		}
		if(string.length() != 10){
			throw new IllegalArgumentException();
		}
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * First split the original string into an array of words by using a 
	 * regular expression that separates unbroken groups of letters (how we
	 * arbitrarily define "words") and removes all other characters.  Then
	 * iterate through the word array.  If the word is not found in the result
	 * map, then add the word with a value of 1.  If the word is found, increase
	 * the quantity by 1.
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] words = string.split("[^A-Za-z]+");
		Map<String, Integer> wordCountMap = new HashMap<>();
		for(String word : words) {
			if(!wordCountMap.containsKey(word)) {
				wordCountMap.put(word, 1);
			} else {
				wordCountMap.put(word, wordCountMap.get(word)+1);
			}
		}
		return wordCountMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	
	/*
	 * William's Notes:
	 * Use a while loop to look at the middle element and determine whether to keep searching in the
	 * first half or the second half of the List by using Comparable values.  If the middle value of the
	 * list or "sublist" ever equals the search key, return the index.  If the sublist becomes too 
	 * small and the key is not found, return a -1 value.
	 * 
	 * This is a reasonably standard binary search implementation.  The interesting part is extending Comparable
	 * to be able to compare generics.
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int beginning = 0;
			int end = sortedList.size();
			
			while(beginning < end) {
				int middle = (beginning + end - 1)/2;
				int compareValue = sortedList.get(middle).compareTo(t);
				if(compareValue == 0) {
					return middle;
				}
				if(compareValue < 0) {
					beginning = middle+1;
					continue;
				}				
				end = middle-1;
			}
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * Split all words by spaces into a word array.  Iterate through each word
	 * and send them to custom method pigLatinizeOneWord, which uses custom methods
	 * to determine if the word starts with a trigraph, digraph or vowel, then returns
	 * the appropriate String with rules applied.  Add these "Pig Latinized" words
	 * to a StringBuilder to construct the phrase.
	 */
	public String toPigLatin(String string) {
		String[] words = string.split(" ");
		StringBuilder result = new StringBuilder();
		for (String word : words) {
			//if this is the first word
			if(result.toString().equals("")) {
				result.append(pigLatinizeOneWord(word));
				continue;
			}
			//all other words except first
			result.append(" " + pigLatinizeOneWord(word));
		}
		return result.toString();
	}
	
	public String pigLatinizeOneWord(String word) {
		if(word.length() >= 3 && isTrigraph(word.substring(0,  3))) {
			return word.substring(3) + word.substring(0, 3) + "ay";
		} 
		if(word.length() >= 2 && isDigraph(word.substring(0, 2))) {
			return word.substring(2) + word.substring(0, 2) + "ay";
		}
		if(isVowel(word.charAt(0))) {
			return word + "ay";
		} 
		return word.substring(1) + word.charAt(0) + "ay";
	}
	
	public boolean isVowel(char c) {
		c = Character.toUpperCase(c);
		switch((int) c) {
			case 65:
			case 69:
			case 73:
			case 79:
			case 85:
				return true;
		}
		return false;
	}
	
	public boolean isTrigraph(String chars) {
		List<String> validTrigraphs = Arrays.asList(
			"nth", "sch", "scr", "shr", "spl", "spr", "squ", "str", "thr"
			);
		return (validTrigraphs.contains(chars));
	}
	
	public boolean isDigraph(String chars) {
		List<String> validDigraphs = Arrays.asList(
			"bl", "br", "ch", "ck", "cl", "cr", "dr", "fl", "fr", 
			"gh", "gl", "gr", "ng", "ph", "pl", "pr", "qu", "sc", 
			"sh", "sk", "sl", "sm", "sn", "sp", "st", "sw", "th", 
			"tr", "tw", "wh", "wr"
			);
		return (validDigraphs.contains(chars));
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * Break the input integer into a character array (to find digits easily).
	 * Count the array size to get number of digits, then iterate through the array
	 * and raise each digit to the power of the total digits and keep the result total.
	 * Compare the final result total to the original input; if they match
	 * the method will return true.
	 */
	public boolean isArmstrongNumber(int input) {
		char[] charifiedDigits = String.valueOf(input).toCharArray();
		int numberOfDigits = charifiedDigits.length;
		int sumResult = 0;
		
		for(char i : charifiedDigits) {
			sumResult += Math.pow(Character.getNumericValue(i), numberOfDigits);
		}
		
		return (input == sumResult);
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * Get a List of all potential prime factors with a custom method.  Iterate through this List
	 * and check if the values divide evenly into the long value.  If it does, add the factor to the 
	 * List of found factors, replace the value of l and repeat the process until all factors are prime.
	 * 
	 * This one was frustrating and I went down a few bad rabbit holes.  I'm still not too happy with it.
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> possibleFactors = getAllPrimesUnderN((long) Math.ceil(Math.sqrt(l)));
		List<Long> foundFactors = new ArrayList<>();
		for(int i = 0; i < possibleFactors.size() ;) {
			if(l % possibleFactors.get(i) == 0) {
				foundFactors.add(possibleFactors.get(i));
				l /= possibleFactors.get(i);
			} else {
				i++;
			}
		}
		return foundFactors;
	}
	
	public List<Long> getAllPrimesUnderN(long l){
		List<Long> primes = new ArrayList<>();
		if(l < 2) return primes;
		primes.add((long) 2);		
		
		long checker = 3;
		while(checker <= l) {
			if(isPrime(checker)) {
				primes.add(checker);
			}
			checker+=2;
		}
		return primes;
	}
	
	public boolean isPrime(long l) {
		for(int i = 2; i <= l/2; i++) {
			if(l % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	
	/*
	 * William's Notes:
	 * This method analyzes each character of the String and builds a result String.  
	 * If the char is not a letter, it copies the character directly.  If it is an 
	 * uppercase or a lowercase letter, it adjusts the ASCII value of the character by the 
	 * value of the key, makes a check to see if the value wrapped around the alphabet, 
	 * and returns the ciphered character.
	 * 
	 * This could possibly be refactored to be cleaner with regular expressions, but 
	 * ASCII values work in a pinch.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuilder result = new StringBuilder();
			char[] charsToCipher = string.toCharArray();
			for(char c : charsToCipher) {
				result.append(cipherOneCharacter(c));
			}
			return result.toString();			
		}
		
		private char cipherOneCharacter(char c) {
			if((int)c >= 65 && (int)c <= 90) {
				c = (char)(c + key);
				if(c > 90) {
					return (char)(c - 26);
				}
				return c;
			} else if((int)c >= 97 && (int)c <= 122) {
				c = (char)(c + key);
				if(c > 122) {
					return (char)(c - 26);
				}
				return c;
			} else {
				return c;
			}
		}
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * This problem uses an overloaded copy of isPrime() (from problem #10).
	 * 
	 * Create a Linked List and start iterating through odd numbers and performing isPrime() checks on them.
	 * When prime numbers are found they are added to the List.  The method runs until the list reaches
	 * the size of the input variable, then returns the last value found.
	 */
	public int calculateNthPrime(int i) {
		if(i < 1) {
			throw new IllegalArgumentException();
		}
		
		int checker = 3;
		LinkedList<Integer> primes = new LinkedList<>();
		primes.add(2);
		
		while(primes.size() < i) {
			if(isPrime(checker)) {
				primes.add(checker);
			}
			checker += 2;
		}
		return primes.getLast();
	}
	
	public boolean isPrime(int input) {
		for(int i = 2; i <= input/2; i++) {
			if(input % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	
	/*
	 * William's Notes:
	 * Encoding and decoding is largely similar, the only difference is the treatment of spaces.
	 * Therefore "ciphering" the string is placed in its own method cipherString(), and 
	 * encode and decode merely run cipherString() and treat the returned String with appropriate
	 * spaces.
	 * 
	 * Ciphering the string itself follows the following rules: if a letter, then cipher it,
	 * if a digit, then print it, if anything else, skip it.
	 */
	static class AtbashCipher {
		public static char cipherSingleChar(char c){
			return (char)(122-((int)c-97));
		}
		
		public static String spacifyString(String s) {
			for(int i = 5; i < s.length(); i+=6) {
				s = s.substring(0,  i) + " " + s.substring(i);
			}
			return s;
		}
		
		public static String cipherString(String string) {
			char[] charsToCipher = string.toCharArray();
			StringBuilder resultBuilder = new StringBuilder();
			
			for(char c : charsToCipher) {
				if(((int)c >= 65 && (int)c <= 90) || ((int)c >= 97 && (int)c <= 122)) {
					resultBuilder.append(cipherSingleChar(Character.toLowerCase(c)));
				} else if((int)c >= 48 && (int)c <= 57) {
					resultBuilder.append(c);
				} else {
					continue;
				}				
			}
			return resultBuilder.toString();
		}
		
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {			
			return spacifyString(cipherString(string));			
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {			
			return cipherString(string).replaceAll(" ", "");			
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * A StringBuilder looks through the original string and strips out the digits,
	 * looking for invalid characters.  Then a simple for loop looks through the 
	 * numerical values in the StringBuilder and multiplies them appropriately to 
	 * find the total.  The method then returns whether the total is divisible by 11.
	 */
	public boolean isValidIsbn(String string) {
		StringBuilder resultBuilder = new StringBuilder();
		char[] chars = string.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			if(chars[i] == '-') {
				continue;
			} else if (i == chars.length-1 && string.charAt(string.length()-1) == 'X') {
				resultBuilder.append('X');
			} else if (!Character.isDigit(chars[i])) {
				return false;
			} else {
				resultBuilder.append(chars[i]);
			}
		}
		
		if(resultBuilder.length() != 10)
			return false;
		
		int total = 0;
		for(int i = 0; i < 10; i++) {
			if(i == 9 && resultBuilder.charAt(i) == 'X') {
				total += 10;
				break;
			}
			total += (Character.getNumericValue(resultBuilder.charAt(i)) * (10-i));
		}
		return (total % 11 == 0);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * This method iterates through the characters and stores them in a Set.
	 * If the Set size is the alphabet size, the string is a pangram.
	 */
	public boolean isPangram(String string) {
		Set<Character> letterTotal = new HashSet<>();
		char[] stringLetters = string.toCharArray();
		for (char c : stringLetters) {
			if(Character.isLetter(c))
				letterTotal.add(c);
		}
		
		return (letterTotal.size() == 26);
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * First check the input type of the Temporal object to see if it is a LocalDate or a LocalDateTime,
	 * and turn it into a LocalDateTime of the appropriate timestamp.  Return the new object adding 10^9
	 * seconds.
	 * 
	 * I don't feel fantastic about this one, it doesn't feel very "object-oriented" to just brute check
	 * the types.  I feel like there should be a way to operate on any Temporal object but I do not know
	 * how.
	 */
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime result;
		if(given instanceof LocalDate) {
			Instant instant = ((LocalDate) given).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			result = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		} else {
			result = (LocalDateTime) given;
		}

		return result.plusSeconds(1_000_000_000);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * First, iterate through each set integer, stopping to check every multiple
	 * of that value until that value exceeds i.  Once the entire set has been
	 * assessed and the results saved in a Set, iterate through the Set and add
	 * the values.
	 * 
	 * The use of an Iterator here is 100% gratuitous and silly.  I just did it
	 * because I've never used an Iterator in Java before (except for JDBC) and
	 * wanted to try it out.
	 */
	public int getSumOfMultiples(int i, int[] set) {
		Set<Integer> results = new HashSet<>();
		for(int setValue : set) {
			for(int checker = setValue; checker < i; checker += setValue) {
				results.add(checker);
			}
		}
		int total = 0;

		Iterator<Integer> iter = results.iterator();
		while(iter.hasNext()) {
			total += iter.next();
		}
		return total;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * First, iterate through every character of the string.  If char is a space, skip it, if char is 
	 * a digit, add it to a List, if it's an invalid character, return false immediately.
	 * 
	 * Take the List of Integers and run the doubling algorithm on it.  Then iterate through the List
	 * and save a total of the digits.  Finally check this total to see if it is divisible by 10 and
	 * return the result.
	 */
	public boolean isLuhnValid(String string) {
		if(string.length() < 1) return false;
		
		//validate and clean string
		List<Integer> cleanNumbers = new ArrayList<Integer>();
		for(char c : string.toCharArray()) {
			if(c == ' ') {
				continue;
			} else if(Character.isDigit(c)) {
				cleanNumbers.add(Character.getNumericValue(c));
			} else {
				return false;
			}
		}
		
		doubleEverySecondDigitFromRight(cleanNumbers);
				
		int totalOfAllDigits = 0;
		
		for(int i : cleanNumbers) {
			totalOfAllDigits += i;
		}
		
		return (totalOfAllDigits % 10 == 0);
	}
	
	private void doubleEverySecondDigitFromRight(List<Integer> numberList) {
		for(int i = numberList.size()-2; i >= 0; i-=2) {
			numberList.set(i, numberList.get(i)*2);
			if(numberList.get(i) > 9) {
				numberList.set(i, numberList.get(i)-9);
			}
		}
	}
	
	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	
	/*
	 * William's Notes:
	 * First, strip the useless words out of the string and return a List of three useful words
	 * (first number, operation, second number).  Process these useful words by turning the numbers
	 * into integers and then performing the operation on them.
	 */
	public int solveWordProblem(String string) {
		List<String> usefulWords = getUsefulWords(string);
		return calculateResult(usefulWords);
	}
	
	private List<String> getUsefulWords(String string) {
		string = string.substring(8, string.length()-1);
		List<String> result = new ArrayList<String>(Arrays.asList(string.split(" ")));
		
		if(result.size() == 4)
			result.remove(2);
		
		return result;
	}
	
	private int calculateResult(List<String> args) {
		int num1 = Integer.parseInt(args.get(0));
		int num2 = Integer.parseInt(args.get(2));
		if(args.get(1).equals("plus")) 
			return num1 + num2;
		if(args.get(1).equals("minus"))
			return num1 - num2;
		if(args.get(1).equals("multiplied"))
			return num1 * num2;
		return num1 / num2;		
	}
}
