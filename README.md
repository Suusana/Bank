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
||       id  | card_holder | PAN | created_time | last_4_digits |
|------|-----------|-------------|-----|--------------|--------|
|**Description**|The unique id of the card| The card's holder | Card Number   | The card's created date|The last 4 digits of the PAN|
|**Data Type**|Integer|varchar(50)|varchar(16)|datetime|varchar(4)|
|**Keys**|Primary Key||Unique Key


```sql
create table if not exists card
(
    id            int auto_increment
        primary key,
    card_holder   varchar(50) not null,
    PAN           varchar(64) not null,
    create_time   datetime    not null,
    last_4_digits varchar(4)  not null,
    constraint card_pk
        unique (PAN)
);
```

Here are also some initial data
```sql
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (1, 'Alice', '2LWYSMdnDJSym1TSN54uesXryeud7lOPCtlpWV16dAw=', '2025-09-22 18:00:25', '3456');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (2, 'Bob', 'qxQ8Sb7xmCn9K+Q8uXgP3jphdUZuPASenhrhsL/1J9c=', '2025-09-22 18:00:38', '4567');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (3, 'Charlie', 'FBKxuZDP0YxNOUBZy5e6W56qt9shHONKFvAk8ddxh3U=', '2025-09-22 18:00:48', '8765');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (4, 'David', 'zK6T9v4mQUye8JNb9v1PLzQa/YdSxIg07UaEiFUNF44=', '2025-09-22 18:00:57', '1353');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (5, 'Emma', 'e7esXwEE+heRpk7OQgMRCj6sI2iEfL8wyT1NZgna3XY=', '2025-09-22 18:01:09', '5213');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (6, 'Frank', '/+ov6j1aT0PSxM0a/p1pZyrLLZttYmSWulqG7KvLWqo=', '2025-09-22 18:01:20', '2456');
INSERT INTO bank.card (id, card_holder, PAN, create_time, last_4_digits) VALUES (7, 'Grace', 'OpMe1MukqMsBY4ebXka2K8GcAsHA8tboATk8fM6wQLQ=', '2025-09-22 18:01:32', '5486');
```

### Design Reason:
1. All tables need a unique identifier, so an integer primary key **(id)** is used to efficient indexing.  
2. Used varchar(50) to flexibly store different length of name and make sure that we can save some storage usage.  
3. Although PAN is numbers, they will encrypted before store, so I finnally used varchar(64) to store the PAN(encrypted PAN). Also, the PAN should be unique.
4. Used datetime to store the created time of the card so that we can know the precise time of the card.
5. Store the last 4 digits of the card used to search function, because after AES encryption, it is impossible to find the pan by the last 4 digits. But we can find it by last_4_digits column.

### When runs this project
please go to the url: http://localhost:8080/index.html to see the feature.  
You will see two pages which looks like:
<img width="1258" height="844" alt="image" src="https://github.com/user-attachments/assets/c6cdd633-a4fe-4ac9-8b56-fd6ad8b3af6e" />  
<img width="1359" height="498" alt="image" src="https://github.com/user-attachments/assets/4bba11da-c0e7-4c8b-af6c-612df47e494f" />






