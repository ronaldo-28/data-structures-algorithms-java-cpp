# Write your MySQL query statement below
SELECT b.bin, IFNULL(COUNT(s.session_id),0) AS total
FROM 
    (SELECT session_id, 
           CASE WHEN duration/60<5 THEN '[0-5>'
                WHEN duration/60>=5 AND duration/60<10 THEN '[5-10>'
                WHEN duration/60>=10 AND duration/60<15 THEN '[10-15>'
                ELSE '15 or more' END AS bin
    FROM sessions) s
RIGHT JOIN 
    (SELECT '[0-5>' AS bin
     UNION ALL
     SELECT '[5-10>' AS bin
     UNION ALL
     SELECT '[10-15>' AS bin
     UNION ALL
     SELECT '15 or more' AS bin) b
        ON b.bin=s.bin
GROUP BY bin