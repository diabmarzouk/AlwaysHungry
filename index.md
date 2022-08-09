## Pitch

Ever wonder about what to make for lunch or dinner? You check in your fridge and find nothing that sparks you. Do not worry, our app has got you covered! Just enter in the items currently in your fridge and our app will magically produce a list of recipes you can make! You can also include items you just purchased and we will generate recipes accordingly. 

Always Hungry Project Pitch Video: https://www.youtube.com/watch?v=b8GIL1Gh6Do 


### Our team

Omar Diabmarzouk - odiabmar@sfu.ca

Sadaf Ahmadi - Saa142@sfu.ca 

Risa Kawagoe - rkawagoe@sfu.ca 

Mehreen Uzma - smu3@sfu.ca 

Hayato Koyama - hka137@sfu.ca


You can see our [source code on GitHub](https://github.com/diabmarzouk/AlwaysHungry).
There you can also see an APK where you can download and try our app

### How does the "magic" work?

Firstly, upon the download of the app, the user is prompted to create an account and sign up. This is done through Firebase. User entered their email and firebase authentication sends a verification email, ensuring the email address exists. Once the email verification has been approved, the user must log in with their password and are welcomed into the app. 

Once logged in, the user will see three display tabs: Add, Items and Recipes

The Add tab is where the user will enter the items you have. A drop down menu of categories lets them specify which item they would like to see. From there they get a list of items that are catered based on the category selected. The user is then requested to enter the amount and units of that particular item. Lasly, as mentioned, they can include the purchase date to help us prioritise which food item they should consider making a meal with. Once the user presses the Add Item button, the item will then get added to the database using Firebase.  

Next and most importantly is the Items tab. This tab is where the items from the database are displayed. Once items from the database are added, it will be shown in the Items section as well. This is a chance for users to verify the ingredients in their fridge or pantry that they will like to cook with. At the bottom of the screen is a Find Recipes button. Once clicked, it will open a new activity and prompt the user to specify any conditions to their search. For example, if you want to see items with bread and cheese the user may enter those options (separated via a comma) in the search box. Additionally, the user has options of adding tags to their search. Some of the pre-generated tags include, “5 ingredients are less”, “under 15 minutes” and much more. These tags help confine the search results and help cator the best results for the user. A simple click of the Get Recipe button will then launch a new activity with the selected recipes in a list view. From there, the user may scroll to search through options, click to view more details, and even select an icon to favourite their recipe which will be viewed in the Recipe tab. 

Lastly is the Recips tab. This is where the user will come to view their selected recipes. n this tab, the users favorited recipes will be populated here, and they can view there favorited recipes and remove them as well. 


### Support or Contact
Having trouble with running our app? Check out our [README](https://github.com/diabmarzouk/AlwaysHungry) on our GitHu
