package com.rest.controllers;

import com.rest.models.Book;
import com.rest.repositories.IBookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindByNameController {
    private final IBookRepository iBookRepository;

    public FindByNameController(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @GetMapping("/find")
    public ResponseEntity findByName(@RequestParam(name="name", required=true) String name){
        if(name.trim().equals("")){
            return new ResponseEntity<>("You haven't entered data", HttpStatus.BAD_REQUEST);
        }
        List<Book> books = iBookRepository.findByName(name);
        if(books.size() != 0) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("This book doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
