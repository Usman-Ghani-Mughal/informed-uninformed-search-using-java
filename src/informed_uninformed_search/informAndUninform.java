/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author usman
 */
package informed_uninformed_search;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node {

    int gn;
    int hn;
    int fn;
    String name;
    Node Previous;
    List<Node> finch;

    Node() {
        this.finch = null;
        this.gn = 0;
        this.hn = 0;
        this.fn = 0;
        this.name = null;
        this.Previous = null;
    }
}

class Algos {
    // Declaration of all Atributes
    protected List<Node> open;
    protected List<Node> close;
    protected List<Node> Visited_Nodes;
    protected List<Node> GRAPH;
    protected int number_of_nodes;
    public Scanner input;

    /*
    description:
        This is constructor used to init all attriutes
    */
    Algos() {
        this.GRAPH = new ArrayList<>();
        input = new Scanner(System.in);
    }

    
    /*
    description:
        This function used to diplay Graph
    */
    public void displayGraph() {
        System.out.println("\n<<<< ==================================================================================  >>>>\n");
        for (Node node : this.GRAPH) {
            System.out.print("Node " + node.name + " : ");
            for (Node edges : node.finch) {
                System.out.print("-> " + edges.name + " (" + edges.gn + ")");
            }
            System.out.println("");
        }
        System.out.println("\n<<<< ==================================================================================  >>>>\n");
    }

    
    /*
    description:
        this Method is used to init and re init the open and close list
    */
    private void initializeLists() {
        this.open = new LinkedList<>();
        this.close = new LinkedList<>();
        this.Visited_Nodes = new LinkedList<>();
        for(int i = 0; i<this.number_of_nodes;i++)
        {
            this.GRAPH.get(i).Previous = null;
        }
    }

    
    /*
    description:
        this Method is used for finding the index of min value of gn in open list
    */
    public int findMin_gn() {
        int min = this.open.get(0).gn;
        int min_index = 0;
        for (int i = 0; i < this.open.size(); i++) {
            if (min > this.open.get(i).gn) {
                min = this.open.get(i).gn;
                min_index = i;
            }
        }
        return min_index;
    }
    
    /*
    description:
        this Method is used for finding the index of min value of hn in open list
    */      
    public int findMin_hn() {
        int min = this.open.get(0).hn;
        int min_index = 0;
        for (int i = 0; i < this.open.size(); i++) {
            if (min > this.open.get(i).hn) {
                min = this.open.get(i).hn;
                min_index = i;
            }
        }
        return min_index;
    }
    
