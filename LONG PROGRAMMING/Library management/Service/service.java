package com.example.libaraies.Service;

import com.example.libaraies.DHO.book;
import com.example.libaraies.Repository.repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class service {
    @Autowired
    private repository repo;


    public List<book> getbook() {

        return repo.findAll();
    }
    public book saved(book b) {
        return repo.save(b);
    }

    public book update(int id, book b) {
        book existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        existing.setBookname(b.getBookname());
        existing.setAuthorname(b.getAuthorname());
        existing.setPublishername(b.getPublishername());

        return repo.save(existing);
    }

    public String delete(int id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Book not found");
        }
        repo.deleteById(id);
        return "Book deleted successfully";
    }


}
