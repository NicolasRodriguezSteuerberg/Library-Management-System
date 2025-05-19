package com.nsteuerber.library.books.presentation.controller;

import com.nsteuerber.library.books.presentation.dto.response.BookResponse;
import com.nsteuerber.library.books.service.implementation.BooksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BooksServiceImpl booksService;

    public BooksController(BooksServiceImpl booksService) {
        this.booksService = booksService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    BookResponse findBookById(@PathVariable Long id) {
        return null;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<BookResponse> findBooksByTitle(
            @RequestParam String title
    ){
        return booksService.findBooksByTitle(title);
    }
}
