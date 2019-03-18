/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IPermissionRepository;
import interfaces.IRolePermissionRepository;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Permission;
import models.RolePermission;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "PermissionController", urlPatterns ={"/Permissions", "/Permissions/Create", "/Permissions/Edit", "/Permissions/Details", "/Permissions/Delete"})
public class PermissionController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IRolePermissionRepository _rolePermissionRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditGet(request, response);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Permissions/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeletePost(request, response);
            }
            else
            {
                response.sendError(400);
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
        List<Permission> permissions = this._permissionRepository.findAll();
        
        request.setAttribute("permissions", permissions);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/permissions/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/permissions/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //PERMISSION OBJECT
            Permission permission = new Permission();
            permission.setPermissionName(request.getParameter("permissionName"));
            permission.setPermissionUrl(request.getParameter("permissionUrl"));
            permission.setPermissionDescription(request.getParameter("permissionDescription"));
            permission.setPermissionCreationDate(new Date());
            
            if(!this._permissionRepository.save(permission))
                throw new Exception("An error has ocurred creating the permission");
            
            response.sendRedirect(request.getContextPath() + "/Permissions");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            doCreateGet(request, response);
        }
    }

    private void doEditGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String permissionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Permission permission = this._permissionRepository.findById(new BigDecimal(permissionId));
        
        if(permission == null)
            response.sendRedirect(request.getContextPath() + "/Permissions");
        else
        {
            request.setAttribute("permission", permission);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/permissions/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String permissionId = request.getParameter("permissionId");
            
            if(Utility.isNullOrWhiteSpace(permissionId))
                throw new Exception("PermissionId is empty");
            
            Permission permission = this._permissionRepository.findById(new BigDecimal(permissionId));
            
            if(permission == null)
                throw new Exception("Permission does not exist");
            
            //ROLE OBJECT
            permission.setPermissionName(request.getParameter("permissionName"));
            permission.setPermissionUrl(request.getParameter("permissionUrl"));
            permission.setPermissionDescription(request.getParameter("permissionDescription"));
            
            if(!this._permissionRepository.update(permission))
                throw new Exception("An error has ocurred saving the permission");
            
            response.sendRedirect(request.getContextPath() + "/Permissions");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("permissionId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String permissionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Permission permission = this._permissionRepository.findById(new BigDecimal(permissionId));
        
        if(permission == null)
            response.sendRedirect(request.getContextPath() + "/Permissions");
        else
        {
            request.setAttribute("permission", permission);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/permissions/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String permissionId = request.getParameter("permissionId");
            
            if(Utility.isNullOrWhiteSpace(permissionId))
                throw new Exception("ID Empty");
            
            Permission permission = this._permissionRepository.findById(new BigDecimal(permissionId));
            
            if(permission == null)
                throw new Exception("Permission not found");
            
            if(!this._permissionRepository.delete(permission))
                throw new Exception("Error trying to delete permission");
            
            //DELETING EXISTING RECORD FROM TABLE ROLES_PERMISSIONS IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<RolePermission> oldDefinedPermissions = this._rolePermissionRepository.findRolesPermissions(permission);
            
            for (RolePermission oldDefinedPermission : oldDefinedPermissions)
            {
                if(!this._rolePermissionRepository.delete(oldDefinedPermission))
                    throw new Exception("An error has been occured deleting the rolePermission");
            }
            
            servletResponse = "success";
        }
        catch(Exception ex)
        {
            servletResponse = "error";
        }
        finally
        {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
    }

    private void doDeletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String permissionId = request.getParameter("permissionId");
            
            if(Utility.isNullOrWhiteSpace(permissionId))
                throw new Exception("ID Empty");
            
            Permission permission = this._permissionRepository.findById(new BigDecimal(permissionId));
            
            if(permission == null)
                throw new Exception("Permission not found");
            
            if(!this._permissionRepository.delete(permission))
                throw new Exception("Error trying to delete permission");
            
            //DELETING EXISTING RECORD FROM TABLE ROLES_PERMISSIONS IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<RolePermission> oldDefinedPermissions = this._rolePermissionRepository.findRolesPermissions(permission);
            
            for (RolePermission oldDefinedPermission : oldDefinedPermissions)
            {
                if(!this._rolePermissionRepository.delete(oldDefinedPermission))
                    throw new Exception("An error has been occured deleting the rolePermission");
            }
            
            response.sendRedirect(request.getContextPath() + "/Permissions");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("permissionId"));
            doDetailsGet(request, response);
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