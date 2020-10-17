Feature: Flight automation

Scenario: Flight booking system

Given user arrived on homepage
When heading is MakeMyTrip
Then initialize hotel factory
Then user clicks on hotels
Then user fills the destination city
Then user enters checkin and checkout date
Then user clicks on search button
Then user closes the location suggestion box
Then user filters output
Then user sort the hotels based on price from low to high
Then user clears all the applied filters
Then close hotel window
