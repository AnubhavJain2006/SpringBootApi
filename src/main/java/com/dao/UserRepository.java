package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
