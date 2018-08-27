package com.easymenuplanner.backend.rest.resources;

import com.oscardelgado83.easymenuplanner.pojos.ExportPOJO;
import com.oscardelgado83.easymenuplanner.pojos.TimestampPOJO;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Stateless
public class BackendResource {

    @Inject
    protected EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(BackendResource.class);
    
    protected static final String REDIRECT_HOST = "easymenuplanerwildfly-ods.rhcloud.com";

    @GET
    @Path("/last-update-timestamp")
    public TimestampPOJO lastUpdateTimestamp(@QueryParam("acc") String encAccName, @QueryParam("dev") String encDevId) {
    	
         logger.info("lastUpdateTimestamp");

         ExportPOJO obtainedPojo = null;

         if (encDevId != null) {
             try {
                 obtainedPojo = (ExportPOJO) em.createQuery("SELECT e FROM ExportPOJO e "
                         + "WHERE e.accountName = :acc "
                         + "AND e.deviceId = :dev "
                         + "AND e.fromError = 0 "
                         + "ORDER BY e.updateTimestamp DESC")
                         .setParameter("acc", encAccName)
                         .setParameter("dev", encDevId)
                         .getSingleResult();
             } catch (NoResultException e) {
                 return null;
             }
         } else {
             List<?> pojos = null;
             pojos = em.createQuery("SELECT e FROM ExportPOJO e "
                     + "WHERE e.accountName = :acc "
                     + "AND e.fromError = 0 "
                     + "ORDER BY e.updateTimestamp DESC")
                     .setParameter("acc", encAccName)
                     .setMaxResults(1)
                     .getResultList();

             if (pojos != null && !pojos.isEmpty()) {
                 obtainedPojo = (ExportPOJO) pojos.get(0);
             } else {
                 return null;
             }
         }
         TimestampPOJO pojo = new TimestampPOJO();
         pojo.updateTimestamp = obtainedPojo.getUpdateTimestamp();
         return pojo;
    }
    
    @GET
    @Path("/is-alive")
    public String isAlive() {
    	
         logger.info("isAlive");

         ExportPOJO obtainedPojo = null;

         List<?> pojos = null;
         pojos = em.createQuery("SELECT e FROM ExportPOJO e "
                 + "WHERE e.accountName = :acc "
                 + "AND e.fromError = 0 "
                 + "ORDER BY e.updateTimestamp DESC")
                 .setParameter("acc", "test")
                 .setMaxResults(1)
                 .getResultList();

         if (pojos != null && !pojos.isEmpty()) {
             obtainedPojo = (ExportPOJO) pojos.get(0);
         } else {
             return null;
         }
         return obtainedPojo.getUpdateTimestamp();
    }
}
