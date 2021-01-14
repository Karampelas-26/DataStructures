import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomizeBST implements MafiaInterface{

    private TreeNode root;

    public RandomizeBST() {
    }

    @Override
    public void insert(Suspect item) {
        if (root != null) {
            Suspect temp = searchByAFM(item.key());
            if (temp != null) {
                if (temp.key() == item.key()) {
                    System.out.println("Suspect already exists.");
                    return;
                }
            }
        }

        if (root == null)
            root = new TreeNode(item);

        TreeNode current = root;

        while (true) {
            if (current.getItem() == item)
                return;

            if (current.getItem().key() < item.key()) {
//            if (current.getItem().compareTo(item) < 0) {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(item));
                    return;
                } else {
                    current = current.getRight();
                }
            } else {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(item));
                    return;
                } else {
                    current = current.getLeft();
                }
            }
        }
    }

    @Override
    public void load(String filename) {
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String[] arr;
            int AFM;
            String firstName, lastName;
            double savings, taxedIncome;
            Suspect tempSuspect;
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                arr = data.split(" ");
                AFM = Integer.parseInt(arr[0]);
                firstName = arr[1];
                lastName = arr[2];
                savings = Double.parseDouble(arr[3]);
                taxedIncome = Double.parseDouble(arr[4]);
                insert(new Suspect(AFM, firstName, lastName, savings, taxedIncome));
            }
            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error in reading file.");
            fileNotFoundException.printStackTrace();
        }
    }

    @Override
    public void updateSavings(int AFM, double savings) {
        Suspect tempSuspect = searchByAFM(AFM);
        if (tempSuspect == null){
            System.err.println("No suspect with this AFM exists.");
            return;
        }
        tempSuspect.setSavings(savings);
    }


    @Override
    public Suspect searchByAFM(int AFM) {
        TreeNode temp = root;
        while (temp.getItem().key() != AFM){
            if (AFM > temp.getItem().key()){
                temp = temp.getLeft();
            }
            else {
                temp = temp.getRight();
            }
            if (temp == null){
                return null;
            }
        }
        return temp.getItem();
    }


