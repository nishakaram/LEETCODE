class Solution {
    private List<List<String>> result = new ArrayList<>();
    private Map<String, List<String>> parents = new HashMap<>();
    private Set<String> wordSet = new HashSet<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordSet.addAll(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) {
                            continue;
                        }

                        chars[j] = c;
                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            parents.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(word);

                            if (levelVisited.add(nextWord)) {
                                queue.offer(nextWord);
                            }

                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }

                    chars[j] = original;
                }
            }

            visited.addAll(levelVisited);
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, path);
        }

        return result;
    }

    private void backtrack(String word, String beginWord, List<String> path) {
        if (word.equals(beginWord)) {
            List<String> sequence = new ArrayList<>(path);
            Collections.reverse(sequence);
            result.add(sequence);
            return;
        }

        for (String parent : parents.getOrDefault(word, new ArrayList<>())) {
            path.add(parent);
            backtrack(parent, beginWord, path);
            path.remove(path.size() - 1);
        }
    }
}