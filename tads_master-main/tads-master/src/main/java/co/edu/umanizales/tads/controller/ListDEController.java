package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
}
