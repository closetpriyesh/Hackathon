Feature: Homepage automation

@homepage
Scenario: Navbar and broken links verification on homepage

Given user is already on homepage
When title is MMT
Then initialize homepage factory
Then user clicks on flights and checks if its link is working properly
Then user clicks on hotels and checks if its link is working properly
Then user clicks on villas&apts and checks if its link is working properly
Then user clicks on holidays and checks if its link is working properly
Then user clicks on trains and checks if its link is working properly
Then user clicks on buses and checks if its link is working properly
Then user clicks on cabs and checks if its link is working properly
Then user clicks on visa and checks if its link is working properly
Then user clicks on giftcards and checks if its link is working properly
Then user goes to deals page
Then user verifies if the deal page is working properly
Then user closes the deals window
Then user gets the footer links
Then user displays the broken links
Then close home window
