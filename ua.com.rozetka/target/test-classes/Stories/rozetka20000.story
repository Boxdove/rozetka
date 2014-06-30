Narrative:
In order to purchase the product
As a buyer
I want to the system count total price I have to pay 

Scenario: if total sum is more than 20000 grn gift is added to the cart
Given main page rozetka.com.ua
When user has entered to the rozetka.com.ua
And shopping cart is cleared
And user goes to the nessessary huge products page for choosing
And total sum in shopping cart is more than 20000 grn
Then gift is added to the shopping cart