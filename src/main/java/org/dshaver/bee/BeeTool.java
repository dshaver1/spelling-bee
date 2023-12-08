package org.dshaver.bee;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BeeTool {
    // only letters, and no 's'
    private final static Pattern BLACKLIST_PATTERN = Pattern.compile("[^abcdefghijklmnopqrtuvwyz]");
    private final static Pattern VOWELS_PATTERN = Pattern.compile("[aeiou]");
    public static void main(String[] args) {
        try {
            URL resource = BeeTool.class.getResource("/all-words.txt");
            List<String> words = Files.readAllLines(Paths.get(resource.toURI()), UTF_8);
            System.out.println("Loaded " + words.size() + " words!");

            List<String> filteredWords = words.stream()
                    .map(String::toLowerCase)
                    .filter(word -> !BLACKLIST_PATTERN.matcher(word).find())
                    .filter(word -> VOWELS_PATTERN.matcher(word).find()) // Must have at least 1 vowel
                    .filter(word -> word.length() >= 7) // We're looking for pangrams
                    .toList();

            System.out.println("Loaded " + filteredWords.size() + " filtered words!");

            // List of sorted letters
            Set<String> uniqueLetterSets = new HashSet<>();
            for (String word : filteredWords) {
                // Put characters in set to dedupe
                HashSet<Character> charSet = new HashSet<>(word.chars().mapToObj(c -> (char) c).toList());
                // Put them in list to sort
                List<Character> charList = new ArrayList<>(charSet);
                Collections.sort(charList);
                // Cobble it back together
                String sortedWord = charList.stream()
                        .map(character -> character.toString())
                        .collect(Collectors.joining());

                if (sortedWord.length() == 7) {
                    uniqueLetterSets.add(sortedWord);
                }
            }

            System.out.println("Found " + uniqueLetterSets.size() + " unique 7 letter sets!");

        } catch (Exception e) {
            System.out.println("BAD! " + e.getMessage());
        }
    }
}
