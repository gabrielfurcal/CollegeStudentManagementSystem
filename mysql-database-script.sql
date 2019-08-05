/*CREATING DATABASE*/
CREATE DATABASE CSMS_DB;

/*USING DATABASE*/
USE CSMS_DB;

/*CREATING TABLE ADDRESSES*/
CREATE TABLE ADDRESSES
(
    ADDRESS_ID INT NOT NULL AUTO_INCREMENT,
    ADDRESS_STREET_AND_NUMBER VARCHAR(255) NOT NULL,
    ADDRESS_NEIGHBORHOOD VARCHAR(255),
    ADDRESS_SECTOR VARCHAR(60) NOT NULL,
    ADDRESS_MUNICIPALITY VARCHAR(60) NOT NULL,
    ADDRESS_PROVINCE VARCHAR(60) NOT NULL,
    ADDRESS_LATITUDE VARCHAR(30),
    ADDRESS_LONGITUDE VARCHAR(30),
    ADDRESS_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (ADDRESS_ID)
);

/*CREATING TABLE POSITIONS*/
CREATE TABLE POSITIONS
(
    POSITION_ID INT NOT NULL AUTO_INCREMENT,
    POSITION_NAME VARCHAR(60) NOT NULL,
    POSITION_DESCRIPTION VARCHAR(200) NOT NULL,
    POSITION_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (POSITION_ID)
);

/*CREATING TABLE USERS*/
CREATE TABLE USERS
(
    USER_ID INT NOT NULL AUTO_INCREMENT,
    USER_FIRST_NAME VARCHAR(60) NOT NULL,
    USER_FATHER_LAST_NAME VARCHAR(60) NOT NULL,
    USER_MOTHER_LAST_NAME VARCHAR(60),
    USER_USERNAME VARCHAR(25) NOT NULL,
    USER_PASSWORD VARCHAR(64) NOT NULL,
    USER_CREATION_DATE DATE NOT NULL,
    ADDRESS_ID INT NOT NULL,
    POSITION_ID INT NOT NULL,
    PRIMARY KEY (USER_ID),
    CONSTRAINT `ADDRESSES_USERS_FK` FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES(ADDRESS_ID),
    CONSTRAINT `POSITIONS_USERS_FK` FOREIGN KEY (POSITION_ID) REFERENCES POSITIONS(POSITION_ID),
    CONSTRAINT USER_USERNAME_UNIQUE UNIQUE(USER_USERNAME)
);

