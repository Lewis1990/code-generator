
//uncap_first、cap_first:首字母大寫和小寫
public class RoleServiceImpl {

    private RoleDao roleDao;

    public RoleDto get(Integer id) {
        Role role = new Role();
        role.setId(id);
        List<RoleDto> list = roleDao.query(role);
        if (null != list && list.size() > 0) {
            return BeanMapper.toObj(list.get(0), RoleDto.class);
        }
        return null;
    }

    public List<RoleDto> query(RoleDto roleDto) {
        Role role = BeanMapper.toObj(roleDto, Role.class);
        List<RoleDto> list = roleDao.query(role);
        return BeanMapper.toList(list, RoleDto.class);
    }

    public int insert(RoleDto roleDto) {
        Role role = BeanMapper.toObj(roleDto, Role.class);
        return roleDao.insert(role);
    }

    public int delete(RoleDto roleDto) {
        Role role = BeanMapper.toObj(roleDto, Role.class);
        return roleDao.delete(role);
    }

    public int update(RoleDto roleDto) {
        Role role = BeanMapper.toObj(roleDto, Role.class);
        return roleDao.update(role);
    }

}