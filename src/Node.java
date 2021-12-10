import java.util.ArrayList;
import java.util.List;

public class Node{
    public boolean isMaxPlayer;
    public Board board;
    public int score;
    public List<Node> children;
    int[] coup;
    Node parentnode;
    public Node(boolean b, Board board)
    {
        this.isMaxPlayer = b;
        this.board = board;
        children = new ArrayList<>();
        score = board.valueBoard();
    }
    public Node(boolean b, Board board, int[] coup)
    {
        this.isMaxPlayer = b;
        this.board = board;
        children = new ArrayList<>();
        score = board.valueBoard();
        this.coup = coup;
    }
    public Node(Node parentnode,boolean b, Board board, int[] coup)
    {
        this.isMaxPlayer = b;
        this.board = board;
        children = new ArrayList<>();
        score = board.valueBoard();
        this.coup = coup;
        this.parentnode = parentnode;
    }
    public void addChild(Node newNode) {
        children.add(newNode);
    }
}