public interface AdminUserService {

    public List<AdminUserDto> query(AdminUserDto adminUser);

    public int insert(AdminUserDto adminUser);

    public int delete(AdminUserDto adminUser);

    public int update(AdminUserDto adminUser);

}