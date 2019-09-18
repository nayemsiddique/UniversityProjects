package com.nayemsiddique.humanresourceserver.repository;

import com.nayemsiddique.humanresourceserver.model.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTokenRepository extends JpaRepository<LoginToken, String> {
}
