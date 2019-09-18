package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
