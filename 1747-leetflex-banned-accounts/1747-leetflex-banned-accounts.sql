# Write your MySQL query statement below
SELECT
    l.account_id
FROM LogInfo l
JOIN LogInfo l1
    ON l.account_id = l1.account_id
    AND l.ip_address <> l1.ip_address
WHERE l1.login BETWEEN l.login AND l.logout
GROUP BY l.account_id