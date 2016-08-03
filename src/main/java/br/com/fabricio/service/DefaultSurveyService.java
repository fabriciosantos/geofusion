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

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;
import br.com.fabricio.repository.SurveyRepository;
import br.com.fabricio.repository.UserRepository;
import br.com.fabricio.util.FailureResponseBuilder;
import br.com.fabricio.util.ServiceUtil;
import br.com.fabricio.util.StatusException;

public class DefaultSurveyService implements SurveyService{

    private SurveyRepository surveyRepository;
    private UserRepository userRepository;
    private Validator validator;
    private Logger logger = LoggerFactory.getLogger(DefaultSurveyService.class);

    @Inject
    public DefaultSurveyService(SurveyRepository surveyRepository, UserRepository userRepository, Validator validator) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    /**
	 * Serviço responsavel para inserir uma Survey.
	 * @param pessoa entidade que sera inserida
	 * @return 201 created, ou 500 internal server error.
	 * @throws Exception
	 */
	@Override
	public Response create(UriInfo uriInfo, String compositeKey, Survey survey) {
		try {
			Set<ConstraintViolation<Survey>> validationErrors = validator.validate(survey);
			if (!validationErrors.isEmpty()) {
				String error = ServiceUtil.getErrors(validationErrors);
	            logger.error(error);
	            return new FailureResponseBuilder().toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), error));
	        }
			User userReturn = userRepository.update(survey.getUser());
			survey.setUser(userReturn);
			Survey surveyReturn = surveyRepository.create(survey.getUser().getCompositeKey(), survey);
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(surveyReturn.getId())).build();
            logger.debug("Service : Pesquisa criada.");
            return Response.created(uri).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
            return new FailureResponseBuilder().toResponse(e);
		}
	}
	
	/**
	 * Serviço responsavel por verificar se se existe uma Survey com User que contenha a compositeKey referenciada.
	 * @param String compositeKey
	  * @return 200 , 404 se a Survey não for encontrada ou 500 internal server error
	 * @throws Exception
	 */
	@Override
	public Response verify(String compositeKey) {
		try {
		     if (surveyRepository.verify(compositeKey)) {
                logger.error("Service : Pesquisa já foi realizada por esse email");
                return new FailureResponseBuilder().toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), "Service : Pesquisa já foi realizada por esse email"));
            }
            return Response.ok().build();
		} catch (Exception e) {
			logger.equals(e.getMessage());
			return new FailureResponseBuilder().toResponse(e);
		}
	}
}