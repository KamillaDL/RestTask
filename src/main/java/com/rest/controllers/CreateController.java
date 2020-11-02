package com.rest.controllers;

import com.rest.models.Book;
import com.rest.repositories.IBookRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateController {
    private final IBookRepository iBookRepository;

    public CreateController(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @PostMapping("/createBook")
    public ResponseEntity<?> CreateBook(@NotNull @RequestBody Book book){
        Boolean isNotEmpty = book.Validate();
        if(isNotEmpty){
            iBookRepository.create(book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("You haven't entered data", HttpStatus.BAD_REQUEST);
    }
}
