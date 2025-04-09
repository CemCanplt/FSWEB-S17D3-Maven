package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAll() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable int id) {
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo add(@RequestBody Kangaroo kangaroo) {
        if(kangaroos.containsKey(kangaroo.getId())) {
            throw new ZooException("Kangaroo with given id already exist: " + kangaroo.getId(), HttpStatus.BAD_REQUEST);
        }
        if(kangaroo.getName() == null) {
            throw new ZooException("Kangaroo's given name cannot be null, here is the id: " + kangaroo.getId(), HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        if(!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id) {
        Kangaroo kangaroo = getById(id); // Bu zaten hata fırlatacak
        kangaroos.remove(id);
        return kangaroo;
    }
}