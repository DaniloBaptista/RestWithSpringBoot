package br.com.erudio.services;

import java.util.List;

import br.com.erudio.data.model.Books;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class BooksServices {

    @Autowired
    BooksRepository repository;

    public BooksVO create(BooksVO books) {
        var entity = DozerConverter.parseObject(books, Books.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BooksVO.class);
        return vo;
    }

    public Page<BooksVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToBooksVO);
    }

    private BooksVO convertToBooksVO(Books entity){
        return DozerConverter.parseObject(entity, BooksVO.class);
    }

    public BooksVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, BooksVO.class);
    }

    public BooksVO update(BooksVO books) {
        var entity = repository.findById(books.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(books.getAuthor());
        entity.setLaunchDate(books.getLaunchDate());
        entity.setPrice(books.getPrice());
        entity.setTitle(books.getTitle());

        var vo = DozerConverter.parseObject(repository.save(entity), BooksVO.class);
        return vo;
    }

    public void delete(Long id) {
        Books entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
