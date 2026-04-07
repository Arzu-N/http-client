package com.example.demo.service;

import com.example.demo.dao.entity.Person;
import com.example.demo.dao.repository.PersonRepository;
import com.example.demo.dto.PersonNameAndAge;
import com.example.demo.dto.PersonNameAndAgeProjection;
import com.example.demo.dto.PersonRequestDto;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.PersonMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<Person>getPersons(){
        return personRepository.getAll();
    }

    public Person getPersonss(Long id){
        return personRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("user not found with id: "+id));
    }

    public List<String>getUserNames(){
        return personRepository.getUserNames();
    }
    public String getUserName(String name){
        return personRepository.getUserName(name).orElseThrow(()->
                new RuntimeException("UserName not found"));
    }
    public List<Person>getNameAndAge(String name,Integer age){
        return personRepository.getNameAndAge(name,age);
    }
    public List<Person>getPersonsByOrdering(){
        return personRepository.getPersonsByOrdering();
    }
    public List<Person>getNames(List<String>names){
        return personRepository.getNames(names);
    }
    public Integer countPersons(Integer age){
        return personRepository.countPersons(age);
    }
    public List<Person>salaryInterval(Double minSalary,Double maxSalary){
       return  personRepository.salaryInterval(minSalary,maxSalary);
    }
    public List<Person>getNamesLike(String h){
        return personRepository.getNamesLike(h);
    }
    public List<Person>emailIsNull(){
        return personRepository.emailIsNull();
    }
    public Double sumSalary(){
        return personRepository.sumSalary();
    }
    public List<Person>namesOrder(){
        return personRepository.namesOrder();
    }
    public Boolean nameExists(String name){
        return personRepository.nameExists(name);
    }
    public List<Person>persons(){
        return personRepository.persons();
    }
    public List<PersonNameAndAge>nameAndAge(){
        return personRepository.nameAndAge();
    }
    public List<PersonNameAndAge>nameAndAgeParam(String name,Integer age){
        return personRepository.nameAndAgeParam(name,age);
    }
    public List<PersonNameAndAge>nameAndAgeMapper(String name,Integer age){
        return personMapper.toDtoList(personRepository.nameAndAgeMapper(name,age));
    }
    public List<PersonNameAndAgeProjection>nameAndAgeProjection(){
        return personRepository.nameAndAgeProjection();
    }


    public Person getPersonHandler(Long id){
        return personRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("user not found with id: "+id));

    }

    public Person addPersonHandler(Person person){
        Optional<Person> person1 = personRepository.findByUserName(person.getUserName());
        if(person1.isPresent())
            throw new UserAlreadyExistException("User already exist");
        else{return personRepository.save(person);}
    }

    public void addPersonHandler1(PersonRequestDto dto){
        Person person = new Person();
        person.setUserName(dto.getUserName());
        person.setEmail(dto.getEmail());
        person.setAge(dto.getAge());
        person.setSalary(dto.getSalary());
        person.setBirthDay(dto.getBirthDay());
        personRepository.save(person);
    }
}
