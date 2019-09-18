package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.security.Role;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.services.RoleService;

@Service
@Profile("springdatajpa")
public class RoleServiceRepoImpl implements RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<?> listAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	@Override
	public Role getById(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role saveOrUpdate(Role domainObject) {
		return roleRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		roleRepository.delete(id);
	}

}
