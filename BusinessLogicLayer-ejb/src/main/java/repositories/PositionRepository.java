/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IPositionRepository;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Position;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class PositionRepository extends BaseRepository<Position> implements IPositionRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;

    public PositionRepository()
    {
        super(Position.class);
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
}