SELECT 
	username, 
	COALESCE(age, (SELECT AVG(age) FROM (SELECT * FROM students EXCEPT SELECT * FROM students WHERE username='auser1'))) AS age, 
	gender, COALESCE(native_country, 'na') AS native_country, 
	t_82, t_52, t_5, t_22, t_72, s_35, s_7, s_40, s_57, s_77, 
		(SELECT ((age - 25)*(age - 25) + 
		(CASE WHEN gender='f' THEN 0 ELSE 1 END) + 
		(CASE WHEN native_country='China' THEN 0 ELSE 1 END) + 
		(CASE WHEN t_82 IS NULL THEN 25 ELSE (t_82 - 2)*(t_82 - 2) END) + 
		(CASE WHEN t_52 IS NULL THEN 25 ELSE (t_52 - 4)*(t_52 - 4) END) + 
		(CASE WHEN t_5 IS NULL THEN 25 ELSE (t_5 - 4)*(t_5 - 4) END) + 
		(CASE WHEN t_22 IS NULL THEN 25 ELSE (t_22 - 4)*(t_22 - 4) END) + 
		(CASE WHEN t_72 IS NULL THEN 25 ELSE (t_72 - 3)*(t_72 - 3) END) + 
		(CASE WHEN s_35 IS NULL THEN 25 ELSE (s_35 - 2)*(s_35 - 2) END) + 
		(CASE WHEN s_7 IS NULL THEN 25 ELSE (s_7 - 1)*(s_7 - 1) END) + 
		(CASE WHEN s_40 IS NULL THEN 25 ELSE (s_40 - 3)*(s_40 - 3) END) + 
		(CASE WHEN s_57 IS NULL THEN 25 ELSE (s_57 - 2)*(s_57 - 2) END) + 
		(CASE WHEN s_77 IS NULL THEN 25 ELSE (s_77 - 2)*(s_77 - 2) END)
		) AS distance FROM part3 WHERE username='ncjc') distance 
FROM part3 WHERE username='ncjc' 

UNION 

SELECT 
	username, 
	COALESCE(age, (SELECT AVG(age) FROM (SELECT * FROM students EXCEPT SELECT * FROM students WHERE username='auser1'))) AS age, 
	gender, COALESCE(native_country, 'na') AS native_country, 
	t_82, t_52, t_5, t_22, t_72, s_35, s_7, s_40, s_57, s_77, 
		(SELECT ((age - 25)*(age - 25) + 
		(CASE WHEN gender='f' THEN 0 ELSE 1 END) + 
		(CASE WHEN native_country='China' THEN 0 ELSE 1 END) + 
		(CASE WHEN t_82 IS NULL THEN 25 ELSE (t_82 - 2)*(t_82 - 2) END) + 
		(CASE WHEN t_52 IS NULL THEN 25 ELSE (t_52 - 4)*(t_52 - 4) END) + 
		(CASE WHEN t_5 IS NULL THEN 25 ELSE (t_5 - 4)*(t_5 - 4) END) + 
		(CASE WHEN t_22 IS NULL THEN 25 ELSE (t_22 - 4)*(t_22 - 4) END) + 
		(CASE WHEN t_72 IS NULL THEN 25 ELSE (t_72 - 3)*(t_72 - 3) END) + 
		(CASE WHEN s_35 IS NULL THEN 25 ELSE (s_35 - 2)*(s_35 - 2) END) + 
		(CASE WHEN s_7 IS NULL THEN 25 ELSE (s_7 - 1)*(s_7 - 1) END) + 
		(CASE WHEN s_40 IS NULL THEN 25 ELSE (s_40 - 3)*(s_40 - 3) END) + 
		(CASE WHEN s_57 IS NULL THEN 25 ELSE (s_57 - 2)*(s_57 - 2) END) + 
		(CASE WHEN s_77 IS NULL THEN 25 ELSE (s_77 - 2)*(s_77 - 2) END)
		) AS distance FROM part3 WHERE username='nielie') distance 
FROM part3 WHERE username='nielie' 

