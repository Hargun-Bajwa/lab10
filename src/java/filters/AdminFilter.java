/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author 839217
 */
public class AdminFilter implements Filter {
   
    
    public AdminFilter() {
    }    
    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        //getting request and response
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession();
        
        //getting email to run further checks
        String email = (String)session.getAttribute("email");
        
        
        UserDB userDB = new UserDB();
        
        //getting the user object associated with that email.
        User user = userDB.get(email);
        
       // no need to check here for null email because that is already checked in Authentication filter.( requirement of lab, but already completed)
        
        //checking that if it is not admin then do not let them go to /admin url
        if(user.getRole().getRoleId() != 1){
            httpResponse.sendRedirect("notes");
            return;
        }
        
        
        chain.doFilter(request, response); 
        }
        
        
    @Override
    public void destroy() {        
    }
    @Override
    public void init(FilterConfig filterConfig) {        
       
        }
    }

   
    

