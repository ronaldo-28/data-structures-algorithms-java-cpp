/**
 * // This is the FontInfo's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface FontInfo {
 *     // Return the width of char ch when fontSize is used.
 *     public int getWidth(int fontSize, char ch) {}
 *     // Return Height of any char when fontSize is used.
 *     public int getHeight(int fontSize)
 * }
 */
class Solution
{
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo)
    {
        char[] textArr = text.toCharArray();
        if (!fit(textArr, w, h, fonts[0], fontInfo))
        {
            return -1;
        }

        int l = 0;
        int r = fonts.length - 1;
        while (l < r)
        {
            int mid = r - (r - l) / 2;
            if (fit(textArr, w, h, fonts[mid], fontInfo))
            {
                l = mid;
            }
            else
            {
                r = mid - 1;
            }
        }

        return fonts[l];
    }

    private static boolean fit(char[] text, int w, int h, int fontSize, FontInfo fontInfo)
    {
        if (fontInfo.getHeight(fontSize) > h)
        {
            return false;
        }

        int textWidth = 0;
        for (char ch : text)
        {
            textWidth += fontInfo.getWidth(fontSize, ch);
            if (textWidth > w)
            {
                return false;
            }
        }

        return true;
    }
}