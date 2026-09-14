# Write your MySQL query statement below
SELECT
    CASE

        WHEN (A + B <= C)
          OR (C + B <= A) 
          OR (C + A <= B) 
        THEN 'Not A Triangle'

        WHEN (A = B) AND (B = C) 
        THEN 'Equilateral'

        WHEN (A = B AND B <> C)
          OR (A = C AND C <> B) 
          OR (C = B AND B <> A) 
        THEN 'Isosceles'

        ELSE 'Scalene'

    END AS triangle_type

FROM Triangles