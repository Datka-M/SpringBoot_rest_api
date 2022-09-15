package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import peaksoft.dto.PaginationResponse;
import peaksoft.dto.PersonRequest;
import peaksoft.dto.PersonResponse;
import peaksoft.dto.PersonResponseView;
import peaksoft.entity.Passport;
import peaksoft.entity.Person;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;


    private Person create(PersonRequest request) {
        Person person = new Person();
        person.setFullName(request.getFirstName()+" "+request.getLastName());
        person.setAge(request.getAge());
        person.setPhoneNumber(request.getPhoneNumber());
        person.setEmail(request.getEmail());
        person.setPassword(request.getPassword());
        Passport passport = new Passport();
        passport.setInn(request.getInn());
        passport.setCitizen(request.getCitizen());
        person.setPassport(passport);
        return person;
    }

    private PersonResponse getResponse(Person person) {
        PersonResponse response = new PersonResponse(person.getId(),
                        person.getFullName(),person.getAge(),
                person.getEmail(), person.getPhoneNumber());
//        response.setId(person.getId());
//        response.setFullName(person.getFullName());
//        response.setEmail(person.getEmail());
//        response.setAge(person.getAge());
//        response.setPhoneNumber(person.getPhoneNumber());
//        Passport passport = new Passport();
//        passport.setInn(person.getPassport().getInn());
//        passport.setCitizen(person.getPassport().getCitizen());
        return response;
    }

    public PersonResponse addPerson(PersonRequest request) {
        Person person = create(request);
        return getResponse(personRepository.save(person));
    }

    public PersonResponse getById(Long id) {
        return getResponse(personRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Person with %d id not found",id))));
    }

    public PersonResponse delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Person with %d id not found",id)));
        person.setPassport(null);
        personRepository.delete(person);
        return getResponse(person);
    }

    public PersonResponse update(Long id, PersonRequest personRequest) {
        Person person = personRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("Person with %d id not found",id)));
        Person person1 = saveUpdate(person, personRequest);
        return getResponse(person1);
    }

    public Person saveUpdate(Person person, PersonRequest request) {
        person.setFullName(request.getFirstName()+" "+request.getLastName());
        person.setAge(request.getAge());
        person.setEmail(request.getEmail());
        person.setPassword(request.getPassword());
        person.setPassport(person.getPassport());
        return personRepository.save(person);
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public PersonResponseView personResponseView(String text, int page, int size) {
        PersonResponseView view = new PersonResponseView();
        Pageable pageable = PageRequest.of(page - 1, size);
        view.setPersonResponseList(getResponseList(getListPage(text, pageable)));
        return view;
    }

    private List<PersonResponse> getResponseList(List<Person> personList) {
        List<PersonResponse> personResponses = new ArrayList<>();
        for (Person person : personList) {
            personResponses.add(getResponse(person));
        }
        return personResponses;
    }

    private List<Person> getListPage(String text, Pageable pageable) {
        String string = text == null ? "" : text;
        return personRepository.searchByFirstName(string.toUpperCase(), pageable);
    }

    public List<PersonResponse> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<PersonResponse> personResponses = new ArrayList<>();
        for (Person person:personRepository.findAll(pageable)) {
            personResponses.add(getResponse(person));
        }
        return personResponses;
    }

    public PaginationResponse getPaginationResponse(int page, int size){
        PaginationResponse paginationResponse = new PaginationResponse();
        Pageable pageable = PageRequest.of(page-1,size, Sort.by("fullName"));
        List<PersonResponse> personResponses = new ArrayList<>();
        Page<Person> all = personRepository.findAll(pageable);
        for (Person p:all) {
            personResponses.add(getResponse(p));
        }
        paginationResponse.setPersonResponses(personResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setTotalPage(all.getTotalPages());
        return paginationResponse;
    }





}

