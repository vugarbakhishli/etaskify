package az.bakhishli.user.ms.repository;

import az.bakhishli.user.ms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.id IN(:idList)")
//    List<User> findAllById(@Param("idList") List<Long> idList);

//    @Override
//    @EntityGraph(attributePaths = {"authorities"})
//    Optional<User> findById(Long id);



}
