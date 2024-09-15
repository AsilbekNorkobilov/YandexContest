package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Median {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        sc.close();

        int count = 0;
        int balance = 0;
        Map<Integer, Integer> balanceCount = new HashMap<>();
        balanceCount.put(0, 1);
        boolean foundB = false;

        for (int i = 0; i < N; i++) {
            if (A[i] == B) {
                foundB = true;
            }
            if (foundB) {
                if (A[i] < B) {
                    balance--;
                } else if (A[i] > B) {
                    balance++;
                }
                count += balanceCount.getOrDefault(balance, 0);
            } else {
                if (A[i] < B) {
                    balance--;
                } else if (A[i] > B) {
                    balance++;
                }
                balanceCount.put(balance, balanceCount.getOrDefault(balance, 0) + 1);
            }
        }

        System.out.println(count);
    }
}