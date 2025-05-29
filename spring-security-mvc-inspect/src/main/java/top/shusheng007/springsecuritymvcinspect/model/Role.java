package top.shusheng007.springsecuritymvcinspect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.shusheng007.springsecuritymvcinspect.model.enumerate.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private RoleType roleType;

}
