public interface RoleService {

    public List<RoleDto> query(RoleDto role);

    public int insert(RoleDto role);

    public int delete(RoleDto role);

    public int update(RoleDto role);

}