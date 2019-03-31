**Editing a profile**

When an account is in the setup profile page, the user has the option to input information into a bio text. When they enter text into the field specified “bio” and click on the save button, all of the characters in the “bio” text will be added to the database. The user then has the option to go to the setup profile page where they can see their bio with the characters that they typed. This bio can be further edited and re-saved. Additionally, there are other fields in the set up page that must be filled out. These are required and will return a message and an error if they are not. Following SOA protocols, the bio and other information is stored in the database and is hidden from the user. They must explicitly go to their setup profile page to augment the information. It has a discrete unit of functionality that is to display what they have written in this section.

The test for this story was to login to an account, go to the setup profile page and then enter information in the bio section and the other fields of information. The characters entered varied from empty strings, to foreign characters, etc. Then to re go into the bio page to determine if the information was stored. Another test was to have an account whose information was already added and to see if the information was stored after logging out. 

**Deleting an account (athlete/coach)**

When in the settings page, there is a button to delete your account. When you click on this button, you will be sent to the login page, and your account will be removed from the database. If you try to log in with the information from the account that was just deleted, you will not be allowed to. You can then remake the account from the register page. Direct access to the database is not allowed so users cannot manually delete their account. They must delete their account through the settings page. Information about your account is only shown through messages given from the app. The only functionality of this story is to delete the account. 

The test for this story was to login to an existing account and to then go to the settings page. Once at the settings page, I clicked on the delete account button. When sent to the login page, I re tried logging into the same account used previously and the account no longer exists.

**Creating a new challenge (coach)**

If you are a coach, you can go to a create challenge page. This page has fields that the user coach must fill out. They specify the difficulty, title, length, activities and description of the challenge. The user can then click a create button and the challenge will appear in the database. The coach has no access to the database and must fill out the create challenge form to create a challenge that appears in the database. The only functionality this page has is to create a challenge making it discrete.

The tests for this create challenge is to log in to a coach account, go to the create challenge page and enter fields. The fields entered will vary testing for null and empty inputs as well as a regular challenge. 

**Changing settings on account**

When logged in and in the settings page, there are many sliders or checkboxes for the user. When the user clicks on one of these settings, the corresponding setting will change for the user. For example, if the user clicks on night-mode, the app will refresh on the settings page with night-mode enabled. The user has no way of accessing these settings without these sliders and checkboxes. Each individual setting has a corresponding checkbox/slider and they can only be changed from the checkbox/slider.

The tests for these is to go into the settings page and click on each setting. Then to determine if the setting is enabled, the test will go into different pages to determine if they are shown correctly.
