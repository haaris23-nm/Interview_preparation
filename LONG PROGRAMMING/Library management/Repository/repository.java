package com.example.libaraies.Repository;

import com.example.libaraies.DHO.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<book, Integer> {
}