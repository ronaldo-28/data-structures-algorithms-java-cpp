class VideoSharingPlatform {

    private static class Video {
        String content;
        int likes;
        int dislikes;
        int views;
        
        Video (String content){
            this.content = content;
            this.likes = 0;
            this.dislikes = 0;
            this.views = 0;
        }
    }

    private List<Video> videos;
    private PriorityQueue<Integer> freeIds;
    private int nextId;

    public VideoSharingPlatform() {
        videos = new ArrayList<>();
        freeIds = new PriorityQueue<>();
        nextId = 0;
    }
    
    public int upload(String video) {
        int id;

        if(!freeIds.isEmpty()) {
            id = freeIds.poll();
            videos.set(id, new Video(video));
        } else {
            id = nextId++;
            videos.add(new Video( video ));
        }
        
        return id;
    }
    
    public void remove(int videoId) {
        if(!isValid(videoId))
            return;

        videos.set(videoId, null);
        freeIds.offer(videoId);
        
    }
    
    public String watch(int videoId, int startMinute, int endMinute) {
        if(!isValid(videoId))
            return "-1";

        Video video = videos.get(videoId);
        video.views++;

        int end = Math.min(endMinute, video.content.length() - 1);

        return video.content.substring(startMinute, end + 1);
        
    }

    
    public void like(int videoId) {

        if(!isValid(videoId))
            return;

        videos.get(videoId).likes++;
        
    }
    
    public void dislike(int videoId) {
        if(!isValid(videoId))
            return;
        videos.get(videoId).dislikes++;
        
    }
    
    public int[] getLikesAndDislikes(int videoId) {

        if(!isValid(videoId))
            return new int[]{-1};

        Video video = videos.get(videoId);
        return new int[]{video.likes, video.dislikes};
        
    }
    
    public int getViews(int videoId) {

        if(!isValid(videoId))
            return -1;

        return videos.get(videoId).views;
    }

    private boolean isValid(int videoId) {
        return videoId >= 0 && videoId < videos.size() && videos.get(videoId) != null;
    }
}

/**
 * Your VideoSharingPlatform object will be instantiated and called as such:
 * VideoSharingPlatform obj = new VideoSharingPlatform();
 * int param_1 = obj.upload(video);
 * obj.remove(videoId);
 * String param_3 = obj.watch(videoId,startMinute,endMinute);
 * obj.like(videoId);
 * obj.dislike(videoId);
 * int[] param_6 = obj.getLikesAndDislikes(videoId);
 * int param_7 = obj.getViews(videoId);
 */