package com.nsteuerber.library.books.service.interfaces;


import com.nsteuerber.library.books.presentation.dto.request.BookRequest;
import com.nsteuerber.library.books.presentation.dto.response.BookResponse;

import java.util.List;

public interface IBooksService {
    void saveBook(BookRequest bookRequest);

    List<BookResponse> findBooksByTitle(String title);
}
