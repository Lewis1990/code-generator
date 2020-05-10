import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUser {

    private Integer id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private Date createTime;

    private Date updateTime;

    private Date loginTime;

    private Integer status;

}