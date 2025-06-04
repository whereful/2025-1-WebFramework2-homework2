package kr.ac.hansung.cse.SpringDataJpaAndSecurity.service;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyRole;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyUser;

import java.util.List;

public interface RegistrationService {
    MyUser createUser(MyUser user, List<MyRole> userRoles);

    boolean checkEmailExists(String email);

    MyRole findByRolename(String rolename);
}
