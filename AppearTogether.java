package affirm;

import java.util.*;

public class AppearTogether {
    Map<String, Map<String, Integer>> map;

    public AppearTogether() {
        map = new HashMap<>();
    }

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>(Arrays.asList(new String[]{"Casper", "Purple", "Wayfair"}));
        List<String> list2 = new ArrayList<>(Arrays.asList(new String[]{"Purple", "Wayfair", "Tradesy"}));
        List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"Wayfair", "Tradesy", "Peloton"}));
        List<String> list4 = new ArrayList<>(Arrays.asList(new String[]{"Casper", "Tradesy", "Peloton"}));
        list.add(list1);
        list.add(list2);
        list.add(list3);

        AppearTogether at = new AppearTogether();
        Map<String, List<String>> res1 = at.findMostTogether(list);
        Map<String, List<String>> res2 = at.addStrings(list4);
        at.printValues(res2);
    }

    class Pair {
        String str;
        int count;
        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
    // Find the strings appear most times together with current string
    // Space : mn + max(n) where m is the size of the list, and n is the size of each list inside the high lever list
    public Map<String, List<String>> findMostTogether(List<List<String>> list) {
        Map<String, List<String>> output = new HashMap<>();
        if (list == null || list.size() == 0) {
            return output;
        }

        // Time: O(mn^2)
        for (List<String> segment : list) {
            for (String cur : segment) {
                for (String s : segment) {
                    if (s.equals(cur)) continue;
                    if (map.containsKey((cur))) {
                        Map<String, Integer> newStrCount = map.get(cur);
                        newStrCount.put(s, newStrCount.getOrDefault(s, 0) + 1);
                    } else {
                        Map<String, Integer> newStrCount = new HashMap<String, Integer>();
                        newStrCount.put(s, 1);
                        map.put(cur, newStrCount);
                    }
                }
            }
        }
        output = rankByFreqs();
        return output;
    }

    public Map<String, List<String>> rankByFreqs() {
        Map<String, List<String>> output = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.count == p2.count ? p1.str.compareTo(p2.str) : p2.count - p1.count;
            }
        });
        // O(mn^2 * logN): total mn words, each word does a rank by pq, nlog(N)
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            Map<String, Integer> countMap = entry.getValue();
            for (Map.Entry<String, Integer> e : countMap.entrySet()) {
                Pair p = new Pair(e.getKey(), e.getValue());
                pq.offer(p);
            }
            List<String> outputList = new ArrayList<>();
            Pair most = pq.poll();
            outputList.add(most.str);
            while (!pq.isEmpty() && pq.peek().count >= most.count) {
                outputList.add(pq.poll().str);
            }
            output.put(entry.getKey(), outputList);
            pq.clear();
        }
        return output;
    }

    public Map<String, List<String>> addStrings(List<String> listOfStrings){
        for (String cur : listOfStrings) {
            for (String s : listOfStrings) {
                if (s.equals(cur)) continue;
                if (map.containsKey((cur))) {
                    Map<String, Integer> newStrCount = map.get(cur);
                    newStrCount.put(s, newStrCount.getOrDefault(s, 0) + 1);
                } else {
                    Map<String, Integer> newStrCount = new HashMap<String, Integer>();
                    newStrCount.put(s, 1);
                    map.put(cur, newStrCount);
                }
            }
        }
        return rankByFreqs();
    }

    public void printValues(Map<String, List<String>> input) {
        for (Map.Entry<String, List<String>> entry : input.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append("->");
            for (int i = 0; i < entry.getValue().size(); i++) {
                sb.append(entry.getValue().get(i));
                if (i < entry.getValue().size() - 1)
                    sb.append(",");
            }
            System.out.println(sb.toString());
        }
    }
}
