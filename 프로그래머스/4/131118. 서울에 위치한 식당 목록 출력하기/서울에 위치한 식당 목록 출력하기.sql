-- 코드를 입력하세요


SELECT INLINE.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, INLINE.SCORE FROM (
    SELECT REST_ID, ROUND(AVG(REVIEW_SCORE),2) SCORE FROM REST_REVIEW
    GROUP BY REST_ID
) INLINE JOIN REST_INFO I
ON INLINE.REST_ID = I.REST_ID
WHERE ADDRESS LIKE "서울%"
ORDER BY INLINE.SCORE DESC, FAVORITES DESC;