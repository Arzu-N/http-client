package com.example.demo.controller;

import com.example.demo.dao.entity.Person;
import com.example.demo.dao.repository.PersonRepository;
import com.example.demo.dto.PersonNameAndAge;
import com.example.demo.dto.PersonNameAndAgeProjection;
import com.example.demo.dto.PersonRequestDto;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService personService;
    private final PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {

        return ResponseEntity.ok(personService.getPersons());
    }

    @GetMapping("/userNames")
    public ResponseEntity<List<String>> getUserName() {

        return ResponseEntity.ok(personService.getUserNames());
    }

    @PostMapping("/userName")
    public ResponseEntity<String> getUserName(@RequestParam String name) {
        return ResponseEntity.ok(personService.getUserName(name));
    }

    @GetMapping("/nameAndAge")
    public ResponseEntity<List<Person>> getNameAndAge(@RequestParam String name, @RequestParam Integer age) {
        return ResponseEntity.ok(personService.getNameAndAge(name, age));
    }
    @GetMapping("/ordering")
    public ResponseEntity<List<Person>>getPersonsByOrdering(){
        return ResponseEntity.ok(personService.getPersonsByOrdering());
    }
    @GetMapping("/names")
    public ResponseEntity<List<Person>>getNames(@RequestParam List<String>names){
        return ResponseEntity.ok(personService.getNames(names));
    }
    @GetMapping("/count")
    public ResponseEntity<Integer>countPersons(@RequestParam Integer age){
        return ResponseEntity.ok(personService.countPersons(age));
    }
    @GetMapping("/salary-interval")
    public ResponseEntity<List<Person>>salaryInterval(@RequestParam Double minSalary,
                                                      @RequestParam Double maxSalary){
        return ResponseEntity.ok(personService.salaryInterval(minSalary,maxSalary));
    }
    @GetMapping("/like")
    public ResponseEntity<List<Person>>getNamesLike(@RequestParam String h){
        return ResponseEntity.ok(personService.getNamesLike(h));
    }
    @GetMapping("/email")
    public ResponseEntity<List<Person>>emailIsNull(){
        return ResponseEntity.ok(personService.emailIsNull());
    }
    @GetMapping("/salary")
    public ResponseEntity<Double>sumSalary(){
        return ResponseEntity.ok(personService.sumSalary());
    }
    @GetMapping("/order")
    public ResponseEntity<List<Person>>namesOrder(){
        return ResponseEntity.ok(personService.namesOrder());
    }
    @GetMapping("/name-exists")
    public ResponseEntity<Boolean>nameExists(@RequestParam String name){
        return ResponseEntity.ok(personService.nameExists(name));
    }
    @GetMapping("/persons")
    public ResponseEntity<List<Person>>persons(){
        return ResponseEntity.ok(personService.persons());
    }
    @GetMapping("/name-and-age")
    public ResponseEntity<List<PersonNameAndAge>>nameAndAge(){
        return ResponseEntity.ok(personService.nameAndAge());
    }
    @GetMapping("/name-and-age-param")
    public ResponseEntity<List<PersonNameAndAge>>nameAndAgeParam(@RequestParam String name,@RequestParam Integer age){
        return ResponseEntity.ok(personService.nameAndAgeParam(name,age));
    }
@GetMapping("/mapper")
    public ResponseEntity<List<PersonNameAndAge>>nameAndAgeMapper(@RequestParam String name,@RequestParam Integer age){
        return ResponseEntity.ok(personService.nameAndAgeMapper(name,age));
}

@GetMapping("/projection")
public ResponseEntity<List<PersonNameAndAgeProjection>>nameAndAgeProjection() {
    return ResponseEntity.ok(personService.nameAndAgeProjection());
}

@GetMapping("/findById/{id}")
    public ResponseEntity<Person>getPersonss(@PathVariable Long id){
    return ResponseEntity.ok(personService.getPersonss(id));

}

@GetMapping("/handler/{id}")
    public ResponseEntity<Person>getPersonHandler(@PathVariable Long id){
        return ResponseEntity.ok(personService.getPersonHandler(id));
}

@PostMapping("/post-handler")
    public ResponseEntity<Person>addPerson(@RequestBody Person person){

        return ResponseEntity.status(HttpStatus.CREATED).body( personService.addPersonHandler(person));
}

@PostMapping("/add-person-handler")
    public ResponseEntity<Void>addPerson1(@Valid @RequestBody PersonRequestDto dto){
    personService.addPersonHandler1(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}
}
