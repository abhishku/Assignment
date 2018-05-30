package com.assignment.demo;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class GreetingController {
    
    @Autowired
    private AccountRepository accountrepository;
    private RegistrationResponse registrationresponse;
    @RequestMapping(value = "/account", method = RequestMethod.POST,produces = "application/json")
    public String addAccount(@RequestBody BareAccount bareaccount) {
        if(accountrepository.existsById(bareaccount.AccountId) == false)
        {
        	 Account account=new Account(bareaccount.AccountId);
             String accountID=Long.toString(account.getAccountId());
             StringBuilder accountPassword=account.getPassword();
             accountrepository.save(account);
        	 registrationresponse=new RegistrationResponse("SUCCESSFULL_STRING", true, account.getPassword());
        	 inMemoryUserDetailsManager.createUser(User.withUsername(accountID).password("{noop}"+accountPassword).roles("ADMIN").build());
        return registrationresponse.toString();
        }
        else {
        	registrationresponse=new RegistrationResponse("FAILURE_STRING", false);
        	return registrationresponse.toString();	
        }      
    }
    
   @Autowired
   private UrlRepository urlrepository;

   private Url urlrepo;
   @RequestMapping(value="/register", method= RequestMethod.POST , produces = "application/json")
   public String registerUrl(@RequestBody BareUrl urlo )
   {
   	long id = new Date().getTime();
   	String encodedId = UrlShortner.urlShorten(id);
 	Url tempurl = null;
   	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   		if (!(authentication instanceof AnonymousAuthenticationToken)) {
   		String currentUserName = authentication.getName();
	   		Iterable<Url> urllist=urlrepository.findAll();
	  		boolean a=false;
	   		for (Url url2 : urllist)
	  		{
	  			if(url2.getAccountId().toString().equals(currentUserName) && url2.getLongUrl().toString().equals(urlo.url))
	  			{	
	  				a=true;
	  				tempurl=url2;	  				
	  			}
	  		}
	   		if(!a)
	   		{
	   			urlrepo=new Url(new Long(currentUserName),new StringBuilder(urlo.url),new StringBuilder(encodedId),new Long(0));
  				urlrepository.save(urlrepo); // It saves all the URL's as every account can generate their unique shortened URL
  				return "{ shortUrl: "+"'http://localhost:8080/"+encodedId+"' }";
	   		}
	   	}
   	return "{ shortUrl: "+"'http://localhost:8080/"+tempurl.getShortUrl()+"' }";
   }
   
   @RequestMapping(value = "/{id}", method=RequestMethod.GET)
   public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {

	   Iterable<Url> urllist=urlrepository.findAll();
  		for (Url url2 : urllist)
  		{
			if(url2.getShortUrl().toString().equals(id))
			{
				 String redirectUrlString = url2.getLongUrl().toString();
					       RedirectView redirectView = new RedirectView();
					       if(redirectUrlString.startsWith("http://") || redirectUrlString.startsWith("https://") )
					       redirectView.setUrl(redirectUrlString);
					       else
					    	   redirectView.setUrl("http://" + redirectUrlString);
					       url2.setHitcounts(url2.getHitcounts()+1);
					       urlrepository.delete(url2);
					       urlrepository.save(url2);
					       return redirectView;
			}
  		}
		return null;
   }

   @RequestMapping(value="/statistic/{AccountId}" , produces = "application/json")
   public String staticReport(@PathVariable Long AccountId) {
	List <String> responsestat=new ArrayList<String>();
	Iterable<Url> urllist=urlrepository.findAll();
	for (Url url : urllist) {
		if(url.getAccountId().equals(AccountId))
		{	
			responsestat.add(url.getLongUrl()+": "+url.getHitcounts());
		}
	}
	return "{ "+responsestat.toString().replace("[","").replace("]","")+" }";
	
   }
   
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    public GreetingController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
		this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
	}
    
}
