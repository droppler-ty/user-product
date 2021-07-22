package com.example.dockerKafka.repository;


import com.example.dockerKafka.model.Product;
import com.example.dockerKafka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
