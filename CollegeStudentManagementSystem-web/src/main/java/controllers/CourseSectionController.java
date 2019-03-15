/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import interfaces.IBuildRepository;
import interfaces.ICourseRepository;
import interfaces.ICourseSectionRepository;
import interfaces.IPermissionRepository;
import interfaces.ITeacherRepository;
import interfaces.ICampusRepository;
import interfaces.IClassroomRepository;
import interfaces.ICourseSectionHistoricalRepository;
import interfaces.IPeriodRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Campus;
import models.Classroom;
import models.Course;
import models.CourseSection;
import models.CourseSectionHistorical;
import models.CourseSectionPK;
import models.Period;
import models.PeriodPK;
import models.Teacher;
import utilities.Utility;
import models.Build;
import models.ClassParticipant;
import models.Student;

/**
 * @author Gabriel_Liberato
 */
@WebServlet(name = "CourseSectionController", urlPatterns = {"/CoursesSections", "/CoursesSections/Create",
        "/CoursesSections/Edit", "/CoursesSections/Details", "/CoursesSections/Delete",
        "/CoursesSections/Participants", "/CoursesSections/Participants/All"})
public class CourseSectionController extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Inject
    private ICourseRepository _courseRepository;

    @Inject
    private ICourseSectionRepository _courseSectionRepository;

    @Inject
    private ITeacherRepository _teacherRepository;

    @Inject
    private ICampusRepository _campusRepository;

    @Inject
    private IBuildRepository _buildRepository;

    @Inject
    private IPeriodRepository _periodRepository;

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
            if (request.getRequestURI().contains("/Create"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Create"))
                    throw new Exception("User doesn't have permission");

                doCreateGet(request, response);

            }
            else if (request.getRequestURI().contains("/Edit"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditGet(request, response);
            }
            else if (request.getRequestURI().contains("/Details"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Details"))
                    throw new Exception("User doesn't have permission");

                doDetailsGet(request, response);
            }
            else if (request.getRequestURI().contains("/Participants/All"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Participants/All"))
                    throw new Exception("User doesn't have permission");

                doParticipantsAllGet(request, response);
            }
            else if (request.getRequestURI().contains("/Participants"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Participants"))
                    throw new Exception("User doesn't have permission");

                doParticipantsGet(request, response);
            }
            else if (request.getRequestURI().contains("/Delete"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Delete"))
                    throw new Exception("User doesn't have permission");

                doDeleteGet(request, response);
            }
            else
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections"))
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
            if (request.getRequestURI().contains("/Create"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Create"))
                    throw new Exception("User doesn't have permission");

                doCreatePost(request, response);
            }
            else if (request.getRequestURI().contains("/Edit"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Edit"))
                    throw new Exception("User doesn't have permission");

                doEditPost(request, response);
            }
            else if (request.getRequestURI().contains("/Details"))
            {
                response.sendError(400);
            }
            else if (request.getRequestURI().contains("/Delete"))
            {
                if (!this._permissionRepository.hasUserPermission((BigDecimal) request.getSession(false).getAttribute("userId"), "/CoursesSections/Delete"))
                    throw new Exception("User doesn't have permission");

                doDeletePost(request, response);
            }
            else if (request.getRequestURI().contains("/Participants"))
            {
                response.sendError(400);
            }
            else
            {
                response.sendError(400);
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

    protected void doIndexGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/coursesSections/index.jsp").forward(request, response);
    }

    private void doCreateGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String courseId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ?
                request.getAttribute("id").toString() : request.getParameter("id");

        if (Utility.isNullOrWhiteSpace(courseId))
            response.sendRedirect(request.getContextPath() + "/CoursesSections");

        Course course = this._courseRepository.findById(courseId);

        if (course == null)
            response.sendRedirect(request.getContextPath() + "/CoursesSections");

        request.setAttribute("course", course);

        List<Teacher> teachers = this._teacherRepository.findAll();

        request.setAttribute("teachers", teachers);

        List<Campus> campus = this._campusRepository.findAll();

        request.setAttribute("campus", campus);

        request.getRequestDispatcher("/WEB-INF/coursesSections/create.jsp").forward(request, response);
    }

    private void doCreatePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //COURSE SECTION OBJECT
            CourseSection courseSection = new CourseSection();
            courseSection.setCourseSectionDay(request.getParameter("courseSectionDay"));
            courseSection.setCourseSectionStartHour(request.getParameter("courseSectionStartHour"));
            courseSection.setCourseSectionEndHour(request.getParameter("courseSectionEndHour"));
            courseSection.setCourseSectionActive((short) 1);
            courseSection.setCourseSectionCreationDate(new Date());

            Course course = this._courseRepository.findById(request.getParameter("courseId"));

            if (course == null)
                throw new Exception("Course does not exist");

            CourseSectionPK courseSectionPK = new CourseSectionPK();
            courseSectionPK.setCourseId(course.getCourseId());

            courseSection.setCoursesSectionsPK(courseSectionPK);

            courseSection.setCourse(course);

            CourseSectionHistorical courseSectionHistorical = new CourseSectionHistorical();
            courseSectionHistorical.setCourseSectHistPrice(courseSection.getCourse().getCoursePrice());
            courseSectionHistorical.setCourseSectHistQuota(new BigDecimal(request.getParameter("quota")));
            courseSectionHistorical.setCourseSectHistCreationDate(new Date());
            courseSectionHistorical.setCourseSection(courseSection);

            int[] currentYearAndQuarter = Utility.getCurrentYearAndQuarter();

            PeriodPK periodsPK = new PeriodPK();
            periodsPK.setPeriodYear(new BigDecimal(Integer.toString(currentYearAndQuarter[0])));
            periodsPK.setPeriodQuarter(new BigDecimal(Integer.toString(currentYearAndQuarter[1])));

            Period period = this._periodRepository.findById(periodsPK);

            if (period == null)
                throw new Exception("Period does not exist");

            courseSectionHistorical.setPeriod(period);

            Teacher teacher = this._teacherRepository.findTeacher(new BigDecimal(request.getParameter("teacher")));

            if (teacher == null)
                throw new Exception("Teacher does not exist");

            courseSectionHistorical.setTeacher(teacher);

            Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(request.getParameter(
                    "classroom")));

            if (classroom == null)
                throw new Exception("Classroom does not exist");

            courseSectionHistorical.setClassroom(classroom);

            if (!this._courseSectionHistoricalRepository.save(courseSectionHistorical))
                throw new Exception("An error has ocurred creating the course section historical");

            response.sendRedirect(request.getContextPath() + "/CoursesSections");
        }
        catch (Exception ex)
        {
            request.setAttribute("isSuccess", false);
            doCreateGet(request, response);
        }
    }

    private void doEditGet(HttpServletRequest request, HttpServletResponse response)
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

            List<Campus> campus = this._campusRepository.findAll();
            request.setAttribute("campus", campus);

            List<Build> builds =
                    this._buildRepository.findBuilds(coursesSectionsHistorical.get(0).getClassroom().getBuild().getCampus());
            request.setAttribute("builds", builds);

            List<Classroom> classrooms =
                    this._classroomRepository.findClassrooms(coursesSectionsHistorical.get(0).getClassroom().getBuild());
            request.setAttribute("classrooms", classrooms);

            List<Teacher> teachers = this._teacherRepository.findAll();
            request.setAttribute("teachers", teachers);

            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            List<String> daysOfWeekList = Arrays.asList(daysOfWeek);
            request.setAttribute("daysOfWeek", daysOfWeekList);

            request.setAttribute("courseSection", courseSection);

            request.setAttribute("courseSectionHistorical", coursesSectionsHistorical.get(0));

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));

            request.getRequestDispatcher("/WEB-INF/coursesSections/edit.jsp").forward(request, response);
        }
    }

    private void doEditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String courseSectionId = request.getParameter("courseSectionId");

            if (Utility.isNullOrWhiteSpace(courseSectionId))
                throw new Exception("CourseSectionId is empty");

            CourseSection courseSection =
                    this._courseSectionRepository.findCourseSection(new BigDecimal(courseSectionId));

            if (courseSection == null)
                throw new Exception("CourseSection does not exist");

            //COURSE SECTION OBJECT
            courseSection.setCourseSectionDay(request.getParameter("courseSectionDay"));
            courseSection.setCourseSectionStartHour(request.getParameter("courseSectionStartHour"));
            courseSection.setCourseSectionEndHour(request.getParameter("courseSectionEndHour"));
            courseSection.setCourseSectionActive(Short.parseShort(request.getParameter("courseSectionActive")));

            CourseSectionHistorical courseSectionHistorical =
                    this._courseSectionHistoricalRepository.findCoursesSectionsHistorical(courseSection).get(0);

            if (courseSectionHistorical == null)
                throw new Exception("CourseSectionHistorical does not exist");

            int storedLastPeriodSum =
                    Integer.parseInt(courseSectionHistorical.getPeriod().getPeriodsPK().getPeriodYear().toString()) + Integer.parseInt(courseSectionHistorical.getPeriod().getPeriodsPK().getPeriodQuarter().toString());

            int currentPeriodSum = Utility.getCurrentYearAndQuarter()[0] + Utility.getCurrentYearAndQuarter()[1];

            if (storedLastPeriodSum < currentPeriodSum)
            {
                CourseSectionHistorical newCourseSectionHistorical = new CourseSectionHistorical();
                newCourseSectionHistorical.setCourseSectHistPrice(courseSection.getCourse().getCoursePrice());
                newCourseSectionHistorical.setCourseSectHistQuota(new BigDecimal(request.getParameter("quota")));
                newCourseSectionHistorical.setCourseSectHistCreationDate(new Date());
                newCourseSectionHistorical.setCourseSection(courseSection);

                int[] currentYearAndQuarter = Utility.getCurrentYearAndQuarter();

                PeriodPK periodsPK = new PeriodPK();
                periodsPK.setPeriodYear(new BigDecimal(Integer.toString(currentYearAndQuarter[0])));
                periodsPK.setPeriodQuarter(new BigDecimal(Integer.toString(currentYearAndQuarter[1])));

                Period period = this._periodRepository.findById(periodsPK);

                if (period == null)
                    throw new Exception("Period does not exist");

                courseSectionHistorical.setPeriod(period);

                Teacher teacher = this._teacherRepository.findTeacher(new BigDecimal(request.getParameter("teacher")));

                if (teacher == null)
                    throw new Exception("Teacher does not exist");

                courseSectionHistorical.setTeacher(teacher);

                Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(request.getParameter(
                        "classroom")));

                if (classroom == null)
                    throw new Exception("Classroom does not exist");

                courseSectionHistorical.setClassroom(classroom);

                if (!this._courseSectionHistoricalRepository.save(courseSectionHistorical))
                    throw new Exception("An error has ocurred creating the course section historical");
            }
            else
            {
                courseSectionHistorical.setCourseSectHistPrice(courseSection.getCourse().getCoursePrice());
                courseSection.setCourseSectionDay(request.getParameter("courseSectionDay"));
                courseSection.setCourseSectionStartHour(request.getParameter("courseSectionStartHour"));
                courseSection.setCourseSectionEndHour(request.getParameter("courseSectionEndHour"));
                courseSection.setCourseSectionActive(Short.parseShort(request.getParameter("courseSectionActive")));

                Teacher teacher = this._teacherRepository.findTeacher(new BigDecimal(request.getParameter("teacher")));

                if (teacher == null)
                    throw new Exception("Teacher does not exist");

                courseSectionHistorical.setTeacher(teacher);

                Classroom classroom = this._classroomRepository.findClassroom(new BigDecimal(request.getParameter(
                        "classroom")));

                if (classroom == null)
                    throw new Exception("Classroom does not exist");

                courseSectionHistorical.setClassroom(classroom);

                if (!this._courseSectionHistoricalRepository.update(courseSectionHistorical))
                    throw new Exception("An error has ocurred updating the course section historical");
            }

            response.sendRedirect(request.getContextPath() + "/CoursesSections");
        }
        catch (Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("courseId"));
            doEditGet(request, response);
        }
    }

    private void doDetailsGet(HttpServletRequest request, HttpServletResponse response)
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

            if (coursesSectionsHistorical.size() < 0)
                response.sendRedirect(request.getContextPath() + "/CoursesSections");

            request.setAttribute("coursesSectionsHistorical", coursesSectionsHistorical);

            List<Campus> campus = this._campusRepository.findAll();
            request.setAttribute("campus", campus);

            List<Build> builds =
                    this._buildRepository.findBuilds(coursesSectionsHistorical.get(0).getClassroom().getBuild().getCampus());
            request.setAttribute("builds", builds);

            List<Classroom> classrooms =
                    this._classroomRepository.findClassrooms(coursesSectionsHistorical.get(0).getClassroom().getBuild());
            request.setAttribute("classrooms", classrooms);

            List<Teacher> teachers = this._teacherRepository.findAll();
            request.setAttribute("teachers", teachers);

            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            List<String> daysOfWeekList = Arrays.asList(daysOfWeek);
            request.setAttribute("daysOfWeek", daysOfWeekList);

            request.setAttribute("courseSection", courseSection);

            request.setAttribute("courseSectionHistorical", coursesSectionsHistorical.get(0));

            request.setAttribute("simpleDateFormat", new SimpleDateFormat("MM/dd/yyyy"));

            request.getRequestDispatcher("/WEB-INF/coursesSections/details.jsp").forward(request, response);
        }
    }

    private void doDeleteGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse = "";

        try
        {
            String courseId = request.getParameter("courseId");

            if (Utility.isNullOrWhiteSpace(courseId))
                throw new Exception("ID Empty");

            Course course = this._courseRepository.findById(courseId);

            if (course == null)
                throw new Exception("Course not found");

            course.setCourseActive((short) 0);

            if (!this._courseRepository.update(course))
                throw new Exception("Error trying to update build");

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

    private void doDeletePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            String courseId = request.getParameter("courseId");

            if (Utility.isNullOrWhiteSpace(courseId))
                throw new Exception("ID Empty");

            Course course = this._courseRepository.findById(courseId);

            if (course == null)
                throw new Exception("Course not found");

            course.setCourseActive((short) 0);

            if (!this._courseRepository.update(course))
                throw new Exception("Error trying to update build");

            response.sendRedirect(request.getContextPath() + "/Courses");
        }
        catch (Exception ex)
        {
            request.setAttribute("isSuccess", false);
            request.setAttribute("id", request.getParameter("courseId"));
            doDetailsGet(request, response);
        }
    }

    private void doParticipantsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String courseSectionId = Utility.isNullOrWhiteSpace(request.getParameter("id")) ?
                request.getAttribute("id").toString() : request.getParameter("id");

        CourseSection courseSection = this._courseSectionRepository.findCourseSection(new BigDecimal(courseSectionId));

        if (courseSection == null)
            response.sendRedirect(request.getContextPath() + "/CoursesSections");

        List<CourseSectionHistorical> courseSectionsHistorical =
                this._courseSectionHistoricalRepository.findCoursesSectionsHistorical(courseSection);

        int[] periodAndQuarter = Utility.getCurrentYearAndQuarter();

        BigDecimal currentCourseSectionHistoricalId = BigDecimal.ZERO;

        for (CourseSectionHistorical courseSectionHistorical : courseSectionsHistorical)
        {
            String dbYear = courseSectionHistorical.getPeriod().getPeriodsPK().getPeriodYear().toString();
            String currentYear = new BigDecimal(periodAndQuarter[0]).toString();

            String dbQuarter = new BigDecimal(periodAndQuarter[1]).toString();
            String currentQuarter = new BigDecimal(periodAndQuarter[1]).toString();

            if (dbYear.equals(currentYear) && dbQuarter.equals(currentQuarter))
                currentCourseSectionHistoricalId = courseSectionHistorical.getCourseSectHistId();
        }

        request.setAttribute("currentCourseSectionHistoricalId", currentCourseSectionHistoricalId);
        request.setAttribute("courseSectionsHistorical", courseSectionsHistorical);
        request.setAttribute("currentPeriod", periodAndQuarter);

        request.getRequestDispatcher("/WEB-INF/coursesSections/participants.jsp").forward(request, response);
    }

    private void doParticipantsAllGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletResponse;

        try
        {
            String courseSectionHistoricalId = request.getParameter("id");

            if (Utility.isNullOrWhiteSpace(courseSectionHistoricalId))
                throw new Exception("Empty parameter");

            CourseSectionHistorical courseSectionHistorical =
                    this._courseSectionHistoricalRepository.findById(new BigDecimal(courseSectionHistoricalId));

            if (courseSectionHistorical == null)
                throw new Exception("Course section historical not found");

            List<Student> courseSectionHistoricalStudents = new ArrayList<>();

            for (ClassParticipant classParticipant : courseSectionHistorical.getClassParticipants())
            {
                classParticipant.getStudent().setClassParticipants(null);
                classParticipant.getStudent().getUser().setUsersGroups(null);
                classParticipant.getStudent().getUser().setUsersRoles(null);
                classParticipant.getStudent().getUser().setStudents(null);
                classParticipant.getStudent().getUser().setAddress(null);

                courseSectionHistoricalStudents.add(classParticipant.getStudent());
            }

            servletResponse = new Gson().toJson(courseSectionHistoricalStudents);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(servletResponse);
        }
        catch (Exception ex)
        {
            if (ex.getMessage().toUpperCase().contains("NOT FOUND"))
                response.sendError(404);
            else if (ex.getMessage().equals("Empty parameter"))
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