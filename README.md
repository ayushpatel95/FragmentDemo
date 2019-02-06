# FragmentDemo
In this assignment you will revisit “RecipePuppy recipe finder” app. The App displays recipes from Recipe Puppy (http://www.recipepuppy.com/). This allows you to search the recipes of the dish you are interested in, with proper ingredients. In this assignment, you will learn how to use fragments instead of multiple activities. The base URL of the API is: http://www.recipepuppy.com/api/?i=&lt;INGREDIENTS LIST, COMMA SEPARATED>&amp;q=&lt;DISH NAME>. An example URL is: http:// www.recipepuppy.com/api/?i=onions,garlic&amp;q=omelet . You can load the URL in codebeautify.org/jsonviewer for better understanding of JSON you receive. In this assignment we will build two fragments. The first screen is to search, and the second screen is to display the results of the recipe search.

You need to implement the following:
1. An EditText to put the dish name.
2. A scrollable add ingredients panel which can add up to 5 ingredients in total. 
3. The panel to add ingredients should be implemented using RecyclerView, see figure
1 (b).
4. It should display an empty EditText, and a floating action Add button. Once, you add
one ingredient, the floating action button should be changed from Add to Remove
button. Follow figure 1(b).
5. Then you need to create the URL as: http://www.recipepuppy.com/api/?
i=<INGREDIENTS LIST, COMMA SEPARATED>&q=<DISH NAME>.
6. There should be a Search button. Clicking on that button should take you to the
Display recipes screen where it displays a list of recipes.
Recipes Screen (60 Points)
1. Use an AsyncTask or Thread pool to communicate with the RecipePuppy api and to
parse the result. Do not use the main thread to parse them.
2. In our example, we wanted to search for a Omelet recipe having two key
ingredients: Onions, and Garlic.
3. In received JSON, you will find that there are two levels of the hierarchy. Inside
“array”, you will find “results”. You need to parse all the items in results.
4. Use AsyncTask or Thread pool to retrieve and parse it.
5. You need to implement a ProgressBar to display the progress while it is parsing, see
figure 1(c). You can do it using a different fragment.
6. Finally display your result. Please follow the instructions:
1. The whole list of results should be displayed in a horizontal Recycler/CardView.
2. You have four things to display in each item:
1. Title (“title” in JSON)
2. Recipe image (“thumbnail” in JSON)
3. Ingredients (“ingredients” in JSON)
4. URL (“href” in JSON)
3. You need to use a separate AsyncTask when you load the image. Alternatively,
you can use Picasso library to load the image. (http://square.github.io/picasso/).
4. The URL should be clickable, use implicit intents to open it in browsers.
5. f no recipe is found, return to the main screen and toast to report that there were
no recipes found.
6. Clicking on Finish button should Finish the second screen and return you to the
main screen.
7. Do not use the default Back Button options, override the method to use the
BackStack.
