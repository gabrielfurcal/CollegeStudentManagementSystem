/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IGroupRepository;
import interfaces.IGroupRoleRepository;
import interfaces.IPermissionRepository;
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
import models.Group;
import models.GroupRole;
import models.Role;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "GroupController", urlPatterns ={"/Groups", "/Groups/Create", "/Groups/Edit", "/Groups/Details", "/Groups/Delete"})
public class GroupController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IGroupRepository _groupRepository;
    
    @Inject
    private IRoleRepository _roleRepository;
    
    @Inject
    private IGroupRoleRepository _groupRoleRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);   
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Groups/Delete"))
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

    protected void doIndexGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<Group> groups = this._groupRepository.findAll();
        
        request.setAttribute("groups", groups);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/groups/index.jsp").forward(request, response);
    }

    protected void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<Role> roles = this._roleRepository.findAll();
        
        request.setAttribute("roles", roles);
        
        request.getRequestDispatcher("/WEB-INF/groups/create.jsp").forward(request, response);
    }

    protected void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //GROUP OBJECT
            Group group = new Group();
            group.setGroupName(request.getParameter("groupName"));
            group.setGroupDescription(request.getParameter("groupDescription"));
            group.setGroupCreationDate(new Date());
            
            if(!this._groupRepository.save(group))
                throw new Exception("An error has ocurred creating the group");
            
            String[] rolesSelected = request.getParameterValues("rolesSelected");
            
            if(rolesSelected != null)
            {
                for (String roleSelected : rolesSelected)
                {
                    Role role = this._roleRepository.findById(new BigDecimal(roleSelected));

                    if(role != null)
                    {
                        GroupRole groupRole = new GroupRole();

                        groupRole.setGroup(group);
                        groupRole.setRole(role);

                       if(!this._groupRoleRepository.save(groupRole))
                           throw new Exception("An error has been occured creating the groupRole");
                    }
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Groups");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            doCreateGet(request, response);
        }
    }

    protected void doEditGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String groupId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Group group = this._groupRepository.findById(new BigDecimal(groupId));
        
        if(group == null)
            response.sendRedirect(request.getContextPath() + "/Groups");
        else
        {
            request.setAttribute("group", group);
            
            List<Role> definedRoles = this._roleRepository.findRoles(group);
            
            request.setAttribute("definedRoles", definedRoles);
            
            List<Role> roles = this._roleRepository.findAll();
            
            request.setAttribute("roles", roles);

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/groups/edit.jsp").forward(request, response);
        }        
    }

    protected void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String groupId = request.getParameter("groupId");
            
            if(Utility.isNullOrWhiteSpace(groupId))
                throw new Exception("GroupId is empty");
            
            Group group = this._groupRepository.findById(new BigDecimal(groupId));
            
            
            if(group == null)
                throw new Exception("Group does not exist");
            
            String[] rolesSelected = request.getParameterValues("rolesSelected");
            
            //DELETING EXISTING RECORD FROM TABLE GROUPS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<GroupRole> oldDefinedRoles = this._groupRoleRepository.findGroupsRoles(group);
            
            if(rolesSelected == null)
            {
                for (GroupRole oldDefinedRole : oldDefinedRoles)
                {
                    if(!this._groupRoleRepository.delete(oldDefinedRole))
                        throw new Exception("An error has been occured deleting the groupRole");
                }
            }
            else
            {
                for (GroupRole oldDefinedRole : oldDefinedRoles)
                {
                    if(!Arrays.asList(rolesSelected).contains(oldDefinedRole.getRole().getRoleId().toString()))
                    {
                        if(!this._groupRoleRepository.delete(oldDefinedRole))
                            throw new Exception("An error has been occured deleting the groupRole");
                    }
                }
            }
            
            //GROUP OBJECT
            group.setGroupName(request.getParameter("groupName"));
            group.setGroupDescription(request.getParameter("groupDescription"));
            
            if(!this._groupRepository.update(group))
                throw new Exception("An error has ocurred saving the group");
            
            if(rolesSelected != null)
            {
                for (String roleSelected : rolesSelected)
                {
                    //VERIFYING AND CREATING NEW RECORD WITH THE ROLE AND GROUP IN TABLE GROUPS_ROLES
                    Role role = this._roleRepository.findById(new BigDecimal(roleSelected));

                    if(role != null)
                    {
                        List<GroupRole> storedGroupsRoles = this._groupRoleRepository.findGroupsRoles(group, role);
                        
                        if(storedGroupsRoles.size() == 0)
                        {
                            GroupRole groupRole = new GroupRole();

                            groupRole.setGroup(group);
                            groupRole.setRole(role);

                           if(!this._groupRoleRepository.save(groupRole))
                               throw new Exception("An error has been occured creating the groupRole");
                        }
                    }
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Groups");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("groupId"));
            doEditGet(request, response);
        }
    }

    protected void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String groupId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Group group = this._groupRepository.findById(new BigDecimal(groupId));
        
        if(group == null)
            response.sendRedirect(request.getContextPath() + "/Groups");
        else
        {
            request.setAttribute("group", group);
            
            List<Role> definedRoles = this._roleRepository.findRoles(group);
            
            if(definedRoles == null)
                definedRoles = new ArrayList<>();
            
            request.setAttribute("definedRoles", definedRoles);

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            request.getRequestDispatcher("/WEB-INF/groups/details.jsp").forward(request, response);
        }
    }

    protected void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String groupId = request.getParameter("groupId");
            
            if(Utility.isNullOrWhiteSpace(groupId))
                throw new Exception("ID Empty");
            
            Group group = this._groupRepository.findById(new BigDecimal(groupId));
            
            if(group == null)
                throw new Exception("Group not found");
            
            if(!this._groupRepository.delete(group))
                throw new Exception("Error trying to delete group");
            
            //DELETING EXISTING RECORD FROM TABLE GROUPS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<GroupRole> oldDefinedRoles = this._groupRoleRepository.findGroupsRoles(group);
            
            for (GroupRole oldDefinedRole : oldDefinedRoles)
            {
                if(!this._groupRoleRepository.delete(oldDefinedRole))
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

    protected void doDeletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String groupId = request.getParameter("groupId");
            
            if(Utility.isNullOrWhiteSpace(groupId))
                throw new Exception("ID Empty");
            
            Group group = this._groupRepository.findById(new BigDecimal(groupId));
            
            if(group == null)
                throw new Exception("Group not found");
            
            if(!this._groupRepository.delete(group))
                throw new Exception("Error trying to delete group");
            
            //DELETING EXISTING RECORD FROM TABLE GROUPS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<GroupRole> oldDefinedRoles = this._groupRoleRepository.findGroupsRoles(group);
            
            for (GroupRole oldDefinedRole : oldDefinedRoles)
            {
                if(!this._groupRoleRepository.delete(oldDefinedRole))
                    throw new Exception("An error has been occured deleting the groupRole");
            }
            
            response.sendRedirect(request.getContextPath() + "/Groups");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("groupId"));
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