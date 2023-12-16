
package FinalEE.Repository;

import FinalEE.Entity.Permission;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

    Permission findByLevel(int level);

    List<Permission> findAll(Sort sort);
    List<Permission> findAllByNameContains(String name,Sort sort);

    @Query("select p from Permission p where p.id=?1 or p.id IS NULL")
    Permission findByID(Integer id);

}