    /*
    description:
        this Method is used for finding the index of min value of fn in open list
    */
    public int findMin_fnInOpen() {
        int min = this.open.get(0).fn;
        int min_index = 0;
        for (int i = 0; i < this.open.size(); i++) {
            if (min > this.open.get(i).fn) {
                min = this.open.get(i).fn;
                min_index = i;
            }
        }
        return min_index;
    }
    
    
    /*
    Arg 1: Name, string
    Arg 2: list, Node list
    description:
        it will search the given name in the given list: if it finds it will
        return the index of that name in the list other wise it will return -1.  
    */
    public int contains(String name, List<Node> list) {
        int result = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(name)) {
                result = i;
                return result;
            }
        }
        return result;
    }

    
    /*
    Arg 1: Node list
    description:
        it method will print all fn value of node present in given list  
    */
    protected void printList_fn(List<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + "(" + list.get(i).fn + ")  ");
        }
        System.out.println("");
    }
    
    
    /*
    Arg 1: Node list
    description:
        it method will print all fn value of node present in given list  
    */
    protected void printList_hn(List<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + "(" + list.get(i).hn + ")  ");
        }
        System.out.println("");
    }
    
    
    /*
    Arg 1: Node list
    description:
        it method will print all names of node present in given list  
    */
    protected void printList_names(List<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name+" , ");
        }
        System.out.println("");
    }
    
    
    /*
    Arg 1: Node list
    description:
        it method will print all gn value of node present in given list
        
    */
    protected void printList_gn(List<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + "(" + list.get(i).gn + ")  ");
        }
        System.out.println("");
    }

    
    /*
    description:
        it method will apply uniform cost search on given Graph
        
    */
    public void uniformCostSearch() {
        // Make sure Open and Close lists are Empty.
        this.initializeLists();
        
        // Take the start node
        System.out.print("Please Enter the name of start Node : ");
        String start_node = this.input.next();
        // Take the Goal node
        System.out.print("Please Enter the name of Goal Node : ");
        String goal_node = this.input.next();
        
        
        // Check if start and Goal nodes are presnt in the Graph
        int index_goal = this.contains(goal_node, this.GRAPH);
        int index_start = this.contains(start_node, this.GRAPH);
        
        // if start or goal node is not in graph then stop here.
        if (index_goal == -1 || index_start == -1) {
            System.out.println("In Valid Goal Node or Start node");
            return;
        }
        
        // add start Node in the open list
        this.open.add(this.GRAPH.get(index_start));
        
        
        System.out.print("Open List : ");
        this.printList_gn(this.open);
        
        System.out.print("Close List : ");
        this.printList_gn(this.close);
        
        // Satrt searching untill Open list is empty
        while (!open.isEmpty()) {
            
            // Find the index of node in open list which hav min gn value
            int min_index = this.findMin_gn();
            
            // remove the min value node from open list
            Node visited_node = this.open.remove(min_index);
            
            // After iterate through its finch add this node into close list
            this.close.add(visited_node);
            this.Visited_Nodes.add(visited_node);
            
            
            System.out.print("Open List : ");
            this.printList_gn(this.open);
        
            System.out.print("Close List : ");
            this.printList_gn(this.close);
            
            
            // check if Goal node is visited and found in the close list then stop here
            int goalnode_found = this.contains(goal_node, close);
            if (goalnode_found != -1) {
                System.out.println(">>> ******* \tGoal Node Found\t >>> ******* <<<");
                System.out.println("Cost to Goal Node : " + this.close.get(goalnode_found).gn);
                System.out.println("Path to Goal Node : "+ this.printPath(visited_node));
                System.out.print("Toal Visited Nodes  : ");
                this.printList_names(this.Visited_Nodes);
                return;
            }
            
            
            // iterate through the finch of Removed Node 
            for (Node succ : visited_node.finch) 
            {
                
                // if Node is already resent in the finch then indexOf_node_in_Finch will have the index of it
                // if Node is not in open list then indexOf_node_in_Finch will have -1
                int indexOf_node_in_Finch = this.contains(succ.name, this.open);
                
                // this will have the index of that node in the Graph
                int index_of_node_in_graph = this.contains(succ.name, this.GRAPH);
                
                // calculate total cost
                int total_cost = succ.gn + visited_node.gn;
                
                if (indexOf_node_in_Finch != -1) {
                    // if new gn is less then old gn then Replace it with new gn
                    if (total_cost < this.open.get(indexOf_node_in_Finch).gn) 
                    {
                        this.open.get(indexOf_node_in_Finch).Previous = visited_node;
                        this.open.get(indexOf_node_in_Finch).gn = total_cost;
                    }
                } 
                else 
                {   
                    Node temp_ = this.GRAPH.get(index_of_node_in_graph);
                    temp_.Previous = visited_node;
                    temp_.gn = total_cost;
                    this.open.add(temp_);
                }
            }
            
        }
        System.out.println(">>> ******* \tGoal Node is not Found\t >>> ******* <<<");
    }
    
    
    
    /*
    description:
        it method will apply Best First search on given Graph
        
    */
    public void BestFirstSearch() {
        // Make sure Open and Close lists are Empty.
        this.initializeLists();
        
        // Take the start node
        System.out.print("Please Enter the name of start Node : ");
        String start_node = this.input.next();
        // Take the Goal node
        System.out.print("Please Enter the name of Goal Node : ");
        String goal_node = this.input.next();
        
        
        // Check if start and Goal nodes are presnt in the Graph
        int index_goal = this.contains(goal_node, this.GRAPH);
        int index_start = this.contains(start_node, this.GRAPH);
        
        // if start or goal node is not in graph then stop here.
        if (index_goal == -1 || index_start == -1) {
            System.out.println("In Valid Goal Node or Start node");
            return;
        }
        
        // add start Node in the open list
        this.open.add(this.GRAPH.get(index_start));
        
        
        // Satrt searching untill Open list is empty
        while (!open.isEmpty()) {
            
            // Find the index of node in open list which hav min gn value
            int min_index = this.findMin_hn();
            
            // remove the min value node from open list
            Node visited_node = this.open.remove(min_index);
            
            
            
            // After iterate through its finch add this node into close list
            this.close.add(visited_node);
            this.Visited_Nodes.add(visited_node);
            
            
            System.out.print("Open List : ");
            this.printList_hn(this.open);
        
            System.out.print("Close List : ");
            this.printList_hn(this.close);
            
            
            // check if Goal node is visited and found in the close list then stop here
            int goalnode_found = this.contains(goal_node, close);
            if (goalnode_found != -1) {
                System.out.println(">>> ******* \tGoal Node Found\t >>> ******* <<<");
                System.out.println("Cost to Goal Node : " + this.close.get(goalnode_found).hn);
                System.out.print("Path to Goal Node : "+ this.printPath(visited_node));
                System.out.print("Toal Visited Nodes  : ");
                this.printList_names(this.Visited_Nodes);
                return;
            }
            
            
            // iterate through the finch of Removed Node 
            for (Node succ : visited_node.finch) 
            {
                
                // if Node is already resent in the finch then indexOf_node_in_Finch will have the index of it
                // if Node is not in open list then indexOf_node_in_Finch will have -1
                int indexOf_node_in_Finch = this.contains(succ.name, this.open);
                
                // this will have the index of that node in the Graph
                int index_of_node_in_graph = this.contains(succ.name, this.GRAPH);
                
                // calculate total cost
                int total_cost = succ.hn + visited_node.hn;
                
                if (indexOf_node_in_Finch != -1) {
                    // if new gn is less then old gn then Replace it with new gn
                    if (total_cost < this.open.get(indexOf_node_in_Finch).hn) 
                    {
                        this.open.get(indexOf_node_in_Finch).Previous = visited_node;
                        this.open.get(indexOf_node_in_Finch).hn = total_cost;
                    }
                } 
                else 
                {   
                    Node temp_ = this.GRAPH.get(index_of_node_in_graph);
                    temp_.Previous = visited_node;
                    temp_.hn = total_cost;
                    this.open.add(temp_);
                }
            }
            
            
        }
        System.out.println(">>> ******* \tGoal Node is not Found\t >>> ******* <<<");
    }
    
    
    /*
    description:
        it method will apply A* search on given Graph
        
    */
    public void aStarSerch() {
        // Make sure Open and Close lists are Empty.
        this.initializeLists();
        
        // Take the start node
        System.out.print("Please Enter the name of start Node : ");
        String start_node = this.input.next();
        // Take the Goal node
        System.out.print("Please Enter the name of Goal Node : ");
        String goal_node = this.input.next();
        
        
        // Check if start and Goal nodes are presnt in the Graph
        int index_goal = this.contains(goal_node, this.GRAPH);
        int index_start = this.contains(start_node, this.GRAPH);
        
        // if start or goal node is not in graph then stop here.
        if (index_goal == -1 || index_start == -1) {
            System.out.println("In Valid Goal Node or Start node");
            return;
        }
        
        // add start Node in the open list
        this.open.add(this.GRAPH.get(index_start));
        
        System.out.print("Open List : ");
        this.printList_fn(this.open);
        
        System.out.print("Close List : ");
        this.printList_fn(this.close);
        
        // Satrt searching untill Open list is empty
        while (!open.isEmpty()) {
            
            
            // Find the index of node in open list which hav min fn value
            int min_index = this.findMin_fnInOpen();
            
            // remove the min value node from open list
            Node visited_node = this.open.remove(min_index);
            
            // After iterate through its finch add this node into close list
            this.close.add(visited_node);
            this.Visited_Nodes.add(visited_node);
            
            System.out.print("Open List : ");
            this.printList_fn(this.open);
        
            System.out.print("Close List : ");
            this.printList_fn(this.close);
            
            
            // check if Goal node is visited and found in the close list then stop here
            int goalnode_found = this.contains(goal_node, close);
            if (goalnode_found != -1) {
                System.out.println(">>> ******* \tGoal Node Found\t >>> ******* <<<");
                System.out.println("Cost to Goal Node : " + this.close.get(goalnode_found).fn);
                System.out.println("Path to Goal Node : "+ this.printPath(visited_node));
                System.out.print("Toal Visited Nodes  : ");
                this.printList_names(this.Visited_Nodes);
                return;
            }
            
            // iterate through the finch of Removed Node 
            for (Node succ : visited_node.finch) 
            {
                
                // get the index of node in open list, if not present then index will be -1
                int indexOf_node_in_open = this.contains(succ.name, this.open);
                
                // get the index of node in close list, if not present then index will be -1
                int indexOf_node_in_close = this.contains(succ.name, this.close);
                
                // this will have the index of that node in the Graph
                int index_of_node_in_graph = this.contains(succ.name, this.GRAPH);
                
                // calculate total cost       
                //           gn of this node   +   gn of previous all nodes 
                int total_gn   =  succ.gn + visited_node.gn;
                int total_cost = total_gn + succ.hn;
                
                // if already present in Open list
                if (indexOf_node_in_open != -1) {
                    // if new gn is less then old gn then Replace it with new gn in open list
                    if (total_cost < this.open.get(indexOf_node_in_open).fn) 
                    {
                        this.open.get(indexOf_node_in_open).Previous = visited_node;
                        this.open.get(indexOf_node_in_open).fn = total_cost;
                        this.open.get(indexOf_node_in_open).gn = total_gn;
                    }
                } 
                // if already present in Close list
                else if(indexOf_node_in_close != -1)
                {
                    // if new cost is less then old cost
                    if(total_cost < this.close.get(indexOf_node_in_close).fn)
                    {
                        // remove from close
                        this.close.remove(indexOf_node_in_close);
                        
                        Node temp_ = this.GRAPH.get(index_of_node_in_graph);
                        temp_.Previous = visited_node;
                        temp_.fn = total_cost;
                        temp_.gn = total_gn;
                        
                        // add in open list with new cost
                        this.open.add(temp_);
                    }
                }
                // if not resent in open or close list
                else 
                {   
                    Node temp_ = this.GRAPH.get(index_of_node_in_graph);
                    temp_.Previous = visited_node;
                    temp_.fn = total_cost;
                    temp_.gn = total_gn;
                    
                    // add in open list
                    this.open.add(temp_);
                }
            }
        }
        System.out.println(">>> ******* \tGoal Node is not Found\t >>> ******* <<<");
    }
    
    
    
    /*
    Arg 1: Node goalnode
    description:
        it method will return the path in string from start node to goal node
        
    */
    protected String printPath(Node goalnode)
    {
        Node previous = goalnode;
        String path  = "";
        while(previous != null)
        {
            path = previous.name + "-> "+path; 
            previous = previous.Previous;
            
        }
        return path;
    }
    
    
    /*
    description:
        it method is used take input from user and make graph of it
        
    */
    public void makeGraph() {
        // Number of Noodes/Vertices in Graph?
        System.out.print("Enter the Number of Nodes : ");
        this.number_of_nodes = this.input.nextInt();
        
        // Give Take Name and h(n) for every node in Graph
        for (int i = 0; i < this.number_of_nodes; i++) {
            Node temp_node = new Node();// Temp Node will be add in Graph
            System.out.println("------------------------------------------------------------------------------");
            // Take Name of Node
            System.out.print("Enter the Name of : " + i + " Node : ");
            temp_node.name = this.input.next();
            // Take h(n) of Node
            System.out.print("Enter the h(n) of : " + i + " Node : ");
            temp_node.hn = this.input.nextInt();
            
            System.out.println("------------------------------------------------------------------------------");
            // Make A finch which is empty now
            temp_node.finch = new LinkedList<>();
            
            // Add node to Graph
            this.GRAPH.add(temp_node);
        }

        System.out.println("Now Enter the Edges for All nodes");

        // Make All Edges for every node in Graph
        for (int i = 0; i < this.number_of_nodes; i++) {

            System.out.println("Enter the Edges For : " + this.GRAPH.get(i).name + " Node");
            
            // This will be false if user don't want to add any other edge from a Particular node
            boolean add_edge = true;
            
            while (add_edge) {
                
                System.out.println("Press 1 for Entering the Edge");
                System.out.println("Press 2 for Exit");
                int choice = this.input.nextInt();
                
                switch (choice) {
                    case 1: {
                        Node temp_eadge = new Node();
                        
                        // Name of node where Edge is Leading
                        System.out.print("Enter the name of node : ");
                        temp_eadge.name = this.input.next();
                        
                        // Cost of that edge
                        System.out.print("Enter the Cost of Edge : ");
                        temp_eadge.gn = this.input.nextInt();
                        
                        
                        int index_in_graph = this.contains(temp_eadge.name, this.GRAPH);
                        
                        temp_eadge.hn = this.GRAPH.get(index_in_graph).hn;
                        // Add into finch
                        this.GRAPH.get(i).finch.add(temp_eadge);
                        break;
                    }
                    case 2: {
                        // No more Edge for this node
                        add_edge = false;
                        break;
                    }

                }

            }
        }
    }
}

