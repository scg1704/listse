package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
import lombok.Data;

@Data
public class ListDECircular {
    private NodeDE head;
    private int size;

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

    public void addPetByPosition (Pet pet, int position){

    }
}
