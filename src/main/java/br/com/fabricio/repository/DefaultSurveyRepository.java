package br.com.fabricio.repository;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricio.model.Survey;
import br.com.fabricio.util.StatusException;

@Named
@Transactional
public class DefaultSurveyRepository implements SurveyRepository {

    @PersistenceContext
	EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultSurveyRepository.class);

    @Override
	public Survey create(String compositeKey, Survey survey) throws Exception {
		try {
			if (findOne(compositeKey) != null) {
	    	   logger.error("Pesquisa ja realizada.");
	           throw new StatusException(Status.BAD_REQUEST.getStatusCode(), "Pesquisa ja realizada.");
			}
			entityManager.persist(survey);
			logger.debug("Pesquisa persistida.");
			return survey;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public Survey findOne(String compositeKey) throws Exception {
	try {
		Query query = entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey");
		query.setParameter("compositeKey", compositeKey);
        Survey survey = (Survey) query.getSingleResult();
        if (survey != null) {
    	   logger.error("Pesquisa ja realizada.");
           throw new StatusException(Status.BAD_REQUEST.getStatusCode(), "Pesquisa ja realizada.");
        }	    
        logger.debug("Pesquisa não realizada.");
        return survey;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
