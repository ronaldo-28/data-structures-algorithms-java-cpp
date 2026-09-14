# Write your MySQL query statement below
WITH 
    FraudRanked AS (
        SELECT 
            *,
            PERCENT_RANK() OVER (PARTITION BY state ORDER BY fraud_score DESC) AS rnk
        FROM 
            Fraud
    )

SELECT 
    policy_id,
    state,
    fraud_score 
FROM FraudRanked
WHERE rnk <= 0.05
ORDER BY state ASC, fraud_score DESC, policy_id ASC