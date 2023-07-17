use expensetracker;

INSERT INTO user VALUES(null, "marco", "password"); 

INSERT INTO account VALUES(null, 1, "Transportation", 200); 

INSERT INTO account VALUES(null, 1, "Entertainment", 20); 

INSERT INTO expense VALUES(null, 1, "Gas", 180);

INSERT INTO expense VALUES(null, 1, "Car Maintenance", 15);

INSERT INTO expense VALUES(null, 2, "Netflix", 15);

INSERT INTO expense VALUES(null, 2, "World of Warcraft", 15);


SELECT * FROM user;

SELECT * FROM account;

SELECT * FROM expense;

SELECT a.accountid, a.name, a.monthlyBudget FROM user u JOIN account a on u.userid = a.userid WHERE u.userid = 1;

SELECT e.expenseid, e.name, e.monthlyCost FROM account a JOIN expense e on a.accountid = e.accountid WHERE a.accountid = 2;