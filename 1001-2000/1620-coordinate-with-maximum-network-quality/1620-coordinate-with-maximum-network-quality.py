class Solution:
    def bestCoordinate(self, towers: List[List[int]], radius: int) -> List[int]:

        best_quality = 0
        best_coord = [0, 0]
        rsq = radius ** 2

        def calcQ(x, y):
            q_net = 0
            for xt, yt, qt in towers:
                d = (x-xt) ** 2 + (y-yt) ** 2
                if d > rsq:
                    continue
                d = d ** 0.5
                q_net += qt // (1+d)
            return q_net
        
        q = collections.deque()
        visited = set()

        q.append((0, 0, calcQ(0, 0)))
        visited.add((0, 0))

        for xt, yt, _ in towers:
            q.append((xt, yt, calcQ(xt, yt)))
            visited.add((xt, yt))

        while q:
            x, y, q_net = q.popleft()
            if q_net > best_quality:
                best_quality = q_net
                best_coord = [x, y]
            elif q_net == best_quality:
                if x < best_coord[0]:
                    best_coord = [x, y]
                elif x == best_coord[0]:
                    best_coord = [x, min(y, best_coord[1])]
            
            for dx, dy in [(0, -1), (0, 1), (-1, 0), (1, 0)]:
                nx = x + dx
                ny = y + dy

                if 0 <= nx < 51 and 0 <= ny < 51 and (nx, ny) not in visited:
                    visited.add((nx, ny))
                    q_c = calcQ(nx, ny)
                    if q_c >= best_quality:
                        q.append((nx, ny, q_c))
        return best_coord









        # best_quality = 0.0
        # best_coord = [0, 0]
        # rsq = radius ** 2

        # for x in range(51):
        #     for y in range(51):
        #         q_net = 0
        #         for xt, yt, qt in towers:
        #             d = (x-xt) ** 2 + (y-yt) ** 2
        #             if d > rsq:
        #                 continue
        #             d = d ** 0.5
        #             q_net += qt // (1+d)
        #         if q_net > best_quality:
        #             best_quality = q_net
        #             best_coord = [x, y]
        #         elif q_net == best_quality:

        #             if x < best_coord[0]:
        #                 best_coord = [x, y]
        #             elif x == best_coord[0]:
        #                 best_coord = [x, min(y, best_coord[1])]
        
        # return best_coord


        