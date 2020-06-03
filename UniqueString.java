package affirm;

import java.util.*;

public class UniqueString {
    public UniqueString() {

    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Cheapair");
        list.add("Cheapoair");
        list.add("Peleton");
        list.add("Ton");
        UniqueString us = new UniqueString();
        us.findShortestUniqueSubstring(list);
    }

    public List<String> rankSubstringsByOccurTimes(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return output;
        }
        Map<String, List<Integer>> dict = new HashMap<>();
        Map<String, Integer> freq = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            String cur = input.get(i);
            for (int left = 0; left < cur.length(); left++) {
                for (int right = left + 1; right <= cur.length(); right++) {
                    String s = cur.substring(left, right);
                    dict.computeIfAbsent(s, k -> new ArrayList<Integer>()).add(i);
                    freq.put(s, freq.getOrDefault(s, 0) + 1);
                }
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.count == p2.count ? p1.s.compareTo(p2.s) : p1.count - p2.count);
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            Pair p = new Pair(entry.getKey(), entry.getValue());
            pq.offer(p);
        }
        List<String> ranking = new ArrayList<>();
        while (!pq.isEmpty()){
            Pair p = pq.poll();
            ranking.add(p.s + ": " + p.count);
        }

        String[] res = new String[input.size()];
        for (Map.Entry<String, List<Integer>> entry : dict.entrySet()) {
            List<Integer> idx = entry.getValue();
            for (Integer i : idx) {
                String existing = res[i];
                String key = entry.getKey();
                if (existing == null || idx.size() < freq.get(existing) || (key.length() < existing.length() && idx.size() == freq.get(existing))) {
                    res[i] = key;
                }
            }
        }

        for (int i = 0; i < input.size(); i++) {
            StringBuilder strb = new StringBuilder();
            strb.append(input.get(i));
            strb.append("->");
            strb.append(res[i]);
            strb.append(" count: " + freq.get(input.get(i)));
            output.add(strb.toString());
            System.out.println(i + ": " + output.get(i));
        }

        return output;
    }
    class Pair{
        String s;
        int count;
        public Pair(String s, int count) {
            this.s = s;
            this.count = count;
        }
    }

    public List<String> findShortestUniqueSubstring(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return output;
        }
        String[] res = new String[input.size()];
        Map<String, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String cur = input.get(i);
            for (int left = 0; left < cur.length(); left++) {
                for (int right = left + 1; right <= cur.length(); right++) {
                    String s = cur.substring(left, right);
                    dict.computeIfAbsent(s, k -> new ArrayList<Integer>()).add(i);
                }
            }
        }

        for (Map.Entry<String, List<Integer>> entry : dict.entrySet()) {
            List<Integer> idx = entry.getValue();
            if (idx.size() == 1) {
                String existing = res[idx.get(0)];
                if (existing == null || entry.getKey().length() < existing.length()) {
                    res[idx.get(0)] = entry.getKey();
                }
            }
        }
        for (int i = 0; i < input.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(input.get(i));
            sb.append("->");
            sb.append(res[i] == null ? "" : res[i]);
            output.add(sb.toString());
            System.out.println(i + ": " + output.get(i));
        }
        return output;
    }

}
