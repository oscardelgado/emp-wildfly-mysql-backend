
package com.easymenuplanner.backend.rest.resources;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oscardelgado83.easymenuplanner.pojos.ExportPOJO;

import java.net.URLDecoder; import java.net.URLEncoder;

@Path("/days2")
@Stateless
public class DayResource2 extends BackendResource {

	private static final Logger logger = LoggerFactory.getLogger(DayResource.class);
    private static final int MAX = 100;
    
    //TODO: change to return only Days
    @GET
    @Path("/")
    public ExportPOJO getDays(@QueryParam("acc") String encAccName, @QueryParam("dev") String encDevId) {
    	
         logger.info("download");

         List<?> pojos = null;

         if (encDevId != null) {
             try {
                 pojos = (List<?>) em.createQuery("SELECT e FROM ExportPOJO e "
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
             pojos = em.createQuery("SELECT e FROM ExportPOJO e "
                     + "WHERE e.accountName = :acc "
                     + "AND e.fromError = 0 "
                     + "ORDER BY e.updateTimestamp DESC")
                     .setParameter("acc", encAccName)
                     .setMaxResults(1)
                     .getResultList();
         }
         if (pojos != null && !pojos.isEmpty()) {
             final ExportPOJO exportPOJO = (ExportPOJO) pojos.get(0);
           
           exportPOJO.setDaysJSON(decodeStringUrl(exportPOJO.getDaysJSON()));
           
             return exportPOJO;
         } else {
             return null;
         }
    }

    //TODO: change to manage only Days
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
    public ExportPOJO updateDays(ExportPOJO pojo) {
    	
         logger.info("updateDays");
         logger.debug("pojo: {}", pojo);
      
        pojo.setDaysJSON(encodeStringUrl(pojo.getDaysJSON()));

         return saveOrUpdate(pojo);
  }

  private ExportPOJO saveOrUpdate(ExportPOJO pojo) {
      logger.info("saveOrUpdate");
      return em.merge(pojo);
  }
  
  public static String encodeStringUrl(String url) {
      String encodedUrl =null;
      try {
           encodedUrl = URLEncoder.encode(url, "UTF-8");
      } catch (UnsupportedEncodingException e) {
          return encodedUrl;
      }
      return encodedUrl;
  }

  public static String decodeStringUrl(String encodedUrl) {
      String decodedUrl =null;
      try {
           decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
      } catch (UnsupportedEncodingException e) {
          return decodedUrl;
      }
      return decodedUrl;
  }
}
