# Write your MySQL query statement below
WITH RECURSIVE

    # Add a "previous bus time" column:
    upd_buses AS (
        SELECT
            B.bus_id,
            B.arrival_time,
            B.capacity,
            COALESCE(LAG(B.arrival_time) OVER (ORDER BY B.arrival_time), 0) AS prev_bus_time
        FROM Buses B
    ),

    # For each bus, add a new_pax column showing how many new passengers
    # arrived to the bus stop since the last bus
    running_totals AS (
        SELECT
            B.bus_id,
            B.arrival_time,
            B.capacity,
            B.prev_bus_time,
            COUNT(P.passenger_id) AS new_pax,
            ROW_NUMBER() OVER (ORDER BY B.arrival_time) AS row_num
        FROM upd_buses AS B

        LEFT JOIN Passengers P
        ON
            P.arrival_time <= B.arrival_time
            AND
            P.arrival_time > B.prev_bus_time

        GROUP BY B.bus_id, B.arrival_time, B.capacity
    ),

    # Use a recursive CTE to build the final table row-by-row. "Boarded" is how many
    # people entered the bus, and "remaining" is how many didn't fit in.
    recursive_cte AS (
        # Base - select first row
        SELECT
            row_num,
            bus_id,
            LEAST(capacity, new_pax) AS boarded,
            (new_pax - LEAST(capacity, new_pax)) AS remaining
        FROM running_totals
        WHERE row_num = 1

        UNION ALL

        # Create all other rows, by combining the next row from the `running_totals` table,
        # and the previously built row from this CTE
        SELECT
            T.row_num,
            T.bus_id,
            LEAST(capacity, new_pax + remaining) AS boarded,
            (new_pax + remaining) - LEAST(capacity, new_pax + remaining) AS remaining
        FROM
            recursive_cte REC,
            running_totals T
        WHERE
            T.row_num = REC.row_num + 1
    )

# The data is ready, just select the columns we need
SELECT
    bus_id,
    boarded AS passengers_cnt
FROM recursive_cte
ORDER BY bus_id