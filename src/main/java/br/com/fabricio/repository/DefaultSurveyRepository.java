package br.com.fabricio.repository;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;
import br.com.fabricio.util.StatusException;

@Named
@Transactional
public class DefaultSurveyRepository implements SurveyRepository {

    @PersistenceContext
	EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(DefaultSurveyRepository.class);

    /**
	 * Esse método é responsável para inserir uma nova pesquisa no banco de dados. 
	 * @param pesquisa Entidade que sera inserida
	 * @return Survey persistidas.
	 * @throws Exception
	 */
    @Override
	public Survey create(String compositeKey, Survey survey) throws Exception {
		try {
			if (verify(compositeKey)) {
	    	   logger.error("Pesquisa ja realizada.");
	           throw new StatusException(Status.BAD_REQUEST.getStatusCode(), "Pesquisa ja realizada.");
			}
			survey.setDate(new Date());
			entityManager.persist(survey);
			logger.debug("Pesquisa persistida.");
			return survey;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
    /**
	 * Esse método é responsavel por verificar se exixte um passoa com a compositeKey referenciada e se a uma pesquisa com uma pessoa com esta conpositeKey
	 * @param String compositeKey
	 * @returnBoolean true se existe Survey, false se nao tiver Survey no banco de dados. 
	 * @throws Exception
	 */
	@Override
	public Boolean verify(String compositeKey) throws Exception {
		try {
			if (getUser(compositeKey) == false) {
				logger.error("Usuario não encontrado.");
				throw new StatusException(Status.NOT_FOUND.getStatusCode(), "Usuario não encontrado.");
			}
			
			Query query = entityManager.createQuery("SELECT s FROM Survey s INNER JOIN s.user u WHERE u.compositeKey = :compositeKey");
			query.setParameter("compositeKey", compositeKey);
	        @SuppressWarnings("unchecked")
			List<Survey> surveys = query.getResultList();
			
	        if (surveys != null && surveys.size() > 0) {
	    	   logger.error("Pesquisa ja realizada.");
	           return true;
	        }	    
	        logger.debug("Pesquisa não realizada.");
	        return false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Esse método é responsavel por procurar uma pessoa com a compositeKey referenciada
	 * @param String compositeKey.
	 * @returnBoolean true se existe User, false se nao tiver User no banco de dados. 
	 * @throws Exception
	 */
	private boolean getUser(String compositeKey) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ");
        query.setParameter("compositeKey", compositeKey);
        
        @SuppressWarnings("unchecked")
		List<User> list =  query.getResultList();
        if (list != null && list.size() > 0) {
        	return true;
        }        
		return false;        
	}	
}
