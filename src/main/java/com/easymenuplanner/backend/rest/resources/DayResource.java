package com.easymenuplanner.backend.rest.resources;

import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.Response;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oscardelgado83.easymenuplanner.pojos.ExportPOJO;

@Path("/days")
@Stateless
public class DayResource extends BackendResource {

    private final static Logger logger = LoggerFactory.getLogger(DayResource.class);
    private static final int MAX = 100;
    
    @Context
    protected HttpServletResponse servletResponse;
    
    @Context
    protected HttpServletRequest servletRequest;
    
    private String getFullURL() {
        logger.info("localName: " + servletRequest.getLocalName());
        String requestURL = servletRequest.getRequestURL().toString().replaceAll(servletRequest.getLocalName(), "easymenuplanerwildfly-ods.rhcloud.com");
        if (servletRequest.getQueryString() != null) {
            requestURL.concat("?").concat(servletRequest.getQueryString());
        }
        return requestURL;
    }

    //TODO: change to return only Days
    @GET
    @Path("/")
    public ExportPOJO getDays(@QueryParam("acc") String encAccName, @QueryParam("dev") String encDevId) {
        logger.info("redirect!");
        try {
            String url = getFullURL();
            logger.info("url: " + url);
            servletResponse.sendRedirect(url);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        
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
            
//            //FIXME: temporal fix because Proguard was changing jsonId field
//            //Although it works, change to a regex to keep both a and jsonId. (Mixed app versions).
//            if (exportPOJO.daysJSON != null) exportPOJO.daysJSON = exportPOJO.daysJSON.replace("\"a\":", "\"jsonId\":");
//            if (exportPOJO.coursesJSON != null) exportPOJO.coursesJSON = exportPOJO.coursesJSON.replace("\"a\":", "\"jsonId\":");
//            if (exportPOJO.courseIngredientJSONs != null) exportPOJO.courseIngredientJSONs = exportPOJO.courseIngredientJSONs.replace("\"a\":", "\"jsonId\":");
//            if (exportPOJO.ingredientJSONs != null) exportPOJO.ingredientJSONs = exportPOJO.ingredientJSONs.replace("\"a\":", "\"jsonId\":");
            
            return exportPOJO;
        } else {
            return null;
        }
    }
    
     @GET
    @Path("/gallery")
    public List<ExportPOJO> getGallery(
            @QueryParam("language") String language,
            @QueryParam("start") int start,
            @QueryParam("count") int count
    ) {
        logger.info("download");
        List<ExportPOJO> pojos = null;
        try {
            String sql = "SELECT e FROM ExportPOJO e "
                    + "WHERE e.fromError = 0 ";
            if (language != null) sql += "AND e.language = :language ";
            sql += "ORDER BY e.updateTimestamp DESC";
            final TypedQuery<ExportPOJO> q = em.createQuery(sql, ExportPOJO.class);
            if (language != null) q.setParameter("language", language);
            q.setFirstResult(start);
            q.setMaxResults((count != 0)? count : MAX);
            pojos = q.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
        return pojos;
    }

    //TODO: change to manage only Days
    @PUT
    @Path("/")
    public ExportPOJO updateDays(ExportPOJO pojo) {
        logger.info("updateDays");
        logger.debug("pojo: {}", pojo);

        saveOrUpdate(pojo);
        return pojo;
    }

    private void saveOrUpdate(ExportPOJO pojo) {
        logger.info("saveOrUpdate");
        em.merge(pojo);
    }
}
