/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import interfaces.ICourseRepository;
import interfaces.ICourseSectionRepository;
import interfaces.IPermissionRepository;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Course;
import models.CourseSection;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "CourseController", urlPatterns ={"/Courses", "/Courses/Create", "/Courses/Edit", "/Courses/Details", "/Courses/Delete", "/Courses/Find"})
public class CourseController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ICourseRepository _courseRepository;
    
    @Inject
    private ICourseSectionRepository _courseSectionRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else if(request.getRequestURI().contains("/Find"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Find"))
                    throw new Exception("User doesn't have permission");
                
                doFindGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Courses/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeletePost(request, response);
            }
            else if(request.getRequestURI().contains("/Find"))
            {
                response.sendError(400);
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
        List<Course> courses = this._courseRepository.findAll().stream().filter(x -> x.getCourseActive() == 1).collect(Collectors.toList());
        
        request.setAttribute("courses", courses);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/courses/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/courses/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //COURSE OBJECT
            Course course = new Course();
            course.setCourseId(request.getParameter("courseId"));
            course.setCourseAmountHours(Short.parseShort(request.getParameter("courseAmountHours")));
            course.setCourseName(request.getParameter("courseName"));
            
            if(Double.parseDouble(request.getParameter("coursePrice")) < 0)
                throw new Exception("Price in lower than zero");
            
            course.setCoursePrice(Double.parseDouble(request.getParameter("coursePrice")));
            course.setCourseCreationDate(new Date());
            course.setCourseActive((short)1);

            if(!this._courseRepository.save(course))
                throw new Exception("An error has ocurred creating the course");
            
            response.sendRedirect(request.getContextPath() + "/Courses");
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
        String courseId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Course course = this._courseRepository.findById(courseId);
        
        if(course == null)
            response.sendRedirect(request.getContextPath() + "/Courses");
        else
        {
            List<CourseSection> courseSections = this._courseSectionRepository.findCoursesSections(course);
            
            course.setCoursesSections(courseSections);
            
            request.setAttribute("course", course);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/courses/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String courseId = request.getParameter("courseId");
            
            if(Utility.isNullOrWhiteSpace(courseId))
                throw new Exception("CourseId is empty");
            
            Course course = this._courseRepository.findById(courseId);
            
            if(course == null)
                throw new Exception("Course does not exist");
            
            //COURSE OBJECT
            course.setCourseName(request.getParameter("courseName"));
            course.setCourseAmountHours(Short.parseShort(request.getParameter("courseAmountHours")));
            course.setCoursePrice(Double.parseDouble(request.getParameter("coursePrice")));
            
            
            if(!this._courseRepository.update(course))
                throw new Exception("An error has ocurred saving the course");
            
            response.sendRedirect(request.getContextPath() + "/Courses");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("courseId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String courseId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Course course = this._courseRepository.findById(courseId);
        
        if(course == null)
            response.sendRedirect(request.getContextPath() + "/Courses");
        else
        {
            List<CourseSection> courseSections = this._courseSectionRepository.findCoursesSections(course);
            
            course.setCoursesSections(courseSections);
            
            request.setAttribute("course", course);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/courses/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String courseId = request.getParameter("courseId");
            
            if(Utility.isNullOrWhiteSpace(courseId))
                throw new Exception("ID Empty");
            
            Course course = this._courseRepository.findById(courseId);
            
            if(course == null)
                throw new Exception("Course not found");
            
            course.setCourseActive((short)0);
            
            if(!this._courseRepository.update(course))
                throw new Exception("Error trying to update build");
            
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
            String courseId = request.getParameter("courseId");
            
            if(Utility.isNullOrWhiteSpace(courseId))
                throw new Exception("ID Empty");
            
            Course course = this._courseRepository.findById(courseId);
            
            if(course == null)
                throw new Exception("Course not found");
            
            course.setCourseActive((short)0);
            
            if(!this._courseRepository.update(course))
                throw new Exception("Error trying to update build");
            
            response.sendRedirect(request.getContextPath() + "/Courses");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("courseId"));
            doDetailsGet(request, response);
        }
    }
    
    private void doFindGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String courseIdFind = request.getParameter("courseId");
            
            if(Utility.isNullOrWhiteSpace(courseIdFind))
                throw new Exception("Empty parameter");
            
            Course course = this._courseRepository.findById(courseIdFind.toUpperCase());
            
            if(course == null)
                throw new Exception("Course not found");
            
            if(course.getCoursesSections() == null || course.getCoursesSections().isEmpty())
            {
                List<CourseSection> courseSections = this._courseSectionRepository.findCoursesSections(course);
                
                if(courseSections.size() > 0)
                    course.setCoursesSections(courseSections);
            }
            
            for (CourseSection courseSection: course.getCoursesSections())
            {
                courseSection.setCourse(null);
                courseSection.setCoursesSectionsHistorical(null);
            }
            
            String servletResponse = new Gson().toJson(course);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch(Exception ex)
        {
            if(ex.getMessage() == "Course not found")
                response.sendError(404);
            else if(ex.getMessage() == "Empty parameter")
                response.sendError(400);
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