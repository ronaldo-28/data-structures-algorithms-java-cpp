# Write your MySQL query statement below
WITH PrevEnd AS(
    SELECT employee_id, start_time, end_time, 
        MAX(end_time) OVER(PARTITION BY employee_id ORDER BY start_time ROWS BETWEEN UNBOUNDED PRECEDING AND 1 PRECEDING) AS prev_end
    FROM Tasks
), TotalHours AS(
    SELECT employee_id, FLOOR((
        SUM(TIMESTAMPDIFF(SECOND, start_time, end_time)) - 
        SUM(
            CASE
                WHEN start_time < prev_end AND end_time >= prev_end # partial overlap
                    THEN TIMESTAMPDIFF(SECOND, start_time, prev_end) 
                WHEN start_time < prev_end AND end_time <= prev_end # entire overlap
                    THEN TIMESTAMPDIFF(SECOND, start_time, end_time)
                ELSE 0
            END
        ))/60.0/60.0) AS total_task_hours
    FROM PrevEnd
    GROUP BY employee_id
), Concurrent AS(
    SELECT t1.employee_id, t1.start_time, COUNT(*) AS concurrent_tasks
    FROM Tasks AS t1
    LEFT JOIN Tasks AS t2
    ON t1.employee_id = t2.employee_id AND t1.start_time <= t2.start_time AND t2.start_time < t1.end_time
    GROUP BY t1.employee_id, t1.start_time
)
SELECT c.employee_id, t.total_task_hours,
    MAX(c.concurrent_tasks) AS max_concurrent_tasks
FROM Concurrent AS c
LEFT JOIN TotalHours AS t
ON c.employee_id = t.employee_id
GROUP BY c.employee_id
ORDER BY c.employee_id ASC


