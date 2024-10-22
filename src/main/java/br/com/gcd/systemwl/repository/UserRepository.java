package br.com.gcd.systemwl.repository;

import br.com.gcd.systemwl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
