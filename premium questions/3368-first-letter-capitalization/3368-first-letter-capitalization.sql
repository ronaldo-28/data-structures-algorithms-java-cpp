# Write your MySQL query statement below
WITH RECURSIVE cte AS (
    
        -- first word
    SELECT
        content_id,
        content_text,
        1 AS pos,

            -- SUBSTRING_INDEX(string, delimiter, count)
        SUBSTRING_INDEX(content_text, ' ', 1) AS word,

            -- SUBSTRING(string, start_position, length)
        SUBSTRING(            
            content_text,
            LENGTH(SUBSTRING_INDEX(content_text, ' ', 1)) + 2
        ) AS remaining          
    FROM user_content

            UNION ALL

        -- next words
    SELECT
        content_id,
        content_text,
        pos + 1,
        
            -- SUBSTRING_INDEX(string, delimiter, count)
        SUBSTRING_INDEX(remaining, ' ', 1),

            -- SUBSTRING(string, start_position, length)
        SUBSTRING(            
            remaining,
            LENGTH(SUBSTRING_INDEX(remaining, ' ', 1)) + 2
        )         
    FROM cte
    WHERE remaining <> ''

)

SELECT 
    content_id,
    content_text AS original_text,

    GROUP_CONCAT(

        CONCAT(
            UPPER(LEFT(word, 1)),      -- 1st letter upper case
            LOWER(SUBSTRING(word, 2))  -- from 2nd to end lower case
        )
        ORDER BY pos
        SEPARATOR  ' '

    ) AS converted_text

FROM cte 
GROUP BY content_id
ORDER BY content_id;