package br.com.fabricio.service;

import java.net.URI;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fabricio.model.Survey;
import br.com.fabricio.model.User;
import br.com.fabricio.repository.SurveyRepository;
import br.com.fabricio.service.SurveyService;
import br.com.fabricio.util.FailureResponseBuilder;
import br.com.fabricio.util.ServiceUtil;
import br.com.fabricio.util.StatusException;

public class DefaultSurveyService implements SurveyService{

    private SurveyRepository surveyRepository;
    private Validator validator;
    private Logger logger = LoggerFactory.getLogger(DefaultSurveyService.class);

    @Inject
    public DefaultSurveyService(SurveyRepository surveyRepository, Validator validator) {
        this.surveyRepository = surveyRepository;
        this.validator = validator;
    }

	@Override
	public Response create(UriInfo uriInfo, String compositeKey, Survey survey) {
		try {
			Set<ConstraintViolation<Survey>> validationErrors = validator.validate(survey);
			if (!validationErrors.isEmpty()) {
				String error = ServiceUtil.getErrors(validationErrors);
	            logger.error(error);
	            return new FailureResponseBuilder().toResponse(new StatusException(Status.BAD_REQUEST.getStatusCode(), error));
	        }
			Survey userReturn = surveyRepository.create(compositeKey, survey);
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(userReturn.getId())).build();
            logger.debug("Service : Pesquisa criada.");
            return Response.created(uri).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
            return new FailureResponseBuilder().toResponse(e);
		}
}

	@Override
	public Response findOne(String compositeKey) {
		try {
			Survey survey = surveyRepository.findOne(compositeKey);
            if (survey == null) {
                logger.error("Service : Usuario não encontrado");
                return new FailureResponseBuilder().toResponse(new StatusException(Status.NOT_FOUND.getStatusCode(), "Service : Pesquisa não encontrado"));
            }
            return Response.ok().entity(survey).build();
		} catch (Exception e) {
			logger.equals(e.getMessage());
			return new FailureResponseBuilder().toResponse(e);
		}
	}	
 }
