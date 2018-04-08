/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JA
 */
public class CLI {

    private Scanner input = new Scanner(System.in);
    private ConceptList c;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GUI gui = new GUI();
        //CLI cli = new CLI();
    }
    
    public CLI(){
        c = new ConceptList();
        mainloop();
    }
    
    private void mainloop(){
        while(true){
            c.printTreeGraphic();
            System.out.println("[1] Select concept");
            System.out.println("[2] Set super concept");
            System.out.print("# ");
            int command = input.nextInt();
            switch(command){
                case 1:
                    selectNode();
                    break;
                case 2:
                    c.setSuper(getInputString());
                    break;
                default:
                    System.out.println("ERROR");
            }
        }
    }
    
    private void selectNode(){
        String value = "";
        String newValue = "";
        while(true){
            value = getInputString();
            if(c.search(value)!=null){
                System.out.println("[1] Set concept");
                System.out.println("[2] Add parent concept");
                System.out.println("[3] Add child concept");
                System.out.println("[4] Remove concept");
                System.out.println("[5] Remove branch");
                System.out.print("# ");
                int command = input.nextInt();
                switch(command){
                    case 1:
                        newValue = getInputString();
                        c.setValue(value, newValue);
                        break;
                    case 2:
                        newValue = getInputString();
                        c.addParent(value, newValue);
                        break;
                    case 3:
                        newValue = getInputString();
                        c.addChild(value, newValue);
                        break;
                    case 4:
                        c.removeSelf(value);
                        break;
                    case 5:
                        c.removeBranch(value);
                        break;
                    default:
                        System.out.println("ERROR");
                }
                break;
            }else{
                System.out.println("Concept not found.");
            }
        }
    }
    
    private String getInputString(){
        System.out.print("Enter node value: ");
        return new Scanner(System.in).nextLine().toLowerCase();
    }

}
