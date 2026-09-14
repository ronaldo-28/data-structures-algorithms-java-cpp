# Write your MySQL query statement below
# Write your MySQL query statement below
WITH find_top_user AS (
    SELECT course_id, course_name first_course, completion_date, COUNT(course_id) OVER(PARTITION BY user_id) count_course, AVG(course_rating) OVER(PARTITION BY user_id) avg_course,
        LEAD(course_name) OVER(PARTITION BY user_id ORDER BY completion_date ) second_course
    FROM course_completions
)
    SELECT DISTINCT first_course , second_course, COUNT(1) OVER(PARTITION BY first_course, second_course) transition_count
    FROM find_top_user
    WHERE count_course > 4 AND avg_course >= 4 AND second_course IS NOT NULL
    ORDER BY transition_count DESC, first_course, second_course