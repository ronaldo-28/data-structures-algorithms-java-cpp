# Write your MySQL query statement below
-- Create a table with all friend pairs
WITH ALL_PAIRS AS (
    SELECT user_id1 AS user_id,
        user_id2 AS friend_id
    FROM Friends
    UNION ALL 
    SELECT user_id2 AS user_id,
        user_id1 AS friend_id
    FROM Friends
),
-- Create a table with friend pairs that shared mutual friends
SHARED_FRIEND_PAIRS AS (
    SELECT A1.user_id AS user_id1,
        A2.user_id AS user_id2
    FROM ALL_PAIRS A1
    JOIN ALL_PAIRS A2
    ON A1.friend_id = A2.friend_id
)
SELECT user_id1, user_id2
FROM Friends
WHERE (user_id1, user_id2) NOT IN (
    SELECT user_id1, user_id2
    FROM SHARED_FRIEND_PAIRS
)
ORDER BY user_id1, user_id2