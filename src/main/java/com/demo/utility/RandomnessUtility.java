package com.demo.utility;

import java.util.concurrent.ThreadLocalRandom;

public class RandomnessUtility {
	/**
	 * This function will return the array of random number within particular range.
	 * @param size
	 * @param min
	 * @param max
	 * @return
	 */
	public static int[] getRandomNumberArray(int size, int min, int max){
		int[] randomNumberArray = new int[size];
		for(int i=0;i<size;i++){
			randomNumberArray[i] = ThreadLocalRandom.current().nextInt(min, max);
		}
		return randomNumberArray;
	}
	
	/**
	 * Return the Random number between particular range.
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNum(int min, int max){
		int returnValue = 0;
		returnValue  = ThreadLocalRandom.current().nextInt(min, max + 1);
		return returnValue;
	}
}
