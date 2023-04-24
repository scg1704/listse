package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;
}
