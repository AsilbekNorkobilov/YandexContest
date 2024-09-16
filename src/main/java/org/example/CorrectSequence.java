package org.example;

import java.util.Scanner;

public class CorrectSequence {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        String sequence = scanner.nextLine();
        scanner.close();

        System.out.println(countValidSequences(N, sequence));
    }

    private static int countValidSequences(int N, String sequence) {
        if (N % 2 != 0) return 0;

        int[][] dp = new int[N + 1][N + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (sequence.charAt(i - 1) == '?') {
                    if (j > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                    if (j < N) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                } else if (sequence.charAt(i - 1) == '(' || sequence.charAt(i - 1) == '[' || sequence.charAt(i - 1) == '{') {
                    if (j > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                } else {
                    if (j < N) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        return dp[N][0];
    }
}
