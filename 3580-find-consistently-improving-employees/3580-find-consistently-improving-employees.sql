WITH ranked AS (
        SELECT employee_id, review_date, rating,
                   ROW_NUMBER() OVER (PARTITION BY employee_id ORDER BY review_date DESC, review_id DESC) AS rn
                       FROM performance_reviews
                       ), last_three AS (
                           SELECT employee_id,
                                      MAX(CASE WHEN rn = 1 THEN rating END) AS latest_rating,
                                                 MAX(CASE WHEN rn = 2 THEN rating END) AS middle_rating,
                                                            MAX(CASE WHEN rn = 3 THEN rating END) AS earliest_rating,
                                                                       COUNT(*) AS review_count
                                                                           FROM ranked
                                                                               WHERE rn <= 3
                                                                                   GROUP BY employee_id
                                                                                       HAVING COUNT(*) = 3
                                                                                              AND MAX(CASE WHEN rn = 1 THEN rating END) > MAX(CASE WHEN rn = 2 THEN rating END)
                                                                                                     AND MAX(CASE WHEN rn = 2 THEN rating END) > MAX(CASE WHEN rn = 3 THEN rating END)
                                                                                                     )
                                                                                                     SELECT e.employee_id, e.name, lt.latest_rating - lt.earliest_rating AS improvement_score
                                                                                                     FROM last_three AS lt
                                                                                                     JOIN employees AS e ON e.employee_id = lt.employee_id
                                                                                                     ORDER BY improvement_score DESC, e.name ASC;# Write your MySQL query statement below