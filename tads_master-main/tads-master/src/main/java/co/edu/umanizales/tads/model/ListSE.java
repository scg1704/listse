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

    public int getCountKidsByDeptCode(String code){
        int count = 0;
        if (this.head != null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getCity().getCode().substring(0, 5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountKidsByMunicipalCode(String code){
        int count = 0;
        if (this.head != null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getCity().getCode().substring(6, 9).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public boolean addKidDone(Kid newKid){
        Node temp = this.head;
        while (temp != null){
            Kid kid = temp.getData();
            if (kid.getIdentification().equals(newKid.getIdentification()) && kid.getName().equals(newKid.getName())
                && kid.getAge() == newKid.getAge() && kid.getGender() == newKid.getGender()
                && kid.getCity().getCode().equals(newKid.getCity().getCode())){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void deleteByAge (int age){
        Node temp = head;
        Node prev = null;
        while (temp != null && temp.getData().getAge() != age){
            prev = temp;
            temp = temp.getNext();
        }
        if (temp != null){
            if (prev == null){
                head = temp.getNext();
            }
            else{
                prev.setNext(temp.getNext());
            }
        }
    }

    public void sendBottomByLetter(char initial){

        //Creamos la lista copia
        ListSE sendBottom = new ListSE();
        Node temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null){
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)){
                sendBottom.add(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = sendBottom.getHead();
    }

    public void boyStartGirlsLast(){
        ListSE listCopy = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if (temp.getData().getGender() == 'M'){
                listCopy.add(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null){
            if (temp.getData().getGender() == 'F'){
                listCopy.add((temp.getData()));
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }

    public void boyThenGirl(){
        ListSE listMale = new ListSE();
        ListSE listFemale = new ListSE();
        Node temp = this.head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                listMale.add(temp.getData());
            }
            if(temp.getData().getGender()=='F'){
                listFemale.add(temp.getData());
            }
            temp = temp.getNext();
        }

        /*A partir de las listas creadas vamos a generar una nueva lista donde vamos a ingresar
        * los kids de forma alternada
        */
        ListSE sortedList = new ListSE();
        Node maleNode = listMale.getHead();
        Node femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                sortedList.add(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null){
                sortedList.add(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        this.head = sortedList.getHead();
    }

    /*
    10 métodos:
    1) Invertir lista (CHECK)
    2) Niños al inicio, niñas al final (CHECK)
    3) Intercalar niño-niña (CHECK)
    4) Eliminar por edad (CHECK)
    5) Promedio edad niños lista (CHECK)
    6) Reporte de niños por ciudad (CHECK)
    7) Adelante x número de posiciones
    8) Pierda x número de posiciones
    9) Informe de niños por rango de edades
    10) Enviar al final de la lista niños cuyo nombre inicie con una letra dada (CHECK)
     */
}
