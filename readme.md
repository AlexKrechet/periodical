Project option 19

Title: Periodicals system.

Tasks:

    - Administrator managing periodicals catalogue;
	- Reader (User) selecting periodicals from the list and subscribing;
	- System calculating total price and forms payment.

Project requirements:

	- classes which describe entities according to the task field;
	- classes and methods should be named according to their nature and properly structured within packages;
	- related to the task field information should be stored in DB, API JDBC should be used for accesses with use of pool of connections (standard or custom). It is recommended to use MySQL;
	- application must maintain Cyrillic (including data in DB);
	- code must be documented; 
	- application must be covered with unit-tests;
	- session and filters must be used and incidents must be handled by Log4j;
	- depends on project, Pagination and Transaction should be used;
	- servlets and JSP must be used for application functions;
	- JSTL library should be used at JSP pages;
	- correct errors and exceptions handling, user must not see them at all;
	- authorization and authentication system must be implemented.

Using tips:

	- After successful registration, Periodicals system allows clients to search through the list of periodicals and subscribe.
	- When subscribe procedure is done, system will represent the order with a total price to the client.
    - In case of clients satisfied, they confirm the order
	- After successful payment, system administrator changing order status for paid.
    - On demand system administrator may change/restore clients password, name and surname.
	- In any case of illegal payment, system administrator can block client by setting up banned status.

Tech requirements:

    1. Apache Tomcat;

    2. Maven;

    3. MySQL database;

    3.1. New database named "eshop1" (user "root", password "kA500asvB1QtaiSDXrxT")

