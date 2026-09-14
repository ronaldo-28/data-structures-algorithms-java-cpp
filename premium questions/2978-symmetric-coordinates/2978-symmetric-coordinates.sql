# Write your MySQL query statement below
WITH
    Coords AS (
        SELECT 
            *,
            ROW_NUMBER() OVER(ORDER BY X ASC, Y ASC) AS id
        FROM 
            Coordinates
    ), cte AS (
        SELECT
            LeftCoords.X,
            LeftCoords.Y
        FROM Coords AS LeftCoords
        INNER JOIN Coords AS RightCoords
            ON LeftCoords.X = RightCoords.Y
            AND LeftCoords.Y = RightCoords.X
            AND LeftCoords.id != RightCoords.id
        WHERE LeftCoords.X <= LeftCoords.Y
    )

SELECT
    DISTINCT X, Y
FROM cte 
ORDER BY X ASC, Y ASC