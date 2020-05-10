public interface RoleDao {

    public List<Role> query(Role role);

    public int insert(Role role);

    public int delete(Role role);

    public int update(Role role);

}