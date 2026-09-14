# Write your MySQL query statement below
WITH all_friendships AS (
  SELECT F1.user1_id AS friend, F1.user2_id AS friend_with FROM Friendship F1
  UNION ALL
  SELECT F2.user2_id AS friend, F2.user1_id AS friend_with FROM Friendship F2
)
SELECT
  AF1.friend AS user1_id,
  AF2.friend AS user2_id,
  COUNT(*) AS common_friend
FROM
  all_friendships AF1
  INNER JOIN all_friendships AF2
    ON AF1.friend_with = AF2.friend_with
      AND AF1.friend < AF2.friend
WHERE
  EXISTS (SELECT 1 FROM Friendship F WHERE F.user1_id = AF1.friend AND F.user2_id = AF2.friend)
GROUP BY
  AF1.friend, AF2.friend
HAVING
  common_friend >= 3;