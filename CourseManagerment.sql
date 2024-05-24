CREATE DATABASE CoursesManagerment
GO

USE CoursesManagerment
GO
CREATE TABLE CentralLocation
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(50),
    Address NVARCHAR(100)
);

CREATE TABLE Course
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(50),
    Price FLOAT
);

CREATE TABLE Account
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(50),
    DoB DATE,
    Gender BIT,
    Address NVARCHAR(100),
    IDCard NVARCHAR(12) unique,
    Phone NVARCHAR(12)  unique,
    Role INT,
    Password VARCHAR(20)
);

CREATE TABLE CourseSchedule
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    IDCourse INT,
    Slot INT,
    SlotNow INT,
    StartDay DATE,
    EndDay DATE,
	IDTeacher INT,
    CONSTRAINT fk_CourseSchedule_Course FOREIGN KEY(IDCourse) REFERENCES dbo.Course(ID),
	CONSTRAINT fk_CourseSchedule_Account FOREIGN KEY(IDTeacher) REFERENCES dbo.Account(ID)
);

CREATE TABLE Schedule
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    Day DATE,
    Time NVARCHAR(10),
    NumberOfShift INT,
    IDCourseSchedule INT,
    CONSTRAINT fk_Schedule_CourseSchedule FOREIGN KEY(IDCourseSchedule) REFERENCES dbo.CourseSchedule(ID)
);

CREATE TABLE RegisterCourse
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    IDStudent INT,
    IDCourseSchedule INT,
    IDCentralLocation INT,
	IsPayment bit,
    CONSTRAINT fk_RegisterCourse_Student FOREIGN KEY(IDStudent) REFERENCES dbo.Account(ID),
    CONSTRAINT fk_RegisterCourse_Course FOREIGN KEY(IDCourseSchedule) REFERENCES dbo.CourseSchedule(ID),
    CONSTRAINT fk_RegisterCourse_CentralLocation FOREIGN KEY(IDCentralLocation) REFERENCES dbo.CentralLocation(ID)
);

CREATE TABLE Mail
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    IDAccount INT,
    Title NVARCHAR(50),
    Message NVARCHAR(300),
    IsRead BIT,
    CONSTRAINT fk_Mail_Account FOREIGN KEY(IDAccount) REFERENCES dbo.Account(ID)
);

CREATE TRIGGER trg_IncreaseSlotNow
ON RegisterCourse
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE cs
    SET cs.SlotNow = cs.SlotNow + 1
    FROM CourseSchedule cs
    INNER JOIN inserted i ON cs.ID = i.IDCourseSchedule;
END;
GO

CREATE TRIGGER trg_DecreaseSlotNow
ON RegisterCourse
AFTER DELETE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE cs
    SET cs.SlotNow = cs.SlotNow - 1
    FROM CourseSchedule cs
    INNER JOIN deleted d ON cs.ID = d.IDCourseSchedule;

END;
GO

select * from CourseSchedule
select * from RegisterCourse
select * from Course


INSERT INTO CentralLocation (Name, Address) VALUES 
(N'PIE MAP',N'140 Lê Trọng Tấn'),
(N'PIE TASK',N'1950 Trần Quang Khải'),
(N'PIE MON',N'360 Lê Đại Hành')


INSERT INTO Course (Name, Price) VALUES 
(N'Full Course Java',102.50),
(N'React JS',302.50),
(N'Sveltekit Full Course',303.12),
(N'Flutter Firebase',105.33),
(N'Firestore Data Modeling',50.66),
(N'The Angular Firebase Project Course',99.15),
(N'Git And Github',116.00),
(N'Stripe For SASS',33.15),
(N'Docker For Beginner',60.10),
(N'Backend RESTFul Server with NodeJS And Express',99.50),
(N'DevOps on AWS for Beginner',126.96),
(N'CI/CD With Jenkins Using Pipeline And Docker',536.22)

select * from Course

