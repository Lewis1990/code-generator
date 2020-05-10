public interface PermissionDao {

    public List<Permission> query(Permission permission);

    public int insert(Permission permission);

    public int delete(Permission permission);

    public int update(Permission permission);

}