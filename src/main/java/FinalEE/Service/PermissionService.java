
package FinalEE.Service;

import FinalEE.Entity.Permission;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PermissionService {
    
    boolean create(Permission permission);
    boolean deleteByID(int id);
    Permission getPermission(int id);
    List<Permission> getAllPermission();

    Permission findByLevel(int level);

    List<Permission> findAll(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Permission> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder);

    Permission findByID(int permissionID);
    
}
