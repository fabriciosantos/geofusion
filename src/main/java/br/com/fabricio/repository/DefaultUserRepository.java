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
    
	@Override
	public User create(User user) throws Exception {
		try {
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

	@Override
	public User findOne(int idUser) throws Exception {
		try {
			User user = new User();
			user = entityManager.find(User.class, idUser);
			if(user == null){
                logger.error("Pessoa nao encontrada");
                throw new StatusException(Status.NOT_FOUND.getStatusCode(), "Pessoa id " + idUser + " não encontrado.");
			}
			logger.debug("Pessoa encontrada com sucesso.");
			return user;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
}

	@Override
	public User update(User user) throws Exception {
		try {
			findOne(user.getId());
			entityManager.merge(user);
			logger.debug("Pessoa atualizada com sucesso.");
			return user;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


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
	

	private String compositeKey() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
