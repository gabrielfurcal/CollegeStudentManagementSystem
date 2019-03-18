/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IPermissionRepository;
import interfaces.IPositionRepository;
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
import models.Position;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "PositionController", urlPatterns ={"/Positions", "/Positions/Create", "/Positions/Edit", "/Positions/Details", "/Positions/Delete"})
public class PositionController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IPositionRepository _positionRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions"))
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
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((BigDecimal)request.getSession(false).getAttribute("userId"), "/Positions/Delete"))
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
        List<Position> positions = this._positionRepository.findAll();
        
        request.setAttribute("positions", positions);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/positions/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/positions/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //POSITION OBJECT
            Position position = new Position();
            position.setPositionName(request.getParameter("positionName"));
            position.setPositionDescription(request.getParameter("positionDescription"));
            position.setPositionCreationDate(new Date());

            if(!this._positionRepository.save(position))
                throw new Exception("An error has ocurred creating the position");

            response.sendRedirect(request.getContextPath() + "/Positions");
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
        String positionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Position position = this._positionRepository.findById(new BigDecimal(positionId));
        
        if(position == null)
            response.sendRedirect(request.getContextPath() + "/Positions");
        else
        {
            request.setAttribute("position", position);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/positions/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String positionId = request.getParameter("positionId");
            
            if(Utility.isNullOrWhiteSpace(positionId))
                throw new Exception("PositionId is empty");
            
            Position position = this._positionRepository.findById(new BigDecimal(positionId));
            
            
            if(position == null)
                throw new Exception("Position does not exist");
            
            //POSITION OBJECT
            position.setPositionName(request.getParameter("positionName"));
            position.setPositionDescription(request.getParameter("positionDescription"));
            
            if(!this._positionRepository.update(position))
                throw new Exception("An error has ocurred saving the position");
            
            response.sendRedirect(request.getContextPath() + "/Positions");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("positionId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String positionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Position position = this._positionRepository.findById(new BigDecimal(positionId));
        
        if(position == null)
            response.sendRedirect(request.getContextPath() + "/Positions");
        else
        {
            request.setAttribute("position", position);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/positions/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String positionId = request.getParameter("positionId");
            
            if(Utility.isNullOrWhiteSpace(positionId))
                throw new Exception("ID Empty");
            
            Position position = this._positionRepository.findById(new BigDecimal(positionId));
            
            if(position == null)
                throw new Exception("Position not found");
            
            if(!this._positionRepository.delete(position))
                throw new Exception("Error trying to delete position");
            
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
            String positionId = request.getParameter("positionId");
            
            if(Utility.isNullOrWhiteSpace(positionId))
                throw new Exception("ID Empty");
            
            Position position = this._positionRepository.findById(new BigDecimal(positionId));
            
            if(position == null)
                throw new Exception("Position not found");
            
            if(!this._positionRepository.delete(position))
                throw new Exception("Error trying to delete position");
            
            response.sendRedirect(request.getContextPath() + "/Positions");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("positionId"));
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