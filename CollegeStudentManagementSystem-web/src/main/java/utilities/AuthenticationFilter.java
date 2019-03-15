/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel_Liberato
 */
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession(false);
        
        boolean loggedIn = session != null && session.getAttribute("userUsername") != null && httpRequest.getRemoteUser() != null;
        boolean loginRequest = path.equals(httpRequest.getContextPath() + "/Login");
        boolean resourcesRequest = path.contains("/resources/") || path.contains("https://");
        
        if (loggedIn || loginRequest || resourcesRequest)
        {
            chain.doFilter(httpRequest, httpResponse);    
        }
        else   
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login");
    }

    @Override
    public void destroy()
    {
    }
}