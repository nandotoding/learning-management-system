
# Learning Management System

A simple Learning Management System RESTful API

1. Student Registration
POST /auth/registration  
REQUEST BODY  
{
    "username":"<username>",
    "password":"<password>",
    "studentName":"<name>"
}  

2. Student Login
POST /auth/login  
REQUEST BODY  
{
    "username": "<username>",
    "password": "<password>"
}

3. Student Logout
POST /auth/logout  
REQUEST BODY  
{
    "token":"<JWT token>"
}

4. Student List
GET /student  
REQUEST HEADER  
"Authorization":"Bearer <JWT token>"

5. Student Search by Name
POST /search  
REQUEST HEADER  
"Authorization":"Bearer <JWT token>"  
REQUEST BODY  
{"query":"<name query>"}