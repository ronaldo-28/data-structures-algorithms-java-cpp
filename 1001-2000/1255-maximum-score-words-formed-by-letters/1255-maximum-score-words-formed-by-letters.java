import java.util.*;
import java.math.*;
import java.io.*;
class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        if (words == null || letters == null || score == null) {
            return 0;
        }

        int[] letterCount = new int[26];
        for (char letter : letters) {
            letterCount[letter - 'a']++;
        }

        return backtrack(words, letterCount, score, 0);
    }

    private int backtrack(String[] words, int[] letterCount, int[] score, int index) {
        if (index == words.length) {
            return 0;
        }

        // Option 1: Skip the current word
        int maxScore = backtrack(words, letterCount, score, index + 1);

        // Option 2: Include the current word if possible
        int currentScore = 0;
        int[] tempCount = letterCount.clone();
        boolean canForm = true;

        for (char c : words[index].toCharArray()) {
            int charIndex = c - 'a';
            if (tempCount[charIndex] > 0) {
                currentScore += score[charIndex];
                tempCount[charIndex]--;
            } else {
                canForm = false;
                break;
            }
        }

        if (canForm) {
            maxScore = Math.max(maxScore, currentScore + backtrack(words, tempCount, score, index + 1));
        }

        return maxScore;
    }
}