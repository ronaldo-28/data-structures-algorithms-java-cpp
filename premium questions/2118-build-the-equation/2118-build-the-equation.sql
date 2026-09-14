# Write your MySQL query statement below
WITH eqn_reps AS (
  SELECT
    T.power,
    CONCAT(
      (CASE
        WHEN T.factor > 0 THEN '+'
        ELSE '-'
      END),
      (CASE
        WHEN T.power > 1 THEN CONCAT(ABS(T.factor), 'X^', T.power)
        WHEN T.power = 1 THEN CONCAT(ABS(T.factor), 'X')
        ELSE ABS(T.factor)
      END)
    ) AS eqn_rep
  FROM
    Terms T
)
SELECT
  CONCAT(GROUP_CONCAT(E.eqn_rep ORDER BY E.power DESC SEPARATOR ''), '=0') AS equation
FROM
  eqn_reps E;