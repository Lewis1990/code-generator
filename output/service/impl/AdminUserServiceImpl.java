
//uncap_first、cap_first:首字母大寫和小寫
public class AdminUserServiceImpl {

    private AdminUserDao adminUserDao;

    public AdminUserDto get(Integer id) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        List<AdminUserDto> list = adminUserDao.query(adminUser);
        if (null != list && list.size() > 0) {
            return BeanMapper.toObj(list.get(0), AdminUserDto.class);
        }
        return null;
    }

    public List<AdminUserDto> query(AdminUserDto adminUserDto) {
        AdminUser adminUser = BeanMapper.toObj(adminUserDto, AdminUser.class);
        List<AdminUserDto> list = adminUserDao.query(adminUser);
        return BeanMapper.toList(list, AdminUserDto.class);
    }

    public int insert(AdminUserDto adminUserDto) {
        AdminUser adminUser = BeanMapper.toObj(adminUserDto, AdminUser.class);
        return adminUserDao.insert(adminUser);
    }

    public int delete(AdminUserDto adminUserDto) {
        AdminUser adminUser = BeanMapper.toObj(adminUserDto, AdminUser.class);
        return adminUserDao.delete(adminUser);
    }

    public int update(AdminUserDto adminUserDto) {
        AdminUser adminUser = BeanMapper.toObj(adminUserDto, AdminUser.class);
        return adminUserDao.update(adminUser);
    }

}