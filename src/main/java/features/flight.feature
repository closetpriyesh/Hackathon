Feature: Flight automation


@WithoutDeals @flight
Scenario Outline: Flight booking system without deals

Given user has reached homepage
When description is MakeMT
Then initialize flight factory
Then user inputs the "<source>" , "<destination>"  and "<dateOfJourney>"
Then user search for flights
Then user applies various filters
Then store initial price list
Then sort the flights based on price in descending order
Then check if the price is sorted correctly
Then user selects one flight and click book now
Then user reviews the booking
Then user confirms the review
Then close the browser

Examples:
		| source | destination | dateOfJourney   |
		| del  	 | ixr 		   | Fri Sep 18 2020 |
		| bom	 | del		   | Sun Sep 20 2020 |
	

@WithDeals @flight
Scenario Outline: Flight booking system with deals

Given user has reached homepage
When description is MakeMT
Then initialize flight factory
Then user inputs the "<source>" , "<destination>"  and "<dateOfJourney>"
Then user search for flights
Then user applies various filters
Then store initial price list
Then sort the flights based on price in descending order
Then check if the price is sorted correctly
Then user selects one flight and click book now
Then user reviews the booking
Then user confirms the review
Then close the browser

Examples:
		| source | destination | dateOfJourney   |
		| del  	 | ixr 		   | Fri Sep 18 2020 |
		| bom	 | del		   | Sun Sep 20 2020 |
	