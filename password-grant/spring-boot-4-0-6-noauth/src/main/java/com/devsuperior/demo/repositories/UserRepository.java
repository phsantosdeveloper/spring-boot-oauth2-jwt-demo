package com.devsuperior.demo.repositories;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT u.email AS username, u.password, r.id AS roleId, r.authority " +
            "FROM tb_user u " +
            "INNER JOIN tb_user_role ur ON u.id = ur.user_id " +
            "INNER JOIN tb_role r ON r.id = ur.role_id " +
            "WHERE u.email = :username")
    List<UserDetailsProjection> searchUserAndRolesByUsername(String username);
}
