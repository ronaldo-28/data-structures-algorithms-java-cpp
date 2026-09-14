# Write your MySQL query statement below
WITH cte1 AS (
    -- Friends where user 1 is stored as user1
    SELECT
        user2_id
    FROM Friendship
    WHERE user1_id = 1
),

cte2 AS (
    -- Friends where user 1 is stored as user2
    SELECT
        user1_id
    FROM Friendship
    WHERE user2_id = 1
),

cte3 AS (
    -- Combine both friend lists into one column
    SELECT
        cte1.user2_id AS one_friend_id
    FROM cte1

    UNION ALL

    SELECT
        cte2.user1_id
    FROM cte2
)

SELECT 
    DISTINCT l.page_id AS recommended_page
FROM Likes l

-- Get pages liked by all friends of user 1
JOIN cte3 c
    ON c.one_friend_id = l.user_id

-- Exclude pages that user 1 already likes
WHERE l.page_id NOT IN (
    SELECT
        page_id
    FROM Likes
    WHERE user_id = 1
);