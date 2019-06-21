package co.descubra.descubraapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.core.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	@Query(value = "select administrator_id from administrators where user_id = :id", nativeQuery = true)
	int findByUser(@Param("id") Long id);
	
}
