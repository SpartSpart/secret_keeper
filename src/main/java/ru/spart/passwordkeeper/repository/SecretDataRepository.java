package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.repository.model.SecretData;

@Repository
public interface SecretDataRepository extends JpaRepository<SecretData,Long> {

}
