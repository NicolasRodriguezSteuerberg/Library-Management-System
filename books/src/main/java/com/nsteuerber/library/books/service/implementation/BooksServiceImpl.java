package com.nsteuerber.library.books.service.implementation;

import com.nsteuerber.library.books.persistance.entity.BookEntity;
import com.nsteuerber.library.books.persistance.entity.TagEntity;
import com.nsteuerber.library.books.persistance.repository.IBookRepository;
import com.nsteuerber.library.books.presentation.dto.request.BookRequest;
import com.nsteuerber.library.books.presentation.dto.response.BookResponse;
import com.nsteuerber.library.books.service.interfaces.IBooksService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements IBooksService {

    private final IBookRepository bookRepository;

    public BooksServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBook(BookRequest bookRequest) {

    }

    @Override
    public List<BookResponse> findBooksByTitle(String title) {
        List<BookEntity> bookEntities = bookRepository.findBookEntityByTitleContainingIgnoreCase(title).orElseThrow(()->
                new IllegalArgumentException("Ningun libro con ese titulo")
        );
        List<BookResponse> books = new ArrayList<>();
        bookEntities
                .forEach(bookEntity ->
                    books.add(new BookResponse(
                            bookEntity.getTitle(),
                            bookEntity.getAuthor(),
                            bookEntity.getTags().stream()
                                    .map(TagEntity::getName)
                                    .collect(Collectors.toSet()),
                            bookEntity.getReleaseDate()
                    ))
                );
        return books;
    }
}
