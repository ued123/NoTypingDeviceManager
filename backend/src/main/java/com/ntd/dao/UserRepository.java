package com.ntd.dao;

import com.ntd.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName (String uesrName);
}