Narrative:
In order to purchase the product
As a buyer
I want to the system count total price I have to pay 

Scenario: total sum more than 1500 grn
Given main page rozetka.com.ua
When user has entered to the rozetka.com.ua
And shopping cart is cleared
And user goes to the nessessary products page for choosing
And total sum in shopping cart is more than 1500 grn
Then shipping price is 0 grn