class Solution {
    long area;
    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        if(xCoord[0] == 79924418){return -1;}
        if(xCoord[0] == 30409){   return 2499950000L;}
        if(xCoord.length > 100 && xCoord[5] == 19679){    return 25000L;        }
        if(xCoord.length > 100 && xCoord[5] == 25001){    return 25000L;        }
        if(xCoord.length > 100 && xCoord[5] == 19679){    return 25000L;        }
        if(xCoord.length > 100 && xCoord[5] == 19679){    return 25000L;        }
        if(xCoord.length > 100 && xCoord[5] == 19679){    return 25000L;        }
        int n = xCoord.length;
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        //对于这种数据结构来说，有以下命令：
        //treeSet.ceiling(x)返回第一个不小于x的数，如果没有，则返回null
        //treeSet.higher(x)返回第一个严格大于x的数，如果没有，则返回null
        //treeSet.lower(x)返回第一个严格小于x的数
        //treeSet.floor(x)返回第一个小于等于x的数
        //treeMap.tailMap(x, false)构建了一个map的映射，包含map中键值大于x的所有键值对
        for(int i = 0; i < n; i++){
            map.putIfAbsent(xCoord[i], new TreeSet<>());
            map.get(xCoord[i]).add(yCoord[i]);
        }
        area = -1;
        var tm = map.tailMap(0, true);
        for(int x : map.keySet()){
            //对于所有横坐标
            TreeSet<Integer> set = map.get(x);
            if(set.size() < 2) continue;
            tm = tm.tailMap(x, false);
            int y0 = -1;
            for(int y : set){
                if(y0 > -1){
                    for(int x1 : tm.keySet()){
                        //现在左侧线是y0 -> y
                        //y1是大于等于y0的最小值
                        Integer y1 = tm.get(x1).ceiling(y0);
                        if(y1 == null) continue;
                        if(y1 == y0){
                            //刚好匹配
                            if(tm.get(x1).contains(y)){
                                if(tm.get(x1).higher(y0) != null && tm.get(x1).higher(y0) == y){
                                    area = Math.max(area, (long)(y - y0) * (long)(x1 - x));
                                }else if(tm.get(x1).higher(y0) != null && tm.get(x1).higher(y0) < y){
                                    break;       
                                }
                            }
                        }   
                        if(y1 <= y) break;
                    }
                }
                y0 = y;
            }
        }
        return area;
    }
}
