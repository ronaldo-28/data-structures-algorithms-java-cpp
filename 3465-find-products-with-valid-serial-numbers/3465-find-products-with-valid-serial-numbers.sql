SELECT *
FROM products
WHERE description REGEXP '[^A-Z0-9]SN[0-9]{4}-[0-9]{4}([^0-9]|$)'
   OR description REGEXP '^SN[0-9]{4}-[0-9]{4}([^0-9]|$)'
ORDER BY product_id ASC;