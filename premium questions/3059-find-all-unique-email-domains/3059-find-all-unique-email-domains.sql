# Write your MySQL query statement below
SELECT * FROM (SELECT domain AS email_domain, COUNT(*) AS count FROM (SELECT domain FROM (SELECT SUBSTRING_INDEX(email, '@', -1) AS domain FROM Emails) a
WHERE domain LIKE '%.com') b GROUP BY domain) t ORDER BY email_domain