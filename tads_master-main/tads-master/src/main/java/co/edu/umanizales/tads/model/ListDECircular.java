package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportSpecieShowerDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListDECircular {
    private NodeDE head;
    private int size;
    private List<Pet> pets = new ArrayList<>();

    /*
    LÓGICA MÉTODO PRINT LISTA CIRCULAR
    Llamamos a un ayudante y le decimos que se posicione en la cabeza
    Le decimos que añada todos los pets que poseemos
     */

    public List <Pet> print (){
        pets.clear();
        NodeDE temp = head;
        do{
            pets.add(temp.getData());
            temp = temp.getNext();
        }
        while (temp != head);
        return pets;
    }

    /*
    LÓGICA MÉTODO AÑADIR:
    Entrada:
    El animal que vamos a añadir a la lista

    Agregamos el animal a un nuevo nodo
    La lista tiene elementos?
    SI
        Llamamos un ayudante a que se situe en la cabeza
        Le decimos al ayudante que recorra la lista hasta que llegue al nodo anterior a la cabeza
        Ahora le decimos que siguiente a ese nodo ponga el nuevo nodo
        Le decimos que el que estaba de ùltimo tome al nuevo
        Le decimos al nuevo que tome al que estaba de último, y a la cabeza
        Le decimos a la cabeza que tome al nuevo nodo
    NO
        Le decimos al nuevo nodo que se tome a si mismo
        Le decimos que se posicione en la cabeza
     */

    public void addPet (Pet pet) {
        NodeDE newPet = new NodeDE(pet);
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp.getNext() != this.head){
                temp = temp.getNext();
            }
            temp.setNext(newPet);
            newPet.setPrevious(temp);
            newPet.setNext(this.head);
            this.head.setPrevious(newPet);
        }
        else{
            newPet.setNext(newPet);
            newPet.setPrevious(newPet);
            this.head=newPet;
        }
        size++;
    }

    /*
    LÓGICA MÉTODO AÑADIR AL INICIO:
    Entrada:
    El animal que vamos a añadir

    Agregamos al animal a un nuevo nodo
    La lista tiene elementos?
    SI
        Llamamos a un ayudante
        Le decimos que recorra la lista hasta llegar al nodo anterior a la cabeza
        Le decimos al temporal que tome al nuevo nodo siguiente a este
        Decimos que nuevo nodo tome a temporal, y tome a cabeza
        Decimos a cabeza que tome al nuevo nodo
        Decimos que el nuevo nodo es la cabeza
    NO
        Insertamos al nuevo nodo como cabeza
        Le decimos que se tome a si mismo
     */

    public void addPetStart (Pet pet) {
        NodeDE newPet = new NodeDE(pet);
        if (this.head != null){
            NodeDE temp = this.head;
            while (temp.getNext() != this.head){
                temp = temp.getNext();
            }
            temp.setNext(newPet);
            newPet.setPrevious(temp);
            newPet.setNext(head);
            head.setPrevious(newPet);
            this.head=newPet;
        }
        else{
            newPet.setNext(newPet);
            newPet.setPrevious(newPet);
            this.head = newPet;
        }
        size++;
    }

    /*
    LÓGICA MÉTODO AÑADIR POR POSICIÓN:
    Entrada:
    Mascota a añadir
    Posición donde debe de ser añadida

    La posición solicitada es menor a 0?
    SI
        Decimos que esto no es posible
    Ponemos a la nueva mascota en un nuevo nodo
    La lista está vacía?
    NO
        Llamamos a un ayudante
        La posición ingresada es 0?
        SI
            Añadimos la nueva mascota al inicio
            Este procedimiento se realiza de la misma forma que en el método de añadir al inicio
            (Le decimos al ayudante que recorra la lista hasta llegar al nodo anterior a la cabeza
            Le decimos al temporal que tome al nuevo nodo siguiente a este
            Decimos que nuevo nodo tome a temporal, y tome a cabeza
            Decimos a cabeza que tome al nuevo nodo
            Decimos que el nuevo nodo es la cabeza)
        La posición ingresada es mayor o igual al tamaño?
        SI
            Añadimos la nueva mascota al final
            Este procedimiento se realiza de la misma forma que en el método añadir al final
            (Le decimos al ayudante que recorra la lista hasta que llegue al nodo anterior a la cabeza
            Ahora le decimos que siguiente a ese nodo ponga el nuevo nodo
            Le decimos que el que estaba de ùltimo tome al nuevo
            Le decimos al nuevo que tome al que estaba de último, y a la cabeza
            Le decimos a la cabeza que tome al nuevo nodo)
        Si no se cumplen los dos casos anteriores:
            Recorremos las posiciones hasta llegar a la anterior a la indicada
            Decimos que el nuevo nodo se una al que le sigue al temporal
            Decimos al nuevo nodo que se una al temporal
            Decimos al siguiente del temporal que tome el nuevo nodo
            Decimos al temporal que tome el nuevo nodo
    SI
        Ponemos como cabeza al nuevo nodo
        Le decimos que se tome a si mismo
     */

    public void addPetByPosition (Pet pet, int position){
        if (position < 0){
            //Esto lo investigué, y nos dice que es algo no posible, por lo tanto no hará cambios
            throw new IllegalArgumentException("ERROR");
        }
        NodeDE newPet = new NodeDE(pet);
        if (this.head != null){
            NodeDE temp = this.head;
            if (position == 0) {
                while (temp.getNext() != this.head){
                    temp = temp.getNext();
                }
                temp.setNext(newPet);
                newPet.setPrevious(temp);
                newPet.setNext(head);
                head.setPrevious(newPet);
                this.head=newPet;
            }
            else if (position >= size){
                while (temp.getNext() != this.head){
                    temp = temp.getNext();
                }
                temp.setNext(newPet);
                newPet.setPrevious(temp);
                newPet.setNext(this.head);
                this.head.setPrevious(newPet);
            }
            else{
                for (int i = 0; i < position -1; i++){
                    temp = temp.getNext();
                }
                newPet.setNext(temp.getNext());
                newPet.setPrevious(temp);
                temp.getNext().setPrevious(newPet);
                temp.setNext(newPet);
            }
        }
        else{
            newPet.setNext(newPet);
            newPet.setPrevious(newPet);
            this.head = newPet;
        }
        size++;
    }

    /*
    NOTA: A mí forma de entender el ejercicio, aquello que se debe de hacer es bañar la mascota.
    Para ello le añadí el atributo booleano de shower, y si este es false, muestra que la mascota no ha sido
    bañada, y si es true, muestra que ha sido bañada. Aquello que se nos pidió fue un método que bañara a las
    mascotas, según la que posición que fue solicitada, y dado el caso de que la posición fuese mayor a la lista
    entonces seguiamos recorriendo la lista.

    LÓGICA MÉTODO BAÑAR MASCOTA:
    Entrada:
    Posición de la mascota que queremos bañar

    La lista tiene datos?
    SI
        Llamamos a un ayudante
        Recorremos las posiciones hasta llegar a la anterior a la indicada
        Decimos que tome los datos del siguiente (Que es la posición indicada) y lo bañe
     */
    public void showerByPosition (int position){
        if (this.head != null){
            NodeDE temp = this.head;
            for (int i = 0; i < position -1; i++){
                temp = temp.getNext();
            }
            temp.getData().setShower(true);
        }
    }

    /*
    LÓGICA MÉTODO TOMAR UNA DUCHA RANDOM
    Entrada:
    Una dirección hacia la cual vamos a bañar a los animales

    La cabeza está vacía?
        Decimos que no hay nada
    Obtenemos el tamaño de la lista y lo almacenamos en una variable
    Creamos el random que nos va a dar el random
    Decimos que el random va a ser un entero seleccionado al azar que va a ser del tamaño de la lista y no sea 0
    Llamamos a un ayudante en la cabeza
    Creamos un contador que empiece en 0
    La dirección ingresada es l(left)?
    SI
        Decimos que se devuelva
    Mientras el contador sea menor a la posición aleatoria
        Si la dirección ingresada es r(right)
            Recorre la lista por la derecha
        Si no
            Recorre la lista por la derecha
        Se aumenta el contador
    Actualizamos los nodos donde está el ayudante para saber si está o no bañado
        El ayudante pasa por cada nodo para actualizar
    Obtenemos los datos de la mascota con ayuda del ayudante
    Si la mascota no ha sido bañado
        Bañarla
     */

    public int takeShower (String direction) {
        if (head == null) {
            return -1;
        }
        int size = getSize();
        Random random = new Random();
        int randomPosition = random.nextInt(size) + 1;
        NodeDE temp = head;
        int cont = 1;
        if (direction.compareTo("l") == 0) {
            temp = head.getPrevious();
        }
        while (cont < randomPosition) {
            if (direction.compareTo("r") == 0) {
                temp = temp.getNext();
            }
            else if (direction.compareTo("l") == 0) {
                temp = temp.getPrevious();
            }
            cont++;
        }
        if (direction.compareTo("l") == 0) {
            temp = temp.getNext();
        }
        Pet pet = temp.getData();
        if (!pet.isShower()) {
            pet.setShower(true);
            return randomPosition;
        } else {
            return -1;
        }
    }

    /*
    Llamamos la lista de especies que poseemos
    Recorremos la lista de mascotas
        Obtenemos las especies
            Agregamos las especies nuevas que vayamos encontrando
    Creamos un objeto que nos va a armar el reporte según las especies
    En este objeto vamos a actualizar la cantidad de especies que hay según se hayan bañado
    Recorremos la lista de mascotas que poseemos
        Decimos que debe de estar bañado
        Si está bañado
            Decimos que obtenga la especie y la muestre por su nombre
            Creamos un entero que nos va a almacenar la cantidad actual que poseemos de la especie
            Le sumamos 1 a esa cantidad que hay en tal caso de que sea de la misma especie, y esté bañado
            Actualizamos el reporte mostrando la especie, y la cantidad que posee
    Mostramos esto ya con todos los valores adquiridos
     */

    public ReportSpecieShowerDTO getReportPetsBySpecieShower(){
        List<Specie> species = new ArrayList<>();
        for (Pet pet : pets) {
            Specie specie = pet.getSpecie();
            if (!species.contains(specie)) {
                species.add(specie);
            }
        }
        ReportSpecieShowerDTO report = new ReportSpecieShowerDTO(species);
        for (Pet pet : pets) {
            boolean shower = pet.isShower();
            if (shower) {
                String specie = pet.getSpecie().getName();
                int currentQuantity = report.getQuantityBySpecie(specie);
                int updatedQuantity = currentQuantity + 1;
                report.updateQuantitySpecies(specie, updatedQuantity);
            }
        }
        return report;
    }
}
