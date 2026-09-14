# Write your MySQL query statement below
SELECT 
    left_operand,
    operator,
    right_operand,
    IF (
        (
            CASE
                WHEN operator = '=' THEN l.value = r.value
                WHEN operator = '<' THEN l.value < r.value
                WHEN operator = '>' THEN l.value > r.value
                ELSE 0
            END
        ) = 1, 'true', 'false'
    ) AS value
FROM
    Expressions
INNER JOIN Variables AS l ON
    Expressions.left_operand = l.name
INNER JOIN Variables AS r ON
    Expressions.right_operand = r.name