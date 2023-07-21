package com.pinkieyun.fitnesscenter.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pinkieyun.fitnesscenter.Constants.PERSON;
import com.pinkieyun.fitnesscenter.entity.Person;
import com.pinkieyun.fitnesscenter.entity.dto.AccountDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonDTO;
import com.pinkieyun.fitnesscenter.entity.dto.PersonFormDTO;
import com.pinkieyun.fitnesscenter.repository.PersonRepository;
import com.pinkieyun.fitnesscenter.service.AccountService;
import com.pinkieyun.fitnesscenter.service.PersonService;
import com.pinkieyun.fitnesscenter.service.QueryService;
import com.pinkieyun.fitnesscenter.service.criteria.PersonCriteria;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modalMapper;
    private final QueryService<Person> queryService;
    private final AccountService accountService;

    @Override
    public PersonDTO toDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();

        personDTO.setId(person.getId());
        personDTO.setFullName(person.getFullName());
        personDTO.setDob(person.getDob());
        personDTO.setIdentityCard(person.getIdentityCard());
        personDTO.setPhone(person.getPhone());
        personDTO.setAccount(new AccountDTO(person.getAccount().getId(), 
                                            person.getAccount().getEmail(),
                                            person.getAccount().getPassword(), 
                                            person.getAccount().getRole(),
                                            person.getAccount().getOrganization(),
                                            person.getAccount().isActive()));

        return personDTO;
    }

    private Specification<Person> buildSpecification(PersonCriteria criteria) {
        Specification<Person> specification = Specification.where(null);

        if(criteria.getId() != null) {
            specification = specification.and(queryService.buildIntegerFilter(PERSON.ID, criteria.getId()));
        }
        if(criteria.getFullName() != null) {
            specification = specification.and(queryService.buildStringFilter(PERSON.FULLNAME, criteria.getFullName()));
        }

        return specification;
    }

    @Override
    public Optional<Person> findOne(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<PersonDTO> findOneDTO(Integer id) {
        return personRepository.findById(id).map(this::toDTO);
    }

    @Override
    public Page<PersonDTO> findAllByCriteria(PersonCriteria criteria, Pageable pageable) {
        Specification<Person> spec = buildSpecification(criteria);
        Page<Person> page = personRepository.findAll(spec, pageable);
        List<PersonDTO> list = page.getContent().stream().map(person -> toDTO(person)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Page<PersonDTO> findAllStaffSaleActive(Pageable pageable) {
        Page<Person> page = personRepository.findByRoleSale(accountService.getCurrentOrganization().getId(), pageable);
        List<PersonDTO> list = page.getContent().stream().map(person -> toDTO(person)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Page<PersonDTO> findAllStaffPTActive(Pageable pageable) {
        Page<Person> page = personRepository.findByRolePT(accountService.getCurrentOrganization().getId(), pageable);
        List<PersonDTO> list = page.getContent().stream().map(person -> toDTO(person)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Page<PersonDTO> findAllMemberActive(Pageable pageable) {
        Page<Person> page = personRepository.findByRoleMember(accountService.getCurrentOrganization().getId(), pageable);
        List<PersonDTO> list = page.getContent().stream().map(person -> toDTO(person)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public Person update(Integer id, Person person) {
        return findOne(id).map(p -> {
            person.setId(id);
            return personRepository.save(person);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public Optional<PersonDTO> update(Integer id, PersonFormDTO personFormDTO) {
        Person person = modalMapper.map(personFormDTO, Person.class);
        update(id, person);
        return Optional.of(toDTO(person));
    }

    @Override
    @Transactional
    public Optional<PersonDTO> changeActive(Integer id) {
        Person person = findOne(id).get();
        accountService.changeActive(person.getAccount().getId());
        return Optional.of(toDTO(person));
    }

}
