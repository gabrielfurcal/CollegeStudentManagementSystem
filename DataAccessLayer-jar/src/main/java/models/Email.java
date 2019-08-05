/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "EMAILS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
    , @NamedQuery(name = "Email.findByEmailId", query = "SELECT e FROM Email e WHERE e.emailId = :emailId")
    , @NamedQuery(name = "Email.findByEmailText", query = "SELECT e FROM Email e WHERE e.emailText = :emailText")
    , @NamedQuery(name = "Email.findByEmailCreationDate", query = "SELECT e FROM Email e WHERE e.emailCreationDate = :emailCreationDate")
})
public class Email implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMAIL_ID")
    private int emailId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "EMAIL_TEXT")
    private String emailText;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMAIL_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emailCreationDate;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User user;

    public Email()
    {
    }

    public Email(int emailId)
    {
        this.emailId = emailId;
    }

    public Email(int emailId, String emailText, Date emailCreationDate)
    {
        this.emailId = emailId;
        this.emailText = emailText;
        this.emailCreationDate = emailCreationDate;
    }

    public int getEmailId()
    {
        return emailId;
    }

    public void setEmailId(int emailId)
    {
        this.emailId = emailId;
    }

    public String getEmailText()
    {
        return emailText;
    }

    public void setEmailText(String emailText)
    {
        this.emailText = emailText;
    }

    public Date getEmailCreationDate()
    {
        return emailCreationDate;
    }

    public void setEmailCreationDate(Date emailCreationDate)
    {
        this.emailCreationDate = emailCreationDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return emailId == email.emailId &&
                Objects.equals(emailText, email.emailText) &&
                Objects.equals(emailCreationDate, email.emailCreationDate) &&
                Objects.equals(user, email.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, emailText, emailCreationDate, user);
    }

    @Override
    public String toString()
    {
        return "Email{" + "emailId=" + emailId + ", emailText=" + emailText + ", emailCreationDate=" + emailCreationDate + '}';
    }
}