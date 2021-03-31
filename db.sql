# 데이터베이스 생성
DROP DATABASE IF EXISTS kjbBoard;
CREATE DATABASE kjbBoard;
USE kjbBoard;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    body TEXT NOT NULL
);

# 게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1 입니다.",
body = "내용1 입니다.";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목2 입니다.",
body = "내용2 입니다.";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목3 입니다.",
body = "내용3 입니다.";

# 회원 테이블 생성
CREATE TABLE member (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId VARCHAR(40) NOT NULL,
    loginPw VARCHAR(100) NOT NULL,
    name VARCHAR(30) NOT NULL,
    nickName VARCHAR(30) NOT NULL,
	phoneNo VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

# 로그인 ID로 검색했을 때
ALTER TABLE member ADD UNIQUE INDEX (`loginId`);

# 회원, 테스트 데이터 생성
INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user1",
loginPw = "user1",
name = "user1",
nickname = "user1",
phoneNo = "01012341234",
email = "user1@gmail.com";

INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user2",
loginPw = "user2",
name = "user2",
nickname = "user2",
phoneNo = "01056785678",
email = "user2@gmail.com";

# 게시물 테이블에 회원번호 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 게시물의 작성자를 회원1로 지정
UPDATE article
SET memberId = 1
WHERE memberId = 0;

-- insert into article(regDate,updateDate,memberId,title,body)
-- values (now(),now(),floor(rand()*2)+1,concat('제목_',floor(rand()*1000)+1),concat('내용_',floor(rand()*1000)+1));
--
-- insert into article(regDate,updateDate,memberId,title,body)
-- select now(),now(),floor(rand()*2)+1,concat('제목_',floor(rand()*1000)+1),concat('내용_',floor(rand()*1000)+1)
-- from article;

# 게시판 테이블 생성
CREATE TABLE board(
id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
code VARCHAR(20) NOT NULL UNIQUE,
NAME VARCHAR(20) NOT NULL UNIQUE
);

# 게시판 데이터 생성
INSERT INTO board
SET regDate = now(),
updateDate = now(),
code = 'notice',
NAME = '공지사항';

INSERT INTO board
SET regDate = now(),
updateDate = now(),
code = 'free',
NAME = '자유';

# 게시물 테이블에 boardId 컬럼 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 게시물 데이터 0에서 1이나 2 로 지정
UPDATE article
SET boardId = floor(rand()*2) + 1 
WHERE boardId = 0;

# 댓글 테이블 생성
CREATE TABLE reply(
id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
articleId INT(10) UNSIGNED NOT NULL,
memberId INT(10) UNSIGNED NOT NULL,
body TEXT NOT NULL
);

-- insert into reply(regDate, updateDate, articleId, memberId, body)
-- values(now(), now(), floor(rand()*226)+1,floor(rand()*3)+1, concat('내용_',floor(rand()*1000)+1));
--
-- insert into reply(regDate, updateDate, articleId, memberId, body)
-- select now(), now(), floor(rand()*226)+1,floor(rand()*3)+1, concat('내용_',floor(rand()*1000)+1)
-- from reply;