/*CREATING TABLE STUDENTS*/
CREATE TABLE STUDENTS
(
    STUDENT_ID VARCHAR(9) NOT NULL,
    USER_ID INT NOT NULL,
    STUDENT_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (STUDENT_ID, USER_ID),
    CONSTRAINT USERS_STUDENTS_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

/*CREATING TABLE TEACHERS*/
CREATE TABLE TEACHERS
(
    TEACHER_ID INT NOT NULL AUTO_INCREMENT,
    USER_ID INT NOT NULL,
    TEACHER_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY(TEACHER_ID, USER_ID),
    CONSTRAINT USERS_TEACHERS_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

/*CREATING TABLE EMAILS*/
CREATE TABLE EMAILS
(
    EMAIL_ID INT NOT NULL AUTO_INCREMENT,
    EMAIL_TEXT VARCHAR(70) NOT NULL,
    USER_ID INT NOT NULL,
    EMAIL_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY(EMAIL_ID),
    CONSTRAINT USERS_EMAILS_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

/*CREATING TABLE TELEPHONES_TYPES*/
CREATE TABLE TELEPHONES_TYPES
(
    TELEPHONE_TYPE_ID INT NOT NULL AUTO_INCREMENT,
    TELEPHONE_TYPE_NAME VARCHAR(30) NOT NULL,
    TELEPHONE_TYPE_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (TELEPHONE_TYPE_ID)
);

/*CREATING TABLE TELEPHONES*/
CREATE TABLE TELEPHONES
(
    TELEPHONE_ID INT NOT NULL AUTO_INCREMENT,
    TELEPHONE_NUMBER VARCHAR(20) NOT NULL,
    TELEPHONE_TYPE_ID INT NOT NULL,
    TELEPHONE_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (TELEPHONE_ID),
    CONSTRAINT TELEPHONES_TYPES_TELEPHONES_FK FOREIGN KEY (TELEPHONE_TYPE_ID) REFERENCES TELEPHONES_TYPES(TELEPHONE_TYPE_ID)
);

/*CREATING TABLE USERS_TELEPHONES*/
CREATE TABLE USERS_TELEPHONES
(
    USER_TELEPHONE_ID INT NOT NULL AUTO_INCREMENT,
    USER_ID INT NOT NULL,
    TELEPHONE_ID INT NOT NULL,
    PRIMARY KEY (USER_TELEPHONE_ID),
    CONSTRAINT USERS_USERS_TELEPHONES_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
    CONSTRAINT TELEPHONES_USERS_TELEPHONES_FK FOREIGN KEY (TELEPHONE_ID) REFERENCES TELEPHONES(TELEPHONE_ID)
);

/*CREATING TABLE GROUPS*/
CREATE TABLE GROUPS
(
    GROUP_ID INT NOT NULL AUTO_INCREMENT,
    GROUP_NAME VARCHAR(60) NOT NULL,
    GROUP_DESCRIPTION VARCHAR(200),
    GROUP_CREATION_DATE DATE,
    PRIMARY KEY (GROUP_ID),
    CONSTRAINT GROUP_NAME_UNIQUE UNIQUE(GROUP_NAME)
);

/*CREATING TABLE USERS_GROUPS*/
CREATE TABLE USERS_GROUPS
(
    USER_GROUP_ID INT NOT NULL AUTO_INCREMENT,
    USER_USERNAME VARCHAR(25) NOT NULL,
    GROUP_NAME VARCHAR(60) NOT NULL,
    PRIMARY KEY (USER_GROUP_ID),
    CONSTRAINT USERS_USERS_GROUPS_FK FOREIGN KEY(USER_USERNAME) REFERENCES `USERS`(USER_USERNAME),
    CONSTRAINT GROUPS_USERS_GROUPS_FK FOREIGN KEY(GROUP_NAME) REFERENCES `GROUPS`(GROUP_NAME)
);

/*CREATING TABLE CAMPUS*/
CREATE TABLE CAMPUS
(
    CAMPUS_ID INT NOT NULL AUTO_INCREMENT,
    CAMPUS_NAME VARCHAR(255) NOT NULL,
    ADDRESS_ID INT NOT NULL,
    CAMPUS_CREATION_DATE DATE,
    PRIMARY KEY (CAMPUS_ID),
    CONSTRAINT ADDRESSES_CAMPUS_FK FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES(ADDRESS_ID)
);

/*CREATING TABLE BUILDS*/
CREATE TABLE BUILDS
(
    BUILD_ID INT NOT NULL AUTO_INCREMENT,
    CAMPUS_ID INT NOT NULL,
    BUILD_NAME VARCHAR(255) NOT NULL,
    BUILD_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (BUILD_ID, CAMPUS_ID),
    CONSTRAINT CAMPUS_BUILDS_FK FOREIGN KEY (CAMPUS_ID) REFERENCES CAMPUS(CAMPUS_ID)
);

/*CREATING TABLE CLASSROOMS*/
CREATE TABLE CLASSROOMS
(
    CLASSROOM_ID INT NOT NULL AUTO_INCREMENT,
    BUILD_ID INT NOT NULL,
    CAMPUS_ID INT NOT NULL,
    CLASSROOM_NAME VARCHAR(255),
    CLASSROOM_CREATION_DATE DATE,
    PRIMARY KEY (CLASSROOM_ID, BUILD_ID, CAMPUS_ID),
    CONSTRAINT BUILDS_CLASSROOMS_FK FOREIGN KEY (BUILD_ID, CAMPUS_ID) REFERENCES BUILDS(BUILD_ID, CAMPUS_ID)
);

/*CREATING TABLE COURSES*/
CREATE TABLE COURSES
(
    COURSE_ID VARCHAR(8) NOT NULL,
    COURSE_NAME VARCHAR(100) NOT NULL,
    COURSE_PRICE NUMERIC(18,2) NOT NULL,
    COURSE_CREATION_DATE DATE NOT NULL,
    COURSE_ACTIVE INT NOT NULL,
    COURSE_AMOUNT_HOURS INT NOT NULL,
    PRIMARY KEY (COURSE_ID)
);

/*CREATING TABLE COURSES_SECTIONS*/
CREATE TABLE COURSES_SECTIONS
(
    COURSE_SECTION_ID INT NOT NULL,
    COURSE_ID VARCHAR(8) NOT NULL,
    COURSE_SECTION_CREATION_DATE DATE NOT NULL,
    COURSE_SECTION_DAY VARCHAR(3) NOT NULL,
    COURSE_SECTION_START_HOUR VARCHAR(10) NOT NULL,
    COURSE_SECTION_END_HOUR VARCHAR(10) NOT NULL,
    COURSE_SECTION_ACTIVE INT NOT NULL,
    PRIMARY KEY (COURSE_SECTION_ID, COURSE_ID),
    CONSTRAINT COURSES_COURSES_SECTIONS_FK FOREIGN KEY (COURSE_ID) REFERENCES COURSES(COURSE_ID)
);


/*CREATING TABLE PERIODS*/
CREATE TABLE PERIODS
(
    PERIOD_YEAR INT NOT NULL,
    PERIOD_QUARTER INT NOT NULL,
    PERIOD_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (PERIOD_YEAR, PERIOD_QUARTER)
);

/*CREATING TABLE COURSES_SECTIONS_HISTORICAL*/
CREATE TABLE COURSES_SECTIONS_HISTORICAL
(
    COURSE_SECT_HIST_ID INT NOT NULL AUTO_INCREMENT,
    COURSE_SECTION_ID INT NOT NULL,
    COURSE_ID VARCHAR(8) NOT NULL,
    TEACHER_ID INT,
    USER_ID INT,
    COURSE_SECT_HIST_QUOTA INT NOT NULL,
    PERIOD_YEAR INT NOT NULL,
    PERIOD_QUARTER INT NOT NULL,
    CAMPUS_ID INT,
    BUILD_ID INT,
    CLASSROOM_ID INT,
    COURSE_SECT_HIST_PRICE NUMERIC(18,2) NOT NULL,
    COURSE_SECT_HIST_CREATION_DATE DATE NOT NULL,
    COURSE_SECT_HIST_DAY VARCHAR(3),
    COURSE_SECT_HIST_START_HOUR VARCHAR(10),
    COURSE_SECT_HIST_END_HOUR VARCHAR(10),
    PRIMARY KEY (COURSE_SECT_HIST_ID),
    CONSTRAINT CRS_SECT_CRS_SECT_HIST_FK FOREIGN KEY (COURSE_SECTION_ID, COURSE_ID) REFERENCES COURSES_SECTIONS(COURSE_SECTION_ID, COURSE_ID),
    CONSTRAINT TEACHERS_COURSES_SECT_HIST_FK FOREIGN KEY (TEACHER_ID, USER_ID) REFERENCES TEACHERS(TEACHER_ID, USER_ID),
    CONSTRAINT PERIODS_COURSES_SECT_HIST_FK FOREIGN KEY (PERIOD_YEAR, PERIOD_QUARTER) REFERENCES PERIODS(PERIOD_YEAR, PERIOD_QUARTER),
    CONSTRAINT CLASSROOM_COURSES_SECT_HIST_FK FOREIGN KEY (CLASSROOM_ID, BUILD_ID, CAMPUS_ID) REFERENCES CLASSROOMS(CLASSROOM_ID, BUILD_ID, CAMPUS_ID)
);



/*CREATING TABLE COURSES_SECTIONS_HISTORICAL*/
CREATE TABLE CLASS_PARTICIPANTS
(
    CLASS_PARTICIPANT_ID INT NOT NULL AUTO_INCREMENT,
    STUDENT_ID VARCHAR(9) NOT NULL,
    USER_ID INT NOT NULL,
    COURSE_SECT_HIST_ID INT NOT NULL,
    PRIMARY KEY (CLASS_PARTICIPANT_ID),
    CONSTRAINT STUDENTS_CLASS_PARTICIPANTS_FK FOREIGN KEY(STUDENT_ID, USER_ID) REFERENCES STUDENTS(STUDENT_ID, USER_ID),
    CONSTRAINT COURSES_SECT_HIST_CLASS_PTN_FK FOREIGN KEY(COURSE_SECT_HIST_ID) REFERENCES COURSES_SECTIONS_HISTORICAL(COURSE_SECT_HIST_ID)
);

/*CREATING TABLE ROLES*/
CREATE TABLE ROLES
(
    ROLE_ID INT NOT NULL AUTO_INCREMENT,
    ROLE_NAME VARCHAR(60) NOT NULL,
    ROLE_DESCRIPTION VARCHAR(200),
    ROLE_CREATION_DATE DATE,
    PRIMARY KEY(ROLE_ID)
);

/*CREATING TABLE PERMISSIONS*/
CREATE TABLE PERMISSIONS
(
    PERMISSION_ID INT NOT NULL AUTO_INCREMENT,
    PERMISSION_NAME VARCHAR(60) NOT NULL,
    PERMISSION_DESCRIPTION VARCHAR(200),
    PERMISSION_URL VARCHAR(1000),
    PERMISSION_CREATION_DATE DATE NOT NULL,
    PRIMARY KEY (PERMISSION_ID)
);

/*CREATING TABLE USERS_ROLES*/
CREATE TABLE USERS_ROLES
(
    USER_ROLE_ID INT NOT NULL AUTO_INCREMENT,
    ROLE_ID INT NOT NULL,
    USER_ID INT NOT NULL,
    PRIMARY KEY (USER_ROLE_ID),
    CONSTRAINT ROLE_USERS_ROLES_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ROLE_ID),
    CONSTRAINT USER_USERS_ROLES_FK FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

/*CREATING TABLE GROUPS_ROLES*/
CREATE TABLE GROUPS_ROLES
(
    GROUP_ROLE_ID INT NOT NULL AUTO_INCREMENT,
    GROUP_ID INT NOT NULL,
    ROLE_ID INT NOT NULL,
    PRIMARY KEY (GROUP_ROLE_ID),
    CONSTRAINT GROUP_GROUPS_ROLES_FK FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(GROUP_ID),
    CONSTRAINT ROLE_GROUPS_ROLES_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ROLE_ID)
);

/*CREATING TABLE ROLES_PERMISSIONS*/
CREATE TABLE ROLES_PERMISSIONS
(
    ROLE_PERMISSION_ID INT NOT NULL AUTO_INCREMENT,
    PERMISSION_ID INT NOT NULL,
    ROLE_ID INT NOT NULL,
    PRIMARY KEY (ROLE_PERMISSION_ID),
    CONSTRAINT PERMISSION_ROLES_PERMISSIONS_FK FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSIONS(PERMISSION_ID),
    CONSTRAINT ROLE_ROLES_PERMISSIONS_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ROLE_ID)
);