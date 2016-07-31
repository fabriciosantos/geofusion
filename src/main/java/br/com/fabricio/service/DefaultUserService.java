package br.com.fabricio.service;

import java.net.URI;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricio.model.User;
import br.com.fabricio.repository.UserRepository;
import br.com.fabricio.util.FailureResponseBuilder;
import br.com.fabricio.util.ServiceUtil;
import br.com.fabricio.util.StatusException;

public class DefaultUserService implements UserService{


    private UserRepository userRepository;
    private Validator validator;
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
    
    @Inject
    public DefaultUserService(UserRepository userRepository, Validator validator){
        this.userRepository = userRepository;
        this.validator = validator;
    }

	@Override
	public Response create(UriInfo uriInfo, User user) throws Exception {
		try {
			Set<ConstraintViolation<User>> validationErrors = validator.validate(user);
			if (!validationErrors.isEmpty()) {
				String error = ServiceUtil.getErrors(validationErrors);
	            logger.error(error);
	            return new FailureResponseBuilder().toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), error));
	        }
			User userReturn = userRepository.create(user);
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(userReturn.getId())).build();
            logger.debug("Service : Usuario criado.");
            return Response.created(uri).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
            return new FailureResponseBuilder().toResponse(e);
		}
}

	@Override
	public Response findOne(int idUser) {
		try {
			User user = userRepository.findOne(idUser);
            if (user == null) {
                logger.error("Service : Usuario não encontrado");
                return new FailureResponseBuilder().toResponse(new StatusException(Status.NOT_FOUND.getStatusCode(), "Service : Usuario não encontrado"));
            }
            return Response.ok().entity(user).build();
		} catch (Exception e) {
			logger.equals(e.getMessage());
			return new FailureResponseBuilder().toResponse(e);
		}

	}

	@Override
	public Response update(User user) {
		try {
			Set<ConstraintViolation<User>> validationErrors = validator.validate(user);
			if (!validationErrors.isEmpty()) {
				String error = ServiceUtil.getErrors(validationErrors);
	            logger.error(error);
	            return new FailureResponseBuilder().toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), error));
	        }
			userRepository.update(user);

			logger.debug("Service : Usuario atualizado.");
            return Response.noContent().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
            return new FailureResponseBuilder().toResponse(e);
		}
	}

       
}
