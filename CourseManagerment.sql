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
    IDTeacher INT,
    IDCentralLocation INT,
	IsPayment bit,
    CONSTRAINT fk_RegisterCourse_Student FOREIGN KEY(IDStudent) REFERENCES dbo.Account(ID),
    CONSTRAINT fk_RegisterCourse_Teacher FOREIGN KEY(IDTeacher) REFERENCES dbo.Account(ID),
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
(N'Phan Minh Tâm','2003-01-21',1,N'72/34 Dương Đức Hiền','3356882236','0384631252',2,'123')

select * from Account

INSERT INTO CourseSchedule (IDCourse, Slot, SlotNow, StartDay, EndDay,IDTeacher) VALUES 
(1, 30, 1, '2024-05-01', '2024-12-01',2),
(2, 30, 0, '2024-05-01', '2024-12-01',2),
(3, 50, 0, '2024-05-01', '2024-12-01',2),
(4, 35, 0, '2024-05-01', '2024-12-01',2),
(5, 30, 0, '2024-05-01', '2024-12-01',2)


INSERT INTO Schedule (Day, Time, NumberOfShift, IDCourseSchedule) VALUES 
('2024-06-01', '09:00', 2, 1),
('2024-06-03', '09:00', 2, 1),
('2024-06-05', '09:00', 2, 1),
('2024-06-07', '09:00', 2, 1),
('2024-06-09', '09:00', 2, 1),
('2024-06-11', '09:00', 2, 1),
('2024-06-13', '09:00', 2,1),
('2024-06-15', '09:00', 2, 1),
('2024-06-17', '09:00', 2, 1),
('2024-06-19', '09:00', 2, 1),
('2024-06-21', '09:00', 2, 1);

INSERT INTO RegisterCourse (IDStudent, IDCourseSchedule, IDTeacher, IDCentralLocation,IsPayment) VALUES 
(3, 1, 2, 1,1),
(4, 1, 2, 1,1),
(6, 1, 2, 1,1),
(8, 1, 2, 1,1),
(9, 1, 2, 1,1)

INSERT INTO Mail (IDAccount, Title, Message, IsRead) VALUES 
(2, 'Welcome', 'Welcome to the course!', 0),
(3, 'Welcome', 'Welcome to the course!', 0)





