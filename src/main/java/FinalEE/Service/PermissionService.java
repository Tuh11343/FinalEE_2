
package FinalEE.Service;

import FinalEE.Entity.Permission;
import java.util.List;

public interface PermissionService {
    
    boolean create(Permission permission);
    boolean deleteByID(int id);
    Permission getPermission(int id);
    List<Permission> getAllPermission();

    Permission findByLevel(int level);
    
}
