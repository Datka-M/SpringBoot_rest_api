package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import peaksoft.dto.PersonResponse;
import peaksoft.entity.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query("select p from Person p where upper(p.fullName)like concat('%',:text,'%')" +
            "or upper(p.email)like concat('%',:text,'%')")
    List<Person> searchByFirstName(@Param("text")String text, Pageable pageable );

    @Override
    Page<Person> findAll(Pageable pageable);



}
