package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chinanko.chinanko.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer>{

}
