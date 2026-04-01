package com.example.libaraies.DHO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookid;
    @Column(name = "bookname")
    private String bookname;
    @Column(name = "authorname")
    private String authorname;
    @Column(name = "publishername")
    private String publishername;
    @ManyToOne
    @JoinColumn(name = "userid")
    private user userid;
}
