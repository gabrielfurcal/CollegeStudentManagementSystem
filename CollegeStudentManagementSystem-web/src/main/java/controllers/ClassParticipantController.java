/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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

/**
 * @author Gabriel_Liberato
 */
@WebServlet(name = "ClassParticipantController", urlPatterns = {"/ClassParticipants", "/ClassParticipants/Students/Unlink"})
public class ClassParticipantController extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    private IStudentRepository _studentRepository;

    @Inject
    private IClassParticipantRepository _classParticipantRepository;

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
            if (request.getRequestURI().contains("/Students/Unlink"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/ClassParticipants/Students/Unlink"))
                    throw new Exception("User doesn't have permission");

                doUnlinkGet(request, response);
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
            if (request.getRequestURI().contains("/Students/Unlink"))
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

    private void doUnlinkGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String servletResponse = "";

        try
        {
            String studentId = request.getParameter("studentId");
            String courseSectionHistoricalId = request.getParameter("courseSectionHistoricalId");

            if(Utility.isNullOrWhiteSpace(studentId) || Utility.isNullOrWhiteSpace(courseSectionHistoricalId))
                throw new Exception("Empty parameters");

            Student student = this._studentRepository.findStudent(studentId);

            if(student == null)
                throw new Exception("Student not found");

            CourseSectionHistorical courseSectionHistorical = this._courseSectionHistoricalRepository.findById(new BigDecimal(courseSectionHistoricalId));

            if(courseSectionHistorical == null)
                throw new Exception("CourseSectionHistorical not found");

            ClassParticipant classParticipant = this._classParticipantRepository.findClassParticipant(courseSectionHistorical, student);

            if(classParticipant == null)
                throw new Exception("ClassParticipant not found");

            if(!this._classParticipantRepository.delete(classParticipant))
                throw new Exception("An error has been occurred trying to delete ClassParticipant");

            servletResponse = "success";
        }
        catch (Exception ex)
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