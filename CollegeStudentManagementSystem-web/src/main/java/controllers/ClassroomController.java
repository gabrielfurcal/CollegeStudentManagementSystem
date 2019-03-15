/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import interfaces.IBuildRepository;
import interfaces.ICampusRepository;
import interfaces.IClassroomRepository;
import interfaces.IPermissionRepository;
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
import models.Build;
import models.Campus;
import models.Classroom;
import models.ClassroomPK;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "ClassroomController", urlPatterns ={"/Classrooms", "/Classrooms/Create", "/Classrooms/Edit", "/Classrooms/Details", "/Classrooms/Delete", "/Classrooms/Buildings", "/Classrooms/All"})
public class ClassroomController extends HttpServlet
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else if(request.getRequestURI().contains("/All"))
            {
                response.sendError(400);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {   
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeletePost(request, response);
            }
            else if(request.getRequestURI().contains("/Buildings"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/Buildings"))
                    throw new Exception("User doesn't have permission");
                
                doBuildingsPost(request, response);
            }
            else if(request.getRequestURI().contains("/All"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Classrooms/All"))
                    throw new Exception("User doesn't have permission");
                
                doClassroomsPost(request, response);
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
        List<Classroom> classrooms = this._classroomRepository.findAll();
        
        request.setAttribute("classrooms", classrooms);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/classrooms/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<Campus> campus = this._campusRepository.findAll();
        
        request.setAttribute("campus", campus);
        
        request.getRequestDispatcher("/WEB-INF/classrooms/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //CLASSROOM OBJECT
            Classroom classroom = new Classroom();
            classroom.setClassroomName(request.getParameter("classroomName"));
            classroom.setClassroomCreationDate(new Date());
            
            String campusId = request.getParameter("campus");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("CampusID empty");
            
            Campus campus = this._campusRepository.findById(new BigDecimal(request.getParameter("campus")));
            
            String buildId = request.getParameter("build");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("BuildID empty");
            
            Build build = this._buildRepository.findBuild(new BigDecimal(request.getParameter("build")));
            
            ClassroomPK classroomPK = new ClassroomPK();
            classroomPK.setCampusId(campus.getCampusId());
            classroomPK.setBuildId(build.getBuildsPK().getBuildId());
            
            classroom.setClassroomsPK(classroomPK);
            
            if(!this._classroomRepository.save(classroom))
                throw new Exception("An error has ocurred creating the classroom");
            
            if(build != null)
                classroom.setBuild(build);
            
            if(!this._classroomRepository.update(classroom))
                throw new Exception("An error has ocurred updating the classroom");
            
            response.sendRedirect(request.getContextPath() + "/Classrooms");
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
        String classroomId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(classroomId));
        
        if(classroom == null)
            response.sendRedirect(request.getContextPath() + "/Classrooms");
        else
        {
            request.setAttribute("classroom", classroom);
            
            List<Campus> campus = this._campusRepository.findAll();
            
            request.setAttribute("campus", campus);
            
            List<Build> campusBuilds = this._buildRepository.findBuilds(classroom.getBuild().getCampus());
            
            request.setAttribute("campusBuilds", campusBuilds);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/classrooms/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String classroomId = request.getParameter("classroomId");
            
            if(Utility.isNullOrWhiteSpace(classroomId))
                throw new Exception("ClassroomId is empty");
            
            Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(classroomId));
            
            if(classroom == null)
                throw new Exception("Classroom does not exist");
            
            //CLASSROOM OBJECT
            classroom.setClassroomName(request.getParameter("classroomName"));
            
            String campusId = request.getParameter("campus");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("CampusID empty");
            
            Campus campus = this._campusRepository.findById(new BigDecimal(request.getParameter("campus")));
            
            String buildId = request.getParameter("build");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("BuildID empty");
            
            Build build = this._buildRepository.findBuild(new BigDecimal(request.getParameter("build")));
            
            classroom.getClassroomsPK().setCampusId(campus.getCampusId());
            classroom.getClassroomsPK().setBuildId(build.getBuildsPK().getBuildId());
            
            classroom.setBuild(build);
            
            if(!this._classroomRepository.update(classroom))
                throw new Exception("An error has ocurred saving the classroom");
            
            response.sendRedirect(request.getContextPath() + "/Classrooms");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("classroomId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String classroomId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(classroomId));
        
        if(classroom == null)
            response.sendRedirect(request.getContextPath() + "/Classrooms");
        else
        {
            request.setAttribute("classroom", classroom);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/classrooms/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String classroomId = request.getParameter("classroomId");
            
            if(Utility.isNullOrWhiteSpace(classroomId))
                throw new Exception("ID Empty");
            
            Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(classroomId));
            
            if(classroom == null)
                throw new Exception("Classroom not found");
            
            classroom.setBuild(null);
            
            if(!this._classroomRepository.delete(classroom))
                throw new Exception("Error trying to delete classroom");
            
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
            String classroomId = request.getParameter("classroomId");
            
            if(Utility.isNullOrWhiteSpace(classroomId))
                throw new Exception("ID Empty");
            
            Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(classroomId));
            
            if(classroom == null)
                throw new Exception("Classroom not found");
            
            classroom.setBuild(null);
            
            if(!this._classroomRepository.delete(classroom))
                throw new Exception("Error trying to delete classroom");
            
            response.sendRedirect(request.getContextPath() + "/Classrooms");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("classroomId"));
            doDetailsGet(request, response);
        }
    }
    
    private void doBuildingsPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Gson gson = new Gson();
            
            String campusId = request.getParameter("campus");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("Campus empty");
            
            Campus campus = this._campusRepository.findById(new BigDecimal(campusId));
            
            List<Build> builds = this._buildRepository.findBuilds(campus);
            
            for (Build build : builds)
            {
                build.setCampus(null);
                build.setClassrooms(null);
            }
            
            Build[] buildsArray = builds.toArray(new Build[builds.size()]);
            
            String servletResponse = gson.toJson(buildsArray);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
    }
    
    private void doClassroomsPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Gson gson = new Gson();
            
            String buildId = request.getParameter("build");
            
            if(Utility.isNullOrWhiteSpace(buildId))
                throw new Exception("Build empty");
            
            Build build = this._buildRepository.findBuild(new BigDecimal(buildId));
            
            List<Classroom> classrooms = this._classroomRepository.findClassrooms(build);
            
            for (Classroom classroom : classrooms)
            {
                classroom.setBuild(null);
                classroom.setCoursesSectionsHistorical(null);
            }
            
            Classroom[] classroomsArray = classrooms.toArray(new Classroom[classrooms.size()]);
            
            String servletResponse = gson.toJson(classroomsArray);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch(Exception ex)
        {
            ex.getMessage();
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