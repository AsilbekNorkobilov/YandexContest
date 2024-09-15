package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        List<String> database = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            database.add(scanner.nextLine());
        }
        int Q = scanner.nextInt();
        scanner.nextLine();
        List<String> queries = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            queries.add(scanner.nextLine());
        }
        scanner.close();

        for (String query : queries) {
            System.out.println(countActions(database, query));
        }
    }

    private static int countActions(List<String> database, String query) {
        int actions = 0;
        for (String word : database) {
            actions++; // сравнение слова
            int commonPrefixLength = getCommonPrefixLength(word, query);
            actions += commonPrefixLength;
            if (commonPrefixLength == query.length() && commonPrefixLength == word.length()) {
                break;
            }
        }
        return actions;
    }

    private static int getCommonPrefixLength(String word, String query) {
        int minLength = Math.min(word.length(), query.length());
        int i = 0;
        while (i < minLength && word.charAt(i) == query.charAt(i)) {
            i++;
        }
        return i;
    }
}
