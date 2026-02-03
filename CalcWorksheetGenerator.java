import java.util.Scanner;
public class CalcWorksheetGenerator{
    static int choice1;
    static int choice2;
    public static void main(String []args){
        Scanner input=new Scanner(System.in);
        System.out.println("Topic!");
        System.out.println("1 for polynomial,2 for radical,3 for rational,");
        System.out.println("4 for exponetial");
        choice1=input.nextInt();//type of problem
        System.out.println("Difficulty");
        System.out.println("1 for easy(whole numbers)");
        System.out.println("2 for medium(good fractions)");
        System.out.println("3 for hard(evil fractions)");
        choice2=input.nextInt();//difficulty of problem
        //ts(this stuff) is the MAIN thinggy
        create();
        
        solve();
    }
    public static void create(){
        
        switch(choice1){  //topic
            case 1: //polynomials
                switch(choice2){ //difficulty
                    case 1:
                        poly1();
                        
                    case 2:
                        poly2();
                        
                    case 3:
                        poly3();
                }
            break;
            case 2: //
                switch(choice2){
                    case 1:
                        rad1();
                        
                    case 2:
                        rad2();
                        
                    case 3:
                        rad3();
                    
                }
            break;
            case 3:
                switch(choice2){
                    case 1:
                        rat1();
                    case 2:
                        rat2();
                        
                    case 3:
                        rat3();
                    
                }
            break;
            case 4:
                switch(choice2){
                    case 1:
                        ex1();
                        
                    case 2:
                        ex2();
                        
                    case 3:
                        ex3();
                    
                }
            break;
            
        }
        
        
    }//all methods for everything else</3
    public static void poly1(){
        
    }
    public static void poly2(){
        
    }
    public static void poly3(){
        
    }
    public static void rad1(){
        
    }
    public static void rad2(){
        
    }
    public static void rad3(){
        
    }
    public static void rat1(){
        
    }
    public static void rat2(){
        
    }
    public static void rat3(){
        
    }
    public static void ex1(){
          
    }
    public static void ex2(){
        
    }
    public static void ex3(){
        
    }
    public static void solve(){
        
    }
}
