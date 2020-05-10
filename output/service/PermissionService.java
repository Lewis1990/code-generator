public interface PermissionService {

    public List<PermissionDto> query(PermissionDto permission);

    public int insert(PermissionDto permission);

    public int delete(PermissionDto permission);

    public int update(PermissionDto permission);

}