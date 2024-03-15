Assignment 4 : API Adventures

Code for Northeastern University's CS4520, Spring 2024
Modified by Audrey Lin

Github Repo: https://github.com/aulin1/cs4520-assg4-starter-code

Additional Information:

The application will begin on the login screen. Unless the correct username and password combo is entered, the app will not continue to the next screen. The correct username and password combination is: "admin" and "admin" (no capitals).

Once logged in, the application will show the products screen. Scroll through to see all the products. The products are fetched, and can alternate between 30 items, 0 items, and an error message. If the JSON returns 0 items, a blank screen with the message "No Products available" will be shown."
If the JSON returns an error, the screen will instead show "Error Loading Data".

If the application has already been loaded and then goes offline, it will load from a database instead.

Clicking the back button on the product list will bring you back to the login screen. Clicking back on the login screen closes the application.

