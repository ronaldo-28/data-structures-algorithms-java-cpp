# Write your MySQL query statement below
(
    SELECT
        co.first_name,
        ca.type,
        TIME_FORMAT(SEC_TO_TIME(ca.duration), '%H:%i:%s') AS duration_formatted
    FROM Contacts co
    JOIN Calls ca
        ON co.id = ca.contact_id
    WHERE ca.type = 'incoming'
    ORDER BY 3 DESC
    LIMIT 3
)
        UNION ALL
(
    SELECT
        co.first_name,
        ca.type,
        TIME_FORMAT(SEC_TO_TIME(ca.duration), '%H:%i:%s') AS duration_formatted
    FROM Contacts co
    JOIN Calls ca
        ON co.id = ca.contact_id
    WHERE ca.type = 'outgoing'
    ORDER BY 3 DESC
    LIMIT 3
)
ORDER BY type DESC, duration_formatted DESC, first_name DESC
