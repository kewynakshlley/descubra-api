package co.descubra.descubraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.models.Administrator;

@Repository
public interface AdministratorService extends JpaRepository<Administrator, Long>{

}
