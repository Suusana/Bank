# Instruction
This is a simple java application where users can save and search cardholder information based on Spring Boot + MySQL + static HTML.


## Feature:  
-- Add a card record (Cardholder Name + PAN).  
-- Search for cards by the last 4 digits.  
-- Encription and Decription.  
-- Display results in a simple table.  

**Static HTML + JavaScript** is used in frontend and **Axios used to call the backend's api**. Backend used **Spring Boot to provide Rest API**. All the data stores in **MySQL**.

---
## Technology Stack:  
**Frontend:** HTML, CSS, JavaScript, Axios  
**Backend:** Spring Boot, MyBatis  
**Database:** MySQL 8.0.40  
**Tool:** Maven, Figma  
**Development Environment:** IntelliJ IDEA, JDK 17  

---
## Database Structure
MySQL 8.0.40 is used in this project. Below shows the structure of the database. SQL query for creating the database is also provided.  

### Database Name
```sql
create database if not exists bank;  
```

### Card Table
||       id  | card_holder | PAN | created_time |
|------|-----------|-------------|-----|--------------|
|**Description**|The unique id of the card| The card's holder | Card Number   | The card's created date|
|**Data Type**|Integer|varchar(50)|varchar(16)|datetime|
|**Keys**|Primary Key|


```sql
create table if not exists bank.card
(
    id           int         not null
        primary key,
    card_holder  varchar(50) not null,
    PAN          varchar(16) not null,
    created_time datetime    not null
);
```

### Design Reason:
1. All tables need a unique identifier, so an integer primary key **(id)** is used to efficient indexing.  
2. Used varchar(50) to flexibly store different length of name and make sure that we can save some storage usage.  
3. Although PAN is numbers, but there are a posibility that the first digit of PAN is 0 which won't be store by Integer (eg. 0155 => 155), so I finnally used varchar(16) to store the PAN.
4. Used datetime to store the created time of the card so that we can know the precise time of the card.




