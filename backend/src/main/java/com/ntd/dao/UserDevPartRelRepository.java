package com.ntd.dao;

import com.ntd.entity.UserDevPartRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDevPartRelRepository extends JpaRepository<UserDevPartRelation,Long> {
}
