package com.example.demo.mapper;

import com.example.demo.dao.entity.Person;
import com.example.demo.dto.PersonNameAndAge;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PersonMapper {
    PersonNameAndAge toDto(Person person);
    List<PersonNameAndAge>toDtoList(List<Person>list);
}
