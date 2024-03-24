package com.buddywindow.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buddywindow.auth.entity.User;

public interface IUserRepository extends MongoRepository<User, String> {

}
