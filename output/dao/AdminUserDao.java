public interface AdminUserDao {

    public List<AdminUser> query(AdminUser adminUser);

    public int insert(AdminUser adminUser);

    public int delete(AdminUser adminUser);

    public int update(AdminUser adminUser);

}