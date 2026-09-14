class Trie {
    static class Node {
        Node sub;
        Node[] children;
        boolean isFolder;

        Node() {
            sub = null;
            children = new Node[26];
            isFolder = false;
        }
    }

    Node root;

    Trie() {
        root = new Node();
    }

    boolean check(String path) {
        Node node = root;

        for (int i = 0, n = path.length(); i < n; i++) {
            char c = path.charAt(i);

            if (c == '/') {
                if (node.isFolder) {
                    return false;
                }
                else {
                    if (node.sub == null) {
                        node.sub = new Node();
                    }
                    node = node.sub;
                }
            }
            else {
                if (node.children[c-'a'] == null) {
                    node.children[c-'a'] = new Node();
                }
                node = node.children[c-'a'];
            }
        }

        node.isFolder = true;
        return true;
    }
}

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        ArrayList<ArrayList<String>> map = new ArrayList();

        for (int i = 0; i <= 50; i++) {
            map.add(new ArrayList());
        }
        
        for (String path : folder) {
            map.get(countSlashes(path)).add(path);
        }

        Trie trie = new Trie();
        ArrayList<String> list = new ArrayList();

        for (ArrayList<String> pathList : map) {
            for (String path : pathList) {
                if (trie.check(path)) {
                    list.add(path);
                }
            }
        }

        return list;
    }

    int countSlashes(String s) {
        int count = 0;

        for (int i = 0, n = s.length(); i < n; i++) {
            if (s.charAt(i) == '/') {
                count++;
            }
        }

        return count;
    }
}