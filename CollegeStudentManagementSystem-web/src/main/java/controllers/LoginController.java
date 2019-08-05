/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IUserRepository;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Login"})
public class LoginController extends HttpServlet implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Inject
    private IUserRepository _userRepository;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(request.getSession().getAttribute("userName") != null && request.getRemoteUser() != null)
            response.sendRedirect(request.getContextPath() + "/Home");
        else
            request.getRequestDispatcher("/WEB-INF/login/index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {    
            String username = request.getParameter("userName");
            String password = request.getParameter("password");

            if (Utility.isNullOrWhiteSpace(username) || Utility.isNullOrEmpty(password))
                throw new AuthenticationException();

            String passwordHashed = Utility.stringToSHA256(password);

            User user = this._userRepository.findUser(username, passwordHashed);

            if (user == null)
                throw new AuthenticationException();
           
            //request.login(username, password);

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userUsername", user.getUserUsername());
            session.setAttribute("userName", user.getUserFirstName() + " " + user.getUserFatherLastName() + " " + user.getUserMotherLastName());
           
            response.sendRedirect(request.getContextPath() + "/Home");
        } 
        catch (AuthenticationException ex)
        {
            request.setAttribute("error-login", "Bad credentials. Please, try again.");
            doGet(request, response);
        } 
        catch (IOException /*| ServletException*/ ex)
        {
            request.setAttribute("error-login", "Sorry, something's wrong loggin in. Please, try again.");
            doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
