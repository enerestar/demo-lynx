package com.example.demo.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(SpringExtension.class) // junit 5 extendwith instead of runwith junit 4
@SpringBootTest
public class PageRepoTest {

    // test junit3-4
    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

    // test junit5
    @Test
    public void testDivide() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(42, 0);
        });
    }

    @Autowired
    private PageRepo pageRepo;
}
