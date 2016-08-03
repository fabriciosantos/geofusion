package br.com.fabricio.repository;

import br.com.fabricio.model.User;

public interface UserRepository {

    User create(User user) throws Exception;
    
    User update(User user) throws Exception;
}
