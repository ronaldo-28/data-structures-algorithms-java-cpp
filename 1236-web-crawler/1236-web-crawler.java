/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

class Solution {
    Set<String> set = new HashSet<>();
    Set<String> visited = new HashSet<>();
    String domain;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        domain = getDom(startUrl);
        dfs(startUrl, htmlParser);
        return new ArrayList<>(set);
    }
    private void dfs(String url, HtmlParser parser) {
        visited.add(url);
        if(!check(url)) return;
        set.add(url);
        List<String> list = parser.getUrls(url);
        for(String child : list) {
            if(visited.contains(child)) continue;
            dfs(child, parser);
        }
    }
    private boolean check(String url) {
        String dom = getDom(url);
        return domain.equals(dom);
    }
    private String getDom(String url) {
        int start = url.indexOf("://");
        int end = url.indexOf("/", start + 3);
        if(end == -1) {
            end = url.length();
        }
        return url.substring(start, end);
    }
}