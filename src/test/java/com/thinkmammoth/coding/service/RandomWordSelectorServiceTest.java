package com.thinkmammoth.coding.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RandomWordSelectorServiceTest {

    private RandomWordSelectorService randomWordSelectorService;

    @Before
    public void setup() {
        randomWordSelectorService = new RandomWordSelectorServiceImpl();
    }

    @Test
    public void testSelectRandomWord_success() throws IOException {
        final String FILE_NAME = "words_alpha.txt";
        String selectedWord1 = randomWordSelectorService.printRandomWord(FILE_NAME);
        String selectedWord2 = randomWordSelectorService.printRandomWord(FILE_NAME);
        Assert.assertNotNull("Selected Random word is not null", selectedWord1);
        Assert.assertNotNull("Selected Random word is not null", selectedWord2);
        Assert.assertNotEquals("Selected Random Words are not equal", selectedWord1, selectedWord2);
    }

    @Test
    public void testSelectRandomWord_empty_file_input() throws IOException {
        final String FILE_NAME = "empty_input.txt";
        String selectedRandomWord = randomWordSelectorService.printRandomWord(FILE_NAME);
        Assert.assertEquals("Selected Random word is empty", " ", selectedRandomWord);
    }

    @Test(expected = FileNotFoundException.class)
    public void testSelectRandomWord_invalid_file_input() throws IOException {
        final String FILE_NAME = "invalid_file_input.txt";
        String selectedRandomWord = randomWordSelectorService.printRandomWord(FILE_NAME);
    }

}
