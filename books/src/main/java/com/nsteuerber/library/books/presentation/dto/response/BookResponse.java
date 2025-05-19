package com.nsteuerber.library.books.presentation.dto.response;

import java.util.Set;

public record BookResponse (
        String name,
        String author,
        Set<String> tags,
        String releaseDate
){}
