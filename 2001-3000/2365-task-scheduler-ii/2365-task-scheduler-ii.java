class Solution {
      public long taskSchedulerII(int[] tasks, int space) {
          Map<Integer, Long> nextAvailable = new HashMap<>();                                                                  
          long day = 0;
          for (int task : tasks) {                                                                             
            day++;                                                            
            Long blocked = nextAvailable.get(task);
            if (blocked != null && blocked > day) {                                              
                day = blocked;
            }
            nextAvailable.put(task, day + space + 1);
        }                 
        return day;
    }                                                                                                                                
}