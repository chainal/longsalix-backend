package com.longsalix.web.dao;

import com.longsalix.web.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query(value = "select t from User t where t.name=?1 ")
    List<User> findUserByName(String username);
}
