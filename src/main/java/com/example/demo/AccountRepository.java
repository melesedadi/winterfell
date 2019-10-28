package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    /*@Query("select amount+change as newamount from transact")
    List<Account> findByChange(float newamount);*/
}