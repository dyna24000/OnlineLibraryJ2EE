# Online Library Management System - J2EE Demo

Aligned to the supplied proposal:
- JSP/JSTL/HTML/CSS/Bootstrap presentation
- Java Servlets as controllers
- JDBC + DAO for MySQL
- MVC structure
- Apache Tomcat
- Admin and Student roles
- Book CRUD
- Borrow approval/rejection
- Return workflow
- AuthFilter session protection
- PreparedStatement
- Password hashing

## Requirements
- JDK 11+
- Apache Tomcat 9
- MySQL 8
- Maven 3.8+
- Eclipse IDE for Enterprise Java Developers (or another Maven-compatible Java IDE)

## 1. Database
Open MySQL Workbench and run:
src/main/resources/schema.sql

The demo users are:
Admin: admin@library.com / admin123
Student: student@library.com / student123

## 2. Database password
Open:
src/main/java/com/library/util/DBConnection.java

Change:
USER = "root"
PASSWORD = "root"

to your actual MySQL credentials.

## 3. Build
From the project folder:
mvn clean package

The WAR will be:
target/online-library.war

## 4. Run on Tomcat
Copy the WAR into Tomcat 9's webapps folder.
Start Tomcat.
Open:
http://localhost:8080/online-library/

## 5. Demo sequence
1. Login as student.
2. Show catalog and available quantities.
3. Click Request Borrow.
4. Logout.
5. Login as admin.
6. Open Borrow Requests.
7. Approve the request.
8. Show quantity decreased and due date.
9. Logout.
10. Login as student.
11. Show approved request and return it.
12. Login as admin and show quantity increased.
13. Open Manage Books and demonstrate Add/Edit/Delete.

## Architecture
Browser -> JSP -> Servlet -> DAO -> JDBC -> MySQL
                       |
                    Session/AuthFilter

## Important
The sample passwords are hashed in the database. Use the plain demo passwords above when logging in.
