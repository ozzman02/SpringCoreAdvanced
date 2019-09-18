package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
