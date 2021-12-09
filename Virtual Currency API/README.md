**Backend Developer Technical Ability Evaluation**

Project Overview:
We need an API that allows users to send virtual currency to each other.

Functional Requirements:
 ```
 ● Users must be able to create an account. It must contain a username, email, and password
 ● Authentication is not needed. Simply mock authentication by allowing a user id to be passed in each 
   request in a header
 ● Users must have a virtual currency property (decimal number; VC henceforth)
 ● All users must accrue 0.25 VC every 30 minutes
 ● A user must be able to send any amount of their current VC to up to 10 different users at a time
 ● A user must be able to retrieve a list of VC transactions where they can see who they’ve sent and 
   received money from
```

Project Requirements:
```
 ● Must be written using the Spring Boot 2.2.1 release
 ● Must follow code standards, code must be clean, documented and readable
 ● Must sensibly use Spring Boot features to accomplish the above tasks in the most maintainable manner
 ● Must develop the project in a development branch (ie. dev/initial), then merge into master once complete. 
   Tag the finished release as v1.0.0-
   dev. If you need to fix things afterwards, do so following semver. Delete the old branch on remote
 ● Write a MySQL script to create any necessary tables
```
#####
**DATABASE configuration**

CREATE DATABASE `wallet` /*!40100 DEFAULT CHARACTER SET latin1 */;

Open `application.properties` file
Change your MySQL database (username & password)
 
Once you Run the Application, wallet database will have those tables
```
 ● user
 ● phone
 ● user_phones
 ● transactions
 ● hibernate_sequence
```
#####


**Folder img has a lot of images that explains some cases:**
```
● signup.PNG
● login.PNG
● transfer to same user -illegal transfer.PNG
● transfer to one user  -successful.PNG
● transfer to more user at same time -successful.PNG
● transfer from.PNG retrieve a list of VC transactions 'sent'
● transfer to.PNG retrieve a list of VC transactions 'received'
```

#####

**When you start, you can register:**

**signup.PNG**
This will return a JSON with the user, and the token that will give access to the system.
Note: The Token is valid for 30 minutes.

```
URL: http://localhost:8080/signup
method: POST
```
##### Body: #####
```json
{
  "firstName":"William",
  "lastName":"Shakespeare",
  "userName":"William Shakespeare",
  "email":"wshakespeare@virtualwallet.com",
  "password":"xq5t3e4r2",
  "virtualCurrency":5000,
  "phones":[
    {
      "number":987654321,"area_code":99,"country_code":"+20"
    }
  ]
}

```




**img/login.PNG**
Login
##### Example: #####
```
URL: /login
method: POST
```
##### Body: #####
```json
{
  "email": "wshakespeare@virtualwallet.com",
  "password": "xq5t3e4r2"
}
```



**img/Transfer is successful - one user.PNG**
##### Example: #####
```
URL: /transfer
method: POST
```
##### Body: #####
```json

  { 
    "amount":500, 
    "from":1, 
    "to":2
  }
```


**img/Transfer is successful - more users.PNG**
 ##### Example: #####

URL: /transfers
method: POST
##### Body: #####
```json
  [
  {  "amount":100,  "from":1,  "to":2  },
  {  "amount":200,  "from":1,  "to":3  },
  {  "amount":300,  "from":1,  "to":4  },
  {  "amount":400,  "from":1,  "to":5  },
  {  "amount":500,  "from":1,  "to":6  },
  {  "amount":600,  "from":1,  "to":7  },
  {  "amount":700,  "from":1,  "to":8  },
  {  "amount":800,  "from":1,  "to":9  },
  {  "amount":900,  "from":1,  "to":10  },
  {  "amount":1000,  "from":1,  "to":11  },
  {  "amount":1500,  "from":1,  "to":12  }
  ]
  ```



#####################################################################################
****Error messages follow the following pattern:****

**Error Response: Code: 400 BAD REQUEST Content:**
```json
{
" message " : "Email already exists",
" code " :  " 400 "
}
```
OR

**Code: 400 BAD REQUEST Content:**
```json
{
" message " : " Invalid fields ",
" code " :  " 400 "
}
```
OR

**Code: 400 BAD REQUEST Content:**
```json
{
" message " : " Missing fields ",
" code " :  " 400 "
}
```
**Success Response: Code: 200 OK Content:**

**Code: 400 BAD REQUEST Content:**
```json
{
" message " : " Invalid e-mail or password ",
" code " :  " 400 "
}
```

**Code: 400 BAD REQUEST Content:**
```json
{
" message " : " Missing fields ",
" code " :  " 400 "
}
```

**Code: 401 UNAUTHORIZED Content:**
```json
{
" message " : " Unauthorized - invalid session ",
" code " :  " 401 "
}
```
