package top.ss007.usefaulltools.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ss007.usefaulltools.model.*;
import top.ss007.usefaulltools.model.convetor.ClzProgramerConvertor;
import top.ss007.usefaulltools.model.convetor.HumanConvertor;
import top.ss007.usefaulltools.model.convetor.ProgramerConvetor;

import java.util.Date;

@Slf4j
@Service
public class ProgramerService {

//    @Autowired
//    private ClzProgramerConvertor clzProgramerConvertor;

    @Autowired
    private ProgramerConvetor programerConvetor;

    @Autowired
    private HumanConvertor humanConvertor;

    public void runMap() {
        Programer programer = new Programer();
        programer.setName("王二狗");
        programer.setHeight(170.2D);
        programer.setBeDate(new Date());

        programer.setGirlName("牛翠华");
        programer.setGirlDes("糟糠之妻");


//        ProgramerDto programerDto = clzProgramerConvertor.toProgramerDto(programer);
        ProgramerDto programerDto = programerConvetor.toProgramerDto(programer);
        log.info("dto: {}", programerDto);
    }

    public void runHumanDemo(){
        Human man = new Man();
        man.setName("王二狗");
        log.info("{}是大男人",humanConvertor.toHumanDto(man));

        Human woman = new Woman();
        woman.setName("牛翠华");
        log.info("{}是小女人", humanConvertor.toHumanDto(woman));
    }
}
