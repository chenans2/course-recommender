SELECT DISTINCT username, age, gender, native_country, rank_before from students NATURAL JOIN skill_rankings WHERE skill_id=52;


SELECT DISTINCT students.username, age, gender, native_country, interest_before AS logic 
FROM 
	students NATURAL JOIN topic_interests 
	WHERE topic_id=52 
	LEFT OUTER JOIN 
	students NATURAL JOIN topic_interests 
	WHERE topic_id=22;


UNION ALL 
SELECT DISTINCT students.username, age, gender, native_country, interest_before AS algorithms from students RIGHT OUTER JOIN topic_interests WHERE topic_id=22 and students.username = topic_interests.username;

// loop all students usernames
// loop this for each topic_id
SELECT DISTINCT students.username, topic_id, interest_before FROM students LEFT JOIN topic_interests AS t1 WHERE students.username = t1.username AND students.username='ilovechicken' AND topic_id=52;

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=1) AS '1', 
2 AS '2' 
FROM students WHERE username='Anson'

SELECT AVG(age) FROM (SELECT * FROM students EXCEPT SELECT * FROM students WHERE username='auser1'); 

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=1) AS a, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=2) AS b, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=5) AS c 
FROM students WHERE username='960120' 
GROUP BY username 
HAVING a IS NOT NULL OR b IS NOT NULL OR c IS NOT NULL;

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=1) AS AVONA 
FROM students WHERE username='960120' AND (AVONA IS NOT NULL );

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ilovechicken' AND topic_id=52) AS logic, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ilovechicken' AND topic_id=5) AS algorithms 
FROM students WHERE username='ilovechicken'

UNION 

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE students.username='960120' AND topic_id=52) AS logic, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE students.username='960120' AND topic_id=5) AS algorithms,
(SELECT ((age - 55)*(age - 55) + (CASE WHEN gender='f' THEN 0 ELSE 1 END) + (logic - 48)*(logic - 48)+(algorithms - 17)*(algorithms - 17)) as distance 
FROM students WHERE username='960120' ;

SELECT DISTINCT interest_before FROM students LEFT JOIN topic_interests AS t1 WHERE students.username = t1.username AND students.username='ilovechicken' AND topic_id=52;

SELECT * FROM students LEFT JOIN skill_rankings AS s1 WHERE students.username = s1.username AND students.username='ccccc' AND skill_id=40;

SELECT students.username, age, gender, native_country, t1.interest_before AS logic, t2.interest_before AS data FROM students LEFT JOIN topic_interests AS t1 LEFT JOIN topic_interests AS t2 WHERE t1.topic_id=52 AND t2.topic_id=22 AND students.username ='ilovechicken';


UNION ALL 
SELECT DISTINCT username, age, gender, native_country, interest_before AS algorithms from students LEFT JOIN topic_interests WHERE topic_id=22;

INSERT INTO enrollments (edition_id, username, letter_grade, course_ranking, instr_ranking) VALUES (5,'auser1','A',5,5);


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

SELECT username, age, gender, native_country, 
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
FROM part3 WHERE username='nielie' AND (
t_82 NOT NULL OR 
t_52 IS NOT NULL OR 
t_5 IS NOT NULL OR 
t_22 IS NOT NULL OR 
t_72 IS NOT NULL OR 
s_35 IS NOT NULL OR 
s_7 IS NOT NULL OR 
s_40 IS NOT NULL OR 
s_57 IS NOT NULL OR 
s_77 IS NOT NULL) 

; 

		(t_82 - 2)*(t_82 - 2) + 
		(t_52 - 4)*(t_52 - 4) + 
		(t_5 - 4)*(t_5 - 4) + 
		(t_22 - 4)*(t_22 - 4) + 
		(t_72 - 3)*(t_72 - 3) + 
		(s_35 - 2)*(s_35 - 2) + 
		(s_7 - 1)*(s_7 - 1) + 
		(s_40 - 3)*(s_40 - 3) + 
		(s_57 - 2)*(s_57 - 2) + 
		(s_77 - 2)*(s_77 - 2)

SELECT neighbors.salarylevel, count (*) FROM
(SELECT salarylevel, ((age - 55)*(age - 55) + (workinhoursperweek - 48)*(workinhoursperweek - 48)
+(educationnumeric - 17)*(educationnumeric - 17)+
(CASE WHEN maritalstatus='Married-civ-spouse' THEN 0 ELSE 1 END)) as distance
FROM person
ORDER BY 2
LIMIT 35) neighbors
GROUP BY neighbors.salarylevel; 


INSERT INTO table(column1, column3) VALUES ('a', 'b');  would set column2 to be null.

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=52) AS s_52,  
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=22) AS s_22 
FROM students WHERE username='Anson' AND (s_52 NOT NULL OR s_22 IS NOT NULL ) 

