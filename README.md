# GermanNovikov
Test task solution for Enefit 

#### Used technologies
- Java 1.8 
- Spring Boot 
- Swagger UI
- Maven wrapper
- ReactJS

## Run
Before running need to install project. Installing project download and install ReactJS and NodeJs.
### Directly from an IDE
#### Using main class
Run main class named `EnefitApplication` (package `com.german.novikov.enefit.EnefitApplication`).

For using frontend part:

In project folder open cmd and run command:
```
npm start
```

Open link in browser to see Swagger UI:
```
localhost:8082
```

Open link in browser to see frontend part:
```
localhost:3000
```

## Frontend
Unfortunately did not have time to finish fronted part.
 
In frontend done:

- Displaying all tickets in table 
- Sort tickets by priority, status and created date by clicking to title of column
- Open form for creating new ticket

In frontend not done:

- Creating new ticket
- Deleting ticket
- Updating ticket 
## Backend

In backend need write validations for up cumming requests.

## Description
API includes:

- Getting the list of tickets:
```
GET ..api/desk
```
- Getting the list of tickets:
```
GET ..api/desk/sort_by_priority
```
- Getting the list of tickets:
```
GET ..api/desk/sort_by_created_date
```
- Getting the list of tickets:
```
GET ..api/desk/sort_by_status
```
- Adding new ticket:
```
PUT ../api/ticket    Arguments: Ticket object
```
- Updating ticket:
```
POST ../api/ticket/update    Arguments: Ticket object
```
- Get ticket by id:
```
GET ../api/ticket?id={id}    Param: Ticket id
```
- Deleting ticket by id:
```
GET ../api/ticket/delete?id={id}    Param: Ticket id
``` 
- Get priority:
```
GET ../api/ticket/priority'    
```
