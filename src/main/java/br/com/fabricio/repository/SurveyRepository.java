package br.com.fabricio.repository;

import br.com.fabricio.model.Survey;

public interface SurveyRepository {

    Survey create(String compositeKey,Survey survey) throws Exception;

    Survey findOne(String compositeKey) throws Exception;
}
