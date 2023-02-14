package top.ss007.springsecuritymvcinspect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import top.ss007.springsecuritymvcinspect.model.enumerate.RoleType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private RoleType roleType;

}
