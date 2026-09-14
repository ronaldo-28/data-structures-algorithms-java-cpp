class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int positionsSize = positions.length;
        char[] dir = directions.toCharArray();
        
        int[] stack = new int[positionsSize];
        int[] map = new int[positionsSize];
        int[] ret = new int[positionsSize];
        int sp = 0, i, j;
        
        for (i = 0; i < positionsSize; i++) {
            map[i] = i;
            ret[i] = 0;
        }
        
        for (i = 1; i < positionsSize && positions[i] >= positions[i-1]; i++);
        for (j = 1; j < positionsSize && dir[j] == dir[j-1]; j++);
        
        if ((i ^ positionsSize) != 0 && (j ^ positionsSize) != 0)
            sort(0, positionsSize - 1, positions, dir, healths, map);
        
        for (i = 0; i < positionsSize; i++) {
            if (dir[i] == 'R')
                stack[sp++] = i;
            else {
                while (sp > 0 && healths[i] > 0 && dir[stack[sp-1]] == 'R') {
                    if (healths[i] > healths[stack[sp-1]]) {
                        healths[i]--;
                        sp--;
                    } else if (healths[i] == healths[stack[sp-1]]) {
                        sp--;
                        healths[i] = 0;
                    } else {
                        healths[stack[sp-1]]--;
                        if (healths[stack[sp-1]] == 0)
                            sp--;
                        healths[i] = 0;
                    }
                }
                if (healths[i] > 0)
                    stack[sp++] = i;
            }
        }
        
        for (i = 0; i < sp; i++)
            ret[map[stack[i]]] = healths[stack[i]];
        
        List<Integer> result = new ArrayList<>();
        for (i = 0; i < positionsSize; i++)
            if (ret[i] != 0)
                result.add(ret[i]);
        
        return result;
    }
    
    private void sort(int left, int right, int[] pos, char[] dir, int[] health, int[] map) {
        if (left >= right) return;
        
        int i = left, j = right;
        int pivot = pos[(left + right) / 2];
        
        while (i <= j) {
            while (pos[i] < pivot) i++;
            while (pos[j] > pivot) j--;
            
            if (i <= j)
                mx(i++, j--, pos, dir, health, map);
        }
        
        sort(left, j, pos, dir, health, map);
        sort(i, right, pos, dir, health, map);
    }
    
    private void exchange(int a, int b, int[] p) {
        int xxx = p[a];
        p[a] = p[b];
        p[b] = xxx;
    }
    
    private void mx(int a, int b, int[] p1, char[] p2, int[] p3, int[] p4) {
        exchange(a, b, p1);
        
        char tmp = p2[a];
        p2[a] = p2[b];
        p2[b] = tmp;
        
        exchange(a, b, p3);
        exchange(a, b, p4);
    }
} 