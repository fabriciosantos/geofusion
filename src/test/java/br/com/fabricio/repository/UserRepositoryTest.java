package br.com.fabricio.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import br.com.fabricio.model.User;
import br.com.fabricio.repository.DefaultUserRepository;

public class UserRepositoryTest {

    private static EntityManager entityManager;
    private static DefaultUserRepository defaultUserRepository;
    private User user;
    
    @BeforeClass
    public static void mockClass(){
        entityManager = mock(EntityManager.class);
        defaultUserRepository = new DefaultUserRepository();
        defaultUserRepository.entityManager = entityManager;
    }

    @Before
    public void init() {
    	user = new User();
    	user.setId(1);
    	user.setName("João");
    	user.setEmail("email@test.com");
    }

    @Test
    public void create() throws Exception {
    	User user = new User();
    	user.setId(2);
    	user.setName("João");
    	user.setEmail("emaildeteste@test.com");
    
    	List<User> list = null;
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(list);
    	User persistedUser = defaultUserRepository.create(user);
        Mockito.verify(entityManager).persist(user);
        assertEquals(user, persistedUser);
    }
    
    @Test(expected=Exception.class)
    public void createException() throws Exception {
    	List<User> list = null;
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(null);
        when(query.getResultList()).thenReturn(list);
    	User persistedUser = defaultUserRepository.create(user);
        Mockito.verify(entityManager).persist(user);
        assertEquals(user, persistedUser);
    }
    
    @Test
    public void createEmailAlreadyExist() throws Exception {
    	List<User> list = new ArrayList<>();
    	list.add(user);
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(list);
    	defaultUserRepository.create(user);
    }

    @Test
    public void findOne() throws Exception {
    	User userFind = new User();
    	userFind.setId(5);
    	userFind.setName("Antonio");
    	userFind.setEmail("curso@teste.com");
    	userFind.setCompositeKey("10298372");
    	
    	
    	when(entityManager.find(User.class, userFind.getId())).thenReturn(userFind);
        User userReturn = defaultUserRepository.findOne(userFind.getId());
    	assertEquals(userFind, userReturn);
    }

    @Test(expected=Exception.class)
    public void findOneNotFound() throws Exception {
    	Query query = mock(Query.class);
    	when(entityManager.createQuery(Matchers.anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(null);
        User userReturn = defaultUserRepository.findOne(user.getId());
    	assertEquals(user, userReturn);
    }
    
    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId(4);
        user.setName("João");
        user.setEmail("de@test.com");
                
    	when(entityManager.find(User.class, user.getId())).thenReturn(user);
        User userReturn = defaultUserRepository.update(user);
        assertEquals(user, userReturn);
        
        }

    @Test(expected=Exception.class)
    public void updateNotFound() throws Exception {
    	when(entityManager.find(User.class, user.getId())).thenReturn(null);
        User userReturn = defaultUserRepository.update(user);
        assertEquals(user, userReturn);
        
    }
}
