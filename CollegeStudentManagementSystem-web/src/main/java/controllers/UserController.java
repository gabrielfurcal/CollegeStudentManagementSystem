/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IEmailRepository;
import interfaces.IGroupRepository;
import interfaces.IPermissionRepository;
import interfaces.IPositionRepository;
import interfaces.IRoleRepository;
import interfaces.ITelephoneRepository;
import interfaces.ITelephoneTypeRepository;
import interfaces.IUserRepository;
import interfaces.IUserRoleRepository;
import interfaces.IUserTelephoneRepository;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Address;
import models.Email;
import models.Group;
import models.Position;
import models.Role;
import models.Telephone;
import models.TelephoneType;
import models.User;
import models.UserRole;
import models.UserTelephone;
import utilities.Utility;

/**
 *
 * @author Gabriel_Liberato
 */
@WebServlet(name = "UserController", urlPatterns = {"/Users", "/Users/Create", "/Users/Edit", "/Users/Details", "/Users/Delete"})
public class UserController extends HttpServlet implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IUserRepository _userRepository;
    
    @Inject
    private ITelephoneRepository _telephoneRepository;
    
    @Inject
    private ITelephoneTypeRepository _telephoneTypeRepository;
    
    @Inject
    private IUserTelephoneRepository _userTelephoneRepository;
    
    @Inject
    private IEmailRepository _emailRepository;
    
    @Inject
    private IRoleRepository _roleRepository;
    
    @Inject
    private IUserRoleRepository _userRoleRepository;
    
    @Inject
    private IGroupRepository _groupRepository;

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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                 if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditGet(request, response);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users"))
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Users/Delete"))
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    protected void doIndexGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<User> users = this._userRepository.findAll();
        
        request.setAttribute("users", users);
        
        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/users/index.jsp").forward(request, response);
    }
    
    protected void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
        request.setAttribute("telephoneTypes", telephoneTypes);  
        
        List<Role> roles = this._roleRepository.findAll();
        request.setAttribute("roles", roles);  
        
        List<Position> positions = this._positionRepository.findAll();
        request.setAttribute("positions", positions);
        
        List<Group> groups = this._groupRepository.findAll();
        request.setAttribute("groups", groups);
        
        request.getRequestDispatcher("/WEB-INF/users/create.jsp").forward(request, response);
    }
    
    protected void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Pattern emailRegex = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE); 
            Matcher matcher = emailRegex.matcher(request.getParameter("userEmail"));
            
            if(!matcher.matches())
                throw new Exception("Invalid email");
            
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

            //USER OBJECT
            User user = new User();
            user.setUserFirstName(request.getParameter("userFirstName"));
            user.setUserFatherLastName(request.getParameter("userFatherLastName"));
            user.setUserMotherLastName(request.getParameter("userMotherLastName"));
            user.setUserUsername(request.getParameter("userUsername"));
            user.setUserPassword(Utility.stringToSHA256(request.getParameter("userPassword")));
            user.setUserCreationDate(new Date());
            user.setAddress(address);

            Position position = this._positionRepository.findById(Integer.parseInt(request.getParameter("position")));

            if(position != null)
                user.setPosition(position);

            if(!this._userRepository.save(user))
                throw new Exception("An error has occurred saving the user");

            //EMAIL OBJECT
            Email email = new Email();

            email.setEmailText(request.getParameter("userEmail"));
            email.setEmailCreationDate(new Date());
            email.setUser(user);
                
            if(!this._emailRepository.save(email))
                throw new Exception("An error has occurred saving the email");

            //TELEPHONE INFORMATION
            String[] telephones = request.getParameterValues("telephones");

            for (String telephone : telephones)
            {
                String[] splittedValues = telephone.split(Pattern.quote("|"));

                Telephone toSaveTelephone = new Telephone();
                toSaveTelephone.setTelephoneNumber(splittedValues[0]);
                toSaveTelephone.setTelephoneCreationDate(new Date());
                toSaveTelephone.setTelephoneType(this._telephoneTypeRepository.findById(Integer.parseInt(splittedValues[1])));
                
                
                if(!this._telephoneRepository.save(toSaveTelephone))
                    throw new Exception("An error has occurred saving a telephone");

                UserTelephone userTelephone = new UserTelephone();
                userTelephone.setTelephone(toSaveTelephone);
                userTelephone.setUser(user);

                if(!this._userTelephoneRepository.save(userTelephone))
                    throw new Exception("An error has occurred saving a userTelephone");
            }
            
            //ROLE INFORMATION
            String[] rolesSelected = request.getParameterValues("rolesSelected");
            
            if(rolesSelected != null)
            {
                for (String roleSelected : rolesSelected)
                {
                    Role role = this._roleRepository.findById(Integer.parseInt(roleSelected));
                    
                    UserRole userRole = new UserRole();
                    
                    userRole.setRole(role);
                    userRole.setUser(user);
                    
                    if(!this._userRoleRepository.save(userRole))
                        throw new Exception("An error has occurred saving a userRole");
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Users");
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
        String userId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        User user = this._userRepository.findById(Integer.parseInt(userId));
        
        if(user == null)
            response.sendRedirect(request.getContextPath() + "/Users");
        else
        {
            request.setAttribute("user", user);
            
            List<Email> emails = this._emailRepository.findEmails(user);
            
            if(emails != null)
            {
                if(emails.size() > 0)
                {
                    Email email = emails.get(0);
                    request.setAttribute("email", email.getEmailText());
                }
            }
            
            List<UserTelephone> userTelephone = this._userTelephoneRepository.findUsersTelephones(user);
            
            if(userTelephone.size() > 0)
                request.setAttribute("telephones", userTelephone.stream().map(UserTelephone::getTelephone).collect(Collectors.toList()));
            
            List<Position> positions = this._positionRepository.findAll();
            request.setAttribute("positions", positions);
            
            List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
            request.setAttribute("telephoneTypes", telephoneTypes);
            
            List<Role> roles = this._roleRepository.findAll();
            request.setAttribute("roles", roles);
            
            List<Role> definedRoles = this._userRoleRepository.findUsersRoles(user).stream().map(UserRole::getRole).collect(Collectors.toList());
            request.setAttribute("definedRoles", definedRoles);
            
            request.getRequestDispatcher("/WEB-INF/users/edit.jsp").forward(request, response);
        }
    }
    
    protected void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String userId = request.getParameter("userId");
        
            if(Utility.isNullOrWhiteSpace(userId))
                throw new Exception("UserId is empty");
            
            User user = this._userRepository.findById(Integer.parseInt(userId));
            
            if(user == null)
                throw new Exception("User does not exist");
            
            Pattern emailRegex = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE); 
            Matcher matcher = emailRegex.matcher(request.getParameter("userEmail"));
            
            if(!matcher.matches())
                throw new Exception("Invalid email");
            
            String[] rolesSelected = request.getParameterValues("rolesSelected");
            
            //DELETING EXISTING RECORD FROM TABLE USERS_ROLES IF IT ALREADY EXISTS AND WAS UNSELECTED
            List<UserRole> oldDefinedRoles = this._userRoleRepository.findUsersRoles(user);
            
            if(rolesSelected == null)
            {
                for (UserRole oldDefinedRole : oldDefinedRoles)
                {
                    if(!this._userRoleRepository.delete(oldDefinedRole))
                        throw new Exception("An error has been occured deleting the userRole");
                }
            }
            else
            {
                for (UserRole oldDefinedRole : oldDefinedRoles)
                {
                    if(!Arrays.asList(rolesSelected).contains(Integer.toString(oldDefinedRole.getRole().getRoleId())))
                    {
                        if(!this._userRoleRepository.delete(oldDefinedRole))
                            throw new Exception("An error has been occured deleting the userRole");
                    }
                }
            }
            
            //ADDRESS OBJECT
            Address address = user.getAddress();
            address.setAddressStreetAndNumber(request.getParameter("addressStreetAndNumber"));
            address.setAddressNeighborhood(request.getParameter("addressNeighborhood"));
            address.setAddressSector(request.getParameter("addressSector"));
            address.setAddressMunicipality(request.getParameter("addressMunicipality"));
            address.setAddressProvince(request.getParameter("addressProvince"));
            address.setAddressLatitude(request.getParameter("addressLatitude"));
            address.setAddressLongitude(request.getParameter("addressLongitude"));

            //USER OBJECT
            user.setUserFirstName(request.getParameter("userFirstName"));
            user.setUserFatherLastName(request.getParameter("userFatherLastName"));
            user.setUserMotherLastName(request.getParameter("userMotherLastName"));
            user.setUserUsername(request.getParameter("userUsername"));
            
            if(!Utility.isNullOrEmpty(request.getParameter("userPassword")))
            {
                String password = request.getParameter("userPassword");
                user.setUserPassword(Utility.stringToSHA256(password));
            }
            
            user.setAddress(address);

            String positionId = request.getParameter("position");

            if(!Utility.isNullOrWhiteSpace(positionId))
            {
                Position position = this._positionRepository.findById(Integer.parseInt(request.getParameter("position")));

                if(position != null)
                    user.setPosition(position);
            }

            if(!this._userRepository.update(user))
                throw new Exception("An error has occurred updating the user");
            
            //EMAIL OBJECT
            List<Email> emails = this._emailRepository.findEmails(user);
            
            if(emails != null && emails.size() > 0)
            {
                Email email = emails.get(0);
                email.setEmailText(request.getParameter("userEmail"));
                
                if(!this._emailRepository.update(email))
                    throw new Exception("An error has occurred saving the email");
            }

            //TELEPHONE INFORMATION
            String[] telephones = request.getParameterValues("telephones");

            for (String telephone : telephones)
            {
                String[] splittedValues = telephone.split(Pattern.quote("|"));
                
                Telephone toSaveTelephone = null;
                
                if(splittedValues.length == 3)
                    toSaveTelephone = this._telephoneRepository.findTelephone(Integer.parseInt(splittedValues[2]), splittedValues[0]);
                
                if(toSaveTelephone == null)
                {
                    toSaveTelephone = new Telephone();
                    
                    toSaveTelephone.setTelephoneNumber(splittedValues[0]);
                    toSaveTelephone.setTelephoneType(this._telephoneTypeRepository.findById(Integer.parseInt(splittedValues[1])));
                    toSaveTelephone.setTelephoneCreationDate(new Date());
                    
                    if(!this._telephoneRepository.save(toSaveTelephone))
                        throw new Exception("An error has occurred saving a telephone");
                }
                else
                {
                    toSaveTelephone.setTelephoneNumber(splittedValues[0]);
                    toSaveTelephone.setTelephoneType(this._telephoneTypeRepository.findById(Integer.parseInt(splittedValues[1])));
                    
                    if(!this._telephoneRepository.update(toSaveTelephone))
                        throw new Exception("An error has occurred updating a telephone");
                }
                
                UserTelephone userTelephone = this._userTelephoneRepository.findUserTelephone(user, toSaveTelephone);
                
                if(userTelephone == null)
                {
                    userTelephone = new UserTelephone();
                    userTelephone.setTelephone(toSaveTelephone);
                    userTelephone.setUser(user);
                    
                    if(!this._userTelephoneRepository.save(userTelephone))
                        throw new Exception("An error has occurred saving a userTelephone");
                }
                else
                {
                    userTelephone.setTelephone(toSaveTelephone);
                    userTelephone.setUser(user);
                    
                    if(!this._userTelephoneRepository.update(userTelephone))
                        throw new Exception("An error has occurred saving a userTelephone");
                }
            }
            
            if(rolesSelected != null)
            {
                for (String roleSelected : rolesSelected)
                {
                    //VERIFYING AND CREATING NEW RECORD WITH THE ROLE AND USER IN TABLE USERS_ROLES
                    Role role = this._roleRepository.findById(Integer.parseInt(roleSelected));
                    
                    if(role != null)
                    {
                        List<UserRole> storedUsersRoles = this._userRoleRepository.findUsersRoles(user, role);
                        
                        if(storedUsersRoles.size() == 0)
                        {
                            UserRole userRole = new UserRole();
                    
                            userRole.setRole(role);
                            userRole.setUser(user);

                            if(!this._userRoleRepository.save(userRole))
                                throw new Exception("An error has occurred saving a userRole");
                        }
                    }
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/Users");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("userId"));
            doEditGet(request, response);
        }
    }
    
    protected void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userId = request.getParameter("id") == null || request.getParameter("id").isEmpty() ? request.getAttribute("id").toString() : request.getParameter("id");
        
        User user = this._userRepository.findById(Integer.parseInt(userId));
        
        if(user == null)
            response.sendRedirect(request.getContextPath() + "/Users");
        else
        {
            request.setAttribute("user", user);
        
            List<Email> emails = this._emailRepository.findEmails(user);

            if(emails != null || emails.size() > 0)
            {
                Email email = emails.get(0);
                request.setAttribute("email", email.getEmailText());
            }
            
            List<Role> definedRoles = this._userRoleRepository.findUsersRoles(user).stream().map(UserRole::getRole).collect(Collectors.toList());
            request.setAttribute("definedRoles", definedRoles);
            
            List<UserTelephone> userTelephone = this._userTelephoneRepository.findUsersTelephones(user);

            if(userTelephone.size() > 0)
                request.setAttribute("telephones", userTelephone.stream().map(UserTelephone::getTelephone).collect(Collectors.toList()));
            
            List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
            request.setAttribute("telephoneTypes", telephoneTypes);  

            request.getRequestDispatcher("/WEB-INF/users/details.jsp").forward(request, response);
        }
    }
    
    protected void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String userId = request.getParameter("userId");   
            
            if(Utility.isNullOrWhiteSpace(userId))
                throw new Exception("ID empty");
            
            User user = this._userRepository.findById(Integer.parseInt(userId));
            
            if(user == null)
                throw new Exception("User not found");
            
            List<Email> emails = this._emailRepository.findEmails(user);
            
            for (Email email : emails)
            {
                if(!this._emailRepository.delete(email))
                    throw new Exception("Error trying to delete email");
            }
            
             List<UserTelephone> usersTelephones = this._userTelephoneRepository.findUsersTelephones(user);
            
            for (UserTelephone userTelephone : usersTelephones)
            {
                if(!this._userTelephoneRepository.deleteById(userTelephone.getUserTelephoneId()))
                    throw new Exception("Error trying to delete userTelephone");
                
                if(!this._telephoneRepository.deleteById(userTelephone.getTelephone().getTelephoneId()))
                    throw new Exception("Error trying to delete telephone");
            }
            
            user.setPosition(null);
            
            List<UserRole> usersRoles = this._userRoleRepository.findUsersRoles(user);
            
            for (UserRole userRole : usersRoles)
            {
                if(!this._userRoleRepository.delete(userRole))
                    throw new Exception("Error trying to delete userRepository");
            }
            
            if(!this._userRepository.delete(user))
                throw new Exception("Error trying to delete user");
            
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
            String userId = request.getParameter("userId");
            
            if(Utility.isNullOrWhiteSpace(userId))
                throw new Exception("ID empty");
            
            User user = this._userRepository.findById(Integer.parseInt(userId));
            
            if(user == null)
                throw new Exception("User not found");
            
            List<Email> emails = this._emailRepository.findEmails(user);
            
            for (Email email : emails)
            {
                if(!this._emailRepository.delete(email))
                    throw new Exception("Error trying to delete email");
            }
            
            List<UserTelephone> usersTelephones = this._userTelephoneRepository.findUsersTelephones(user);
            
            for (UserTelephone userTelephone : usersTelephones)
            {
                if(!this._userTelephoneRepository.deleteById(userTelephone.getUserTelephoneId()))
                    throw new Exception("Error trying to delete userTelephone");
                
                if(!this._telephoneRepository.deleteById(userTelephone.getTelephone().getTelephoneId()))
                    throw new Exception("Error trying to delete telephone");
            }
            
            user.setPosition(null);
            
            List<UserRole> usersRoles = this._userRoleRepository.findUsersRoles(user);
            
            for (UserRole userRole : usersRoles)
            {
                if(!this._userRoleRepository.delete(userRole))
                    throw new Exception("Error trying to delete userRepository");
            }
            
            if(!this._userRepository.delete(user))
                throw new Exception("Error trying to delete user");
            
            response.sendRedirect(request.getContextPath() + "/Users");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("userId"));
            doDetailsGet(request, response);
        }
    }
    
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}