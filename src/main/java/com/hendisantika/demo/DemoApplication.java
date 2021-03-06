package com.hendisantika.demo;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/1/17
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
@RestController
public class DemoApplication {
    @Autowired
    private PersonRepository repository;

    @RequestMapping("/")
    public String home() {
        Person p = this.repository.findAll().iterator().next();
        return "Hello " + p.getName() + "!";
    }

    @PostMapping("/person")
    public Person person(@RequestBody Person person) {
        System.out.println("Person : " + person);
        return repository.save(person);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Repository
interface PersonRepository extends CrudRepository<Person, Long> {

}

@Entity
class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName
                + "]";
    }
    
    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}

