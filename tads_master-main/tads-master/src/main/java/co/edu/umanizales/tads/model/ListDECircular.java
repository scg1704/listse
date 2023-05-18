package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListDEException;
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
    LÓGICA MÉTODO TOMAR UNA DUCHA
    Entrada:
    Una dirección hacia la cual vamos a bañar a los animales
    Llamamos un char que nos diga según que dirección arrancamos, y ponga la letra en minúscula
    Llamamos un ayudante para que se posicione en la cabeza
    La lista es nula?
    SI
        Decimos que no se puede hacer nada
    La lista no empieza ni con los indicativos de derecha o izquierda?
    SI
        Decimos que es incorrecto
    Llamamos a un nuúmero random
    Llamamos a un entero que va a ser el equivalente al random +1
    Si el número random es 1?
        Decimos que tome los datos del nodo donde está
        Decimos que sea cierto que lo bañó
    El número random no es 1
        Decimos que arranque un conteo desde el 1
        Empieza por la derecha?
        SI
            Decimos que vaya al nodo indicado por derecha
        NO
            Decimos que vaya al nodo indicado por izquierda
        Es el nodo que estamos buscando?
        SI
            Decimos que ese se bañó
     */

    public int takeShower (char direction){
        char start = Character.toLowerCase(direction);
        NodeDE temp = head;
        if (temp == null){
            return 0;
        }
        if (start != 'r' && start != 'l'){
            return 0;
        }
        Random random = new Random();
        int num = random.nextInt(size) + 1;
        if (num == 1){
            if (temp.getData().isShower()){
                temp.getData().setShower(true);
            }
            else{
                return 0;
            }
        }
        else{
            int count = 1;
            if (start == 'r'){
                while (count != num){
                    temp = temp.getNext();
                    count++;
                }
            }
            else{
                while (count != num){
                    temp = temp.getPrevious();
                    count++;
                }
            }

            if (temp.getData().isShower()){
                temp.getData().setShower(true);
            }
            else{
                return 0;
            }
        }
        return num;
    }
}
