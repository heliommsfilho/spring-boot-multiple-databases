package br.com.devlog.springbootmultipledatabases.resource;

import br.com.devlog.springbootmultipledatabases.db.h2.model.Address;
import br.com.devlog.springbootmultipledatabases.db.h2.repository.AddressRepository;
import br.com.devlog.springbootmultipledatabases.db.mysql.model.People;
import br.com.devlog.springbootmultipledatabases.db.mysql.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Resource which respond to HTTP requests returning data.
 *
 * Repository: <a>https://github.com/heliomf-dev/spring-boot-multiple-databases</a>
 * @author Hélio Márcio Filho <My GitHub: <a>https://github.com/heliomf-dev</a>>
 * @version 1.0
 * @since 1.0
 * */
@RestController
public class TestResource {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(value = "/h2")
    public ResponseEntity<List<Address>> testH2() {
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @GetMapping(value = "/mysql")
    public ResponseEntity<List<People>> testMysql() {
        return ResponseEntity.ok(peopleRepository.findAll());
    }
}
