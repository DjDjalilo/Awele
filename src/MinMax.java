import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class MinMax {
    Tree tree;
    public void constructTree(Board board) {
        tree = new Tree();
        Node root = new Node(null,true, board, null);
        tree.root = root;
        constructTree(root, 5);
    }
    private void constructTree(Node parentNode, int depth) {
        if(depth == 0)
            return;
        List<int[]> listofPossibleHeaps = parentNode.board.coupValide();
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer;
        Board tempB;
        for(int i = 0;i < listofPossibleHeaps.size();i++)
        {
            tempB = new Board(parentNode.board);
            //System.out.println(System.identityHashCode(tempB )+ " " + System.identityHashCode(parentNode.board ));
            if(listofPossibleHeaps.get(i)[1] == 0)
                tempB.getPlayer().jouerCoup(listofPossibleHeaps.get(i)[0],Color.ROUGE);
            else
                tempB.getPlayer().jouerCoup(listofPossibleHeaps.get(i)[0],Color.BLEU);
            Node newNode = new Node(parentNode,isChildMaxPlayer, tempB, listofPossibleHeaps.get(i));
            parentNode.addChild(newNode);
            constructTree(newNode,depth-1);
        }
        /*listofPossibleHeaps.forEach(n -> {
            tempB = new Board(parentNode.board);
            System.out.println(System.identityHashCode(tempB )+ " " + System.identityHashCode(parentNode.board ));
            if(n[1] == 0)
                tempB.getPlayer().jouerCoup(n[0],Color.ROUGE);
            else
                tempB.getPlayer().jouerCoup(n[0],Color.BLEU);
            Node newNode = new Node(parentNode,isChildMaxPlayer, tempB, n);
            parentNode.addChild(newNode);
            if (depth != 0) {
                depth--;
                constructTree(newNode);
            }
        });*/
    }
    /*private int[] getBestCoup(Node node) {
        resetDepth();
        if(depth == 0)
            return node.coup;
        List<Node> children = node.children;
        boolean isMaxPlayer = node.isMaxPlayer;
        children.forEach(child -> {
            if (child.getNoOfBones() == 0) {
                child.setScore(isMaxPlayer ? 1 : -1);
            } else {
                checkWin(child);
            }
        });
        Node bestChild = findBestChild(isMaxPlayer, children);
        node.setScore(bestChild.getScore());
    }*/
    public Node findBestChild(boolean isMaxPlayer, List<Node> children) {
        int[] scores = new int[children.size()];
        for(int i = 0; i < children.size(); i++) {
                scores[i] = children.get(i).score;
        }
        int minmax = children.get(0).score;
        int minimaxI = 0;
        if (isMaxPlayer)
            for(int i = 0; i < scores.length; i++) {
                if(minmax <= scores[i]) {
                    minmax = scores[i];
                    minimaxI = i;
                }
            }
        else
            for(int i = 0; i < scores.length; i++) {
                if(minmax >= scores[i])
                    minmax = scores[i];
                    minimaxI = i;
            }

        return children.get(minimaxI);
    }
}