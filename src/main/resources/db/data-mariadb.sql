INSERT IGNORE INTO AUTHORITY
    (ID, NAME, DESCRIPTION)
VALUES (1, 'root', '루트'),
       (2, 'admin', '관리자'),
       (3, 'normal', '일반'),
       (4, 'guest', '게스트');

INSERT IGNORE INTO DEPARTMENT
    (ID, NAME, DESCRIPTION, DEPARTMENT_ID)
VALUES (1, 'DevH.Co', '회사명', NULL),
       (2, '대표이사', '대표', 1),
       (3, '기획', '기획', 1),
       (4, '감사', '감사', 1),
       (5, '경영', '경영', 1),
       (6, '영업', '영업', 1),
       (7, '연구소', '연구소', 1),
       (8, '인사', '인사', 5),
       (9, '재무', '재무', 5),
       (10, '국내영업', '국내영업', 6),
       (11, '해외영업', '해외영업', 6),
       (12, '1연구소', '1연구소', 7),
       (13, '2연구소', '2연구소', 7),
       (14, '3연구소', '3연구소', 7),
       (15, 'C개발', 'C개발', 12),
       (16, 'C설계', 'C설계', 12),
       (17, 'JAVA개발', 'JAVA개발', 13),
       (18, 'JAVA설계', 'JAVA설계', 13),
       (19, 'QA', 'QA', 14);

INSERT IGNORE INTO USER
    (ID, USERNAME, PASSWORD, NAME, NICKNAME) /* EMAIL, PHONE, ACCESS_IP, LOGIN_FAIL_IP, LOGIN_FAIL_COUNT, STATUS_MESSAGE, BACKGROUND_IMAGE, PROFILE_IMAGE, LOGIN_AT, LOGIN_FAILED_AT, PASSWORD_CHANGED_AT, CREATED_AT, UPDATED_AT) */
VALUES (1, 'root', '$2a$10$7/OVVzZfA9wI1q4viJVmkefkzF7W7bgzwXoBJUXl2C4IVCVMz4tNi', '김루트', '루트킴'), /* root / root */
       (2, 'admin', '$2a$10$jGszCh15WEW3jxzoLKdNMuFt7ZV53cYPfPnrZH1w7RmZJnGnz3lDC', '김관리', '관리킴'), /* admin / admin */
       (3, 'normal', '$2a$10$mvtG3BrlehPfFLDz5.XNveoRYK1LJhp5yudKuXKiwfiSyjb30kk1i', '김일반',
        '일반킴'), /* normal / normal */
       (4, 'guest', '$2a$10$EWTFu2lWmFvzmP50VtXpPuAdOEESre0ta6FFkxaV/ED9VVcRh/0sW', '김손님', '손님킴'); /* guest / guest */

INSERT IGNORE INTO USER_AUTHORITY
    (ID, USER_ID, AUTHORITY_ID)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (5, 2, 2),
       (6, 2, 3),
       (7, 2, 4),
       (8, 3, 3),
       (9, 3, 4),
       (10, 4, 4);

INSERT IGNORE INTO USER_DEPARTMENT
    (ID, USER_ID, DEPARTMENT_ID)
VALUES (1, 1, 2);