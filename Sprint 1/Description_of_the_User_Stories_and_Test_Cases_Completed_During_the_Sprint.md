# User Stories Completed

## Failed account creation
The user story dictated that there be an error message that appears when there is something that invalidates valid account credentials and prevents the account from being created. Our app currently checks the password, email and username the user has typed in to see if they are all valid. If the account is not valid, there is a message that pops up that specifies what prevented the account creation. The app stays on the same screen allowing for additional attempts at creating an account after failing. Following SOA protocols, implementation details are hidden from the user as they can only view the account creation page and what the error was. It has a discrete unit of functionality that is to display an error message and prevent account creation. 

The test for this story created inputs for the email, username, and password. It individually tested all three ways the password could fail (too short, no numbers, no capital letters), as well as different ways the email could fail (no @ symbol, two @s). It also tested for an already created account to see if you could re-register, and tests included empty strings. All of the tests were self-contained and only tested for registration failure. 

## Successful account creation (Athlete)
When registering an account, if the information used does not violate any rules that prevent its registration, it should send that information to the database and send the user into the account settings page. The user does not see the transfer of information, only that their account was successfully created. The only functionality of this is the creation of the account.

The test for this story was registering an account and using account information that does not violate any of the rules. When inputting the information and clicking register, the page is redirected into a settings page and there is a message that says that the account was successfully created. This tests tests only that it successfully registers by not having an error occur.

## Successful account creation (Coach)
When registering an account as a coach, the same registration page is used. However, there is a checkbox that when checked and if the information used does not violate any rules that prevent its registration will allow creation of the account. It should send that information to the database specifically a section devoted to coaches only and send the user into the account settings page. The database separates the users into coaches and athletes. The user does not see the transfer of information, only that their account was successfully created. The only functionality of this is the creation of the account.

The test for this story was registering an account and using account information that does not violate any of the rules and clicking the checkbox for the coach. When inputting the information, clicking the checkbox, and clicking register, the page is redirected into a settings page and there is a message that says that the account was successfully created. This tests tests only that it successfully registers by not having an error occur.

## Unsuccessful login
The user story stated that when there is a login that fails, there will be an error that states that there is a wrong username/email and password combination. Our app currently, does display whether there is a wrong email and password combination if the account is not in the database. Only the login page is available to the user, the database is hidden and can only be accessed with specific permissions keeping the code encapsulated. The login page only has the function of logging in users or displaying an error preventing them from logging in which is only unit of functionality of the page. 

The tests for this story used accounts that were not registered in the database and tested whether they could log into the app with it. Only tests that anything that does not fit that description will prevent log in.

## Logging into an existing account (player/coach)
When a user has a registered account that they input into the login page, the user should be able to access the homepage and other functionality within the app. Currently, we have a homepage in our app that can only be accessed when a person successfully registers an or logins to their account. Currently, the user can only change pages from the login screen to the registration screen, and to the home screen if they input information that is registered in the database. This prevents the user from going to the homepage without the registration information and keeps any data associated with an account from being shown. The only functionality this user story provides is logging into the app.

The tests for this story was using an account that was already registered in the database and testing if the homepage appeared after inputting the registration information. This tests for accounts that successfully login.
