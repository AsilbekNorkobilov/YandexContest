package org.example;

import java.util.*;

public class History {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine(); // consume the newline

        // Reading the events for three civilizations
        int[] A = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] C = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Create sets for each civilization to find intersection
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Set<Integer> setC = new HashSet<>();

        for (int num : A) setA.add(num);
        for (int num : B) setB.add(num);
        for (int num : C) setC.add(num);

        // Find common events in all three sets
        Set<Integer> commonEvents = new HashSet<>(setA);
        commonEvents.retainAll(setB);
        commonEvents.retainAll(setC);

        // Convert commonEvents to list and create index mappings
        List<Integer> commonList = new ArrayList<>(commonEvents);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < commonList.size(); i++) {
            indexMap.put(commonList.get(i), i);
        }

        // Find positions of common events in A, B, C
        int[] posA = new int[N];
        int[] posB = new int[N];
        int[] posC = new int[N];
        Arrays.fill(posA, -1);
        Arrays.fill(posB, -1);
        Arrays.fill(posC, -1);

        for (int i = 0; i < N; i++) {
            if (indexMap.containsKey(A[i])) posA[i] = indexMap.get(A[i]);
            if (indexMap.containsKey(B[i])) posB[i] = indexMap.get(B[i]);
            if (indexMap.containsKey(C[i])) posC[i] = indexMap.get(C[i]);
        }

        // Calculate the number of deletions required to make all three sequences contain only the common events
        int result = findMinDeletions(posA, posB, posC, commonList.size());

        System.out.println(result);
    }

    private static int findMinDeletions(int[] posA, int[] posB, int[] posC, int k) {
        int N = posA.length;
        int minDeletions = Integer.MAX_VALUE;

        // Calculate prefix sums of the count of common events
        int[] prefixA = new int[N + 1];
        int[] prefixB = new int[N + 1];
        int[] prefixC = new int[N + 1];

        for (int i = 0; i < N; i++) {
            prefixA[i + 1] = prefixA[i] + (posA[i] != -1 ? 1 : 0);
            prefixB[i + 1] = prefixB[i] + (posB[i] != -1 ? 1 : 0);
            prefixC[i + 1] = prefixC[i] + (posC[i] != -1 ? 1 : 0);
        }

        // Check every possible window of length k
        for (int len = 1; len <= k; len += 2) {
            for (int i = 0; i + len - 1 < N; i++) {
                int end = i + len - 1;
                int commonInWindow = Math.min(
                        Math.min(prefixA[end + 1] - prefixA[i], prefixB[end + 1] - prefixB[i]),
                        prefixC[end + 1] - prefixC[i]
                );
                int deletions = N - commonInWindow;
                minDeletions = Math.min(minDeletions, deletions);
            }
        }

        return minDeletions;
    }
}
