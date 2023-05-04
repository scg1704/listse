package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.service.AgeRangeService;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private VetService vetService;
    @Autowired
    private AgeRangeService ageRangeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(200,listDEService.getPets().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path="/add")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody Pet pet) throws ListDEException {
        listDEService.getPets().addPet(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added", null), HttpStatus.OK);
    }

    @GetMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addPetToStart(@RequestBody Pet pet) throws ListDEException{
        listDEService.getPets().addPetToStart(pet);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added to start", null), HttpStatus.OK);
    }

    @GetMapping(path="/addposition")
    public ResponseEntity<ResponseDTO> addPetByPosition(@PathVariable int position, @RequestBody Pet pet) throws ListDEException{
        listDEService.getPets().addPetByPosition(pet, position);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added in the position", null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invertPets() throws ListDEException{
        listDEService.getPets().invertPets();
        return new ResponseEntity<>(new ResponseDTO(200, "The list has been inverted", null), HttpStatus.OK);
    }

    @GetMapping(path="/delete")
    public ResponseEntity<ResponseDTO> deleteByPetCode(@PathVariable String petCode)throws ListDEException{
        listDEService.getPets().deleteByPetCode(petCode);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been deleted", null), HttpStatus.OK);
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAgePets()throws ListDEException{
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getPets().averageAgePets(), null), HttpStatus.OK);
    }

    @GetMapping(path="/changeextremes")
    public ResponseEntity<ResponseDTO> changePetExtremes()throws ListDEException{
        listDEService.getPets().changePetExtremes();
        return new ResponseEntity<>(new ResponseDTO(200, "Extremes have been changed", null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO)throws ListDEException{
        Vet vet = vetService.getVetByCode(petDTO.getCodeVet());
        Boolean addPetDone = listDEService.getPets().addPetDone(new Pet(petDTO.getPetCode(),
                petDTO.getPetName(), petDTO.getSpecie(), petDTO.getPetAge(), petDTO.getGender(), vet));
        if (vet == null){
            return new ResponseEntity<>(new ResponseDTO(404, "Vet doesn't exist", null), HttpStatus.OK);
        }
        else if(addPetDone.equals(false)){
            return new ResponseEntity<>(new ResponseDTO(400, "Already exists", null), HttpStatus.OK);
        }
        else{
            listDEService.getPets().addPet(new Pet(petDTO.getPetCode(), petDTO.getPetName(), petDTO.getSpecie(), petDTO.getPetAge(),
                    petDTO.getGender(), vet));
            return new ResponseEntity<>(new ResponseDTO(200, "Pet has been added", null), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/petsbyvets")
    public ResponseEntity<ResponseDTO> getKidsByVetCode() throws ListDEException{
        List<PetsVetDTO> petsVetDTOList = new ArrayList<>();
        for(Vet vet: vetService.getVets()){
            int count = listDEService.getPets().getCountPetsByVetCode(vet.getCode());
            if(count > 0){
                petsVetDTOList.add(new PetsVetDTO(vet, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbyhospital")
    public ResponseEntity<ResponseDTO> getPetsByHospitalCode()throws ListDEException{
        List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
        for(Vet vet: vetService.getVets()){
            int count = listDEService.getPets().getCountPetsByHospitalCode(vet.getCode());
            if(count > 0){
                petsVetDTOList.add(new PetsVetDTO(vet, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbystore")
    public ResponseEntity<ResponseDTO> getPetsByStoreCode()throws ListDEException{
        List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
        for(Vet vet: vetService.getVets()){
            int count = listDEService.getPets().getCountPetsByStoreCode(vet.getCode());
            if(count > 0){
                petsVetDTOList.add(new PetsVetDTO(vet, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbystuff")
    public ResponseEntity<ResponseDTO> getPetsByStuffCode()throws ListDEException{
        List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
        for(Vet vet: vetService.getVets()){
            int count = listDEService.getPets().getCountPetsByStuffCode(vet.getCode());
            if(count > 0){
                petsVetDTOList.add(new PetsVetDTO(vet, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletebypetage/{age}")
    public ResponseEntity<ResponseDTO> deleteByPetAge(@PathVariable int age)throws ListDEException{
        listDEService.getPets().deleteByPetAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Pets were deleted", null), HttpStatus.OK);
    }

    @GetMapping(path="/sendpetsbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendPetBottomByLetter(@PathVariable char initial)throws ListDEException{
        listDEService.getPets().sendPetBottomByLetter(Character.toUpperCase(initial));
        return new ResponseEntity<>(new ResponseDTO(200, "Pets with that letter at start have been sent to the end",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> maleStartFemaleLast()throws ListDEException{
        listDEService.getPets().maleStartFemaleLast();
        return new ResponseEntity<>(new ResponseDTO(200, "Males are at the start, females are at last",
                null), HttpStatus.OK);
    }

    @GetMapping(path="/malethenfemale")
    public ResponseEntity<ResponseDTO> maleThenFemale()throws ListDEException{
        listDEService.getPets().maleThenFemale();
        return new ResponseEntity<>(new ResponseDTO(200, "Pets have been altered by them gender",
                null), HttpStatus.OK);
    }

    //Usamos DTO's, que ya fueron usados en Kids. No es necesario crear otros
    @GetMapping(path="/rangeagepets")
    public ResponseEntity<ResponseDTO> getPetsAgeRange()throws ListDEException{
        List<AgeRangeKidsDTO> rangeKidsDTOList = new ArrayList<>();

        for (AgeRange i : ageRangeService.getRanges()){
            int quantity = listDEService.getPets().getPetsAgeRange(i.getFrom(), i.getTo());
            rangeKidsDTOList.add(new AgeRangeKidsDTO(i, quantity));
        }
        return new ResponseEntity<>(new ResponseDTO(200, rangeKidsDTOList, null), HttpStatus.OK);
    }

    @GetMapping(path="/petsbyvetgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportPetsByVetGendersByAge(@PathVariable int age)throws ListDEException{
        ReportPetsVetGenderDTO report = new ReportPetsVetGenderDTO(vetService.getVets());
        listDEService.getPets().getReportPetsByVetGendersByAge(age, report);
        return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
    }

    @GetMapping(path="/forwardpetpositions")
    public ResponseEntity<ResponseDTO> forwardPetPositions(@PathVariable String petCode, int positions)throws ListDEException{
        listDEService.getPets().forwardPetPositions(petCode, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been moved to the position", null), HttpStatus.OK);
    }

    @GetMapping(path="/afterwardspetpositions")
    public ResponseEntity<ResponseDTO> afterwardsPetPositions(@PathVariable String petCode, int positions)throws ListDEException{
        listDEService.getPets().afterwardsPetPositions(petCode, positions);
        return new ResponseEntity<>(new ResponseDTO(200, "The pet has been moved to the position", null), HttpStatus.OK);
    }
}
