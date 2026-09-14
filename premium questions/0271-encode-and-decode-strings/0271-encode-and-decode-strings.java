public class Codec {

    String str = "jsdc";
    List<String> strs = null;

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        this.strs = strs;
        return str;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        return this.strs;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));