package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path="/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody Kid kid){
        listSEService.getKids().add(kid);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added", null), HttpStatus.OK);
    }

    @GetMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Kid kid){
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added to start", null), HttpStatus.OK);
    }

    @GetMapping(path="/addposition")
    public ResponseEntity<ResponseDTO> addByPosition(@PathVariable int position, @RequestBody Kid kid){
        listSEService.getKids().addByPosition(kid, position);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been added in the position", null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.getKids().invert();
        return new ResponseEntity<>(new ResponseDTO(200,
                "The list has been inverted", null), HttpStatus.OK);
    }

    @GetMapping(path="/delete")
    public ResponseEntity<ResponseDTO> deleteByIdentification(@PathVariable String identification){
        listSEService.getKids().deleteByIdentification(identification);
        return new ResponseEntity<>(new ResponseDTO(200,
                "The kid has been deleted", null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().averageAge(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/totalkidscity/{code}")
    public ResponseEntity<ResponseDTO> totalKidsByCity(@PathVariable String code){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().totalKidsByCity(code), null), HttpStatus.OK);
    }

    @GetMapping(path="/totalkidscities")
    public ResponseEntity<ResponseDTO> getTotalKidsCities(@PathVariable List<City> cities){
        return new ResponseEntity<>(new ResponseDTO(200,
                listSEService.getKids().getTotalKidsCities(cities), null), HttpStatus.OK);
    }
}
