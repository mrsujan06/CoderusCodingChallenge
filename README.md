# Coderus Coding Challenge

**Target timeframe**: No more than 3 hours

**Submission criteria**: A zip of the project which can be built and run.

# Brief

For the assessment you have been provided a template for an Android app. The project includes all
of the necessary logic to query launch data from the SpaceX launches API. As well as the initial implementation of a fragment to display them in a list using a RecyclerView and adapter.
As you add new features the application should follow a MVVM (Model-View-ViewModel) structure throughout. Using a packages by feature structure.

Don’t worry if you don’t complete all the tasks. You will be assessed on how the application is structured and the application of Android frameworks and their best practise.

## Complete the following tasks

1. Update the list fragment so that when the screen loads, data is retrieved from the API and display it to the user.

2. The results from the API should be cached in local storage so that the app will work offline too.

3. Add a loading indicator to be displayed until the results come back from the server.

4. When the user selects an item from the list, they should be navigated to a second fragment to display further details about the launch. The RocketLaunch class contains the data that is
available to display here.

5. Add unit tests for the view model performing both behavioural and stateful verification. An
example of a test could be to test the view model logic that retrieves the data from the data
source.

6. There is a bug in the custom view that is being used in the list. The launches that have not
yet taken place are being marked as “Unsuccessful”. These should instead be marked as “Upcoming”.


# Extra Credit
The following are optional items which can be completed if you have time remaining:

* Introduce a timeout for when no data can be retrieved from the API and no data has been
saved locally. The app should display a message to the user asking them to try again when
they’re connected to the internet.

* Implement a dependency injection framework such as Dagger2 or alternative.

* Add support for continuous integration (CI) using a tool of your choice (e.g. Bitrise and
GitHub actions).

* Add some automated UI tests using Espresso. A simple suite of UI tests to ensure screens are
setup as expected, and all items displayed.

* Add support for both day and night themes based on the system theme.
