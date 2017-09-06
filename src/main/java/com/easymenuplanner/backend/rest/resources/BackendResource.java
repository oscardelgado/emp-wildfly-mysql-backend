package com.easymenuplanner.backend.rest.resources;

import com.oscardelgado83.easymenuplanner.pojos.ExportPOJO;
import com.oscardelgado83.easymenuplanner.pojos.TimestampPOJO;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/")
@Stateless
public class BackendResource {

    @Inject
    protected EntityManager em;

    private final static Logger logger = LoggerFactory.getLogger(BackendResource.class);
    
    private static final String REDIRECT_HOST = "http://easymenuplanerwildfly-ods.rhcloud.com";

    @Context
    protected HttpServletResponse servletResponse;
    
    @Context
    protected HttpServletRequest servletRequest;
    
    protected void redirect() {
    	logger.info("redirect!");
    	servletResponse.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        servletResponse.setHeader("Location", REDIRECT_HOST);
    }
//        logger.info("redirect!");
    
//    https://stackoverflow.com/a/14951320/1464013
//    servletResponse.setStatus(TEMPORARY_REDIRECT);
//    servletResponse.setHeader("Location", REDIRECT_HOST);
    
//        String requestURL = servletRequest.getRequestURL().toString().replaceAll(servletRequest.getServerName(), REDIRECT_HOST);
//        if (servletRequest.getQueryString() != null) {
//            requestURL.concat("?").concat(servletRequest.getQueryString());
//        }
//        
//        try {
//            logger.info("url: " + requestURL);
//            servletResponse.sendRedirect(requestURL);
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        }
//    }

    @GET
    @Path("/last-update-timestamp")
    public TimestampPOJO lastUpdateTimestamp(@QueryParam("acc") String encAccName, @QueryParam("dev") String encDevId) {
    	
    	redirect();
        
        return null;
    	
//         logger.info("lastUpdateTimestamp");

//         ExportPOJO obtainedPojo = null;

//         if (encDevId != null) {
//             try {
//                 obtainedPojo = (ExportPOJO) em.createQuery("SELECT e FROM ExportPOJO e "
//                         + "WHERE e.accountName = :acc "
//                         + "AND e.deviceId = :dev "
//                         + "AND e.fromError = 0 "
//                         + "ORDER BY e.updateTimestamp DESC")
//                         .setParameter("acc", encAccName)
//                         .setParameter("dev", encDevId)
//                         .getSingleResult();
//             } catch (NoResultException e) {
//                 return null;
//             }
//         } else {
//             List<?> pojos = null;
//             pojos = em.createQuery("SELECT e FROM ExportPOJO e "
//                     + "WHERE e.accountName = :acc "
//                     + "AND e.fromError = 0 "
//                     + "ORDER BY e.updateTimestamp DESC")
//                     .setParameter("acc", encAccName)
//                     .setMaxResults(1)
//                     .getResultList();

//             if (pojos != null && !pojos.isEmpty()) {
//                 obtainedPojo = (ExportPOJO) pojos.get(0);
//             } else {
//                 return null;
//             }
//         }
//         TimestampPOJO pojo = new TimestampPOJO();
//         pojo.updateTimestamp = obtainedPojo.getUpdateTimestamp();
//         return pojo;
    }
}
