# spring-security
Implemented spring security with Employee crud - Role(ADMIN, NORMAL) based authentication &amp; JWT token implementation

# APIS
1. POST: Resgister a employee 
URL - http://localhost:9092/register
Request : 
{
        "name": "Pratik",
        "salary": 40000,
        "emailId": "pratik@gmail.com",
        "password": "pratik@123",
        "role": "ROLE_NORMAL",
        "bankAccount": {
            "balance": 25000,
            "transcationType": "DEBIT",
            "accountNumber": 111
        },
        "company": {
            "companyName": "Paisa Nikal"
        }
    }
Response : 
user added success - Showing emp object.

2. GET: Generate JWT token with email and password
URL - http://localhost:9092/token
Provide email and password in authorisation header - basic auth
![image](https://github.com/pratik5141/spring-security/assets/153704209/378d2e79-c4b6-449f-accd-5ac2bc816361)

Response :
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MDM3NzQ0MTQsImlhdCI6MTcwMzczODQxNH0.5CjsfgkGoPIDErktgvhMjOMKffDypuWKr5zlwZeJ4_c"
}

3. GET : Get employee by ID (Employee with normal role can access this by passing id and jwt token with normal user credentials)
URL - http://localhost:9092/get/employee/{id}

4.  GET : Get all employees (Employee with ADMIN role can only access this and jwt token with admin credentials)
URL - http://localhost:9092/get/employees


