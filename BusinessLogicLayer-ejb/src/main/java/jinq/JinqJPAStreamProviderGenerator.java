/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinq;

import javax.persistence.EntityManager;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
public class JinqJPAStreamProviderGenerator
{
    private static JinqJPAStreamProviderGenerator instance = null;
    
    private JinqJPAStreamProvider JinqJPAStreamProvider = null;
    
    private JinqJPAStreamProviderGenerator(EntityManager em)
    {
        this.JinqJPAStreamProvider = new JinqJPAStreamProvider(em.getMetamodel());
    }
    
    public static JinqJPAStreamProviderGenerator getInstance(EntityManager em) 
    {
        if(instance == null) 
            instance = new JinqJPAStreamProviderGenerator(em);
        
        return instance;
    }
    
    public JinqJPAStreamProvider getJinqJPAStreamProvider() 
    {
        return this.JinqJPAStreamProvider;
    }
}
