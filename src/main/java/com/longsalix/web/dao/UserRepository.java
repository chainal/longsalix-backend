package com.longsalix.web.dao;

import com.longsalix.web.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
