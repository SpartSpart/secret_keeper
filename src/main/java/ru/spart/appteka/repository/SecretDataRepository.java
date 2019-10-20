package ru.spart.appteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.appteka.repository.model.SecretData;
import ru.spart.appteka.repository.model.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretDataRepository extends JpaRepository<SecretData,Long> {

    Optional<SecretData> findByIdAndUserData(Long id, UserData userData);

    List<SecretData> findAllByUserData(UserData userData);

    Optional<SecretData> findById(Long id);
}
