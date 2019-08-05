/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IAddressRepository;
import interfaces.IBuildRepository;
import interfaces.ICampusRepository;
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
import models.Address;
import models.Campus;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "CampusController", urlPatterns ={"/Campus", "/Campus/Create", "/Campus/Edit", "/Campus/Details", "/Campus/Delete"})
public class CampusController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ICampusRepository _campusRepository;
    
    @Inject
    private IBuildRepository _buildRepository;
    
    @Inject
    private IAddressRepository _addressRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);   

            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus"))
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);   
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Campus/Delete"))
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
        List<Campus> campus = this._campusRepository.findAll();
        
        request.setAttribute("campus", campus);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/campus/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        
        request.getRequestDispatcher("/WEB-INF/campus/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //ADDRESS OBJECT
            Address address = new Address();
            address.setAddressStreetAndNumber(request.getParameter("addressStreetAndNumber"));
            address.setAddressNeighborhood(request.getParameter("addressNeighborhood"));
            address.setAddressSector(request.getParameter("addressSector"));
            address.setAddressMunicipality(request.getParameter("addressMunicipality"));
            address.setAddressProvince(request.getParameter("addressProvince"));
            address.setAddressLatitude(request.getParameter("addressLatitude"));
            address.setAddressLongitude(request.getParameter("addressLongitude"));
            address.setAddressCreationDate(new Date());
            
            //CAMPUS OBJECT
            Campus campus = new Campus();
            campus.setCampusName(request.getParameter("campusName"));
            campus.setCampusCreationDate(new Date());
            campus.setAddress(address);

            if(!this._campusRepository.save(campus))
                throw new Exception("An error has ocurred creating the campus");
            
            response.sendRedirect(request.getContextPath() + "/Campus");
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
        String campusId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Campus campus = this._campusRepository.findById(Integer.parseInt(campusId));
        
        if(campus == null)
            response.sendRedirect(request.getContextPath() + "/Campus");
        else
        {
            campus.setBuilds(this._buildRepository.findBuilds(campus));
            
            request.setAttribute("campus", campus);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/campus/edit.jsp").forward(request, response);
        }        
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String campusId = request.getParameter("campusId");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("CampusId is empty");
            
            Campus campus = this._campusRepository.findById(Integer.parseInt(campusId));
            
            if(campus == null)
                throw new Exception("Campus does not exist");
            
            //ADDRESS OBJECT
            Address address = campus.getAddress();
            address.setAddressStreetAndNumber(request.getParameter("addressStreetAndNumber"));
            address.setAddressNeighborhood(request.getParameter("addressNeighborhood"));
            address.setAddressSector(request.getParameter("addressSector"));
            address.setAddressMunicipality(request.getParameter("addressMunicipality"));
            address.setAddressProvince(request.getParameter("addressProvince"));
            address.setAddressLatitude(request.getParameter("addressLatitude"));
            address.setAddressLongitude(request.getParameter("addressLongitude"));
            
            //CAMPUS OBJECT
            campus.setCampusName(request.getParameter("campusName"));
            
            if(!this._campusRepository.update(campus))
                throw new Exception("An error has ocurred saving the campus");
            
            response.sendRedirect(request.getContextPath() + "/Campus");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("campusId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String campusId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Campus campus = this._campusRepository.findById(Integer.parseInt(campusId));
        
        if(campus == null)
            response.sendRedirect(request.getContextPath() + "/Campus");
        else
        {
            campus.setBuilds(this._buildRepository.findBuilds(campus));
            
            request.setAttribute("campus", campus);
            
            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
            
            request.getRequestDispatcher("/WEB-INF/campus/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String campusId = request.getParameter("campusId");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("ID Empty");
            
            Campus campus = this._campusRepository.findById(Integer.parseInt(campusId));
            
            Address address = campus.getAddress();
            
            if(campus == null)
                throw new Exception("Campus not found");
            
            if(!this._campusRepository.delete(campus))
                throw new Exception("Error trying to delete campus");
            
            if(!this._addressRepository.delete(address))
                throw new Exception("Error trying to delete address");
            
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
            String campusId = request.getParameter("campusId");
            
            if(Utility.isNullOrWhiteSpace(campusId))
                throw new Exception("ID Empty");
            
            Campus campus = this._campusRepository.findById(Integer.parseInt(campusId));
            
            Address address = campus.getAddress();
            
            if(campus == null)
                throw new Exception("Campus not found");
            
            if(!this._campusRepository.delete(campus))
                throw new Exception("Error trying to delete campus");
            
            if(!this._addressRepository.delete(address))
                throw new Exception("Error trying to delete address");
            
            response.sendRedirect(request.getContextPath() + "/Campus");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("campusId"));
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