package com.thinkmammoth.coding.service;

import com.thinkmammoth.coding.utils.RandomPickerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RandomWordSelectorServiceImpl implements RandomWordSelectorService {

    private static final Logger logger = LoggerFactory.getLogger(RandomWordSelectorServiceImpl.class);

    @Override
    public String printRandomWord(String file) throws IOException {
        return this.selectRandomWord(file);
    }

    private String selectRandomWord(String file) throws IOException {
        String selectedWord = " ";
        try {
            ClassPathResource resource = new ClassPathResource(file);
            try (InputStream inputStream = Objects.requireNonNull(resource).getInputStream()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                        StandardCharsets.UTF_8));
                List<String> inputListOfWords = bufferedReader.lines().collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(inputListOfWords)) {
                    RandomPickerUtil<String> randomPicker = new RandomPickerUtil<>(inputListOfWords);
                    selectedWord = randomPicker.pick();
                    logger.info("Selected Random word is {}", selectedWord);
                } else {
                    logger.debug("File: {} is empty", file);
                }
            } catch (IOException e) {
                logger.error("IOException occurred while trying to get Input Stream", e);
                throw e;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error("Input File not found {}", file, fileNotFoundException);
            throw fileNotFoundException;
        }
        return selectedWord;
    }
}
