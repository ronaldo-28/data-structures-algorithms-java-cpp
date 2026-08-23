# Write your MySQL query statement below
WITH averages AS (
    SELECT
        event_type,
        AVG(occurrences) AS average
    FROM Events
    GROUP BY 1
)

SELECT
    business_id
FROM (
    SELECT
        e.business_id,
        COUNT(*) AS counter
    FROM Events e
    JOIN averages a
        ON a.event_type = e.event_type
    WHERE e.occurrences > a.average
    GROUP BY 1
) c
WHERE c.counter > 1;