# Write your MySQL query statement below
select a.student_id
from students a
left join courses b on a.major = b.major
left join enrollments c on a.student_id = c.student_id and b.course_id = c.course_id
group by 1
having count(distinct b.course_id) = count(distinct c.course_id)
and max(c.grade) = 'A'
order by 1