# Write your MySQL query statement below
SELECT REGEXP_SUBSTR(tweet,'\#[a-zA-Z]+') hashtag,COUNT(*) hashtag_count FROM Tweets
WHERE DATE_FORMAT(tweet_date,'%Y-%m')='2024-02'
GROUP BY 1
ORDER BY 2 DESC,1 DESC
LIMIT 3