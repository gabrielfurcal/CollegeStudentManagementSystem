/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.IEmailRepository;
import interfaces.IPermissionRepository;
import interfaces.IStudentRepository;
import interfaces.ITelephoneRepository;
import interfaces.ITelephoneTypeRepository;
import interfaces.IUserRepository;
import interfaces.IUserRoleRepository;
import interfaces.IUserTelephoneRepository;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import models.Student;
import models.StudentPK;
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
@WebServlet(name = "StudentController", urlPatterns = {"/Students", "/Students/Create", "/Students/Edit", "/Students/Details", "/Students/Delete", "/Students/DeleteTelephone"})
public class StudentController extends HttpServlet implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private IUserRepository _userRepository;
    
    @Inject
    private IStudentRepository _studentRepository;
    
    @Inject
    private ITelephoneRepository _telephoneRepository;
    
    @Inject
    private ITelephoneTypeRepository _telephoneTypeRepository;
    
    @Inject
    private IUserTelephoneRepository _userTelephoneRepository;
    
    @Inject
    private IEmailRepository _emailRepository;
    
    @Inject
    private IUserRoleRepository _userRoleRepository;
    
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                 if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditGet(request, response);
            }            
            else if(request.getRequestURI().contains("/Details"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Details"))
                    throw new Exception("User doesn't have permission");
                
                doDetailsGet(request, response);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Delete"))
                    throw new Exception("User doesn't have permission");
                
                doDeleteGet(request, response);
            }
            else
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students"))
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
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if(request.getRequestURI().contains("/Edit"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Edit"))
                    throw new Exception("User doesn't have permission");
                
                doEditPost(request, response);
            }
            else if(request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if(request.getRequestURI().contains("/Delete"))
            {
                if(!this._permissionRepository.hasUserPermission((int)request.getSession(false).getAttribute("userId"), "/Students/Delete"))
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
        List<Student> students = this._studentRepository.findAll();
        
        for (Student student : students)
        {
            student.setUser(this._userRepository.findUser(student.getStudentsPK().getStudentId()));
        }
        
        request.setAttribute("students", students);   

        request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));
        request.getRequestDispatcher("/WEB-INF/students/index.jsp").forward(request, response);
    }
    
    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {   
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = currentMonth < 8 ? Calendar.getInstance().get(Calendar.YEAR) : Calendar.getInstance().get(Calendar.YEAR) + 1;   
        
        request.setAttribute("currentYear", currentYear);
        
        List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
        request.setAttribute("telephoneTypes", telephoneTypes);  
        
        request.getRequestDispatcher("/WEB-INF/students/create.jsp").forward(request, response);
    }
    
    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
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
            user.setUserPassword(Utility.stringToSHA256("12345678"));
            user.setUserCreationDate(new Date());
            user.setAddress(address);

            if(!this._userRepository.save(user))
                throw new Exception("An error has occurred saving the address");
            
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

            //STUDENT OBJECT
            StudentPK studentPK = new StudentPK();
            studentPK.setStudentId(request.getParameter("userUsername"));
            studentPK.setUserId(user.getUserId());

            Student student = new Student();
            student.setStudentsPK(studentPK);
            student.setStudentCreationDate(new Date());

            if(!this._studentRepository.save(student))
                throw new Exception("An error has occurred saving the student");
            
            response.sendRedirect(request.getContextPath() + "/Students");
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
        String studentId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Student student = this._studentRepository.findStudent(studentId);
        
        student.setUser(this._userRepository.findUser(student.getStudentsPK().getStudentId()));
        
        if(student == null)
            response.sendRedirect(request.getContextPath() + "/Students");
        else
        {
            request.setAttribute("student", student);
        
            List<Email> emails = this._emailRepository.findEmails(student.getUser());

            if(emails != null || emails.size() > 0)
            {
                Email email = emails.get(0);
                request.setAttribute("email", email.getEmailText());
            }

            List<UserTelephone> userTelephone = this._userTelephoneRepository.findUsersTelephones(student.getUser());

            if(userTelephone.size() > 0)
                request.setAttribute("telephones", userTelephone.stream().map(UserTelephone::getTelephone).collect(Collectors.toList()));
            
            List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
            request.setAttribute("telephoneTypes", telephoneTypes);  

            request.getRequestDispatcher("/WEB-INF/students/edit.jsp").forward(request, response);
        }    
    }
    
    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String studentId = request.getParameter("userUsername");
            
            if(Utility.isNullOrWhiteSpace(studentId))
                throw new Exception("Username is empty");
            
            Student student = this._studentRepository.findStudent(studentId);
            
            if(student == null)
                throw new Exception("Student does not exist");
            
            Pattern emailRegex = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE); 
            Matcher matcher = emailRegex.matcher(request.getParameter("userEmail"));
            
            if(!matcher.matches())
                throw new Exception("Invalid email");
            
            //ADDRESS OBJECT
            Address address = student.getUser().getAddress();
            address.setAddressStreetAndNumber(request.getParameter("addressStreetAndNumber"));
            address.setAddressNeighborhood(request.getParameter("addressNeighborhood"));
            address.setAddressSector(request.getParameter("addressSector"));
            address.setAddressMunicipality(request.getParameter("addressMunicipality"));
            address.setAddressProvince(request.getParameter("addressProvince"));
            address.setAddressLatitude(request.getParameter("addressLatitude"));
            address.setAddressLongitude(request.getParameter("addressLongitude"));

            //USER OBJECT
            User user = student.getUser();
            user.setUserFirstName(request.getParameter("userFirstName"));
            user.setUserFatherLastName(request.getParameter("userFatherLastName"));
            user.setUserMotherLastName(request.getParameter("userMotherLastName"));
            user.setUserUsername(request.getParameter("userUsername"));
            user.setUserPassword(Utility.stringToSHA256("12345678"));
            user.setAddress(address);

            if(!this._userRepository.update(user))
                throw new Exception("An error has occurred saving the address");
            
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

            //STUDENT OBJECT
            if(!this._studentRepository.update(student))
                throw new Exception("An error has occurred saving the student");
            
            response.sendRedirect(request.getContextPath() + "/Students");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("userUsername"));
            doEditGet(request, response);
        }
    }
    
    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String studentId = request.getParameter("id") == null || request.getParameter("id").isEmpty() ? request.getAttribute("id").toString() : request.getParameter("id");
        
        Student student = this._studentRepository.findStudent(studentId);
        
        if(student == null)
            response.sendRedirect(request.getContextPath() + "/Students");
        else
        {
            student.setUser(this._userRepository.findUser(student.getStudentsPK().getStudentId()));
            
            request.setAttribute("student", student);
        
            List<Email> emails = this._emailRepository.findEmails(student.getUser());

            if(emails != null || emails.size() > 0)
            {
                Email email = emails.get(0);
                request.setAttribute("email", email.getEmailText());
            }

            List<UserTelephone> userTelephone = this._userTelephoneRepository.findUsersTelephones(student.getUser());

            if(userTelephone.size() > 0)
                request.setAttribute("telephones", userTelephone.stream().map(UserTelephone::getTelephone).collect(Collectors.toList()));
            
            List<TelephoneType> telephoneTypes = this._telephoneTypeRepository.findAll();
            request.setAttribute("telephoneTypes", telephoneTypes);  

            request.getRequestDispatcher("/WEB-INF/students/details.jsp").forward(request, response);
        }    
    }
    
    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";
        
        try
        {
            String studentId = request.getParameter("studentId");   
            
            if(Utility.isNullOrWhiteSpace(studentId))
                throw new Exception("ID empty");
            
            Student student = this._studentRepository.findStudent(studentId);
            
            if(student == null)
                throw new Exception("Student not found");
            
            List<Email> emails = this._emailRepository.findEmails(student.getUser());
            
            for (Email email : emails)
            {
                if(!this._emailRepository.delete(email))
                    throw new Exception("Error trying to delete email");
            }
            
             List<UserTelephone> usersTelephones = this._userTelephoneRepository.findUsersTelephones(student.getUser());
            
            for (UserTelephone userTelephone : usersTelephones)
            {
                if(!this._userTelephoneRepository.deleteById(userTelephone.getUserTelephoneId()))
                    throw new Exception("Error trying to delete userTelephone");
                
                if(!this._telephoneRepository.deleteById(userTelephone.getTelephone().getTelephoneId()))
                    throw new Exception("Error trying to delete telephone");
            }
            
            student.getUser().setPosition(null);
            
            List<UserRole> usersRoles = this._userRoleRepository.findUsersRoles(student.getUser());
            
            for (UserRole userRole : usersRoles)
            {
                if(!this._userRoleRepository.delete(userRole))
                    throw new Exception("Error trying to delete userRepository");
            }
            
            if(!this._studentRepository.delete(student))
                throw new Exception("Error trying to delete student");
            
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
            String studentId = request.getParameter("userUsername");   
            
            if(Utility.isNullOrWhiteSpace(studentId))
                throw new Exception("ID empty");
            
            Student student = this._studentRepository.findStudent(studentId);
            
            if(student == null)
                throw new Exception("Student not found");
            
            List<Email> emails = this._emailRepository.findEmails(student.getUser());
            
            for (Email email : emails)
            {
                if(!this._emailRepository.delete(email))
                    throw new Exception("Error trying to delete email");
            }
            
            List<UserTelephone> usersTelephones = this._userTelephoneRepository.findUsersTelephones(student.getUser());
            
            for (UserTelephone userTelephone : usersTelephones)
            {
                if(!this._userTelephoneRepository.deleteById(userTelephone.getUserTelephoneId()))
                    throw new Exception("Error trying to delete userTelephone");
                
                if(!this._telephoneRepository.deleteById(userTelephone.getTelephone().getTelephoneId()))
                    throw new Exception("Error trying to delete telephone");
            }
            
            student.getUser().setPosition(null);
            
            List<UserRole> usersRoles = this._userRoleRepository.findUsersRoles(student.getUser());
            
            for (UserRole userRole : usersRoles)
            {
                if(!this._userRoleRepository.delete(userRole))
                    throw new Exception("Error trying to delete userRepository");
            }
            
            if(!this._studentRepository.delete(student))
                throw new Exception("Error trying to delete student");
            
            response.sendRedirect(request.getContextPath() + "/Students");
        }
        catch(Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("userUsername"));
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