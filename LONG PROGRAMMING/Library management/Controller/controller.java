package com.example.libaraies.Controller;


import com.example.libaraies.DHO.book;
import com.example.libaraies.Service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class controller {
    @Autowired
    private service service;
    @GetMapping("/book")
    public List<book> getbook() {

        return service.getbook();
    }
    @PostMapping("/add")
    private book saved(@RequestBody book b) {

        return service.saved(b);
    }
    @PutMapping("/{id}")
    private book update(@PathVariable int id, @RequestBody book b) {

        return  service.update(id, b);
    }
    @DeleteMapping("/{id}")
    private String delete(@PathVariable int id) {

        return  service.delete(id);
    }
}
