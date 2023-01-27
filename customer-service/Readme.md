--> Customer service running port is 9000 - configured in application.properties file 

--> All endpoints are configured in controller file as mentioned below :

URL's - 

1. For getting centralized configured rate of interest -

http://localhost:9000/customer/get-deposit-roi

2. For creating customer in database -

Note : customer object required as RequestBody along with this url : 
http://localhost:9000/customer/add-customer 

If customer object send in request body is already present in database or is null then in these cases Exception handling is done which will return the required ErrorMessage and UI can get info related to error .

3. For getting all customers present in database -

http://localhost:9000/customer/get-all-customers

This will return the list of all customer present in database .

4. For getting customer of particular Id -

Note : id is path variable   

http://localhost:9000/customer/get-customer/{id}

5. For updating customer data : 

Note : put operation is performed by sending customer object in body and exception handling is done for managing error situations .

http://localhost:9000/customer/update-customer

6. For updating customer using patch operation - 

@PatchMapping("/update-customer-patch/{id}")

http://localhost:9000/customer/update-customer-patch/{id}

7. For deleting customer of particular id -

http://localhost:9000/customer/delete-customer/{id}
This request also delete the account of particular customer id if present in account database .

Note : Required scenarios of exceptions are handled in each request which will throw errorMessage and errorCode for error description .


--> GlobalException Handling is performed for handling exceptions .

BusinessException is thrown if exception occurs in Service layer /Business logic layer.
ControllerException is thrown if exception occurs in Controller layer .

--> Customer service talks to account service using @FeignClient . Therefore connection is made using FeignClient.
And required dependencies are added in pom.xml file .






 