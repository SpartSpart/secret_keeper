package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.repository.model.NoteData;
import ru.spart.passwordkeeper.repository.model.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteDataRepository extends JpaRepository<NoteData,Long> {

    Optional<NoteData> findByIdAndUserData(Long id, UserData userData);

    List<NoteData> findAllByUserData(UserData userData);

    Optional<NoteData> findById(Long id);
}
