package FinalEE.ServiceImpl;

import FinalEE.Entity.Permission;
import FinalEE.Repository.PermissionRepository;
import FinalEE.Service.PermissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements PermissionService{
    
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionServiceImpl() {
        
    }
    
    
    
    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
        
    }

    @Override
    public Permission update(Permission permission, int id) {
        
        Permission permissionGet=permissionRepository.findById(id).orElse(null);
        
        return permissionRepository.save(permissionGet);
    }

    @Override
    public void delete(Permission permission) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Permission getPermission(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionRepository.findAll();
    }
    
    
    
}
