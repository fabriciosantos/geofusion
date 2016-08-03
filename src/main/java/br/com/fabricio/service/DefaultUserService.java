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
import br.com.fabricio.util.SendEmail;
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

    /**
	 * Serviço responsavel para inserir uma User.
	 * @param User entidade que sera inserida
	 * @return 201 created, ou 500 internal server error.
	 * @throws Exception
	 */
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
			
			String message = "	<h1>Obrigado pela sua participação no meu curso.</h1> "
	    			+ "<h2>Click no botão abaixo para descobrir o segredo da fluência.</h2>"
	    			+ "<h2>Um abraço e nós vemos em breve.</h2>"
	    			+ "<a href=\"http://fabricioashua-fabricioashua.rhcloud.com/geofusion/survey.html?+" 
	    			+ user.getCompositeKey()+"\" target=\"_blank\" style=\"border-color:#348eda; font-weight:400; text-decoration:none;"
	    			+ " display:inline-block; margin:0; color:#ffffff; background-color:#348eda; border:solid 1px #348eda; border-radius:2px; "
	    			+ "font-size:14px; padding:12px 45px\">Entrar</a>";
			
			SendEmail sendEmail = new SendEmail("smtp.gmail.com","465");
			sendEmail.sendMail("Fluencia Agora <fluenciaagora@gmail.com>", user.getEmail(), "Fluência Agora", message);
			
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(userReturn.getId())).build();
            logger.debug("Service : Usuario criado.");
            return Response.created(uri).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
            return new FailureResponseBuilder().toResponse(e);
		}
	}
}
