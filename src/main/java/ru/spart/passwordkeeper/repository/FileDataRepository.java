package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.repository.model.FileData;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Long> {

    List<FileData> findAll();

    Optional<FileData> findById(Long id);

}
