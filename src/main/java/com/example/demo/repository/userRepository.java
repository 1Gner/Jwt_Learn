package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {
    @Query("SELECT e FROM user e JOIN FETCH e.roles WHERE e.username = (:username)")
    public user findyUsername(@Param("username") String username);

    boolean existsByUsername(String username);

}
