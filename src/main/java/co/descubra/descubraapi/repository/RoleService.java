package co.descubra.descubraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.descubra.descubraapi.models.Role;

@Repository
public interface RoleService extends JpaRepository<Role, Long>{

}