//    @Override
//    public Suspect searchByAFM(int AFM) {
//        TreeNode temp = root;
//        while (true) {
//            if (temp == null)
//                return null;
//            if (temp.getItem().key() == AFM) {
//                return temp.getItem();
//            }
//            if(temp.getItem().key() < AFM){
//                temp = temp.getLeft();
//            }
//            else {
//                temp = temp.getRight();
//            }
//        }
//    }

    private void inOrderSearchLastName(StringDoubleEndedQueue<Suspect> queue, TreeNode treeNode, String last_name){
        if(treeNode != null){
            inOrderSearchLastName(queue, treeNode.getLeft(), last_name);
            if (treeNode.getItem().getLastName().equals(last_name)) {
                queue.addLast(treeNode.getItem());
                if (queue.size()%5 == 0){
                    queue.printQueue(System.out);
                }
            }
            inOrderSearchLastName(queue, treeNode.getRight(), last_name);
        }
    }
    @Override
    public StringDoubleEndedQueue<Suspect> searchByLastName(String last_name) {
        StringDoubleEndedQueue<Suspect> susQueue = new StringDoubleEndedQueueImpl<>();
        int i = 0;
        inOrderSearchLastName(susQueue, root, last_name);
        return susQueue;
    }

    @Override
    public void remove(int AFM) {
        Suspect tempSuspect = searchByAFM(AFM);
        if (tempSuspect == null){
            System.err.println("Trying to remove an element that doesnt exists.");
            return;
        }
        TreeNode temp = root;
        TreeNode parent = null;

        while (true){
            if (temp == null)
                return;
            if(temp.getItem().key() == AFM)
                break;
            parent = temp;

            if(temp.getItem().key() < 0)
                temp = temp.getRight();
            else
                temp = temp.getLeft();
        }

        TreeNode replace = null;

        //only right
        if(temp.getLeft() == null)
            replace = temp.getRight();
        else if (temp.getRight() == null)
            replace = temp.getLeft();
        else {
            TreeNode findCurrent = temp.getRight();

            while (true) {
                if (findCurrent.getLeft() != null)
                    findCurrent = findCurrent.getLeft();
                else
                    break;
            }

            remove(findCurrent.getItem().key());
            findCurrent.setLeft(temp.getLeft());
            findCurrent.setRight(temp.getRight());

            replace = findCurrent;
        }

        if (parent == null)
            root = replace;
        else {
            if(parent.getLeft() == temp)
                parent.setLeft(replace);
            if(parent.getRight() == temp)
                parent.setRight(replace);
        }
    }

    private int countSuspects(TreeNode treeNode){
        if (treeNode == null)
            return 0;
        return 1 + countSuspects(treeNode.getLeft()) + countSuspects(treeNode.getRight());
    }

    private double countSavings(TreeNode treeNode){
        if (treeNode == null)
            return 0.0;
        return treeNode.getItem().getSavings() + countSavings(treeNode.getLeft()) + countSavings(treeNode.getRight());
    }

    @Override
    public double getMeanSavings() {
        return countSavings(root) / countSuspects(root);
    }

    private void helperTopSuspects(PriorityQueueInterface susPQ, TreeNode treeNode, int top_k){
        if (treeNode == null)
            return;
        helperTopSuspects(susPQ, treeNode.getLeft(),  top_k);
        susPQ.add(treeNode.getItem());
        helperTopSuspects(susPQ, treeNode.getRight(), top_k);

    }

    @Override
    public void printTopSuspects(int top_k) {

        PriorityQueueInterface<Suspect> susPQ = new HeapPriorityQueue<>(new SuspectComparator());
        helperTopSuspects(susPQ, root, top_k);
        System.out.println("Top " + top_k + " suspects are: ");
        for (int i = 0; i < top_k; i++)
            System.out.println(susPQ.getMax());

    }

    @Override
    public void printByAFM() {
        inOrderFromRightToLeft(root);
    }

    private void inOrderFromRightToLeft(TreeNode treeNode) {
        if (treeNode == null)
            return;
        inOrderFromRightToLeft(treeNode.getLeft());
        System.out.println(treeNode.getItem());
        inOrderFromRightToLeft(treeNode.getRight());

    }

    private static void printMenu(){
        System.out.println("\n=================MENU================");
        System.out.println("1. Insert suspect");
        System.out.println("2. Load a file with suspects");
        System.out.println("3. Update savings for a suspect");
        System.out.println("4. Search suspect by AFM");
        System.out.println("5. Search suspects with a last name");
        System.out.println("6. Remove suspect by AFM");
        System.out.println("7. Get mean savings from all suspects");
        System.out.println("8. Print Top K suspects");
        System.out.println("9. Print suspects by AFM");
        System.out.println("0. Exit the program");
        System.out.println("=====================================");
    }
    public static void main(String[] args) {
        RandomizeBST tree = new RandomizeBST();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        int choice = scanner.nextInt();
        while (choice != 0){
            switch (choice){
                case 1: //choice for insert suspect
                    System.out.print("AFM: ");
                    int AFM = scanner.nextInt();
                    System.out.print("First name: ");
                    String firstName = scanner.next();
                    System.out.print("Last name: ");
                    String lastName = scanner.next();
                    System.out.print("Savings: ");
                    double savings = scanner.nextDouble();
                    System.out.print("TaxedIncome: ");
                    double taxedIncome = scanner.nextDouble();
                    tree.insert(new Suspect(AFM, firstName, lastName, savings, taxedIncome));
                    break;
                case 2: //load the file with suspects
                    System.out.println("Please insert the absolute path of the file to load the suspects");
                    tree.load(scanner.next());
                    break;
                case 3: //update savings for a suspect
                    System.out.print("Choose suspect by AFM: ");
                    int tempAFM = scanner.nextInt();
                    System.out.print("Insert savings to update: ");
                    double tempSavings = scanner.nextDouble();
                    tree.updateSavings(tempAFM, tempSavings);
                    break;
                case 4: //search suspect from afm
                    System.out.print("Type AFM for the suspect to search: ");
                    int searchAFM = scanner.nextInt();
                    System.out.println(tree.searchByAFM(searchAFM));
                    break;
                case 5: //search suspects with last name
                    System.out.print("Type the last name to search suspects: ");
                    String searchLastName = scanner.next();
                    StringDoubleEndedQueue<Suspect> tempQueue = tree.searchByLastName(searchLastName);
                    System.out.println("All suspects with last name: " + searchLastName);
                    tempQueue.printQueue(System.out);
                    break;
                case 6: //remove suspect
                    System.out.print("Type AFM for the suspect to remove: ");
                    int removeAFM = scanner.nextInt();
                    tree.remove(removeAFM);
                    break;
                case 7: //print meanSavings
                    System.out.println(tree.getMeanSavings());
                    break;
                case 8: // print top k suspects
                    System.out.print("Please choose k top suspects to print: ");
                    int topSuspects = scanner.nextInt();
                    tree.printTopSuspects(topSuspects);
                    break;
                case 9: //print all suspects
                    tree.printByAFM();
                    break;
                default:
                    choice = 0;
                    break;
            }
            printMenu();
            choice = scanner.nextInt();
        }
    }
}
