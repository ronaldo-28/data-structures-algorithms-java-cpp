# Write your MySQL query statement below
WITH req AS (
    SELECT DISTINCT sender_id, send_to_id
    FROM FriendRequest
),
acc AS (
    SELECT DISTINCT requester_id, accepter_id
    FROM RequestAccepted
)

SELECT
    ROUND(
        IFNULL(
            (SELECT COUNT(*) FROM acc) /
            (SELECT COUNT(*) FROM req)
        ,0)
    ,2
) AS accept_rate;