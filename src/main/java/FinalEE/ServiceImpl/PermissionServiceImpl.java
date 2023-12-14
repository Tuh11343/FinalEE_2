package FinalEE.ServiceImpl;

import FinalEE.Entity.Permission;
import FinalEE.Repository.PermissionRepository;
import FinalEE.Service.PermissionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements PermissionService{
    
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionServiceImpl() {
        
    }
    
    
    
    @Override
    public boolean create(Permission permission) {
        try {
            // Kiểm tra xem permission có tồn tại trong database hay không
            Optional<Permission> existingPermission = permissionRepository.findById(permission.getId());

            // Lưu permission và kiểm tra kết quả
            permissionRepository.save(permission);
            if (existingPermission.isPresent()) {
                System.out.println("Cap nhat thanh cong permission:" + permission.getId());
            } else {
                System.out.println("Them thanh cong permission:" + permission.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (permissionRepository.existsById(id)) {
            //permissionRepository.deleteById(permissionID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }
    

    @Override
    public Permission getPermission(int id) {
        try{
            return permissionRepository.findById(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findByLevel(int level) {
        return permissionRepository.findByLevel(level);
    }

    @Override
    public List<Permission> findAll(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return permissionRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Permission> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return permissionRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Permission findByID(int permissionID) {
        try{
            return permissionRepository.findById(permissionID);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
