package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CrossWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int R = scanner.nextInt();
        int C = scanner.nextInt();
        scanner.nextLine();
        String[] crossword = new String[R];
        for (int i = 0; i < R; i++) {
            crossword[i] = scanner.nextLine();
        }
        List<String> words = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String[] horizontal = crossword[i].split("#");
            for (String w : horizontal) {
                if (w.length() >= 2) {
                    words.add(w);
                }
            }
        }
        for (int i = 0; i < C; i++) {
            StringBuilder vertical = new StringBuilder();
            for (int j = 0; j < R; j++) {
                if (crossword[j].charAt(i) != '#') {
                    vertical.append(crossword[j].charAt(i));
                } else {
                    if (vertical.length() >= 2) {
                        words.add(vertical.toString());
                    }
                    vertical.setLength(0);
                }
            }
            if (vertical.length() >= 2) {
                words.add(vertical.toString());
            }
        }
        Collections.sort(words);
        System.out.println(words.get(0));
    }
}
