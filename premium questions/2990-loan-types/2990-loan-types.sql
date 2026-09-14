# Write your MySQL query statement below
SELECT user_id
FROM Loans
GROUP BY user_id
HAVING SUM(loan_type = 'Refinance') >= 1
   AND SUM(loan_type = 'Mortgage') >= 1
ORDER BY user_id
