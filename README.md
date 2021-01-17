# CPSC 210 Term Project

## Topic: Online Store Application
### By Victor Cheng

#### ***What will the application do?***
This application is an online store application that allow users to purchase any goods that we provided. 

In order to use the application, users need to register an account in our application, and they will need to provide 
us their personal details at this stage.

After that, they can start shopping in the application! For now, the application provide limited merchandise, but we 
will keep update the merchandise as we further develop the application in the future.

Users can simply select any item that they desired and put them into the cart. They can pay by credit-card or 
master-card at this stage.

The cost that they spend in this application will be recorded and accumulated, and they will receive coupons or 
discount if they reach certain "goal"(Cost threshold).

####***Who will use it?***
Users around the world with some constraint
*Users Constraint*:
- Must be 16 year old or above to register an account
- Have a valid credit card (VISA/MASTER) to settle the payment

####***Why is this project of interest to you?***
I want to create an online store in the future, and I think this is a good opportunity to start the project.
Also, I can have a chance to think about what feature and merchandise that my store/application can provide to uses.

###***First Phase User Stories***
- As a user(manager), I want to store the customer data, saved in the database.
- As a user(customer), I want to extract my personal information in the application
- As a user(customer), I want to modify my personal information in the application
- As a user(manager), I want to encrypt user's password before saving it (Security perspective).
- As a user(customer), I want to create an account.

###***Second Phase User Stories***
- As a user(customer), I want to be able to save my Account Info to file.
- As a user(customer), I want to be able to be able to load my Account Info from a file.

###***Third Phase User Stories***
- As a user(customer), I can create an account and login via GUI
- As a user(customer),  I wanted to be prompted with the option to load data from a file when the application 
  starts and prompted with the option to save data to file when account was created.
- As a user(customer), I can modify my account info via GUI.

###***Phase 4: Task 2***
- Make appropriate use of the Map interface in my code as a DataBase. 
    Class: CustomerDataBase. The Key is the username, and value is the Customer Object. 
    
###***Phase 4: Task 3***
- I should work on the hierarchy design of those UI classes, create an interface that specify the common UI behaviour.
- I should reduce coupling by removing redundant association between those UI classes.
    For example: SignUpGUI and LoadDataGUI might not depend on MainPageGUI if I refactor the MainPageGUI class.
- I should refactor the MainPageGUI class to reduce cohesion, because I could create a new class to handle read/write 
file/databases. 
- I should create an interface for OnlineStoreApp(Terminal App) and MainPageGUI(GUI APP) as they have common 
method and behaviour.
