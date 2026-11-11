package dev.antoniogrillo.primo.repository;

import dev.antoniogrillo.primo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Query(nativeQuery = true,value = "select * from Persona where email =:email and password = :password")
    Optional<Persona> loginNativa(String email,String password);

    @Query("select p from Persona p where p.email = :email and p.password=:password")
    Optional<Persona> loginJPQL(String email,String password);

    Optional<Persona> findByEmailAndPassword(String email,String password);


    Optional<Persona> findByEmail(String email);

    List<Persona> findAllByABitazione(String aBitazione);


}
