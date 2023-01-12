package top.ss007.usefaulltools.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProgramerServiceTest {

    @Autowired
    private ProgramerService programerService;

    @Test
    void runMap() {
        programerService.runMap();
    }

    @Test
    void runHumanMap(){
        programerService.runHumanDemo();
    }
}