# Cake Manager Micro Service (fictitious) - REDEFINED

Project Info
=====================

To run the server locally execute the following command:

`./mvnw spring-boot:run`

and access the following URL via the web browser to get cakes:

`http://localhost:8080/`

and access the following URL via the web browser to download existing cakes:

`http://localhost:8080/cakes`

*** Login using the Okta credentials below 

### All access to the app requires an authentication.
### Oauth2.0 (AKA Security) is enabled


CakeManagerApplication includes a static configuration subclass called OktaOAuth2WebSecurityConfigurerAdapter which is used to adobt Oauth 2.0 Security

You would need to generate a bearer token using OPEN ID CONNECT DEBUGGER.
As this is a demo account, I created a token which may expire depending on when the application is ran.

Execute Rest Endpoints using  Postman
----------------------------------------------

To access the endpoints via postman, you will need to include an Authorisation Bearer Token on every request

Try this token I created earlier -

```
eyJraWQiOiJFTFd4cWl5S1dmLWxxVG1tY1RqNExrUzdIZHFnVkpuaFJZbDhTc3BnT240IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkg5MHgyQm9jYTZDWnA4SXU1Z2tOZXNJZ1g2VXZRY21jOW1fWGdpenpMZ0EiLCJpc3MiOiJodHRwczovL2Rldi0yNzA5MDA4Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTYyMDM4NTM2NywiZXhwIjoxNjIwMzg4OTY3LCJjaWQiOiIwb2FwN3Z5OTA4QXlsbm83VDVkNiIsInVpZCI6IjAwdXA3Zmt3ZTFpRXQ5QWxYNWQ2Iiwic2NwIjpbIm9wZW5pZCJdLCJzdWIiOiJ3aWxsaWFtc0B3YWRzby5jby51ayJ9.NYyoPRG2hH2FfIhslxqXNErqoz6Eg9QCkqlDA775U52EXqBSIHVZQEthNYAgmg3tDHn6sdsQKJKGsvlcv_N673VsPzIpbk4mzNos9V2aItpAdvXSGQ-fcb2HE28_XEfROiQ8b0YSRLjhX88I4wcnjd_oSR3JduYhEC_FZRp4S-vbMbslsHqbFX4-YiddRBtQKgY51nzAnJBEcATf4ZIWKR3g54OZaXdb3ye-2lHtu_TyO25RlMSTLSJtaf7F8dLaCpMZvRYnA_aQqmpKIPUdU_Y-8JWcEBS9WdesXu3rD7gRCBPwB-SrvHdlo6jLMbfFUeq7uZJ0QhiLh26iRRFncQ
```

### Only If it doesn't work, then 

An easy way to get an access token is to generate one using OpenID Connect Debugger - https://oidcdebugger.com.

Fill in the client ID in application.properties, and use https://dev-2709008.okta.com/oauth2/default/v1/authorize for the Authorize URI. 
The state field must be filled but can contain any characters - Just type 'Anything'. 
Select token for the response type instead of code.

Submit the form to start the authentication process. 
You’ll receive an Okta login form if you are not logged in, or you’ll see the screen below with your custom bearer token.

Grab the token and use in postman.

...


### Post man end points -

GET `http://localhost:8080/`

would list the cakes currently in the system presented mapped automatically to a Cake entity

POST `http://localhost:8080/`

In the Body tab of postman, select raw and select JSON Media Type on Postman

```
{
    "title": "Chocolate Cake",
    "desc": "Choco cake mmmm yummy",
    "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
}
```

would allow a human to add a new cake to the server.

GET `http://localhost:8080/cakes`

would use Spring Rest Template to  download a list of the cakes currently in the system as JSON data

POST `http://localhost:8080/cakes`

In the Body tab of postman, select raw and select JSON Media Type on Postman

```
[
    {
    "title": "Peanut Cake",
    "desc": "Peanut cake mmmm yummy",
    "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
 },
 {
    "title": "Butter Cake",
    "desc": "Butter cake mmmm yummy",
    "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
 }
]
```

would allow a human to add cakes to the server.

To login to okta and find the client credentials
------------------------------------------------

`https://dev-2709008.okta.com`

`Username - williams@wadso.co.uk`

`Okta Password - OktaW@dso2o2o`

Then Navigate to Applications --> General

> You can also find the client credentials in the application properties file (As this is a sample project, I didn't mind exposing this data instead of using an environment variable)


Dockerisation
-------------------
To dockerize this application, a file named Dockerfile is created

To create an image from the Dockerfile, we have to run ‘docker-compose up --build',