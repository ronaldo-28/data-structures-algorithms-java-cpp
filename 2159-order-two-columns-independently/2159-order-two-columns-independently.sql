# Write your MySQL query statement below
WITH col_ranks AS (
  SELECT
    D.first_col,
    D.second_col,
    ROW_NUMBER() OVER (ORDER BY D.first_col) AS fc_rnk,
    ROW_NUMBER() OVER (ORDER BY D.second_col DESC) AS sc_rnk
  FROM
    Data D
)
SELECT
  CR1.first_col,
  CR2.second_col
FROM
  col_ranks CR1
  INNER JOIN col_ranks CR2 ON CR1.fc_rnk = CR2.sc_rnk
ORDER BY
  CR1.fc_rnk;