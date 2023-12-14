
package FinalEE.Repository;

import FinalEE.Entity.Permission;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

    Permission findByLevel(int level);

    List<Permission> findAll(Sort sort);
    List<Permission> findAllByNameContains(String name,Sort sort);

    Permission findById(int id);

}
