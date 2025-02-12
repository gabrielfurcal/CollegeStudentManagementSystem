/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries(
        {
                @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
                , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
                , @NamedQuery(name = "User.findByUserFirstName", query = "SELECT u FROM User u WHERE u.userFirstName = :userFirstName")
                , @NamedQuery(name = "User.findByUserFatherLastName", query = "SELECT u FROM User u WHERE u.userFatherLastName = :userFatherLastName")
                , @NamedQuery(name = "User.findByUserMotherLastName", query = "SELECT u FROM User u WHERE u.userMotherLastName = :userMotherLastName")
                , @NamedQuery(name = "User.findByUserUsername", query = "SELECT u FROM User u WHERE u.userUsername = :userUsername")
                , @NamedQuery(name = "User.findByUserPassword", query = "SELECT u FROM User u WHERE u.userPassword = :userPassword")
                , @NamedQuery(name = "User.findByUserCreationDate", query = "SELECT u FROM User u WHERE u.userCreationDate = :userCreationDate")
        })
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "USER_FATHER_LAST_NAME")
    private String userFatherLastName;

    @Size(max = 60)
    @Column(name = "USER_MOTHER_LAST_NAME")
    private String userMotherLastName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USER_USERNAME")
    private String userUsername;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreationDate;

    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @JoinColumn(name = "POSITION_ID", referencedColumnName = "POSITION_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Position position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserGroup> usersGroups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> usersRoles;

    public User()
    {
    }

    public User(int userId)
    {
        this.userId = userId;
    }

    public User(int userId, String userFirstName, String userFatherLastName, String userUsername, String userPassword,
                Date userCreationDate)
    {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userFatherLastName = userFatherLastName;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userCreationDate = userCreationDate;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName.toUpperCase();
    }

    public String getUserFatherLastName()
    {
        return userFatherLastName;
    }

    public void setUserFatherLastName(String userFatherLastName) { this.userFatherLastName = userFatherLastName.toUpperCase(); }

    public String getUserMotherLastName()
    {
        return userMotherLastName;
    }

    public void setUserMotherLastName(String userMotherLastName) { this.userMotherLastName = userMotherLastName.toUpperCase(); }

    public String getUserUsername()
    {
        return userUsername;
    }

    public void setUserUsername(String userUsername)
    {
        this.userUsername = userUsername;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public Date getUserCreationDate()
    {
        return userCreationDate;
    }

    public void setUserCreationDate(Date userCreationDate)
    {
        this.userCreationDate = userCreationDate;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    @XmlTransient
    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    @XmlTransient
    public List<UserGroup> getUsersGroups()
    {
        return usersGroups;
    }

    public void setUsersGroups(List<UserGroup> usersGroups)
    {
        this.usersGroups = usersGroups;
    }

    @XmlTransient
    public List<UserRole> getUsersRoles()
    {
        return usersRoles;
    }

    public void setUsersRoles(List<UserRole> usersRoles)
    {
        this.usersRoles = usersRoles;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userFirstName, user.userFirstName) &&
                Objects.equals(userFatherLastName, user.userFatherLastName) &&
                Objects.equals(userMotherLastName, user.userMotherLastName) &&
                Objects.equals(userUsername, user.userUsername) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userCreationDate, user.userCreationDate) &&
                Objects.equals(address, user.address) &&
                Objects.equals(position, user.position) &&
                Objects.equals(students, user.students) &&
                Objects.equals(usersGroups, user.usersGroups) &&
                Objects.equals(usersRoles, user.usersRoles);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, userFirstName, userFatherLastName, userMotherLastName, userUsername, userPassword, userCreationDate, address, position, students, usersGroups, usersRoles);
    }

    @Override
    public String toString()
    {
        return "User{" + "userId=" + userId + ", userFirstName=" + userFirstName + ", userFatherLastName=" + userFatherLastName + ", userMotherLastName=" + userMotherLastName + ", userUsername=" + userUsername + ", userPassword=" + userPassword + ", userCreationDate=" + userCreationDate + '}';
    }
}