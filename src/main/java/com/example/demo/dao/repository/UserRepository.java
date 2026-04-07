package com.example.demo.dao.repository;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value="select * from usersss",nativeQuery = true)
    List<User> users();
    @Query(value="select * from usersss u where u.user_name=:name",nativeQuery = true)
    List<User>users1(String name);
    @Query(value="select u.id,u.user_name,r.id as roleId,r.role_name as roleName from usersss u join role r on u.id=r.user_id where u.user_name=:name",nativeQuery = true)
            List<Object[]>users2(String name);
    @Transactional
    @Modifying
    @Query(value = "insert into usersss  (user_name) values(:name)",nativeQuery = true)
    void addUser(String name);
    @Transactional
    @Modifying
    @Query(value = "update usersss set user_name=:name where id=:id",nativeQuery = true)
    void updateUser(String name,Integer id);
    @Transactional
    @Modifying
    @Query(value = "delete from usersss where id=:id",nativeQuery = true)
    void deleteUser(Long id);
}
