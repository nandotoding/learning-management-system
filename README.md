
# Learning Management System

A simple Learning Management System RESTful API

Environment Variables:
```
DB_HOST=<database server host>
DB_PORT=<database server port>
DB_NAME=<database name>
DB_USERNAME=<database username>
DB_PASSWORD=<database password>
JWT_SECRETKEY=<JWT secret key>
```
API Endpoints:

1. Register Student Account  
   POST `/auth/registration`  
   REQUEST BODY
   ```
   {
       "username":"<username>",
       "password":"<password>",
       "studentName":"<name>"
   }
   ```

2. Login as Student  
   POST `/auth/login`  
   REQUEST BODY
   ```
   {
       "username": "<username>",
       "password": "<password>"
   }
   ```

3. Logout Student Session  
   POST `/auth/logout`  
   REQUEST BODY
   ```
   {
       "token":"<JWT token>"
   }
   ```

4. List All Students  
   GET `/student`  
   REQUEST HEADER
   ```
   "Authorization":"Bearer <JWT token>"
   ```

5. Search Students by Name  
   POST `/search`  
   REQUEST HEADER
   ```
   "Authorization":"Bearer <JWT token>"
   ```
   REQUEST BODY
   ```
   {"query":"<name query>"}
   ```

6. List Student's Courses  
   GET `/{username}`  
   REQUEST HEADER
   ```
   "Authorization":"Bearer <JWT token>"
   ```

7. Take Course  
   POST `/{username}/add-course`  
   REQUEST HEADER
   ```
   "Authorization":"Bearer <JWT token>"
   ```
   REQUEST BODY
   ```
   {"courseId":"<course ID>"}
   ```
