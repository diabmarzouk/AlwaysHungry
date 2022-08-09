## About

Ever wonder about what to make for lunch or dinner? You check in your fridge and find nothing that sparks you. Do not worry, our app has got you covered! Just enter in the items currently in your fridge and our app will magically produce a list of recipes you can make! You can also include items you just purchased and our database will store that information for a later use. 

### Our team

Hayato Koyama - hka137@sfu.ca

Sadaf Ahmadi - Saa142@sfu.ca 

Mehreen Uzma - smu3@sfu.ca 

Risa Kawagoe - rkawagoe@sfu.ca 

Omar Diabmarzouk - odiabmar@sfu.ca

You can see our [source code on GitHub](https://github.com/diabmarzouk/AlwaysHungry).
There you can also see an APK where you can download and try our app

### How does the "magic" work?

Firstly, upon the download of the app, the user is prompted to create an account and sign up. This is done through Firebase. User entered their email and firebase authentication sends a verification email, ensuring the email address exists. Once the email verification has been approved, the user must log in with their password and are welcomed into the app. 

Once logged in, the user will see three display tabs: Add, Items and Recipes

The Add tab is where the user will enter the items you have. A drop down menu of categories lets them specify which item they would like to see. From there they get a list of items that are catered based on the category selected. The user is then requested to enter the amount and units of that particular item. Lasly, as mentioned, they can include the purchase date to help us prioritise which food item they should consider making a meal with. Once the user presses the Add Item button, the item will then get added to the database using Firebase.  

Next is the Items tab. This tab is where the items from the database are displayed. Once items from the database are added, it will be shown in the Items section as well.

Lastly and most importantly is the Recipies tab. This is the place the user wants to be when selecting which recipe to make. In this tab, the users favourited recipes will be populated here, and they can view there favourited recipes and remove them as well.


### Support or Contact

Having trouble with running our app? Check out our [README](https://github.com/diabmarzouk/AlwaysHungry) on our GitHub.
