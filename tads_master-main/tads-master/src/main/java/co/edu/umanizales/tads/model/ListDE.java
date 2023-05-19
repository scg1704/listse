package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ReportPetsVetGenderDTO;
import co.edu.umanizales.tads.exception.ListDEException;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;
    private List<Pet> pets = new ArrayList<>();

    /*
    LÓGICA MÉTODO PARA IMPRIMIR LA LISTA:
    Llamamos a la lista de leds
    Le decimos que si tiene elementos pase por toda la lista con ayuda de un temporal y los añada
     */

    public List <Pet> print (){
        pets.clear();
        if (head != null){
            NodeDE temp = head;
            while (temp != null){
                pets.add(temp.getData());
                temp =temp.getNext();
            }
        }
        return pets;
    }

    /*LÓGICA MÉTODO AÑADIR:
    Entrada:
    Tomamos los datos de la mascota a añadir
    Tenemos datos?
    SI
        Llamamos al ayudante y lo posicionamos en la cabeza
        Decimos que recorra la lista mientras haya datos
        Ponemos a la mascota en un nuevo nodo
        Unimos el nodo donde estamos con el nuevo
        Unimos el nuevo nodo con el anterior
    NO
        Lo colocamos en la cabeza
    Subimos el tamaño de la lista
    */

    public void addPet(Pet pet) throws ListDEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            NodeDE newPet = new NodeDE(pet);
            temp.setNext(newPet);
            newPet.setPrevious(temp);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    /*LÓGICA MÉTODO AÑADIR AL INICIO:
    Entrada:
    Tomamos los datos de la mascota a añadir
    Tenemos datos?
    SI
        Llamamos a un nuevo nodo donde vamos a almacenar la nueva mascota
        Le decimos que tome la cabeza
        A la cabeza decimos que tome al nuevo nodo
        Decimos que la cabeza es el nuevo nodo
    NO
        Lo añadimos a la cabeza
    Aumentamos el tamaño
    */
    public void addPetToStart(Pet pet) throws ListDEException {
        if (head != null) {
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }

    /*LÓGICA MÉTODO AÑADIR POR POSICIÓN:
    Entrada:
    Tomamos los datos de la mascota a añadir, y la posición donde lo deseamos añadir
    Ponemos a la mascota en un nuevo nodo
    La posición es 0?
        Decimos que tome la cabeza
        La cabeza tiene datos?
        SI
            Decimos que la cabeza tome el nuevo nodo
        Ponemos al nuevo nodo como cabeza
    NO
        Llamamos a un ayudante
        Recorremos la lista según las posiciones solicitadas
        El siguiente posee datos?
        SI
            Decimos a ese que tome al nuevo nodo
        Decimos a donde estamos que tome a nuevo nodo
        Decimos a nuevo nodo que tome al anterior y al siguiente
    Aumentamos el tamaño de la lista
    */
    public void addPetByPosition(Pet pet, int position) throws ListDEException {
        NodeDE newNode = new NodeDE(pet);
        if (position == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrevious(newNode);
            }
            head = newNode;
        } else {
            NodeDE temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPrevious(newNode);
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        size++;
    }

    /*LÓGICA MÉTODO ELIMINAR SEGÚN CÓDIGO:
    Entrada: El código que nos dirá que mascota eliminar
    Llamamos un ayudante que se va a posicionar en la cabeza
    Creamos una lista copia
    Creamos un boolean en caso de que encontremos a una mascota con ese código
    Recorremos la lista
        No tiene el código?
            Lo añadimos a una nueva lista
        Tiene el código?
            Decimos que si tiene ese código
        Pasamos al siguiente
    Pegamos la cabeza de la lista copia sin el del código
    */
    public void deleteByPetCode (String petCode) throws ListDEException {
        NodeDE temp = head;
        ListDE listCopy = new ListDE();
        boolean found = false;
        while (temp != null){
            if (temp.getData().getPetCode().compareTo(petCode)!=0){
                listCopy.addPet(temp.getData());
            }
            else{
                found = true;
                size--;
            }
            temp = temp.getNext();
        }
        if (found) {
            this.head = listCopy.getHead();
        }
    }

    /*LÓGICA MÉTODO INVERTIR:
    La cabeza está vacía?
        NO
        Creamos una lista DE adicional
        Llamamos al temporal y lo ponemos en la cabeza
        Le decimos al temporal que recorra toda la lista de mascotas y a cada una la añada al inicio de la nueva lista
    Reemplazamos la nueva lista hecha en la original
    */
    public void invertPets() throws ListDEException {
        if (this.head != null) {
            ListDE listCP = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCP.addPetToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        }
    }

    /*LÓGICA MÉTODO PROMEDIO EDADES:
    La cabeza está vacía?
    NO
        Llamamos a un ayudante
        Creamos un contador que nos va a decir cuantas mascotas hay en la lista
        Creamos otro contador que va a sumar la edad de cada mascota
        Recorremos la lista
            Por cada nodo añadimos uno al primer contador
            Por cada nodo obtenemos la edad y se la sumamos al segundo contador
        Ya con ambos contadores finalizados hacemos la operación. Dividimos el resultado del total de las edades con el total de mascotas
     SI
        Decimos que el promedio es de 0
    */
    public float averageAgePets() throws ListDEException {
        if (head != null) {
            NodeDE temp = head;
            int contador = 0;
            int ages = 0;
            while (temp.getNext() != null) {
                contador++;
                ages = ages + temp.getData().getPetAge();
                temp = temp.getNext();
            }
            return (float) ages / contador;
        } else {
            return (float) 0;
        }
    }

    /*LÓGICA MÉTODO CAMBIAR EXTREMOS:
    Hay datos?
    SI
        Llamamos al ayudante
        Le decimos que recorra la lista hasta el final
        Obtenemos los datos de la primer mascota
        Ponemos los datos de la última mascota al inicio
        Ponemos los datos de la primer mascota al final
    */
    public void changePetExtremes() throws ListDEException {
        if (this.head != null && this.head.getNext() != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Pet copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    /*LÓGICA MÉTODO OBTENER MASCOTAS SEGÚN EL CÓDIGO DEL VETERINARIO:
    Entrada:
    Código de la veterinaria
    Creamos un contador
    Tiene datos?
    SI
        Llamamos al ayudante
        Le decimos que recorra la lista
        El nodo donde está tiene el mismo código de la veterinaria al de entrada?
        SI
            Añadimos 1 al contador
    Devolvemos los resultados del contador
    */
    public int getCountPetsByVetCode(String code) throws ListDEException {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getVet().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    /*LÓGICA MÉTODO OBTENER MASCOTAS SEGÚN CÓDIGO DEL HOSPITAL:
    Usamos la misma lógica del código de listas SE. No se le hace ningún cambio
    */
    public int getCountPetsByHospitalCode(String code) throws ListDEException {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getVet().getCode().substring(3, 3).equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    /*LÓGICA MÉTODO REVISAR SI LA MASCOTA YA HA SIDO AÑADIDA:
    Entrada:
    Los datos de la mascota
    Llamamos al ayudante
    Le decimos que recorra la lista y obtenga los datos de las mascotas
        Los datos obtenidos de la mascota son iguales a los ingresados?
        SI
            Decimos que es cierto, y no lo añadimos
    */
    public boolean addPetDone(Pet newPet) throws ListDEException {
        NodeDE temp = this.head;
        while (temp != null) {
            Pet pet = temp.getData();
            if (pet.getPetCode().equals(newPet.getPetCode()) && pet.getPetName().equals(newPet.getPetName())
                    && pet.getPetAge() == newPet.getPetAge() && pet.getGender() == newPet.getGender()
                    && pet.getVet().getCode().equals(newPet.getVet().getCode())) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /*LÓGICA MÉTODO ELIMINAR MASCOTA POR EDAD:
    Entrada: La edad que nos dirá que mascotas eliminar
    Llamamos un ayudante que se va a posicionar en la cabeza
    Creamos una lista copia
    Creamos un boolean en caso de que encontremos mascotas que tengan esa edad
    Recorremos la lista
        No tiene la edad?
            Lo añadimos a una nueva lista
        Tiene la edad?
            Decimos que si tiene esa edad
        Pasamos al siguiente
    Pegamos la cabeza de la lista copia sin los de la edad
    */
    public void deleteByPetAge (int age) throws ListDEException {
        NodeDE temp = head;
        ListDE listCopy = new ListDE();
        boolean found = false;
        while (temp != null){
            if (temp.getData().getPetAge()!=age){
                listCopy.addPet(temp.getData());
            }
            else{
                found = true;
                size--;
            }
            temp = temp.getNext();
        }
        if (found) {
            this.head = listCopy.getHead();
        }
    }

    /*LÓGICA MÉTODO ENVIAR AL FONDO POR LETRA:
    Entrada:
    La inicial que buscamos enviar al fondo
    Creamos una lista copia
    Llamamos al ayudante
    Le decimos al ayudante que recorra la lista
        Su primer cáracter es diferente al solicitado?
        SI
            Lo añadimos al final de la lista copia
        Su primer carácter es el solicitado?
        SI
            Lo añadimos al final de la lista copia
    Reemplazamos la lista copia por la original
     */
    public void sendPetBottomByLetter(char initial) throws ListDEException {

        //Creamos la lista copia
        ListDE sendBottom = new ListDE();
        NodeDE temp = this.head;

        while (temp != null) {
            if (temp.getData().getPetName().charAt(0) != Character.toUpperCase(initial)) {
                sendBottom.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        temp = this.head;

        while (temp != null) {
            if (temp.getData().getPetName().charAt(0) == Character.toUpperCase(initial)) {
                sendBottom.addPet(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = sendBottom.getHead();
    }

    /*LÓGICA MÉTODO NIÑOS AL INICIO, NIÑAS AL FINAL:
    Creamos una lista copia
    Llamamos al ayudante
    La lista está vacía?
    No
        Recorro la lista de niños
            Su género es 'M'?
            SI
                Lo añadimos al final de la lista copia
            Su género es 'F'?
            SI
                Lo añadimos al final de la lista copia
     Reemplazamos la lista copia por la original
     */
    public void maleStartFemaleLast() throws ListDEException {
        ListDE listCopy = new ListDE();
        NodeDE temp = this.head;
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listCopy.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;

        while (temp != null) {
            if (temp.getData().getGender() == 'F') {
                listCopy.addPet((temp.getData()));
            }
            temp = temp.getNext();
        }
        this.head = listCopy.getHead();
    }

    /*LÓGICA MÉTODO NIÑO, LUEGO NIÑA:
    Creamos una lista copia donde vamos a almacenar todas las chicas
    Creamos una lista copia donde vamos a almacenar todos los chicos
    Llamamos al ayudante
    Hay datos?
    SI
        Recorro la lista de niños
            Su género es 'M'?
            SI
                Lo añado a la lista copia de chicos
            Su género es 'F'?
            SI
                Lo añado a la lista copia de chicas
     Creo una nueva lista copia
     Recorro ambas listas copia de niños y niñas
        Tomo la cabeza de la lista de niños y la agrego a la nueva lista copia
        Tomo la cabeza de la lista de niñas y la agrego a la nueva lista copia
     Reemplazo la nueva lista en la lista original
     */
    public void maleThenFemale() throws ListDEException {
        ListDE listMale = new ListDE();
        ListDE listFemale = new ListDE();
        NodeDE temp = this.head;
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listMale.addPet(temp.getData());
            }
            if (temp.getData().getGender() == 'F') {
                listFemale.addPet(temp.getData());
            }
            temp = temp.getNext();
        }
        /*A partir de las listas creadas vamos a generar una nueva lista donde vamos a ingresar
         * los kids de forma alternada*/
        ListDE sortedList = new ListDE();
        NodeDE maleNode = listMale.getHead();
        NodeDE femaleNode = listFemale.getHead();
        while (maleNode != null || femaleNode != null) {
            if (maleNode != null) {
                sortedList.addPet(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null) {
                sortedList.addPet(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        this.head = sortedList.getHead();
    }

    /*LÓGICA MÉTODO OBTENER MASCOTAS SEGÚN EL RANGO DE EDAD:
    Entrada:
    Mínimo de edad, máximo de edad
    Llamo al ayudante
    Creo un contador
    La lista tiene datos?
    SI
        Recorro la lista
            El nodo tiene una edad entre la mínima y la máxima?
            SI
                Añado 1 al contador
    Devuelvo todos los valores
     */
    public int getPetsAgeRange(int min, int max) throws ListDEException {
        NodeDE temp = head;
        int count = 0;
        while (temp != null) {
            if (temp.getData().getPetAge() >= min && temp.getData().getPetAge() <= max) {
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }

    /*LÓGICA MÉTODO OBTENER MASCOTAS SEGÚN GÉNERO Y VETERINARIA:
    Entrada:
    La edad mínima de mascotas que se buscan, el reporte obtenido anteriormente por el DTO
    La lista tiene datos?
    SI
        Llamamos un ayudante
        Recorremos la lista
            El nodo donde estamos parados tiene edad mayor a la solicitada?
            SI
                Obtenemos a ese nodo, y actualizamos la cantidad
     */
    public void getReportPetsByVetGendersByAge(int age, ReportPetsVetGenderDTO report) throws ListDEException {
        if (head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getPetAge() > age) {
                    report.updateQuantityPets(temp.getData().getVet().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }

    /*LÓGICA MÉTODO ADELANTAR POSICIONES:
    Entrada:
    Codigo de la mascota a cambiar de posición, el número de posiciones que lo queremos cambiar, la lista de datos que poseemos
    Llamamos a un ayudante para que se haga en la cabeza
    Ponemos un contador en 1
    Recorremos la lista, y mientras hayan datos comparamos y vamos pasando mientras el código de la mascota no coincida con el código solicitado
        Pasamos al siguiente nodo
        Lo añadimos al contador
    Temporal tiene datos?
    SI
        Creamos una variable diff tipo int, que tendrá el conteo que fue hecho - las posiciones que pedimos
        Obtenemos los datos de la mascota
        Decimos que lo borre
        La diff es mayor a 0?
            Decimos que añada en posición a la mascota del que tomamos los datos, y la diferencia que ya fue establecida
        Si no
            Añadimos al inicio
    Decimos que la mascota
    */
    public void forwardPetPositions(String petCode, int positions, ListDE listDE) throws ListDEException {
        NodeDE temp = this.head;
        int count = 1;
        while (temp != null && !temp.getData().getPetCode().equals(petCode)) {
            temp = temp.getNext();
            count++;
        }
        if (temp != null){
            int diff = count - positions;
            Pet pet = temp.getData();
            listDE.deleteByPetCode(temp.getData().getPetCode());
            if (diff > 0){
                listDE.addPetByPosition(pet, diff);
            }
            else{
                listDE.addPetToStart(pet);
            }
        }
        else{
            throw new ListDEException("Pet doesn't exists");
        }
    }

    /*LÓGICA MÉTODO RETROCEDER POSICIONES:
    Entrada:
    Código de la mascota a cambiar de posición, y el número de posiciones que lo queremos cambiar
    Llamamos a un ayudante que se posicione en la cabeza
    Creamos una variable tipo entero que empiece en 1
    Recorremos la lista mientras hayan datos comparamos y vamos pasando mientras el código de la mascota no coincida con el código solicitado
        Pasamos al siguiente nodo
        Lo añadimos al contador
    Creamos una variable suma que será la suma entre las posiciones de entrada, y el conteo realizado anteriormente
    Tenemos datos?
    SI
        Obtenemos los datos de la mascota donde estamos
        Eliminamos a esa mascota
        Añadimos por posición a la mascota, según la suma
    SI NO
        Decimos que la mascota no existe
    */
    public void afterwardsPetPositions(String petCode, int positions) throws ListDEException {
        NodeDE temp = this.head;
        int count = 1;
        while (temp != null && !temp.getData().getPetCode().equals(petCode)) {
            temp = temp.getNext();
            count++;
        }
        int sum = positions + count;
        if (temp != null) {
            Pet pet = temp.getData();
            deleteByPetCode(temp.getData().getPetCode());
            addPetByPosition(pet, sum);
        }
        else{
            throw new ListDEException("Pet doesn't exists");
        }
    }

    //Eliminar en sitio

    /*
    LÓGICA ELIMINAR EN SITIO:
    Entrada:
    Usamos identificación como entrada, de esta forma podemos saber cual es el nodo que deseamos eliminar,
    y así poder pararnos sobre este.
    La lista tiene datos?
    SI
        En la cabeza está la mascota que deseamos eliminar?
        SI
            Decimos a la cabeza que tome al siguiente a la cabeza
            Cabeza tiene datos?
                Decimos que el anterior a la nueva cabeza no conecte con nada
            Mermamos el tamaño
        Llamamos a un contador
        Llamamos a un ayudante para que se posicione en la cabeza
        Recorremos la lista
            La mascota en donde está temporal posee el código solicitado?
            SI
                Decimos al anterior que tome al siguiente de donde estamos
                LLegamos al final?
                SI
                    Decimos que tome al anterior
                Mermamos el tamaño
           Vamos al siguiente nodo
    */

    public void deleteKamikaze (String petCode) throws ListDEException {
        if (head != null) {
            if (head.getData().getPetCode().compareTo(petCode)==0){
                head = head.getNext();
                if (head != null){
                    head.setPrevious(null);
                }
                size --;
                return;
            }
            int count = 0;
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getPetCode().compareTo(petCode)==0) {

                    temp.getPrevious().setNext(temp.getNext());

                    if (temp.getNext() != null){
                        temp.getNext().setPrevious(temp.getPrevious());
                    }
                    size --;
                    count ++;
                }
                temp = temp.getNext();
            }
        }
    }
}