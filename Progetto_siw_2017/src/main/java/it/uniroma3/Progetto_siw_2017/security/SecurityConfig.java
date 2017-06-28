package it.uniroma3.Progetto_siw_2017.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 
	    @Autowired
	    private DataSource dataSource;
	 
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {   
			auth
				.jdbcAuthentication()
					.dataSource(dataSource)
			
					.usersByUsernameQuery(getUsersQuery())
					.authoritiesByUsernameQuery(getAuthoritiesQuery());
		}
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {    
	        http
	        .authorizeRequests()
	        	.antMatchers("/**", "/index.html").permitAll()//.hasRole("ROLE_USER")   //matcha tutto ciò che è "/**" o /home 
	        	.antMatchers("/login.html").access("hasRole('ROLE_ADMIN')")
	            .anyRequest().authenticated()    //ogni url che non è già stato matchato, richede che l' utente debba essere autenticato
	            .and()
	        .formLogin()
	            .loginPage("/login.html")
	            .defaultSuccessUrl("/menuAmministratore.html")
	            .failureUrl("/login?error=true")
	            .permitAll()
	            .and()
	        .logout()
	        	.logoutSuccessUrl("/logout.html")
	        	.deleteCookies("remove")
	            .permitAll()
	            .and()
	         .exceptionHandling().accessDeniedPage("/403.html");  
	    }
	    
	    private String getUsersQuery() {
	    	return "SELECT username, password, enabled FROM users WHERE username=?";
	    }
	    
	    private String getAuthoritiesQuery() {
	    	return "SELECT username, role FROM user_roles WHERE username=?";
	    }

}





//Oauth2  (non usata)

//The client is the application that is attempting to get access to the user's account.
//The resource server is the API server used to access the user's information.
//The Authorization Server is the server that presents the interface where the user approves or denies the request.
//The resource owner is the person who is giving access to some portion of their account.

//Redirect URIs...

//OAuth 2 provides several "grant types" for different use cases. The grant types defined are:
	//Authorization Code for apps running on a web server, browser-based and mobile apps
	//Password for logging in with a username and password
	//Client credentials for application access
	//Implicit was previously recommended for clients without a secret, but has been superceded by using the Authorization Code grant with no secret

//Web apps are written in a server-side language and run on a server where the source code of the application is not available to the public. 
//Authorization
	//Create a "Log In" link sending the user to:
		//https://oauth2server.com/auth?response_type=code&client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=photos(esempio)&state=1234zyx
		//code - Indicates that your server expects to receive an authorization code
		//client_id - The client ID you received when you first created the application
		//redirect_uri - Indicates the URI to return the user to after authorization is complete
		//scope - One or more scope values indicating which parts of the user's account you wish to access
		//state - A random string generated by your application, which you'll verify later
	//If the user clicks "Allow," the service redirects the user back to your site with an auth code:
		//https://oauth2client.com/cb?code=AUTH_CODE_HERE&state=1234zyx
		//code - The server returns the authorization code in the query string
		//state - The server returns the same state value that you passed
		//You should first compare this state value to ensure it matches the one you started with. 
//Token Exchange
	//Your server exchanges (scambia) the auth code for an access token:
		//POST https://api.oauth2server.com/token
  			//grant_type=authorization_code&
  			//code=AUTH_CODE_HERE&
  			//redirect_uri=REDIRECT_URI&
  			//client_id=CLIENT_ID&
  			//client_secret=CLIENT_SECRET
		//grant_type=authorization_code - The grant type for this flow is authorization_code
		//code=AUTH_CODE_HERE - This is the code you received in the query string
		//redirect_uri=REDIRECT_URI - Must be identical to the redirect URI provided in the original link
		//client_id=CLIENT_ID - The client ID you received when you first created the application
		//client_secret=CLIENT_SECRET - Since this request is made from server-side code, the secret is included
	//The server replies with an access token and expiration time (tempo di scadenza)
		//{
	  		//"access_token":"RsT5OjbzRn430zqMLgV3Ia",
	  		//"expires_in":3600
		//}
	//or if there was an error
		//{
	  		//"error":"invalid_request"
		//}
	//Application access
	//In some cases, applications may need an access token to act on behalf of themselves rather than a user. 
	//OAuth provides the client_credentials grant type for this purpose.
		//POST https://api.oauth2server.com/token
			//grant_type=client_credentials&
			//client_id=CLIENT_ID&
			//client_secret=CLIENT_SECRET

//Making Authenticated Requests
	//The end result of all the grant types is obtaining an access token.
	//Now that you have an access token, you can make requests to the API. 
	//You can quickly make an API request using cURL as follows:
		//curl -H "Authorization: Bearer RsT5OjbzRn430zqMLgV3Ia" \
		//https://api.oauth2server.com/1/me
	//Make sure you always send requests over HTTPS and never ignore invalid certificates.