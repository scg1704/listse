package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.AgeRange;
import co.edu.umanizales.tads.model.City;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.service.AgeRangeService;
import co.edu.umanizales.tads.service.CityService;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AgeRangeService ageRangeService;

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

    @GetMapping(path="/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes(){
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "Se han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        City city = cityService.getCityByCode(kidDTO.getCodeCity());
        Boolean addKidDone = listSEService.getKids().addKidDone(new Kid(kidDTO.getIdentification(),
                kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), city));
        if (city == null){
            return new ResponseEntity<>(new ResponseDTO(404, "La ubicación no existe", null), HttpStatus.OK);
        }
        else if(addKidDone.equals(false)){
            return new ResponseEntity<>(new ResponseDTO(400, "El elemento ya existe", null), HttpStatus.OK);
        }
        else{
            listSEService.getKids().add(new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(), kidDTO.getGender(), city));
            return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el niño", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByCity(){
        List<KidsCityDTO> kidsCityDTOList = new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByCityCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbydept")
    public ResponseEntity<ResponseDTO> getKidsByDeptCode(){
        List<KidsCityDTO> kidsCityDTOList=new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByDeptCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbymunicipal")
    public ResponseEntity<ResponseDTO> getKidsByMunicipalCode(){
        List<KidsCityDTO> kidsCityDTOList=new ArrayList<>();
        for(City city: cityService.getCities()){
            int count = listSEService.getKids().getCountKidsByMunicipalCode(city.getCode());
            if(count > 0){
                kidsCityDTOList.add(new KidsCityDTO(city, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsCityDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteByAge(@PathVariable int age){
        listSEService.getKids().deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido eliminados",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/sendbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendBottomByLetter(@PathVariable char initial){
        listSEService.getKids().sendBottomByLetter(Character.toUpperCase(initial));
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños con esa letra se han enviado al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> boyStartGirlsLast(){
        listSEService.getKids().boyStartGirlsLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños salen al inicio, las niñas al final",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boythengirl")
    public ResponseEntity<ResponseDTO> boyThenGirl(){
        listSEService.getKids().boyThenGirl();
        return new ResponseEntity<>(new ResponseDTO(200, "Los niños han sido alternados según su género",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/rangeage")
    public ResponseEntity<ResponseDTO> getAgeRange(){
        List<AgeRangeKidsDTO> rangeKidsDTOList = new ArrayList<>();

        for (AgeRange i : ageRangeService.getRanges()){
            int quantity = listSEService.getKids().getAgeRange(i.getFrom(), i.getTo());
            rangeKidsDTOList.add(new AgeRangeKidsDTO(i, quantity));
        }
        return new ResponseEntity<>(new ResponseDTO(200, rangeKidsDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path="/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKidsByLocationGendersByAge(@PathVariable int age){
        ReportKidsLocationGenderDTO report = new ReportKidsLocationGenderDTO(cityService.getCitiesByCodeSize(8));
        listSEService.getKids().getReportKidsByLocationGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }

}
