package ma.yassine.ecommerce_backend.repository;

import ma.yassine.ecommerce_backend.entity.ERole;
import ma.yassine.ecommerce_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author pc
 **/
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
