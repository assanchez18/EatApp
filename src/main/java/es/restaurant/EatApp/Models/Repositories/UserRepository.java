package es.restaurant.EatApp.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.restaurant.EatApp.Models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM USER u WHERE u.Email=(:email) AND u.Password=(:password)", nativeQuery = true)
	User findUserByNameAndPassword(@Param("email") String email, @Param("password") String password);
	
}