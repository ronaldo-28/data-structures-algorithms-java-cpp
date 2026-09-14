class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        var friendToPair = new int[n];
        for (var pair : pairs) {
            friendToPair[pair[0]] = pair[1];
            friendToPair[pair[1]] = pair[0];
        }

        int unhappy = 0;

        for (var pair : pairs) {
            if (isUnhappy(pair[0], preferences, friendToPair)) {
                unhappy += 1;
            }
            if (isUnhappy(pair[1], preferences, friendToPair)) {
                unhappy += 1;
            }
        }

        return unhappy;
    }

    private boolean isUnhappy(int friend, int[][] friendToPriorities, int[] friendToPair) {
        var priorities = friendToPriorities[friend];
        int pair = friendToPair[friend];

        for (int i = 0; priorities[i] != pair; ++i) {
            int betterPair = priorities[i];
            var betterPairPreferences = friendToPriorities[betterPair];
            if (getPriority(betterPairPreferences, friend) > getPriority(betterPairPreferences, friendToPair[betterPair])) {
                return true;
            }
        }
        return false;
    }

    private int getPriority(int[] preferences, int person) {
        for (int i = 0; i < preferences.length; ++i) {
            if (preferences[i] == person) {
                return preferences.length - i;
            }
        }
        throw new IllegalStateException();
    }
}