UNION 

SELECT DISTINCT username, age, gender, native_country, 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=52) AS 'Logic', 
(SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=5) AS 'Algorithms' 
FROM students WHERE username='Anson' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) 

UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='CC' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='CC' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='CC' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Darkmoot' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Darkmoot' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='Darkmoot' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Dong' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Dong' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='Dong' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='JiH' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='JiH' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='JiH' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='YolandaXiao' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='YolandaXiao' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='YolandaXiao' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c3maiyan' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c3maiyan' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='c3maiyan' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c4lz' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c4lz' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='c4lz' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, 2 AS '52', 2 AS '5' FROM students WHERE username='cai' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ccccc' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ccccc' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='ccccc' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='cheerful' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='cheerful' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='cheerful' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='fw' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='fw' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='fw' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ilovechicken' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ilovechicken' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='ilovechicken' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalala' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalala' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='lalalala' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalalol' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalalol' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='lalalalol' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='madame_id' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='madame_id' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='madame_id' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='mic' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='mic' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='mic' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ncjc' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ncjc' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='ncjc' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nielie' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nielie' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='nielie' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nj' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nj' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='nj' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='oooo' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='oooo' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='oooo' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='q' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='q' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='q' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='raz24' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='raz24' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='raz24' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ruanhaoy' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ruanhaoy' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='ruanhaoy' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='siucourt' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='siucourt' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='siucourt' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='sos' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='sos' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='sos' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='student335' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='student335' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='student335' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ww' AND topic_id=52) AS 'Logic', (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ww' AND topic_id=5) AS 'Algorithms' FROM students WHERE username='ww' AND (Logic IS NOT NULL OR Algorithms IS NOT NULL ) 

SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=1) AS ANOVA FROM students WHERE username='960120' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=1) AS ANOVA FROM students WHERE username='Anson' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='CC' AND topic_id=1) AS ANOVA FROM students WHERE username='CC' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Darkmoot' AND topic_id=1) AS ANOVA FROM students WHERE username='Darkmoot' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Dong' AND topic_id=1) AS ANOVA FROM students WHERE username='Dong' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='JiH' AND topic_id=1) AS ANOVA FROM students WHERE username='JiH' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='YolandaXiao' AND topic_id=1) AS ANOVA FROM students WHERE username='YolandaXiao' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c3maiyan' AND topic_id=1) AS ANOVA FROM students WHERE username='c3maiyan' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='c4lz' AND topic_id=1) AS ANOVA FROM students WHERE username='c4lz' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, 1 AS '1' FROM students WHERE username='cai' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ccccc' AND topic_id=1) AS ANOVA FROM students WHERE username='ccccc' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='cheerful' AND topic_id=1) AS ANOVA FROM students WHERE username='cheerful' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='fw' AND topic_id=1) AS ANOVA FROM students WHERE username='fw' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ilovechicken' AND topic_id=1) AS ANOVA FROM students WHERE username='ilovechicken' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalala' AND topic_id=1) AS ANOVA FROM students WHERE username='lalalala' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='lalalalol' AND topic_id=1) AS ANOVA FROM students WHERE username='lalalalol' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='madame_id' AND topic_id=1) AS ANOVA FROM students WHERE username='madame_id' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='mic' AND topic_id=1) AS ANOVA FROM students WHERE username='mic' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ncjc' AND topic_id=1) AS ANOVA FROM students WHERE username='ncjc' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nielie' AND topic_id=1) AS ANOVA FROM students WHERE username='nielie' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='nj' AND topic_id=1) AS ANOVA FROM students WHERE username='nj' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='oooo' AND topic_id=1) AS ANOVA FROM students WHERE username='oooo' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='q' AND topic_id=1) AS ANOVA FROM students WHERE username='q' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='raz24' AND topic_id=1) AS ANOVA FROM students WHERE username='raz24' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ruanhaoy' AND topic_id=1) AS ANOVA FROM students WHERE username='ruanhaoy' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='siucourt' AND topic_id=1) AS ANOVA FROM students WHERE username='siucourt' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='sos' AND topic_id=1) AS ANOVA FROM students WHERE username='sos' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='student335' AND topic_id=1) AS ANOVA FROM students WHERE username='student335' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='ww' AND topic_id=1) AS ANOVA FROM students WHERE username='ww' AND (ANOVA IS NOT NULL ) ;


SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='960120' AND topic_id=1) AS ANOVA FROM students WHERE username='960120' AND (ANOVA IS NOT NULL ) UNION SELECT DISTINCT username, age, gender, native_country, (SELECT DISTINCT interest_before FROM students NATURAL JOIN topic_interests WHERE username='Anson' AND topic_id=1) AS ANOVA FROM students WHERE username='Anson' AND (ANOVA IS NOT NULL );