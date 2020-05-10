
//uncap_first、cap_first:首字母大寫和小寫
public class PermissionServiceImpl {

    private PermissionDao permissionDao;

    public PermissionDto get(Integer id) {
        Permission permission = new Permission();
        permission.setId(id);
        List<PermissionDto> list = permissionDao.query(permission);
        if (null != list && list.size() > 0) {
            return BeanMapper.toObj(list.get(0), PermissionDto.class);
        }
        return null;
    }

    public List<PermissionDto> query(PermissionDto permissionDto) {
        Permission permission = BeanMapper.toObj(permissionDto, Permission.class);
        List<PermissionDto> list = permissionDao.query(permission);
        return BeanMapper.toList(list, PermissionDto.class);
    }

    public int insert(PermissionDto permissionDto) {
        Permission permission = BeanMapper.toObj(permissionDto, Permission.class);
        return permissionDao.insert(permission);
    }

    public int delete(PermissionDto permissionDto) {
        Permission permission = BeanMapper.toObj(permissionDto, Permission.class);
        return permissionDao.delete(permission);
    }

    public int update(PermissionDto permissionDto) {
        Permission permission = BeanMapper.toObj(permissionDto, Permission.class);
        return permissionDao.update(permission);
    }

}