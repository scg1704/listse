package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidsCityDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;
    private int size;
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
        size++;
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
        size++;
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

    public void changeExtremes(){
        if(this.head !=null && this.head.getNext() !=null){
            Node temp = this.head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    public int getCountKidsByCityCode(String code){
        int count = 0;
        if(this.head != null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getCity().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }
}
