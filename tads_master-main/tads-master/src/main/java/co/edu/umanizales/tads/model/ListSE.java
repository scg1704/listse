package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidsCityDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;
    private int size;

    /*LÓGICA MÉTODO AÑADIR:
    Entrada: El niño que vamos a añadir
    * La cabeza está vacía?
    *   NO
    *   Llamamos al ayudante y lo posiciones en la cabeza
    *   Le decimos al ayudante que recorra toda la lista
    *       Hay algo?
    *       No
    *       Añadimos el nuevo nodo en donde está el ayudante
    *   SI
    *   Agregamos el nuevo nodo como cabeza*/
    public void add(Kid kid) throws ListSEException{
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

    /*LÓGICA MÉTODO AÑADIR AL INICIO:
    Entrada:
    El niño que vamos a añadir
    La cabeza está vacía?
        NO
        Añadimos los datos del niño a un nuevo nodo
        Decimos que se haga al lado de la cabeza
        El nuevo nodo se convierte en la cabeza
        SI
        Añadimos el nuevo niño como la cabeza
     */
    public void addToStart(Kid kid) throws ListSEException{
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

    /*LÓGICA MÉTODO AÑADIR POR POSICIÓN:
    Entrada:
    El niño que vamos a añadir, la posición donde lo vamos a añadir
    Creamos la variable que va a almacenar el niño
    ¿La posición donde lo queremos añadir es la 0?
        SI
        Lo añadimos como cabeza
        NO
        Llamamos un ayudante
        Le decimos que recorra la lista el número de posiciones que fueron solicitadas
        Cuando llegamos a la posición deseada añadimos el niño
     */

    public void addByPosition(Kid kid, int position) throws ListSEException{
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
        size++;
    }

    /*LÓGICA MÉTODO ELIMINAR POR ID:
    Entrada: La identificación que nos dirá que niño eliminar
    Llamamos un ayudante que se va a posicionar en la cabeza
    Creamos una lista copia
    Creamos un boolean en caso de que encontremos a un niño con esa identificación
    Recorremos la lista
        No tiene la id?
            Lo añadimos a una nueva lista
        Tiene la id?
            Decimos que si tiene esa id
        Pasamos al siguiente
    Pegamos la cabeza de la lista copia sin el de la id
     */
    public void deleteByIdentification (String identification) throws ListSEException{
        Node temp = head;
        ListSE listCopy = new ListSE();
        boolean found = false;
        while (temp != null){
            if (temp.getData().getIdentification().compareTo(identification)!=0){
                listCopy.add(temp.getData());
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
        Creamos una lista SE adicional
        Llamamos al temporal y lo ponemos en la cabeza
        Le decimos al temporal que recorra toda la lista de niños y a cada uno lo añada al inicio de la nueva lista
    Reemplazamos la nueva lista hecha en la original
     */
    public void invert() throws ListSEException{
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

    /*LÓGICA MÉTODO PROMEDIO EDAD:
    La cabeza está vacía?
    NO
        Llamamos a un ayudante
        Creamos un contador que nos va a decir cuantos niños hay en la lista
        Creamos otro contador que va a sumar la edad de cada niño
        Recorremos la lista
            Por cada nodo añadimos uno al primer contador
            Por cada nodo obtenemos la edad y se la sumamos al segundo contador
        Ya con ambos contadores finalizados hacemos la operación. Dividimos el resultado del total de las edades con el total de niños
     SI
        Decimos que el promedio es de 0
     */
    public float averageAge() throws ListSEException{
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

    /*LÓGICA MÉTODO CAMBIAR EXTREMOS:
    Hay datos?
    SI
        Llamamos al ayudante
        Le decimos que recorra la lista hasta el final
        Obtenemos los datos del primer niño
        Ponemos los datos del último niño al inicio
        Ponemos los datos del primer niño al final
     */
    public void changeExtremes() throws ListSEException{
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

    /*LÓGICA MÉTODO CONTAR NIÑOS POR CÓDIGO LOCALIZACIÓN:
    Entrada:
    Código de la localización
    Creamos un contador
    Tiene datos?
    SI
        Llamamos al ayudante
        Le decimos que recorra la lista
        El nodo donde está tiene el mismo código de ciudad al de entrada?
        SI
            Añadimos 1 al contador
    Devolvemos los resultados del contador
     */
    public int getCountKidsByCityCode(String code) throws ListSEException{
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

    /*LÓGICA MÉTODO CONTAR NIÑOS POR CÓDIGO LOCALIZACIÓN -DEPARTAMENTO:
    Entrada:
    Código de la localización
    Creamos un contador
    Tiene datos?
    SI
        Llamamos al ayudante
        Le decimos que recorra la lista
        El nodo donde está tiene el mismo código de ciudad al de entrada y posee un máximo de 5 dígitos?
        SI
            Añadimos 1 al contador
    Devolvemos los resultados del contador
     */
    public int getCountKidsByDeptCode(String code) throws ListSEException{
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

    /*LÓGICA MÉTODO REVISAR SI EL NIÑO YA HA SIDO AÑADIDO:
    Entrada:
    Los datos del niño
    Llamamos al ayudante
    Le decimos que recorra la lista y obtenga los datos de los niños
        Los datos obtenidos del niño son iguales a los ingresados?
        SI
            Decimos que es cierto, y no lo añadimos
     */
    public boolean addKidDone(Kid newKid) throws ListSEException{
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

    /*LÓGICA MÉTODO ELIMINAR POR EDAD:
    Entrada: La edad que nos dirá que niños eliminar
    Llamamos un ayudante que se va a posicionar en la cabeza
    Creamos una lista copia
    Creamos un boolean en caso de que encontremos niños que tengan esa edad
    Recorremos la lista
        No tiene la edad?
            Lo añadimos a una nueva lista
        Tiene la edad?
            Decimos que si tiene esa edad
        Pasamos al siguiente
    Pegamos la cabeza de la lista copia sin los de la edad
     */
    public void deleteByAge (int age) throws ListSEException{
        Node temp = head;
        ListSE listCopy = new ListSE();
        boolean found = false;
        while (temp != null){
            if (temp.getData().getAge()!=age){
                listCopy.add(temp.getData());
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

    /*LÓGICA MÉTODO ELIMINAR AL FONDO SEGÚN LA LETRA:
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
    public void sendBottomByLetter(char initial) throws ListSEException{

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
    public void boyStartGirlsLast() throws ListSEException{
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

    /*LÓGICA MÉTODO PRIMERO CHICO, LUEGO CHICA:
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
    public void boyThenGirl() throws ListSEException{
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
        * los kids de forma alternada*/
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

    /*LÓGICA MÉTODO OBTENER RANGO DE EDADES:
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
    public int getAgeRange(int min, int max) throws ListSEException{
        Node temp = head;
        int count=0;
        while (temp != null){
            if(temp.getData().getAge() >= min && temp.getData().getAge() <= max){
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }

    /*LÓGICA MÉTODO REPORTE DE NIÑOS SEGÚN GÉNERO Y LOCALIZACIÓN:
    Entrada:
    La edad mínima de niños que se buscan, el reporte obtenido anteriormente por el DTO
    La lista tiene datos?
    SI
        Llamamos un ayudante
        Recorremos la lista
            El nodo donde estamos parados tiene edad mayor a la solicitada?
            SI
                Obtenemos a ese nodo, y actualizamos la cantidad
     */
    public void getReportKidsByLocationGendersByAge(int age, ReportKidsLocationGenderDTO report) throws ListSEException{
        if (head!=null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getAge()>age){
                    report.updateQuantity(temp.getData().getCity().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }

    /*LÓGICA MÉTODO ADELANTAR POSICIONES:
    Entrada:
    Identificación del niño a cambiar de posición, el número de posiciones que lo queremos cambiar, la lista de datos que poseemos
    Llamamos a un ayudante para que se haga en la cabeza
    Ponemos un contador en 1
    Recorremos la lista, y mientras hayan datos comparamos y vamos pasando mientras la identificación del niño no coincida con la identificación solicitada
        Pasamos al siguiente nodo
        Lo añadimos al contador
    Temporal tiene datos?
    SI
        Creamos una variable diff tipo int, que tendrá el conteo que fue hecho - las posiciones que pedimos
        Obtenemos los datos del niño
        Decimos que lo borre
        La diff es mayor a 0?
            Decimos que añada en posición al kid del que tomamos los datos, y la diferencia que ya fue establecida
        Si no
            Añadimos al inicio
    Decimos que el niño no existe
     */
    public void forwardPositions(String identification, int positions, ListSE listSE) throws ListSEException {
        Node temp = this.head;
        int count = 1;
        while (temp != null && temp.getData().getIdentification().compareTo(identification) != 0) {
            temp = temp.getNext();
            count++;
        }
        if (temp != null){
            int diff = count - positions;
            Kid kid = temp.getData();
            listSE.deleteByIdentification(temp.getData().getIdentification());
            if (diff > 0){
                listSE.addByPosition(kid, diff);
            }
            else{
                addToStart(kid);
            }
        }
        else{
            throw new ListSEException("Kid doesn't exists");
        }
    }

    /*LÓGICA MÉTODO RETROCEDER POSICIONES:
    Entrada:
    Identificación del niño a cambiar de posición, y el número de posiciones que lo queremos cambiar
    Llamamos a un ayudante que se posicione en la cabeza
    Creamos una variable tipo entero que empiece en 1
    Recorremos la lista mientras hayan datos comparamos y vamos pasando mientras la identificación del niño no coincida con la identificación solicitada
        Pasamos al siguiente nodo
        Lo añadimos al contador
    Creamos una variable suma que será la suma entre las posiciones de entrada, y el conteo realizado anteriormente
    Tenemos datos?
    SI
        Obtenemos los datos del niño donde estamos
        Eliminamos a ese niño
        Añadimos por posición al niño, según la suma
    SI NO
        Decimos que el niño no existe
     */
    public void afterwardsPositions(String identification, int positions) throws ListSEException {
        Node temp = this.head;
        int count = 1;
        while (temp != null && temp.getData().getIdentification().compareTo(identification) != 0) {
            temp = temp.getNext();
            count++;
        }
        int sum = positions + count;
        if (temp != null) {
            Kid kid = temp.getData();
            deleteByIdentification(temp.getData().getIdentification());
            addByPosition(kid, sum);
        }
        else{
            throw new ListSEException("Kid doesn't exists");
        }
    }

    /*
    10 métodos:
    1) Invertir lista (CHECK)
    2) Niños al inicio, niñas al final (CHECK)
    3) Intercalar niño-niña (CHECK)
    4) Eliminar por edad (CHECK)
    5) Promedio edad niños lista (CHECK)
    6) Reporte de niños por ciudad (CHECK)
    7) Adelante x número de posiciones (CHECK)
    8) Pierda x número de posiciones (CHECK)
    9) Informe de niños por rango de edades (CHECK)
    10) Enviar al final de la lista niños cuyo nombre inicie con una letra dada (CHECK)
     */

}
