package utils.customLinkedList;

public class CustomLinkedList <T>{
    private Node<T> head;
    private int size;
    public CustomLinkedList(){
        head = null;
        size = 0;
    }

    public void insert(T value){
        Node<T> newNode = new Node<T>(value);
        if(head == null){
            head = newNode;
        }else{
            Node currentNode = head;
            while(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    public void insertHead(T value){
        Node<T> newNode = new Node<T>(value);
        newNode.setNext(head);
        head = newNode;
    }

    public void insertAt(int index, T value){
        Node<T> nodeTobeInserted = new Node<T>(value);
        Node<T> node = head;
        for(int i = 0; i < index-1; i++){
            node = node.getNext();
        }
        nodeTobeInserted.setNext(node.getNext());
        node.setNext(nodeTobeInserted);
    }

    public void delete(int index){
        Node<T> node = head;
        for(int i = 0; i < index-1; i++){
            node = node.getNext();
        }
        node.setNext(node.getNext().getNext());
        size--;
    }

    public T getIndex(int index){
        Node<T> node = head;
        for(int i = 0; i < index-1; i++){
            if(node.getNext() == null){
                return null;
            }
            node = node.getNext();
        }
        return node.getData();
    }

    public void display(){
        if(head != null){
            Node<T> currentNode = head;
            while(currentNode.getNext() != null){
                System.out.println(currentNode.getData());
                currentNode = currentNode.getNext();
            }
            System.out.println(currentNode.getData());
        }
    }

    public int getSize(){
        return size;
    }

    public Node<T> getHead(){
        return head;
    }
}
