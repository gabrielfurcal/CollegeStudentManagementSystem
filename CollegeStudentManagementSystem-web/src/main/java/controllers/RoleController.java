/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IGroupRoleRepository;
import interfaces.IPermissionRepository;
import interfaces.IRolePermissionRepository;
import interfaces.IRoleRepository;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.GroupRole;
import models.Permission;
import models.Role;
import models.RolePermission;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "RoleController", urlPatterns ={"/Roles", "/Roles/Create", "/Roles/Edit", "/Roles/Details", "/Roles/Delete"})
public class RoleController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IGroupRoleRepository _groupRoleRepository;
    
    @Inject
    private IRoleRepository _roleRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditGet(request, response);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Roles/Delete"))
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
        List<Role> roles = this._roleRepository.findAll();
        
        request.setAttribute("roles", roles);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/roles/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<Permission> permissions = this._permissionRepository.findAll();
        
        request.setAttribute("permissions", permissions);
        
        request.getRequestDispatcher("/WEB-INF/roles/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //ROLE OBJECT
            Role role = new Role();
            role.setRoleName(request.getParameter("roleName"));
            role.setRoleDescription(request.getParameter("roleDescription"));
            role.setRoleCreationDate(new Date());
            
            if(!this._roleRepository.save(role))
                throw new Exception("An error has ocurred creating the role");
            
            String[] permissionsSelected = request.getParameterValues("permissionsSelected");
            
            if(permissionsSelected != null)
            {
                for (String permissionSelected : permissionsSelected)
                {
                    Permission permission = this._permissionRepository.findById(new BigDecimal(permissionSelected));

                    if(permission != null)
                    {
                        RolePermission rolePermission = new RolePermission();

                        rolePermission.setRole(role);
                        rolePermission.setPermission(permission);

                        if(!this._rolePermissionRepository.save(rolePermission))
                            throw new Exception("An error has been occured creating the rolePermission");
                    }
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Roles");
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
        String roleId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Role role = this._roleRepository.findById(new BigDecimal(roleId));
        
        if(role == null)
            response.sendRedirect(request.getContextPath() + "/Roles");
        else
        {
            request.setAttribute("role", role);
            
            List<Permission> definedPermissions = this._permissionRepository.findPermissions(role);
            
            request.setAttribute("definedPermissions", definedPermissions);
            
            List<Permission> permissions = this._permissionRepository.findAll();
            
            request.setAttribute("permissions", permissions);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/roles/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String roleId = request.getParameter("roleId");
            
            if(Utility.isNullOrWhiteSpace(roleId))
                throw new Exception("RoleId is empty");
            
            Role role = this._roleRepository.findById(new BigDecimal(roleId));
            
            if(role == null)
                throw new Exception("Role does not exist");
            
            String[] permissionsSelected = request.getParameterValues("permissionsSelected");
            
            //DELETING EXISTING RECORD FROM TABLE ROLES_PERMISSIONS IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<RolePermission> oldDefinedPermisisons = this._rolePermissionRepository.findRolesPermissions(role);
            
            if(permissionsSelected == null)
            {
                for (RolePermission oldDefinedPermisison : oldDefinedPermisisons)
                {
                    if(!this._rolePermissionRepository.delete(oldDefinedPermisison))
                        throw new Exception("An error has been occured deleting the rolePermission");
                }
            }
            else
            {
                for (RolePermission oldDefinedPermisison : oldDefinedPermisisons)
                {
                    String permissionId = Integer.toString(oldDefinedPermisison.getPermission().getPermissionId());
                    if(!Arrays.asList(permissionsSelected).contains(permissionId))
                    {
                        if(!this._rolePermissionRepository.delete(oldDefinedPermisison))
                            throw new Exception("An error has been occured deleting the rolePermission");
                    }
                }
            }
            
            //ROLE OBJECT
            role.setRoleName(request.getParameter("roleName"));
            role.setRoleDescription(request.getParameter("roleDescription"));
            
            if(!this._roleRepository.update(role))
                throw new Exception("An error has ocurred saving the role");
            
            if(permissionsSelected != null)
            {
                for (String permissionSelected : permissionsSelected)
                {
                    //VERIFYING AND CREATING NEW RECORD WITH THE PERMISSION AND ROLE IN TABLE ROLES_PERMISSIONS
                    Permission permission = this._permissionRepository.findById(new BigDecimal(permissionSelected));
                    
                    if(permission != null)
                    {
                        List<RolePermission> storedRolesPermissions = this._rolePermissionRepository.findRolesPermissions(role, permission);
                        
                        if(storedRolesPermissions.size() == 0)
                        {
                            RolePermission rolePermission = new RolePermission();
                        
                            rolePermission.setRole(role);
                            rolePermission.setPermission(permission);

                            if(!this._rolePermissionRepository.save(rolePermission))
                                throw new Exception("An error has been occured creating the rolePermission");
                        }
                    }
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Roles");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("roleId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String roleId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Role role = this._roleRepository.findById(new BigDecimal(roleId));
        
        if(role == null)
            response.sendRedirect(request.getContextPath() + "/Roles");
        else
        {
            request.setAttribute("role", role);
            
            List<Permission> definedPermissions = this._permissionRepository.findPermissions(role);
            
            if(definedPermissions == null)
                definedPermissions = new ArrayList<>();
            
            request.setAttribute("definedPermissions", definedPermissions);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/roles/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String roleId = request.getParameter("roleId");
            
            if(Utility.isNullOrWhiteSpace(roleId))
                throw new Exception("ID Empty");
            
            Role role = this._roleRepository.findById(new BigDecimal(roleId));
            
            if(role == null)
                throw new Exception("Role not found");
            
            if(!this._roleRepository.delete(role))
                throw new Exception("Error trying to delete role");
            
            //DELETING EXISTING RECORD FROM TABLE ROLES_PERMISSIONS IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<RolePermission> oldDefinedPermissions = this._rolePermissionRepository.findRolesPermissions(role);
            
            for (RolePermission oldDefinedPermission : oldDefinedPermissions)
            {
                if(!this._rolePermissionRepository.delete(oldDefinedPermission))
                    throw new Exception("An error has been occured deleting the rolePermission");
            }
            
            //DELETING EXISTING RECORD FROM TABLE GROUPS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<GroupRole> oldDefinedGroupsRoles = this._groupRoleRepository.findGroupsRoles(role);
            
            for (GroupRole oldDefinedGroupsRole : oldDefinedGroupsRoles)
            {
                if(!this._groupRoleRepository.delete(oldDefinedGroupsRole))
                    throw new Exception("An error has been occured deleting the groupRole");
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
            String roleId = request.getParameter("roleId");
            
            if(Utility.isNullOrWhiteSpace(roleId))
                throw new Exception("ID Empty");
            
            Role role = this._roleRepository.findById(new BigDecimal(roleId));
            
            if(role == null)
                throw new Exception("Role not found");
            
            if(!this._roleRepository.delete(role))
                throw new Exception("Error trying to delete role");
            
            //DELETING EXISTING RECORD FROM TABLE ROLES_PERMISSIONS IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<RolePermission> oldDefinedPermissions = this._rolePermissionRepository.findRolesPermissions(role);
            
            for (RolePermission oldDefinedPermission : oldDefinedPermissions)
            {
                if(!this._rolePermissionRepository.delete(oldDefinedPermission))
                    throw new Exception("An error has been occured deleting the rolePermission");
            }
            
            //DELETING EXISTING RECORD FROM TABLE GROUPS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<GroupRole> oldDefinedGroupsRoles = this._groupRoleRepository.findGroupsRoles(role);
            
            for (GroupRole oldDefinedGroupsRole : oldDefinedGroupsRoles)
            {
                if(!this._groupRoleRepository.delete(oldDefinedGroupsRole))
                    throw new Exception("An error has been occured deleting the groupRole");
            }
            
            response.sendRedirect(request.getContextPath() + "/Roles");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("roleId"));
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