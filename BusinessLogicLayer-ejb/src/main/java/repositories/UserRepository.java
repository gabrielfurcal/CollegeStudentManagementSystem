/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IUserRepository;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.User;
import org.jinq.jpa.JinqJPAStreamProvider;
import jinq.JinqJPAStreamProviderGenerator;

/**
 * @author Gabriel_Liberato
 */
@Stateless
public class UserRepository extends BaseRepository<User> implements IUserRepository
{
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "CsmsPU")
    private EntityManager em;

    private JinqJPAStreamProvider streams;

    public UserRepository()
    {
        super(User.class);
    }

    @PostConstruct
    protected void init()
    {
        JinqJPAStreamProviderGenerator jinqGenerator = JinqJPAStreamProviderGenerator.getInstance(em);
        streams = jinqGenerator.getJinqJPAStreamProvider();
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    protected JinqJPAStreamProvider getJinqJPAStreamProvider()
    {
        return streams;
    }

    @Override
    public User findUser(String username)
    {
        try
        {
            User user = getEntityManager().createQuery("select u from User u where u.userUsername = :username",
                    User.class).setParameter("username", username).getSingleResult();

            return user;
        } catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public User findUser(String username, String password)
    {
        try
        {
            User user = getEntityManager().createQuery("select u from User u where u.userUsername = :username and u" +
                    ".userPassword = :password", User.class).setParameter("username", username).setParameter(
                    "password", password).getSingleResult();

            return user;
        } catch (Exception ex)
        {
            return null;
        }
    }
}