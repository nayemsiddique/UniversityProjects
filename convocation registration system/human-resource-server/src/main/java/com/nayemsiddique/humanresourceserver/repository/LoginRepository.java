package com.nayemsiddique.humanresourceserver.repository;

import com.nayemsiddique.humanresourceserver.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
}
