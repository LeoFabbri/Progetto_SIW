package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthenticationController {

   @Autowired
   private CredentialsService credentialsService;

   @GetMapping("/")
    public String getIndex(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index.html";
		}else{
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
			if (credentials.getRole().equals("ARTIST")) {
				return "artistHome.html";
			}else{
                return "userHome.html";
            }

        }
    }
   
   @GetMapping("/login")
   public String getLoginForm() {
       return "login.html";
   }

   @GetMapping("/registration")
   public String getRegistrationForm() {
       return "registration.html";
   }


   
   
    
}
