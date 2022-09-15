package peaksoft.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.PersonRequest;
import peaksoft.dto.PersonResponse;
import peaksoft.dto.PersonResponseView;
import peaksoft.entity.Person;
import peaksoft.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping()
    public PersonResponse save(@RequestBody @Valid PersonRequest request){
        return service.addPerson(request);
    }

    @GetMapping("{id}")
    public PersonResponse getById(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("{id}")
    public PersonResponse delete(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("{id}")
    public PersonResponse update(@PathVariable Long id, @RequestBody PersonRequest request){
        return service.update(id,request);
    }

    @GetMapping()
    public List<Person> getAll(){
        return service.getAllPeople();
    }

    @GetMapping("/getAll")
    public PersonResponseView getAllPagination(@RequestParam(name = "text",required = false)String text,
                                               @RequestParam int page,
                                               @RequestParam int size){
        return service.personResponseView(text, page, size);
    }

    @GetMapping("/pagination")
    public List<PersonResponse> getPagination(@RequestParam int page,
                                              @RequestParam int size){
        return service.getPagination(page,size);
    }

}
