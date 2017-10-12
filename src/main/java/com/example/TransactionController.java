package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionRepository repository;

    @Autowired
    public TransactionController(TransactionRepository repository) {this.repository = repository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> get(@PathVariable("id") Long id) {//looking for an id?
        Transaction transaction = repository.findOne(id);
        if (null == transaction) {
            return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)//saving the transaction object posted by the android app
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction) {
        repository.save(transaction);
        return get(transaction.getId());
    }

    @RequestMapping
    public List<Transaction> all() {
        return repository.findAll();
    }
}
