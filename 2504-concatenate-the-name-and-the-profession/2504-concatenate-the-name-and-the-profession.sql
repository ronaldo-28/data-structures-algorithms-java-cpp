# Write your MySQL query statement below
SELECT person_id, CONCAT(name, '(', SUBSTRING(profession, 1, 1), ')') AS name
FROM Person p
ORDER BY person_id DESC