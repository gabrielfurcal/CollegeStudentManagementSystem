/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IBuildRepository;
import interfaces.ICampusRepository;
import interfaces.IClassroomRepository;
import interfaces.IPermissionRepository;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Build;
import models.BuildPK;
import models.Campus;
import models.Classroom;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "BuildController", urlPatterns ={"/Builds", "/Builds/Create", "/Builds/Edit", "/Builds/Details", "/Builds/Delete", "/Builds/Campus"})
public class BuildController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IClassroomRepository _classroomRepository;
    
    @Inject
    private IBuildRepository _buildRepository;
    
    @Inject
    private ICampusRepository _campusRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"),
                        "/Builds/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"),
                        "/Builds/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"),
                        "/Builds/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"),
                        "/Builds/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"),
                        "/Builds"))
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Builds/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Builds/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Builds/Delete"))
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
        List<Build> builds = this._buildRepository.findAll();
        
        request.setAttribute("builds", builds);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/builds/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<Campus> campus = this._campusRepository.findAll();
        
        request.setAttribute("campus", campus);
        
        request.getRequestDispatcher("/WEB-INF/builds/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //BUILD OBJECT
            Build build = new Build();
            build.setBuildName(request.getParameter("buildName"));
            build.setBuildCreationDate(new Date());

            Campus campus = this._campusRepository.findById(Integer.parseInt(request.getParameter("campus")));
            
            BuildPK buildPK = new BuildPK();
            buildPK.setCampusId(campus.getCampusId());
            
            build.setBuildsPK(buildPK);
            
            if(!this._buildRepository.save(build))
                throw new Exception("An error has ocurred creating the build");
            
            if(campus != null)
                build.setCampus(campus);
            
            if(!this._buildRepository.update(build))
                throw new Exception("An error has ocurred updating the build");
            
            response.sendRedirect(request.getContextPath() + "/Builds");
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
        String buildId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Build build = this._buildRepository.findBuild(Integer.parseInt(buildId));
        
        if(build == null)
            response.sendRedirect(request.getContextPath() + "/Builds");
        else
        {
            request.setAttribute("build", build);
            
            List<Classroom> classrooms = this._classroomRepository.findClassrooms(build);
            
            build.setClassrooms(classrooms);
            
            List<Campus> campus = this._campusRepository.findAll();
            
            request.setAttribute("campus", campus);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/builds/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String buildId = request.getParameter("buildId");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("BuildId is empty");
            
            Build build = this._buildRepository.findBuild(Integer.parseInt(buildId));
            
            if(build == null)
                throw new Exception("Build does not exist");
            
            //BUILD OBJECT
            build.setBuildName(request.getParameter("buildName"));
            
            Campus campus = this._campusRepository.findById(Integer.parseInt(request.getParameter("campus")));
            
            build.getBuildsPK().setCampusId(campus.getCampusId());
            
            build.setCampus(campus);
            
            if(!this._buildRepository.update(build))
                throw new Exception("An error has ocurred saving the build");
            
            response.sendRedirect(request.getContextPath() + "/Builds");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("buildId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String buildId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Build build = this._buildRepository.findBuild(Integer.parseInt(buildId));
        
        if(build == null)
            response.sendRedirect(request.getContextPath() + "/Build");
        else
        {
            List<Classroom> classrooms = this._classroomRepository.findClassrooms(build);
            
            build.setClassrooms(classrooms);
            
            request.setAttribute("build", build);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/builds/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String buildId = request.getParameter("buildId");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("ID Empty");
            
            Build build = this._buildRepository.findBuild(Integer.parseInt(buildId));
            
            if(build == null)
                throw new Exception("Build not found");
            
            build.setCampus(null);
            
            if(!this._buildRepository.delete(build))
                throw new Exception("Error trying to delete build");
            
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
            String buildId = request.getParameter("buildId");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("ID Empty");
            
            Build build = this._buildRepository.findBuild(Integer.parseInt(buildId));
            
            if(build == null)
                throw new Exception("Build not found");
            
            build.setCampus(null);
            
            if(!this._buildRepository.delete(build))
                throw new Exception("Error trying to delete build");
            
            response.sendRedirect(request.getContextPath() + "/Builds");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("buildId"));
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