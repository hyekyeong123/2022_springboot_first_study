package com.example.studyone.repository;

import com.example.studyone.StudyoneApplicationTests;
import com.example.studyone.model.entity.Item;

// import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



class ItemRepositoryTest extends StudyoneApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("한글 입력 안됨");

        Item newItem = itemRepository.save(item);
        assertNotNull(newItem);
        System.out.println("[JHG] : "+newItem);
    }

    @Test
    public void read(){
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);
        assertTrue(item.isPresent());
    }
}