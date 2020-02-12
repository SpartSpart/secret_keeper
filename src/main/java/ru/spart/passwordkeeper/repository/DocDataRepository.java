package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.repository.model.DocData;
import ru.spart.passwordkeeper.repository.model.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocDataRepository extends JpaRepository<DocData,Long> {

    Optional<DocData> findByIdAndUserData(Long id, UserData userData);

    List<DocData> findAllByUserData(UserData userData);

    Optional<DocData> findById(Long id);
}
