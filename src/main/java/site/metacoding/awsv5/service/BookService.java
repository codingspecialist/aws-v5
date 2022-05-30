package site.metacoding.awsv5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.awsv5.domain.Book;
import site.metacoding.awsv5.domain.BookRepository;
import site.metacoding.awsv5.web.dto.BookRespDto;
import site.metacoding.awsv5.web.dto.BookSaveReqDto;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책등록(BookSaveReqDto reqDto) {
        Book bookEntity = bookRepository.save(reqDto.toEntity());
        return new BookRespDto(bookEntity);
    }

    @Transactional(readOnly = true)
    public List<BookRespDto> 책목록보기() {
        List<Book> booksEntity = bookRepository.findAll();
        System.out.println("사이즈 : " + booksEntity.size());
        return booksEntity.stream()
                .map((book) -> new BookRespDto(book))
                .collect(Collectors.toList());
    }
}
