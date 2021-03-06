SELECT COUNT(*)
FROM CALBOARD
WHERE ID='aa' AND SUBSTR(MDATE,1,8)='20210901';

SELECT rn, SEQ ,ID, TITLE, CONTENT, REGDATE 
FROM 
	(
	SELECT ROW_NUMBER() OVER(ORDER BY REGDATE) rn,
	seq, id, title, content, regdate
	FROM ANSWERBOARD
	)
	WHERE CEIL (rn/3)=1;

SELECT (COUNT(*)/3) 
FROM ANSWERBOARD;

SELECT * FROM HKBOARD;	

SELECT * FROM HK;

SELECT * FROM CALBOARD;

SELECT * FROM USERINFO;