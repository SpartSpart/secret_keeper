package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.repository.model.UserData;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository <UserData,Long> {


    Optional<UserData> findByUserLogin(String login);


}