public class informAndUninform {

    public static void main(String[] args) {

        Algos algo = new Algos();
        algo.makeGraph();
        
        System.out.println(">>>>> ******************** This is The Graph ******************** <<<<<");
        algo.displayGraph();
        
        
        System.out.println("\n****************************************************************************************");
        System.out.println("\t>>>\t Uniform Cost Search  : Use g(n) \t<<<");
        System.out.println("****************************************************************************************");
        long before_time = System.currentTimeMillis();
        algo.uniformCostSearch();
        long after_time = System.currentTimeMillis();
        System.out.println("Time Taken by Uniform Cost Search in mili seconds : "+(after_time - before_time));
        System.out.println("****************************************************************************************");
        
        
        
        System.out.println("\n****************************************************************************************");
        System.out.println("\t>>>\t Best first Search  : Use h(n)\t<<<");
        System.out.println("****************************************************************************************");
        before_time = System.currentTimeMillis();
        algo.BestFirstSearch();
        after_time = System.currentTimeMillis();
        System.out.println("Time Taken by Uniform Cost Search in mili seconds : "+(after_time - before_time));
        System.out.println("\n****************************************************************************************");
        System.out.println("****************************************************************************************");
        
            
        
        System.out.println("\n****************************************************************************************");
        System.out.println("\t>>>\t A* Search  : Use g(n) + h(n)\t<<<");
        System.out.println("****************************************************************************************");
        before_time = System.currentTimeMillis();
        algo.aStarSerch();
        after_time = System.currentTimeMillis();
        System.out.println("Time Taken by Uniform Cost Search in mili seconds : "+(after_time - before_time));
        System.out.println("\n****************************************************************************************");
        System.out.println("****************************************************************************************");
        
    }

}
