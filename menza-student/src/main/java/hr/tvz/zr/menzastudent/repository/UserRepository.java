package hr.tvz.zr.menzastudent.repository;

import hr.tvz.zr.menzastudent.model.Role;
import hr.tvz.zr.menzastudent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    User findByRole(Role role);

    Optional<User> findUserByUsername(String username);




}
