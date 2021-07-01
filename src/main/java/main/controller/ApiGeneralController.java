package main.controller;

import main.model.GeneralData;
import main.model.GlobalSetting;
import main.repository.GlobalSettingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiGeneralController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/api/init")
    public ResponseEntity getMainData(){
        return new ResponseEntity(new GeneralData(), HttpStatus.OK);
    }

    @Autowired
    private GlobalSettingRepository globalSettingRepository;


    @GetMapping("/api/settings")
    public ResponseEntity getGlobalSettings(){
        Iterable<GlobalSetting> settingsIterable = globalSettingRepository.findAll();
        List<GlobalSetting> settingsList = new ArrayList<>();
        settingsIterable.forEach(settingsList::add);
        return new ResponseEntity(settingsList, HttpStatus.OK);
    }

    /*@GetMapping("/api/tag")
    public ResponseEntity getTags(){
    }*/

    //@GetMapping("/api/calendar")

    //@GetMapping("/api/statistics/my")

    //@GetMapping("/api/statistics/all")

    //@PostMapping("/api/image")

    //@PostMapping("/api/comment")

    //@PostMapping("/api/moderation")

    //@PostMapping("/api/profile/my")

    //@PutMapping("/api/settings")

}