INSERT INTO Account (Name, DoB, Gender, Address, IDCard, Phone, Role, Password) VALUES 
('Admin','2000-10-10',0,N'NO','000000000000','admin',0,'admin123'),
(N'Hồ Thanh Hải','2003-08-27',0,N'72/34 Dương Đức Hiền','335688542553','0384631254',1,'123'),
(N'Lê Minh Đức','2003-08-10',0,N'72/34 Dương Đức Hiền','335684552666','0384631111',2,'123'),
(N'Nguyễn Thị Bảo Ngọc','2003-08-11',1,N'88 Kênh Tân Hóa','3356885411','0384631223',2,'123'),
(N'Nguyễn Minh Bảo','2003-05-13',0,N'80 Phan Văn Trị','3356885458','0384633365',1,'123'),
(N'David Ngô','2003-10-17',0,N'72 Tân Kì Tân Quý','3356775423','03846313365',2,'123'),
(N'Nguyễn Thị Minh Tâm','2003-08-27',1,N'15/22A Nguyễn Thị Nhài','3344885423','0384631236',1,'123'),
(N'Đinh Thị Tuyết Chinh','2003-11-11',0,N'590 Trường Chinh','3356886411','0384631253',2,'123'),
(N'Phan Minh Tâm','2003-01-21',1,N'72/34 Dương Đức Hiền','3356882236','0384631252',2,'123'),
(N'Phạm Ngọc Minh Anh','2005-10-11',1,N'72 Thống Nhất','3356772236','0386694512',1,'123'),
('Nguyen Van A', '1990-01-01', 0, '123 Main St', '123456789012', '0912345670', 1, '123'),
('Tran Thi B', '1991-02-02', 1, '124 Pine St', '234567890123', '0912345671', 1, '123'),
('Le Van C', '1992-03-03', 0, '125 Oak St', '345678901234', '0912345672', 1, '123'),
('Pham Thi D', '1993-04-04', 1, '126 Maple St', '456789012345', '0912345673', 1, '123'),
('Hoang Van E', '1994-05-05', 0, '127 Cedar St', '567890123456', '0912345674', 1, '123'),
('Nguyen Thi F', '1995-06-06', 1, '128 Birch St', '678901234567', '0912345675', 2, '123'),
('Tran Van G', '1996-07-07', 0, '129 Walnut St', '789012345678', '0912345676', 2, '123'),
('Le Thi H', '1997-08-08', 1, '130 Elm St', '890123456789', '0912345677', 2, '123'),
('Pham Van I', '1998-09-09', 0, '131 Ash St', '901234567890', '0912345678', 2, '123'),
('Hoang Thi J', '1999-10-10', 1, '132 Poplar St', '012345678901', '0912345679', 2, '123'),
('Nguyen Van K', '2000-11-11', 0, '133 Cypress St', '123456789013', '0912345680', 2, '123'),
('Tran Thi L', '2001-12-12', 1, '134 Redwood St', '234567890124', '0912345681', 2, '123'),
('Le Van M', '2002-01-13', 0, '135 Fir St', '345678901235', '0912345682', 2, '123'),
('Pham Thi N', '2003-02-14', 1, '136 Spruce St', '456789012346', '0912345683', 2, '123'),
('Hoang Van O', '2004-03-15', 0, '137 Willow St', '567890123457', '0912345684', 2, '123'),
('Nguyen Thi P', '2005-04-16', 1, '138 Dogwood St', '678901234568', '0912345685', 2, '123'),
('Tran Van Q', '2006-05-17', 0, '139 Magnolia St', '789012345679', '0912345686', 2, '123'),
('Le Thi R', '2007-06-18', 1, '140 Chestnut St', '890123456780', '0912345687', 2, '123'),
('Pham Van S', '2008-07-19', 0, '141 Hickory St', '901234567891', '0912345688', 2, '123'),
('Hoang Thi T', '2009-08-20', 1, '142 Pinehurst St', '012345678902', '0912345689', 2, '123'),
('Nguyen Van U', '2010-09-21', 0, '143 Sycamore St', '123456789023', '0912345690', 2, '123'),
('Tran Thi V', '2011-10-22', 1, '144 Hazel St', '234567890134', '0912345691', 2, '123'),
('Le Van W', '2012-11-23', 0, '145 Linden St', '345678901245', '0912345692', 2, '123'),
('Pham Thi X', '2013-12-24', 1, '146 Alder St', '456789012356', '0912345693', 2, '123'),
('Hoang Van Y', '2014-01-25', 0, '147 Beech St', '567890123467', '0912345694', 2, '123'),
('Nguyen Thi Z', '2015-02-26', 1, '148 Aspen St', '678901234578', '0912345695', 2, '123'),
('Tran Van AA', '2016-03-27', 0, '149 Buckeye St', '789012345689', '0912345696', 2, '123'),
('Le Thi BB', '2017-04-28', 1, '150 Cottonwood St', '890123456790', '0912345697', 2, '123'),
('Pham Van CC', '2018-05-29', 0, '151 Palm St', '901234567901', '0912345698', 2, '123'),
('Hoang Thi DD', '2019-06-30', 1, '152 Olive St', '012345678013', '0912345699', 2, '123');

select * from Account

INSERT INTO CourseSchedule (IDCourse, Slot, SlotNow, StartDay, EndDay,IDTeacher) VALUES 
(1, 30, 1, '2024-05-17', '2024-08-01',2),
(2, 30, 0, '2024-05-18', '2024-09-01',2);


select * from CourseSchedule



INSERT INTO Schedule (Day, Time, NumberOfShift, IDCourseSchedule) VALUES 
('2024-05-17', '10:00', 2, 1),	
('2024-05-20', '10:00', 2, 1),
('2024-05-22', '10:00', 2, 1),
('2024-05-24', '10:00', 2, 1),
('2024-05-27', '10:00', 2, 1),
('2024-05-29', '10:00', 2, 1),
('2024-06-1', '10:00', 2,1),
('2024-06-3', '10:00', 2, 1),
('2024-06-6', '10:00', 2, 1),
('2024-06-8', '10:00', 2, 1),
('2024-06-10', '10:00', 2, 1),
('2024-05-18', '10:00', 2, 2),	
('2024-05-21', '10:00', 2, 2),
('2024-05-23', '10:00', 2, 2),
('2024-05-25', '10:00', 2, 2),
('2024-05-28', '10:00', 2, 2),
('2024-05-30', '10:00', 2, 2),
('2024-06-2', '10:00', 2,2),
('2024-06-4', '10:00', 2, 2),
('2024-06-7', '10:00', 2, 2),
('2024-06-9', '10:00', 2, 2),
('2024-06-11', '10:00', 2,2 );

INSERT INTO RegisterCourse (IDStudent, IDCourseSchedule, IDCentralLocation,IsPayment) VALUES 
(3, 1, 1,1),
(4, 1, 1,1),
(6, 1, 1,1),
(8, 1, 1,1),
(9, 1, 1,1)

INSERT INTO Mail (IDAccount, Title, Message, IsRead) VALUES 
(2, 'Welcome', 'Welcome to the course!', 0),
(3, 'Welcome', 'Welcome to the course!', 0)





