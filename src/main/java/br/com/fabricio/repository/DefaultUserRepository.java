package br.com.fabricio.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricio.model.User;
import br.com.fabricio.util.StatusException;

@Named
@Transactional
public class DefaultUserRepository implements UserRepository{

	@PersistenceContext
	EntityManager entityManager;	
    private static Logger logger = LoggerFactory.getLogger(DefaultUserRepository.class);
    
    /**
	 * Esse método é responsável para inserir uma nova User no banco de dados. 
	 * @param User Entidade que sera inserida
	 * @return User persistida
	 * @throws Exception
	 */
	@Override
	public User create(User user) throws Exception {
		try {
			//Chama função que vai verificar se o usuario ja esta cadastrado(procura pelo email) 
			List<User> listReturn = getUser(user.getEmail());
    		if (listReturn.size() >= 1) {
                logger.error("Email ja cadastrado");
                throw new StatusException(Status.BAD_REQUEST.getStatusCode(), "Email ja cadastrado");
			}    		
    		user.setCompositeKey(compositeKey());
    		entityManager.persist(user);
    		logger.debug("Pessoa persistida com sucesso.");
    		return user;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Esse metodo é responsavel por procurar uma User no banco de dados.
	 * @param String compositeKey da User que se deseja proucurar.
	 * @return User encontrada de acordo com o compositeKey.
	 * @throws Exception
	 */
	private User findOne(String key) throws Exception {
		try {
			Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.compositeKey = :compositeKey ");
	        query.setParameter("compositeKey", key);
	        
	        @SuppressWarnings("unchecked")
			List<User> list =  query.getResultList();
	        if (list != null && list.size() > 0) {
	            logger.debug("Pessoa encontrada com sucesso.");
	        	return list.get(0);
	    	}
	        
			logger.error("Pessoa nao encontrada");
            throw new StatusException(Status.NOT_FOUND.getStatusCode(), "Pessoa não encontrado.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Esse método é responsavel em modificar uma pessoa
	 * @param pessoa entidade modificada.
	 * @return User modificada.
	 * @throws Exception
	 */
	@Override
	public User update(User user) throws Exception {
		try {
			User userReturn = findOne(user.getCompositeKey());
			
			if (userReturn == null ) {
	            logger.debug("Pessoa não encontrada .");
	            throw new StatusException(Status.NOT_FOUND.getStatusCode(), "Pessoa não encontrado.");
	        }
			userReturn.setName(user.getName());
			
			entityManager.merge(userReturn);
			logger.debug("Pessoa atualizada com sucesso.");
			return userReturn;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Esse método é responsavel em listar de User cadastradas no banco de dados.
	 * @return uma listagem com as pessoas com o email referenciado.
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<User> getUser(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email ");
        query.setParameter("email", email);
        
        List<User> list =  query.getResultList();
        if (list == null) {
            list = new ArrayList();
            return list;
        }
        return list;
	}
	
	/**
	 * Esse método é responsavel por gerar um compositeKey.
	 * @return String compositeKey.
	 * @throws Exception
	 */
	private String compositeKey() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