UNION 

SELECT 
	username, 
	COALESCE(age, (SELECT AVG(age) FROM (SELECT * FROM students EXCEPT SELECT * FROM students WHERE username='auser1'))) AS age, 
	gender, COALESCE(native_country, 'na') AS native_country, 
	t_82, t_52, t_5, t_22, t_72, s_35, s_7, s_40, s_57, s_77, 
		(SELECT ((age - 25)*(age - 25) + 
		(CASE WHEN gender='f' THEN 0 ELSE 1 END) + 
		(CASE WHEN native_country='China' THEN 0 ELSE 1 END) + 
		(CASE WHEN t_82 IS NULL THEN 25 ELSE (t_82 - 2)*(t_82 - 2) END) + 
		(CASE WHEN t_52 IS NULL THEN 25 ELSE (t_52 - 4)*(t_52 - 4) END) + 
		(CASE WHEN t_5 IS NULL THEN 25 ELSE (t_5 - 4)*(t_5 - 4) END) + 
		(CASE WHEN t_22 IS NULL THEN 25 ELSE (t_22 - 4)*(t_22 - 4) END) + 
		(CASE WHEN t_72 IS NULL THEN 25 ELSE (t_72 - 3)*(t_72 - 3) END) + 
		(CASE WHEN s_35 IS NULL THEN 25 ELSE (s_35 - 2)*(s_35 - 2) END) + 
		(CASE WHEN s_7 IS NULL THEN 25 ELSE (s_7 - 1)*(s_7 - 1) END) + 
		(CASE WHEN s_40 IS NULL THEN 25 ELSE (s_40 - 3)*(s_40 - 3) END) + 
		(CASE WHEN s_57 IS NULL THEN 25 ELSE (s_57 - 2)*(s_57 - 2) END) + 
		(CASE WHEN s_77 IS NULL THEN 25 ELSE (s_77 - 2)*(s_77 - 2) END)
		) AS distance FROM part3 WHERE username='raz24') distance 
FROM part3 WHERE username='raz24' 

UNION 

SELECT 
	username, 
	COALESCE(age, (SELECT AVG(age) FROM (SELECT * FROM students EXCEPT SELECT * FROM students WHERE username='auser1'))) AS age, 
	gender, COALESCE(native_country, 'na') AS native_country, 
	t_82, t_52, t_5, t_22, t_72, s_35, s_7, s_40, s_57, s_77, 
		(SELECT ((age - 25)*(age - 25) + 
		(CASE WHEN gender='f' THEN 0 ELSE 1 END) + 
		(CASE WHEN native_country='China' THEN 0 ELSE 1 END) + 
		(CASE WHEN t_82 IS NULL THEN 25 ELSE (t_82 - 2)*(t_82 - 2) END) + 
		(CASE WHEN t_52 IS NULL THEN 25 ELSE (t_52 - 4)*(t_52 - 4) END) + 
		(CASE WHEN t_5 IS NULL THEN 25 ELSE (t_5 - 4)*(t_5 - 4) END) + 
		(CASE WHEN t_22 IS NULL THEN 25 ELSE (t_22 - 4)*(t_22 - 4) END) + 
		(CASE WHEN t_72 IS NULL THEN 25 ELSE (t_72 - 3)*(t_72 - 3) END) + 
		(CASE WHEN s_35 IS NULL THEN 25 ELSE (s_35 - 2)*(s_35 - 2) END) + 
		(CASE WHEN s_7 IS NULL THEN 25 ELSE (s_7 - 1)*(s_7 - 1) END) + 
		(CASE WHEN s_40 IS NULL THEN 25 ELSE (s_40 - 3)*(s_40 - 3) END) + 
		(CASE WHEN s_57 IS NULL THEN 25 ELSE (s_57 - 2)*(s_57 - 2) END) + 
		(CASE WHEN s_77 IS NULL THEN 25 ELSE (s_77 - 2)*(s_77 - 2) END)
		) AS distance FROM part3 WHERE username='student335') distance 
FROM part3 WHERE username='student335' 

;