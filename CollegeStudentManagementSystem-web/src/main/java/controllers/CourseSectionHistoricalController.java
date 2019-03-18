/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import interfaces.*;
import models.*;
import utilities.Utility;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Gabriel_Liberato
 */
@WebServlet(name = "CourseSectionHistoricalController", urlPatterns = {"/CoursesSectionsHistorical", "/CoursesSectionsHistorical/Details"})
public class CourseSectionHistoricalController extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    private ICourseSectionRepository _courseSectionRepository;

    @Inject
    private ITeacherRepository _teacherRepository;

    @Inject
    private ICampusRepository _campusRepository;

    @Inject
    private IBuildRepository _buildRepository;

    @Inject
    private IClassroomRepository _classroomRepository;

    @Inject
    private ICourseSectionHistoricalRepository _courseSectionHistoricalRepository;

    @Inject
    private IPermissionRepository _permissionRepository;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the
    // code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if (request.getRequestURI().contains("/Details"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSectionsHistorical/Details"))
                    throw new Exception("User doesn't have permission");

                doDetailsGet(request, response);
            }
            else
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSectionsHistorical"))
                    throw new Exception("User doesn't have permission");

                doIndexGet(request, response);
            }
        }
        catch (Exception ex)
        {
            if (ex.getMessage().equals("User doesn't have permission"))
                response.sendError(403);
            else
                response.sendError(500);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if (request.getRequestURI().contains("/Details"))
                response.sendError(400);
            else
                response.sendError(400);
        }
        catch (Exception ex)
        {
            if (ex.getMessage().equals("User doesn't have permission"))
                response.sendError(403);
            else
                response.sendError(500);
        }
    }

    protected void doIndexGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String courseSectionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ?
                request.getAttribute("id").toString() : request.getParameter("id");

        CourseSection courseSection = this._courseSectionRepository.findCourseSection(new BigDecimal(courseSectionId));

        if (courseSection == null)
            response.sendRedirect(request.getContextPath() + "/CoursesSections");
        else
        {
            List<CourseSectionHistorical> coursesSectionsHistorical =
                    this._courseSectionHistoricalRepository.findCoursesSectionsHistorical(courseSection);

            if (coursesSectionsHistorical.size() <= 0)
                response.sendRedirect(request.getContextPath() + "/CoursesSections");

            request.setAttribute("coursesSectionsHistorical", coursesSectionsHistorical);

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));

            request.getRequestDispatcher("/WEB-INF/coursesSectionsHistorical/index.jsp").forward(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String courseSectionHitoricalId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");

            CourseSectionHistorical courseSectionHistorical = this._courseSectionHistoricalRepository.findById(new BigDecimal(courseSectionHitoricalId));

            if(courseSectionHistorical == null)
                response.sendRedirect(request.getContextPath() + "/CoursesSectionsHistorical?id=" + request.getRequestURI().split("=")[1]);

            request.setAttribute("courseSectionHistorical", courseSectionHistorical);

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));

            request.getRequestDispatcher("/WEB-INF/coursesSectionsHistorical/details.jsp").forward(request, response);
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