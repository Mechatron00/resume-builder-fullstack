package com.resumebuilder.resumebuilder.repository;

import com.resumebuilder.resumebuilder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String>
{
    Optional<User> findByUserEmailAndConfirmPassword(String userEmail, String confirmPassword);
}
