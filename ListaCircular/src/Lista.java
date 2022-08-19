import java.util.Scanner;

public class Lista {
    Scanner sc = new Scanner(System.in);
    private Turn head;
    private Turn current;

    public String menu(String temporalOpt){

        //System.out.println(toPrint(head));
        System.out.println("Digite una opción:\n1. Dar turno\n" +
                "2. Mostrar turno actual\n" +
                "3. Pasar turno\n" +
                "4. Eliminar turno actual y seguir\n"+
                "5. Salir\n");
        String opt = sc.nextLine();
        if(temporalOpt!=""){
            opt=temporalOpt;
        }

        //Dar Turno, Crear turno
        if(opt.equals("1")){
            if(head==null){
                addLast(new Turn(1));
                current = head;
            } else {
                addLast(new Turn(head.getPrev().getIndex()+1));
            }
            System.out.println("Se ha agregado el turno "+head.getPrev().getIndex());
            return menu("");
        }
        // Mostrar Turno actual
        else if (opt.equals("2")) {
            if (current!=null){
                //Current is initialized when the first Turn enter; If there are no turns, Current will be null
                System.out.println("El turno actual es: "+current.getIndex());
            } else {
                System.out.println("No hay turnos aún");
            }
            return menu("");
        }
        //Pasar Turno
        else if (opt.equals("3")) {
            if(head!=null){
                if(current==head){
                    head.setTurnCounter(head.getTurnCounter()+1);
                }else {
                    current.setTurnCounter(current.getTurnCounter()+1);
                }
                current = current.getNext();
                if(current.getPrev().getTurnCounter()==3){
                    if(current.getPrev()==head){
                        deleteTurn(current.getIndex());
                    }
                    //Redireccion hacia opcion 4
                    return menu("4");
                }
            }
            return menu("");
        }
        // Eliminar turno actual y seguir
        else if (opt.equals("4")) {
            if (current!=null){
                deleteTurn(current.getIndex());
            }
        }

        if(opt.equals("5")){
            System.out.println("Fin del Programa");
            return opt;
        }else {
            return menu("");
        }
    }


    public void addLast(Turn input){
        if (head==null){
            head = input;
            head.setNext(input);
            head.setPrev(input);
        }
        else {
            Turn tail = head.getPrev();
            //Prev
            head.setPrev(input);
            input.setPrev(tail);

            //Next
            tail.setNext(input);
            input.setNext(head);
        }
    }

    public String toPrint(){
        return toPrint(head);
    }

    private String toPrint(Turn current){
        //Caso base
        if(head==null){
            return "This list is Empty";
        }
        if(current.getNext() == head){
            return current.toString();
        }
        //Llamado recursivo
        System.out.println(current.toString());
        return toPrint(current.getNext());
    }

    public void deleteTurn(int id){
        Turn toDelete = findTurn(id);
        Turn temporalPrev = toDelete.getPrev(); //Prev
        Turn temporalNext = toDelete.getNext(); //Next

        if(toDelete==head){
            if(head.getNext() == head){
                head = null;
                current = null;
            }else {
                head = temporalNext;
                temporalPrev.setNext(head);
                head.setPrev(temporalPrev);
                current = head;
            }
        }
        else if(toDelete!=null){
            temporalPrev.setNext(temporalNext);
            temporalNext.setPrev(temporalPrev);
            current = temporalNext;
        }
    }

    public Turn findTurn(int id){
        return findTurn(id, head);
    }

    private Turn findTurn(int id, Turn current){
        //Caso base
        if(current.getIndex()==id){
            return current;
        }
        if(current.getNext() == head){
            return null;
        }
        //Llamado recursivo
        return findTurn(id, current.getNext());
    }
}
