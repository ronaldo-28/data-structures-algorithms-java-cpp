# Write your MySQL query statement below
SELECT  question_id as survey_log
FROM SurveyLog
GROUP BY 1
ORDER BY (count(answer_id) / count(case when action = 'show' then question_id ELSE null end )) desc, 1 asc
LIMIT 1