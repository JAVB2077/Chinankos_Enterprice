package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chinanko.chinanko.model.AddressEvent;

public interface AddressEventRepository extends JpaRepository<AddressEvent, Integer> {

}
