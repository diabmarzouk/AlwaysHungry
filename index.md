## Pitch

Ever wonder about what to make for lunch or dinner? You check in your fridge and find nothing that sparks you. Do not worry, our app has got you covered! Just enter in the items currently in your fridge and our app will magically produce a list of recipes you can make! You can also include items you just purchased and we will generate recipes accordingly. 

Always Hungry Project Pitch Video: https://www.youtube.com/watch?v=b8GIL1Gh6Do 


### Our team

Omar Diabmarzouk - odiabmar@sfu.ca

Sadaf Ahmadi - Saa142@sfu.ca 

Risa Kawagoe - rkawagoe@sfu.ca 

Mehreen Uzma - smu3@sfu.ca 

Hayato Koyama - hka137@sfu.ca


## Our Contributions

Sadaf: Basic UI design & essential improvements later on , Add items tab (dynamically add all the components based on selected category, the option to add their own item if they cannot find it in the provided categories using Dialog, used calendar to keep track of the purchased date of each item, worked with firebase database to write the information on db), Items tab (read data from firebase database and show them in categorised listviews)

Risa: main task was backend data operations for recipe API and basic UI for recipe API. More specifically RecipeFinder.kt and RecipeRetriever.kt are the related xml file. Implemented the recipe retrieval functionality using rapid API. Used firebase to store and retrieve recipes.

Omar: Implement login, sign up, and verification for application to allow for cross device usage. This meant we used Firebase authentication and firestore. Implementing the Firestore is vital for our data in the app as that is how we store what we have in the fridge, store our recipes, settings, etc. Created base app with Sadaf and UI design. Also acted as a helping hand to anyone that needed anything + help with implementing database structures across our different parts of the app. Also acted as a helping hand to other members for times they needed help.

Mehreen: Recipe API with Risa( integrate rapid api in the android app, selected recipes from API based on search request, and selected tags, determined what functionalities the tasty api offers, figured out what functions to use for retrieving a list of recipes *without any parameters passed to it, figure out how to pass parameters to function to get a specific list of recipe), Thread Design Diagram (constructed a design that represents the functionality of the app, accurately represented with threads and UI features, using it as a reference when stuck) and Webpage Description (filled in the web page with our pitch, description of how the app works, description of each members workload and required files such as the apk file). 


## APK file

View our APK file and test the app yourself. Enjoy! Let us know what you think. 
https://github.com/diabmarzouk/AlwaysHungry/raw/master/apk/app-debug.apk


### Thread Design Diagram 

The diagram diagram can be viewed here: ![My helpful screenshot](/assets/Better and improved threaded design diagram.pdf)


### Video to our Final Project 

The video to our final project can be viewed here: https://www.youtube.com/watch?v=OVkALWObmVg



### Zipped Final Project

To view the entire project, we have created a nice little zipped package for you. It can be viewed at the link here: https://github.com/diabmarzouk/AlwaysHungry/archive/refs/heads/master.zip




You can see our [source code on GitHub](https://github.com/diabmarzouk/AlwaysHungry).
There you can also see an APK where you can download and try our app

### How does our app work?

Firstly, upon the download of the app, the user is prompted to create an account and sign up. This is done through Firebase. User entered their email and firebase authentication sends a verification email, ensuring the email address exists. Once the email verification has been approved, the user must log in with their password and are welcomed into the app. 

Once logged in, the user will see three display tabs: Add, Items and Recipes

The Add tab is where the user will enter the items you have. A drop down menu of categories lets them specify which item they would like to see. From there they get a list of items that are catered based on the category selected. The user is then requested to enter the amount and units of that particular item. Lasly, as mentioned, they can include the purchase date to help us prioritise which food item they should consider making a meal with. Once the user presses the Add Item button, the item will then get added to the database using Firebase.  

Next and most importantly is the Items tab. This tab is where the items from the database are displayed. Once items from the database are added, it will be shown in the Items section as well. This is a chance for users to verify the ingredients in their fridge or pantry that they will like to cook with. At the bottom of the screen is a Find Recipes button. Once clicked, it will open a new activity and prompt the user to specify any conditions to their search. For example, if you want to see items with bread and cheese the user may enter those options (separated via a comma) in the search box. Additionally, the user has options of adding tags to their search. Some of the pre-generated tags include, “5 ingredients are less”, “under 15 minutes” and much more. These tags help confine the search results and help cator the best results for the user. A simple click of the Get Recipe button will then launch a new activity with the selected recipes in a list view. From there, the user may scroll to search through options, click to view more details, and even select an icon to favourite their recipe which will be viewed in the Recipe tab. 

Lastly is the Recips tab. This is where the user will come to view their selected recipes. n this tab, the users favorited recipes will be populated here, and they can view there favorited recipes and remove them as well. 


### Support or Contact
Having trouble with running our app? Check out our [README](https://github.com/diabmarzouk/AlwaysHungry) on our GitHu
