# Write your MySQL query statement below
Select Sum(Coalesce(c.apple_count, 0) + Coalesce(b.apple_count, 0)) apple_count, Sum(Coalesce(c.orange_count, 0) + Coalesce(b.orange_count, 0)) orange_count
From Boxes b Left Join Chests c On b.chest_id = c.chest_id