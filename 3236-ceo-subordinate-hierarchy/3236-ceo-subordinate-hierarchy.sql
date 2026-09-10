# Write your MySQL query statement below
WITH RECURSIVE cte AS (
    -- Anchor: the CEO
    SELECT
        employee_id   AS subordinate_id,
        employee_name AS subordinate_name,
        0             AS hierarchy_level,
        salary
    FROM Employees
    WHERE manager_id IS NULL

    UNION ALL

    -- Recursive: walk one level down each time
    SELECT
        e.employee_id,
        e.employee_name,
        c.hierarchy_level + 1,
        e.salary
    FROM Employees e
    JOIN cte c
        ON e.manager_id = c.subordinate_id
)

SELECT
    subordinate_id,
    subordinate_name,
    hierarchy_level,
    salary - (SELECT salary FROM Employees WHERE manager_id IS NULL) AS salary_difference
FROM cte
WHERE hierarchy_level > 0   -- exclude the CEO row itself
ORDER BY hierarchy_level, subordinate_id;
