package kr.ac.hansung.cse.SpringDataJpaAndSecurity.repository;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer>
{
    Optional<MyUser> findByEmail(String email);
}