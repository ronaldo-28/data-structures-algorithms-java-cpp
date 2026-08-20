# Write your MySQL query statement below
WITH followers_count AS (
    SELECT
        followee,
        COUNT(*) AS followers
    FROM Follow
    GROUP BY followee
),
following_count AS (
    SELECT
        follower
    FROM Follow
    GROUP BY follower
)

SELECT
    fg.follower,
    fc.followers AS num
FROM following_count AS fg
JOIN followers_count AS fc
    ON fg.follower = fc.followee
ORDER BY 1;