package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ReportPetsVetGenderDTO;
import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void addPet(Pet pet)
    {
        if(this.head!=null)
        {
            NodeDE temp = this.head;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            NodeDE newPet= new NodeDE(pet);
            temp.setNext(newPet);
            newPet.setPrevious(temp);
        }
        else
        {
            this.head= new NodeDE(pet);
        }
        size++;
    }

    public void addPetToStart(Pet pet){
        if(head !=null)
        {
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        else {
            head = new NodeDE(pet);
        }
        size++;
    }

    public void addPetByPosition(Pet pet, int position){
        NodeDE newNode = new NodeDE(pet);
        if (position == 0){
            newNode.setNext(head);
            if (head != null){
                head.setPrevious(newNode);
            }
            head = newNode;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position - 1; i++){
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            if (temp.getNext()!=null){
                temp.getNext().setPrevious(newNode);
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        size++;
    }

    public void deleteByPetCode (String petCode){
        NodeDE temp = head;
        NodeDE prev = null;

        while (temp.getNext() != null && temp.getData().getPetCode() == petCode) {
            prev = temp;
            temp = temp.getNext();
        }

        if(temp != null){
            if (prev == null){
                head = temp.getNext();
                if (head != null){
                    head.setPrevious(null);
                }
            }else {
                prev.setNext(temp.getNext());
                if (temp.getNext() != null){
                    temp.getNext().setPrevious(prev);
                }
            }
            size--;
        }
    }

    public void invertPets(){
        if(this.head != null){
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while(temp != null){
                listCP.addPetToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
    }

    public float averageAgePets(){
        if (head != null){
            NodeDE temp = head;
            int contador = 0;
            int ages = 0;
            while(temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getPetAge();
                temp = temp.getNext();
            }
            return (float) ages/contador;
        }
        else{
            return (float) 0;
        }
    }

    public void changePetExtremes(){
        if(this.head !=null && this.head.getNext() !=null){
            NodeDE temp = this.head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            Pet copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    public int getCountPetsByVetCode(String code){
        int count = 0;
        if(this.head != null){
            NodeDE temp = this.head;
            while(temp != null){
                if(temp.getData().getVet().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByHospitalCode(String code){
        int count = 0;
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp != null){
                if(temp.getData().getVet().getCode().substring(3, 3).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByStoreCode(String code){
        int count = 0;
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp != null){
                if(temp.getData().getVet().getCode().substring(4, 4).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByStuffCode(String code){
        int count = 0;
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp != null){
                if(temp.getData().getVet().getCode().substring(5, 5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public boolean addPetDone(Pet newPet){
        NodeDE temp = this.head;
        while (temp != null){
            Pet pet = temp.getData();
            if (pet.getPetCode().equals(newPet.getPetCode()) && pet.getPetName().equals(newPet.getPetName())
                    && pet.getPetAge() == newPet.getPetAge() && pet.getGender() == newPet.getGender()
                    && pet.getVet().getCode().equals(newPet .getVet().getCode())){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void deleteByPetAge (int age){
        NodeDE temp = head;
        NodeDE prev = null;
        while (temp != null && temp.getData().getPetAge() == age){
            prev = temp;
            temp = temp.getNext();
        }
        if (temp != null){
            if (prev == null){
                head = temp.getNext();
                if (head != null){
                    head.setPrevious(null);
                }
            }
            else{
                prev.setNext(temp.getNext());
                if (temp.getNext() != null){
                    temp.getNext().setPrevious(prev);
                }
            }
            size--;
        }
    }

    public void sendPetBottomByLetter(char initial){

        //Creamos la lista copia
        ListDE sendBottom = new ListDE();
        NodeDE temp = this.head;

        while (temp != null){
            if (temp.getData().getPetName().charAt(0) != Character.toUpperCase(initial)){
                sendBottom.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getData().getPetName().charAt(0) == Character.toUpperCase(initial)){
                sendBottom.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = sendBottom.getHead();
    }

    public void maleStartFemaleLast(){
        ListDE listCopy = new ListDE();
        NodeDE temp = this.head;
        while (temp != null){
            if (temp.getData().getGender() == 'M'){
                listCopy.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null){
            if (temp.getData().getGender() == 'F'){
                listCopy.addPet((temp.getData()));
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }

    public void maleThenFemale(){
        ListDE listMale = new ListDE();
        ListDE listFemale = new ListDE();
        NodeDE temp = this.head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                listMale.addPet(temp.getData());
            }
            if(temp.getData().getGender()=='F'){
                listFemale.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        /*A partir de las listas creadas vamos a generar una nueva lista donde vamos a ingresar
         * los kids de forma alternada*/
        ListDE sortedList = new ListDE();
        NodeDE maleNode = listMale.getHead();
        NodeDE femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                sortedList.addPet(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null){
                sortedList.addPet(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        this.head = sortedList.getHead();
    }

    public int getPetsAgeRange(int min, int max){
        NodeDE temp = head;
        int count=0;
        while (temp != null){
            if(temp.getData().getPetAge() >= min && temp.getData().getPetAge() <= max){
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }

    public void getReportPetsByVetGendersByAge(int age, ReportPetsVetGenderDTO report){
        if (head!=null){
            NodeDE temp = this.head;
            while (temp != null){
                if(temp.getData().getPetAge()>age){
                    report.updateQuantityPets(temp.getData().getVet().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }

    public void forwardPetPositions(String petCode, int positions){
        if (head != null){
            if(positions<size){
                if(head.getData().getPetCode()==petCode){
                    //Como es la cabeza, entonces no puede subir posiciones
                }
                else{
                    int count = 1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getPetCode()!=petCode){
                        temp = temp.getNext();
                        count++;
                        if(temp.getNext()!=null){
                            return;
                        }
                    }
                    if (temp.getNext() != null){
                        NodeDE temp2 = new NodeDE(temp.getNext().getData());
                        temp.getNext().getNext().setPrevious(temp);
                        temp.setNext(temp.getNext().getNext());
                        if (positions >= count+1){
                            addPetToStart(temp2.getData());
                        }
                        else{
                            addPetByPosition(temp2.getData(), (count+1) - positions);
                        }
                    }
                }
            }
            else{
                return;
            }
        }
    }

    public void afterwardsPetPositions(String petCode, int positions){
        if (head!=null){
            if(positions<size){
                if(head.getData().getPetCode()==petCode){
                    NodeDE node = new NodeDE(head.getNext().getData());
                    addPetByPosition(node.getData(), positions+1);
                    head = head.getNext();
                }
                else{
                    int count = 1;
                    NodeDE temp = head;
                    while(temp.getNext().getData().getPetCode()!=petCode) {
                        temp = temp.getNext();
                        count++;
                        if (temp.getNext() != null) {
                            return;
                        }
                    }
                    NodeDE temp2=new NodeDE(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    if (temp.getNext() != null){
                        temp.getNext().setPrevious(temp);
                    }
                    addPetByPosition(temp2.getData(), count+1+positions);
                }
            }
            else{
                return;
            }
        }
    }
}
