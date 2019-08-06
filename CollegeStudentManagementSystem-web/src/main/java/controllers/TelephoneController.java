/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import interfaces.IPermissionRepository;
import interfaces.ITelephoneRepository;
import interfaces.ITelephoneTypeRepository;
import interfaces.IUserTelephoneRepository;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Telephone;
import models.TelephoneType;

/**
 *
 * @author Gabriel_Liberato
 * This is an Rest API Servlet
 */
@WebServlet(name = "TelephoneController", urlPatterns ={"/Telephones", "/Telephones/Create", "/Telephones/Edit", "/Telephones/Details", "/Telephones/Delete", "/Telephones/Types"})
public class TelephoneController extends HttpServlet implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ITelephoneRepository _telephoneRepository;
    
    @Inject
    private ITelephoneTypeRepository _telephoneTypeRepository;

    @Inject
    private IPermissionRepository _permissionRepository;
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if(request.getRequestURI().contains("/Create"))
            {
                response.sendError(404);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                response.sendError(404);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                response.sendError(404);
            }
            else if(request.getRequestURI().contains("/Types"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones/Types"))
                    throw new Exception("User doesn't have permission");

                doTypesGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones"))
                    throw new Exception("User doesn't have permission");
                
                doIndexGet(request, response);
            }
        }
        catch(Exception ex)
        {
            if(ex.getMessage().equals("User doesn't have permission"))
                response.sendError(403);
            else
                response.sendError(500);
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if(request.getRequestURI().contains("/Create"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {   
                response.sendError(404);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Telephones/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeletePost(request, response);
            }
            else
            {            
                response.sendError(404);
            }
        }
        catch(Exception ex)
        {
            if(ex.getMessage().equals("User doesn't have permission"))
                response.sendError(403);
            else
                response.sendError(500);
        }
    }

    private void doIndexGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        try
        {
            List<Telephone> telephones = this._telephoneRepository.findAll();
            
            for (Telephone telephone : telephones)
            {
                telephone.setUsersTelephones(null);
                telephone.getTelephoneType().setTelephones(null);
            }
            
            String servletResponse = new Gson().toJson(telephones);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Telephone not found")
                response.sendError(404);
            else if(ex.getMessage() == "Empty parameter")
                response.sendError(400);
            else
                response.sendError(500);
        }       
    }
    
    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String telephoneNumber = request.getParameter("telephoneNumber");
            String telephoneTypeId = request.getParameter("telephoneTypeId");
            
            if(utilities.Utility.isNullOrWhiteSpace(telephoneNumber) || utilities.Utility.isNullOrWhiteSpace(telephoneTypeId))
                throw new Exception("Empty parameters");
            
            Telephone telephone = new Telephone();
            telephone.setTelephoneNumber(telephoneNumber);
            telephone.setTelephoneCreationDate(new Date());
            
            if(!this._telephoneRepository.save(telephone))
                throw new Exception("An error has been occurred trying to save the telephone");
                    
            TelephoneType telephoneType = this._telephoneTypeRepository.findById(Integer.parseInt(telephoneTypeId));
            telephone.setTelephoneType(telephoneType);
            
            if(!this._telephoneRepository.update(telephone))
                throw new Exception("An error has been occurred trying to update the telephone");
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Ok");
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Empty parameter")
                response.sendError(400);
            else
                response.sendError(500);
        }
    }
    
    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String telephoneId = request.getParameter("telephoneId");
            String telephoneNumber = request.getParameter("telephoneNumber");
            String telephoneTypeId = request.getParameter("telephoneTypeId");
            
            if(utilities.Utility.isNullOrWhiteSpace(telephoneId) || utilities.Utility.isNullOrWhiteSpace(telephoneNumber) || utilities.Utility.isNullOrWhiteSpace(telephoneTypeId))
                throw new Exception("Empty parameters");
            
            Telephone telephone = this._telephoneRepository.findById(Integer.parseInt(telephoneId));
            telephone.setTelephoneNumber(telephoneNumber);
            telephone.setTelephoneCreationDate(new Date());
            
            TelephoneType telephoneType = this._telephoneTypeRepository.findById(Integer.parseInt(telephoneTypeId));
            telephone.setTelephoneType(telephoneType);
          
            if(!this._telephoneRepository.update(telephone))
                throw new Exception("An error has been occurred trying to update the telephone");
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Ok");
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Empty parameters")
                response.sendError(400);
            else
                response.sendError(500);
        }
    }
    
    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        try
        {
            String telephoneId = request.getParameter("id");
            
            if(utilities.Utility.isNullOrWhiteSpace(telephoneId))
                throw new Exception("Empty parameter");
            
            Telephone telephone = this._telephoneRepository.findById(Integer.parseInt(telephoneId));
            
            if(telephone == null)
                throw new Exception("Telephone not found");
            
            telephone.setUsersTelephones(null);
            telephone.getTelephoneType().setTelephones(null);
            
            String servletResponse = new Gson().toJson(telephone);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Telephone not found")
                response.sendError(404);
            else if(ex.getMessage() == "Empty parameter")
                response.sendError(400);
            else
                response.sendError(500);
        }       
    }
    
    private void doDeletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String telephoneId = request.getParameter("id");
            
            if(utilities.Utility.isNullOrWhiteSpace(telephoneId))
                throw new Exception("Empty parameter");
            
            if(!this._telephoneRepository.deleteById(Integer.parseInt(telephoneId)))
                throw new Exception("An error has been occurred trying to delete the telephone");
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Ok");
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Empty parameters")
                response.sendError(400);
            else
                response.sendError(500);
        }
    }

    private void doTypesGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";

        try
        {
            List<TelephoneType> telephoneTypes = _telephoneTypeRepository.findAll();

            if(telephoneTypes == null)
            {
                throw new Exception("Telephone Types not found");
            }
            else if(telephoneTypes.size() == 0)
            {
                throw new Exception("Telephone Types not found");
            }

            for (TelephoneType telephoneType : telephoneTypes)
            {
                telephoneType.setTelephones(null);
            }

            servletResponse = new Gson().toJson(telephoneTypes);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch (Exception ex)
        {
            if(ex.getMessage().toUpperCase().contains("NOT FOUND"))
                response.sendError(404);
            else
                response.sendError(500);
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