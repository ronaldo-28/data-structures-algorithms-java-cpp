# Write your MySQL query statement below
WITH MandatoryCourses AS (
    SELECT c.course_id, c.major
    FROM courses c
    WHERE c.mandatory = 'Yes'
),
ElectiveCourses AS (
    SELECT c.course_id, c.major
    FROM courses c
    WHERE c.mandatory = 'No'
),
StudentMandatory AS (
    SELECT e.student_id, s.major
    FROM enrollments e
    JOIN MandatoryCourses m ON e.course_id = m.course_id
    JOIN students s ON e.student_id = s.student_id AND s.major = m.major
    WHERE e.grade = 'A'
    GROUP BY e.student_id, s.major
    HAVING COUNT(DISTINCT e.course_id) = (SELECT COUNT(*) FROM MandatoryCourses WHERE major = s.major)
),
StudentElective AS (
    SELECT e.student_id, s.major
    FROM enrollments e
    JOIN ElectiveCourses ec ON e.course_id = ec.course_id
    JOIN students s ON e.student_id = s.student_id AND s.major = ec.major
    WHERE e.grade IN ('A', 'B')
    GROUP BY e.student_id, s.major
    HAVING COUNT(DISTINCT e.course_id) >= 2
),
AverageGPA AS (
    SELECT e.student_id, AVG(e.GPA) AS avg_gpa
    FROM enrollments e
    GROUP BY e.student_id
    HAVING AVG(e.GPA) >= 2.5
)
SELECT DISTINCT sm.student_id
FROM StudentMandatory sm
JOIN StudentElective se ON sm.student_id = se.student_id
JOIN AverageGPA ag ON sm.student_id = ag.student_id
ORDER BY sm.student_id;