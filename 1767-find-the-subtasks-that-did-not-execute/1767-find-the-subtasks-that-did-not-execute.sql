# Write your MySQL query statement below
WITH RECURSIVE cte AS (

    -- anchor
    SELECT
        task_id,
        1 AS subtask_id,
        subtasks_count
    FROM Tasks

        UNION ALL

    -- recursive member
    SELECT
        task_id,
        subtask_id + 1,
        subtasks_count
    FROM cte
    WHERE subtask_id < subtasks_count
)
    -- remove executed rows
SELECT
    task_id,
    subtask_id
FROM cte
WHERE (task_id, subtask_id) NOT IN (
    SELECT
        task_id, subtask_id
    FROM Executed
    )
ORDER BY task_id;

    