package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.UserService;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		return userRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}

}
