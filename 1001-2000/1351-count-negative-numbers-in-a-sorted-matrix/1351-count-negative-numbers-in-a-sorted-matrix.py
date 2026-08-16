class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        
        n = len(grid)
        m = len(grid[0])
        i = 0  
        j = m-1
        count = 0

        while i<n and j>=0:
            if grid[i][j]<0:
                count += n-i
                j -= 1
            else:
                i += 1
        return count