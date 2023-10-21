
package FinalEE.Service;

import FinalEE.Entity.Permission;
import java.util.List;

public interface PermissionService {
    
    Permission create(Permission item);
    Permission update(Permission item,int id);
    void delete(Permission item);
    Permission getPermission(int id);
    List<Permission> getAllPermission();
    
}
