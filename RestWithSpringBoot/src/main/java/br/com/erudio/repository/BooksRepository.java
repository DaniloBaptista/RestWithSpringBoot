package br.com.erudio.repository;

import br.com.erudio.data.model.Books;
import br.com.erudio.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
