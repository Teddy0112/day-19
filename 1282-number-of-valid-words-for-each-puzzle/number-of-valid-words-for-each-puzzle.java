import java.util.*;

class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> freq = new HashMap<>();

        
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }

            
            if (Integer.bitCount(mask) <= 7) {
                freq.put(mask, freq.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (String puzzle : puzzles) {
            int mask = 0;
            for (char c : puzzle.toCharArray()) {
                mask |= 1 << (c - 'a');
            }

            int first = 1 << (puzzle.charAt(0) - 'a');
            int count = 0;

            
            int rest = mask ^ first;
            for (int sub = rest; ; sub = (sub - 1) & rest) {
                int candidate = sub | first;

                count += freq.getOrDefault(candidate, 0);

                if (sub == 0) break;
            }

            ans.add(count);
        }

        return ans;
    }
}
