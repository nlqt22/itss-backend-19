package com.pinkieyun.fitnesscenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pinkieyun.fitnesscenter.entity.Person;
import com.pinkieyun.fitnesscenter.entity.dto.PersonDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonFormDTO;
import com.pinkieyun.fitnesscenter.service.criteria.PersonCriteria;

public interface PersonService {

    public Page<PersonDTO> findAllByCriteria(PersonCriteria criteria, Pageable pageable);
    
    public Page<PersonDTO> findByOrganizationIdAndRoleSale(Integer organizationId, Pageable pageable);
    
    public Page<PersonDTO> findByOrganizationIdAndRolePT(Integer organizationId, Pageable pageable);

    public Page<PersonDTO> findByOrganizationIdAndRoleMember(Integer organizationId, Pageable pageable);

    public Optional<Person> findOne(Integer id);

    public Optional<PersonDTO> findOneDTO(Integer id);

    public PersonDTO toDTO(Person person);
    
    public Person save(Person person);

    public Person update (Integer id, Person person);

    public Optional<PersonDTO> update(Integer id, PersonFormDTO personFormDTO);
}
