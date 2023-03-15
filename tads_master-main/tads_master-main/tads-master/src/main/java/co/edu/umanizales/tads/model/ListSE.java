package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidsCityDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;
    public void add(Kid kid){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
    }

    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
        }
    }

    public void addByPosition(Kid kid, int position){
        Node newNode = new Node(kid);
        if (position == 0){
            newNode.setNext(head);
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 0; i < position - 1; i++){
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }
    public void deleteByIdentification (String identification){
        Node temp = head;
        Node prev = null;

        while (temp.getNext() != null && temp.getData().getIdentification() != identification) {
            prev = temp;
            temp = temp.getNext();
        }

        if(temp != null){
            if (prev == null){
                head = temp.getNext();
            }else {
                prev.setNext(temp.getNext());
            }
        }
    }

    public void invert(){
        if(this.head != null){
            ListSE listCP = new ListSE();
            Node temp = this.head;
            while(temp != null){
                listCP.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
    }

    public float averageAge(){
        /*Me toca poner el método como float y no como vacío; Si lo pongo vacío me arroja error
        al momento de retornar valores
        Cambié los valores de age a int, de esa forma se me hizo más fácil trabajar el ejercicio*/
        if (head != null){
            Node temp = head;
            int contador = 0;
            int ages = 0;
            while(temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages/contador;
        }
        else{
            return (float) 0;
        }
    }

    public int totalKidsByCity(String code){
        if (head != null){
            Node temp = head;
            int sum = 0;
            while (temp.getNext() != null){
                if (temp.getNext().getData().equals(code)){
                    sum = sum + 1;
                }
                temp = temp.getNext();
            }
            return sum;
        }
        else{
            return 0;
        }
    }

    public KidsCityDTO getTotalKidsCities (List<City> cities) {
        KidsCityDTO kidsCityDTO = new KidsCityDTO(null);
        ArrayList<City> TotalKidsCities;
        TotalKidsCities = new ArrayList<>();
        for (City city : cities){
            ///El equipo no me quiere tomar la lista de niños que hay en el service. no logro determinar el error.
            kids.totalKidsByCity(city.getCode());
            kidsCityDTO = new KidsCityDTO(new City(null, null));
        }
        return kidsCityDTO;
    }
}
