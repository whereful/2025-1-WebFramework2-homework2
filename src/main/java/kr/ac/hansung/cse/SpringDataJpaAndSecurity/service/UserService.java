package kr.ac.hansung.cse.SpringDataJpaAndSecurity.service;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyUser;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.Product;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.repository.ProductRepository;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<MyUser> listAll() {
        return userRepository.findAll();
    }


}