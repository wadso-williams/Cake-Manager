# Cake Manager Micro Service (fictitious) - REDEFINED

Project Info
=====================

To run the server locally execute the following command:

`./mvnw spring-boot:run`

and access the following URL:

`http://localhost:8080/`

*** Login using the Okta credentials below

###All access to the app requires an authentication.
###Oauth2.0 (AKA Security) is enabled


CakeManagerApplication includes a static configuration subclass called OktaOAuth2WebSecurityConfigurerAdapter which is used to adobt Oauth 2.0 Security

#### Change the follow code block to use JWT token:

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().permitAll()
					.and().oauth2Login();
		}
To

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated()
					.and().oauth2ResourceServer().jwt();
		}

However you would need to generate a bearer token using OPEN ID CONNECT DEBUGGER.
As this is a demo account, the grant type available was Authorisation code

To login to okta and find the client credentials
------------------------------------------------

`https://dev-2709008.okta.com`

`Username - williams@wadso.co.uk`

`Okta Password - OktaW@dso2o2o`

Then Navigate to Applications --> General

You can also find the client credentials in the application properties file (As this is a sample project, I didn't mind exposing this data)


Dockerisation
-------------------
To dockerize this application, a file named Dockerfile is created with the following content:

`FROM openjdk:8-jdk-alpine`

`MAINTAINER waracle.com`

`COPY target/cake-manager-1.0.0.jar cake-manager-1.0.0.jar`

`ENTRYPOINT ["java","-jar","/cake-manager-1.0.0.jar"]`

To create an image from the Dockerfile, we have to run ‘docker-compose up --build',