package ru.spart.appteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.appteka.repository.model.DrugsData;
import ru.spart.appteka.repository.model.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugsDataRepository extends JpaRepository<DrugsData,Long> {

    Optional<DrugsData> findByIdAndUserData(Long id, UserData userData);

    List<DrugsData> findAllByUserData(UserData userData);

    Optional<DrugsData> findById(Long id);
}
