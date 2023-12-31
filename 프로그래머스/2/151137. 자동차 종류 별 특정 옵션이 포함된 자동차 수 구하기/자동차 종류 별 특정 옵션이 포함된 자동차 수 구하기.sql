-- 코드를 입력하세요
SELECT INLINE.CAR_TYPE, COUNT(INLINE.CAR_ID) CARS
FROM (SELECT CAR_ID, CAR_TYPE, DAILY_FEE, OPTIONS FROM CAR_RENTAL_COMPANY_CAR WHERE FIND_IN_SET("가죽시트",OPTIONS) > 0 || FIND_IN_SET("열선시트",OPTIONS) > 0 || FIND_IN_SET("통풍시트",OPTIONS) > 0) INLINE
GROUP BY INLINE.CAR_TYPE
ORDER BY INLINE.CAR_TYPE;


# SELECT CAR_TYPE, COUNT(CAR_ID) FROM CAR_RENTAL_COMPANY_CAR 
# WHERE FIND_IN_SET("가죽시트",OPTIONS) > 0 OR FIND_IN_SET("열선시트",OPTIONS) > 0 OR FIND_IN_SET("통풍시트",OPTIONS) > 0
# GROUP BY CAR_TYPE
# ORDER BY CAR_TYPE;

#SELECT CAR_TYPE ,SUM(CAR_ID) FROM CAR_RENTAL_COMPANY_CAR GROUP BY CAR_TYPE;

# SELECT * FROM CAR_RENTAL_COMPANY_CAR WHERE OPTIONS IN '네비게이션';