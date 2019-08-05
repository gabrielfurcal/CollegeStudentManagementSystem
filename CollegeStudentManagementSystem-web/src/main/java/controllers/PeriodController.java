/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IPeriodRepository;
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
import models.Period;
import models.PeriodPK;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "PeriodsController", urlPatterns ={"/Periods", "/Periods/Create"})
public class PeriodController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IPeriodRepository _periodRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Periods/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Periods"))
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
                response.sendError(400);
            else
                response.sendError(400);
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
        List<Period> periods = this._periodRepository.findAll();
        
        request.setAttribute("periods", periods);
        
        int[] currentYearAndPeriod = Utility.getCurrentYearAndQuarter();
            
        Period period = this._periodRepository.findById(new PeriodPK(currentYearAndPeriod[0],currentYearAndPeriod[1]));
            
        if(period == null)
            request.setAttribute("isCurrentPeriodExist", "false");
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/periods/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            int[] currentYearAndPeriod = Utility.getCurrentYearAndQuarter();
            
            Period restoredPeriod = this._periodRepository.findById(new PeriodPK(currentYearAndPeriod[0],currentYearAndPeriod[1]));
            
            if(restoredPeriod == null)
            {
                //PERIOD OBJECT
                Period period = new Period();
                period.setPeriodCreationDate(new Date());
                period.setPeriodsPK(new PeriodPK(currentYearAndPeriod[0],currentYearAndPeriod[1]));
                
                if(!this._periodRepository.save(period))
                   throw new Exception("An error has ocurred saving the period");   
            }
            
            response.sendRedirect(request.getContextPath() + "/Periods");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            doCreateGet(request, response);
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