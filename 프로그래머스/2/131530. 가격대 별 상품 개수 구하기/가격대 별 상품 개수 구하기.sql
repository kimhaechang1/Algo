-- 코드를 입력하세요
SELECT (PRICE*10000) AS PRICE_GROUP, COUNT(PRODUCT_CODE) AS PRODUCTS
FROM (
    SELECT TRUNCATE((PRICE/10000), 0) AS PRICE, PRODUCT_CODE
    FROM PRODUCT
) INLINE
GROUP BY PRICE
ORDER BY PRICE_GROUP;
#SELECT  FROM PRODUCT;

