
package FinalEE.Repository;

import FinalEE.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

    Permission findByLevel(int level);

}
