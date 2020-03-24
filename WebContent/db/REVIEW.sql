DROP TABLE REVIEW;
CREATE TABLE REVIEW(
    RNO NUMBER(9) PRIMARY KEY,
    MID VARCHAR2(30) REFERENCES MEMBER(MID) NOT NULL,
    PCODE VARCHAR2(30) REFERENCES PRODUCT (PCODE) NOT NULL,
    RTITLE VARCHAR2(100) NOT NULL,
    RCONTENT VARCHAR2(4000),
    RFILENAME VARCHAR2(4000),
    RHIT NUMBER(6) DEFAULT 0,
    RRDATE DATE DEFAULT SYSDATE
);
DROP SEQUENCE REVIEW_SEQ;
CREATE SEQUENCE REVIEW_SEQ MAXVALUE 999 NOCACHE NOCYCLE;



INSERT INTO REVIEW (RNO, MID, PCODE, RTITLE, RCONTENT, RFILENAME)
    VALUES (REVIEW_SEQ.NEXTVAL, 'aaa123','GOGI010','제목','본문',NULL);
    
SELECT * FROM REVIEW;

-- 글목록 불러오기 (페이징)

SELECT * FROM (SELECT ROWNUM RN, A.* FROM
    (SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE  
    FROM REVIEW R, MEMBER M, PRODUCT P 
    WHERE R.MID=M.MID AND R.PCODE=P.PCODE
    ORDER BY RRDATE DESC)A) 
    WHERE RN BETWEEN 1 AND 10;

-- 상품 밑에 리뷰 

SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME ,P.PPRICE
    FROM REVIEW R, MEMBER M, PRODUCT P 
    WHERE R.MID=M.MID AND R.PCODE=P.PCODE AND R.PCODE ='GOGI027'
    ORDER BY RRDATE DESC;
    
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
    (SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE 
    FROM REVIEW R, MEMBER M, PRODUCT P 
    WHERE R.MID=M.MID AND R.PCODE=P.PCODE AND R.PCODE ='GOGI027'
    ORDER BY RRDATE DESC)A) 
    WHERE RN BETWEEN 1 AND 10;    


-- 상품 밑에 리뷰 글개수
SELECT COUNT(*) FROM REVIEW R, PRODUCT P WHERE R.PCODE=P.PCODE AND R.PCODE ='GOGI027';    
    
-- 글 개수
SELECT COUNT(*) FROM REVIEW;

-- 조회수 올리기 

UPDATE REVIEW SET RHIT= RHIT+1 WHERE RNO='1'; 

-- 글 상세보기 
SELECT R.*,M.MNAME,P.PFILENAME, P.PNAME, P.PPRICE 
    FROM REVIEW R, MEMBER M, PRODUCT P 
    WHERE R.MID=M.MID AND R.PCODE=P.PCODE AND RNO='1';
    
-- 글쓰기
INSERT INTO REVIEW (RNO, MID, PCODE, RTITLE, RCONTENT, RFILENAME)
    VALUES (REVIEW_SEQ.NEXTVAL, 'aaa123','GOGI010','제목','본문',NULL);


-- 글수정 
UPDATE REVIEW SET RTITLE='제목수정',RCONTENT='본문수정',RFILENAME='파일이름수정' WHERE RNO='1';

--글삭제 
DELETE FROM REVIEW WHERE RNO='5';    
    
    
    
    
    
    
    
    
    
    
    
    
    