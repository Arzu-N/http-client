package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Person;
import com.example.demo.dao.entity.User;
import com.example.demo.dto.PersonNameAndAge;
import com.example.demo.dto.PersonNameAndAgeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p")
    List<Person> getAll();

    @Query("SELECT p.userName FROM Person p")
    List<String> getUserNames();

    @Query("select p.userName from Person p where p.userName=:userName")
    Optional<String> getUserName(@Param("userName") String name);

    @Query("select p from Person p where p.userName=:userName and p.age=:age")
    List<Person> getNameAndAge(@Param("userName") String name, @Param("age") Integer age);

    @Query("select p from Person p order by p.salary desc")
    List<Person> getPersonsByOrdering();

    @Query("select p from Person p where p.userName in :list")
    List<Person> getNames(@Param("list") List<String> names);

    @Query("select count(p) from Person p where p.age>:age")
    Integer countPersons(Integer age);

    @Query("select p from Person p where p.salary between :minSalary and :maxSalary")
    List<Person> salaryInterval(Double minSalary, double maxSalary);

    @Query("select p from Person p where p.userName ilike concat(:h,'%') ")
    List<Person> getNamesLike(String h);

    @Query("select p from Person p where p.email is null")
    List<Person> emailIsNull();

    @Query("select sum(p.salary) from Person p ")
    Double sumSalary();

    @Query("select p from Person p order by p.userName desc")
    List<Person> namesOrder();
    @Query("select count(p)>0 from Person p where p.userName=:name")
    Boolean nameExists(String name);
    @Query("select p from Person p where p.birthDay<current_date")
    List<Person>persons();
    @Query("select new com.example.demo.dto.PersonNameAndAge(p.userName,p.age) from Person p")
    List<PersonNameAndAge>nameAndAge();
    @Query("select new com.example.demo.dto.PersonNameAndAge(p.userName,p.age) from Person p where p.userName=:userName and p.age= :age")
    List<PersonNameAndAge>nameAndAgeParam(@Param("userName")String name,Integer age);
@Query("select p from Person p where p.userName=:name  and p.age=:age")
List<Person>nameAndAgeMapper(@RequestParam("name")String name,Integer age);
@Query("select p.userName as userName,p.age as age from Person p ")
    List<PersonNameAndAgeProjection>nameAndAgeProjection();

    Optional<Person> findByUserName(String userName);
}

