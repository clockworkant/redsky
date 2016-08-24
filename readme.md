This is a repo to represent what can be done in roughly 4 hours and so is pretty darn incomplete!

However! It has the following feature:
* Retrofit
* Gson
* Rxjava
* RecyclerView
* MVP implementation
* Seperation of networking and ui layers
* Exceptional ugly recycler view

For testing we have junit tests, one for testing json deserialization and the other to test the MVP implementation.

Given more time I would like to 
* Enhance the listview row. I wanted to show icons, temp, humidity, and wind.
* Write more conclusive tests for edge cases in the MVP tests
* Write null checking for the converting to viewmodels. It's currently dangerous as it does not account for some elements not being returned.
* Investigate why the api has weather returned as an array and consider how to represent this to a user if a time slot has multiple weathers.
* Look at adding headers for the days in the listview so you can see the where each day starts and finishes.
