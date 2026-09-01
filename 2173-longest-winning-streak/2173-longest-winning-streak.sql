# Write your MySQL query statement below
WITH base_data AS (
  SELECT
    M1.*,
    ROW_NUMBER() OVER (PARTITION BY M1.player_id ORDER BY M1.match_day) AS match_seq_num
  FROM
    Matches M1
), grps AS (
  SELECT
    BD.*,
    BD.match_seq_num - ROW_NUMBER() OVER (PARTITION BY BD.player_id ORDER BY BD.match_day) AS grp
  FROM
    base_data BD
  WHERE
    BD.result = 'Win'
), consec_counts AS (
  SELECT
    G.player_id,
    COUNT(G.grp) AS consec_count,
    ROW_NUMBER() OVER(PARTITION BY G.player_id ORDER BY COUNT(G.grp) DESC) AS consec_rnk
  FROM
    grps G
  GROUP BY
    G.player_id, G.grp
)
SELECT
  M2.player_id,
  (CASE WHEN CC.player_id IS NULL THEN 0 ELSE CC.consec_count END) AS longest_streak
FROM
  Matches M2
  LEFT JOIN consec_counts CC ON M2.player_id = CC.player_id
WHERE
  CC.consec_rnk = 1 OR CC.player_id IS NULL
GROUP BY
  M2.player_id, longest_streak;