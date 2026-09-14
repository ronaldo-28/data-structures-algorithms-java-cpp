class Solution {
    public List<String> braceExpansionII(String expression) {
        List<String> splits = splits(expression);
        if (splits.size() == 1)
        {
            int i = 0;
            List<String> multi = new ArrayList<>();
            while (i < expression.length())
            {
                char c = expression.charAt(i);
                if (c == '{')
                {
                    int j = findClose(expression, i);
                    String sub = expression.substring(i+1, j);
                    List<String> res = braceExpansionII(sub);
                    multi = multi.isEmpty() ? res : doMulti(multi, res);
                    i = j + 1;
                }
                else
                {
                    int j = i + 1;
                    while (j < expression.length())
                    {
                        if (expression.charAt(j) >= 'a' && expression.charAt(j) <= 'z')
                            j ++;
                        else
                            break;
                    }
                    List<String> res = Collections.singletonList(expression.substring(i, j));
                    multi = multi.isEmpty() ? res : doMulti(multi, res);
                    i = j;
                }
            }
            return multi;

        }
        List<String> merge = new ArrayList<>();
        for (String split : splits) {
            List<String> subs = braceExpansionII(split);
            for (String sub : subs) {
                if (!merge.contains(sub)) {
                    merge.add(sub);
                }
            }
        }
        Collections.sort(merge);
        return merge;
    }

    public List<String> splits(String exp) {
        int i=0;
        List<String> res = new ArrayList<>();
        while (i < exp.length())
        {
            int j = i;
            int open = 0;

            while (j < exp.length())
            {
                char c = exp.charAt(j);
                if (c == ',')
                {
                    if (open == 0) break;
                }
                else if (c == '{')
                {
                    open ++;
                }
                else if (c == '}')
                {
                    open --;
                }
                j ++;
            }
            res.add(exp.substring(i, j));
            i = j + 1;
        }
        Collections.sort(res);
        return res;
    }

    private List<String> doMulti(List<String> multi, List<String> res) {
        List<String> product = new ArrayList<>();
        for (String m : multi) {
            for (String re : res) {
                product.add(m + re);
            }
        }
        Collections.sort(product);
        return product;
    }

    public int findClose(String exp, int st)
    {
        int i = st+1;
        int open = 0;
        while (i < exp.length()) {
            if (exp.charAt(i) == '}') {
                if (open > 0) {
                    open--;
                } else if (open == 0) {
                    return i;
                }
            } else if (exp.charAt(i) == '{') {
                open++;
            }
            i++;
        }
        return i;
    }
}