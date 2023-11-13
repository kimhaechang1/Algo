-- 코드를 입력하세요
#SELECT CAR_ID, MAX(START_DATE), MAX(END_DATE)
#FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#GROUP BY CAR_ID
#ORDER BY CAR_ID DESC;

SELECT CAR_ID, 
max(CASE
WHEN START_DATE <= "2022-10-16" AND END_DATE >= "2022-10-16" 
THEN "대여중"
ELSE "대여 가능"
END) AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;

#SELECT CAR_ID
#FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
#WHERE (CAR_ID) NOT IN 


# CAR_ID 
# 30 2022 10 27 ~ 2022 10 27 : 대여 가능
# 29 2022 08 21 ~ 2022-11-29 : 대여 중
# 28 2022-09-13 00:00:00 2022-12-22 00:00:00 : 대여 중
# 27 2022-09-25 00:00:00	2022-12-24 00:00:00 : 대여 중
#