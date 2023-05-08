package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.service.AgeRangeService;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.VetService;
import jakarta.validation.Valid;
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

    //Se insertan las excepciones únicamente donde vayamos a añadir mascotas

    @GetMapping(path="/add")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody @Valid Pet pet) {
        try{
            if (pet == null){
                throw new ListDEException("pet doesn't have any data");
            }
            listDEService.getPets().addPet(pet);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added", null), HttpStatus.OK);
        }
        catch (ListDEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the pet", null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/addstart")
    public ResponseEntity<ResponseDTO> addPetToStart(@RequestBody @Valid Pet pet) {
        try{
            if (pet == null){
                throw new ListDEException("pet doesn't have any data");
            }
            listDEService.getPets().addPetToStart(pet);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added to start", null), HttpStatus.OK);
        }
        catch (ListDEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the pet", null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/addposition")
    public ResponseEntity<ResponseDTO> addPetByPosition(@PathVariable int position, @RequestBody @Valid Pet pet) throws ListDEException{
        try{
            if (pet == null){
                throw new ListDEException("pet doesn't have any data");
            }
            listDEService.getPets().addPetByPosition(pet, position);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been added in the position", null), HttpStatus.OK);
        }
        catch (ListDEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error adding the pet", null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invertPets() {
        try{
            listDEService.getPets().invertPets();
            return new ResponseEntity<>(new ResponseDTO(200, "The list has been inverted", null), HttpStatus.OK);
        }
        catch (ListDEException e){
            return new ResponseEntity<>(new ResponseDTO(400, "Error inverting the list", null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/delete")
    public ResponseEntity<ResponseDTO> deleteByPetCode(@PathVariable String petCode) {
        try{
            listDEService.getPets().deleteByPetCode(petCode);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been deleted", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error deleting the pet", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAgePets() {
        try{
            return new ResponseEntity<>(new ResponseDTO(200, listDEService.getPets().averageAgePets(), null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/changeextremes")
    public ResponseEntity<ResponseDTO> changePetExtremes() {
        try{
            listDEService.getPets().changePetExtremes();
            return new ResponseEntity<>(new ResponseDTO(200, "Extremes have been changed", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error changing extremes", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody @Valid PetDTO petDTO){
        Vet vet = vetService.getVetByCode(petDTO.getCodeVet());
        if (vet == null) {
            return new ResponseEntity<>(new ResponseDTO(404, "Vet doesn't exist", null), HttpStatus.OK);
        }
        try{
            listDEService.getPets().addPetDone(new Pet(petDTO.getPetCode(),
                    petDTO.getPetName(), petDTO.getSpecie(), petDTO.getPetAge(), petDTO.getGender(), vet));
           }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Already exists", null), HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(new ResponseDTO(200, "Pet has been added", null), HttpStatus.OK);
    }

    @GetMapping(path = "/petsbyvets")
    public ResponseEntity<ResponseDTO> getKidsByVetCode() {
        try{
            List<PetsVetDTO> petsVetDTOList = new ArrayList<>();
            for(Vet vet: vetService.getVets()){
                int count = listDEService.getPets().getCountPetsByVetCode(vet.getCode());
                if(count > 0){
                    petsVetDTOList.add(new PetsVetDTO(vet, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/petsbyhospital")
    public ResponseEntity<ResponseDTO> getPetsByHospitalCode(){
        try{
            List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
            for(Vet vet: vetService.getVets()){
                int count = listDEService.getPets().getCountPetsByHospitalCode(vet.getCode());
                if(count > 0){
                    petsVetDTOList.add(new PetsVetDTO(vet, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/petsbystore")
    public ResponseEntity<ResponseDTO> getPetsByStoreCode(){
        try{
            List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
            for(Vet vet: vetService.getVets()){
                int count = listDEService.getPets().getCountPetsByStoreCode(vet.getCode());
                if(count > 0){
                    petsVetDTOList.add(new PetsVetDTO(vet, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/petsbystuff")
    public ResponseEntity<ResponseDTO> getPetsByStuffCode(){
        try{
            List<PetsVetDTO> petsVetDTOList=new ArrayList<>();
            for(Vet vet: vetService.getVets()){
                int count = listDEService.getPets().getCountPetsByStuffCode(vet.getCode());
                if(count > 0){
                    petsVetDTOList.add(new PetsVetDTO(vet, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(200, petsVetDTOList, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/deletebypetage/{age}")
    public ResponseEntity<ResponseDTO> deleteByPetAge(@PathVariable int age){
        try{
            listDEService.getPets().deleteByPetAge(age);
            return new ResponseEntity<>(new ResponseDTO(200, "Pets were deleted", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/sendpetsbottom/{initial}")
    public ResponseEntity<ResponseDTO> sendPetBottomByLetter(@PathVariable char initial){
        try{
            listDEService.getPets().sendPetBottomByLetter(Character.toUpperCase(initial));
            return new ResponseEntity<>(new ResponseDTO(200, "Pets with that letter at start have been sent to the end",
                    null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/boysfirstgirlslast")
    public ResponseEntity<ResponseDTO> maleStartFemaleLast(){
        try{
            listDEService.getPets().maleStartFemaleLast();
            return new ResponseEntity<>(new ResponseDTO(200, "Males are at the start, females are at last",
                    null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/malethenfemale")
    public ResponseEntity<ResponseDTO> maleThenFemale(){
        try{
            listDEService.getPets().maleThenFemale();
            return new ResponseEntity<>(new ResponseDTO(200, "Pets have been altered by them gender",
                    null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    //Usamos DTO's, que ya fueron usados en Kids. No es necesario crear otros
    @GetMapping(path="/rangeagepets")
    public ResponseEntity<ResponseDTO> getPetsAgeRange(){
        try{
            List<AgeRangeKidsDTO> rangeKidsDTOList = new ArrayList<>();
            for (AgeRange i : ageRangeService.getRanges()){
                int quantity = listDEService.getPets().getPetsAgeRange(i.getFrom(), i.getTo());
                rangeKidsDTOList.add(new AgeRangeKidsDTO(i, quantity));
            }
            return new ResponseEntity<>(new ResponseDTO(200, rangeKidsDTOList, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/petsbyvetgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportPetsByVetGendersByAge(@PathVariable int age){
        ReportPetsVetGenderDTO report = new ReportPetsVetGenderDTO(vetService.getVets());
        try{
            listDEService.getPets().getReportPetsByVetGendersByAge(age, report);
            return new ResponseEntity<>(new ResponseDTO(200, report, null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/forwardpetpositions")
    public ResponseEntity<ResponseDTO> forwardPetPositions(@PathVariable String petCode, int positions){
        try{
            listDEService.getPets().forwardPetPositions(petCode, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been moved to the position", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/afterwardspetpositions")
    public ResponseEntity<ResponseDTO> afterwardsPetPositions(@PathVariable String petCode, int positions){
        try{
            listDEService.getPets().afterwardsPetPositions(petCode, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been moved to the position", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/deletekamikaze")
    public ResponseEntity<ResponseDTO> deleteKamikaze(@PathVariable String petCode){
        try{
            listDEService.getPets().deleteKamikaze(petCode);
            return new ResponseEntity<>(new ResponseDTO(200, "The pet has been deleted", null), HttpStatus.OK);
        }
        catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(400, "Error", null), HttpStatus.BAD_REQUEST);
        }
    }
}
