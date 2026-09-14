# Write your MySQL query statement below
WITH RECURSIVE tags AS (
    SELECT REGEXP_SUBSTR (tweet, "#[^\\s]+") AS hashtag,
           REGEXP_REPLACE(tweet, "#[^\\s]+", "", 1, 1) AS tweet
    FROM Tweets
    UNION ALL
    SELECT REGEXP_SUBSTR (tweet, "#[^\\s]+") AS hashtag,
           REGEXP_REPLACE(tweet, "#[^\\s]+", "", 1, 1) AS tweet
    FROM tags
    WHERE hashtag IS NOT NULL
)

SELECT hashtag,
       COUNT(*) AS count
FROM tags
WHERE hashtag IS NOT NULL
GROUP BY hashtag
ORDER BY count DESC, hashtag DESC
LIMIT 3