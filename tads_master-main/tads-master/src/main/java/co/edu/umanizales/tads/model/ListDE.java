package co.edu.umanizales.tads.model;

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
    }
}
