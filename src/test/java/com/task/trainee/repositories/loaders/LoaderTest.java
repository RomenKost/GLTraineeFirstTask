package com.task.trainee.repositories.loaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:sensors.properties")
public class LoaderTest {
    @Autowired
    private Loader loader;

    @Test
    public void loaderTest() {

    }
}
