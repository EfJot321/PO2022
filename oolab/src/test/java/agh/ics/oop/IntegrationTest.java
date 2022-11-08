package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//NIEAKTUALNE

// public class IntegrationTest {

//     Animal animalWalk(String[] args){
//         OptionsParser parser = new OptionsParser();
//         MoveDirection[] moveTab = parser.parse(args);
//         Animal animal = new Animal();
//         for(MoveDirection dir: moveTab){
//             animal.move(dir);
//         }
//         return animal;
//     }
    
//     @Test
//     public void walkingAnimaltest(){
//         String[] args;
//         //czy chodze do przodu i nie wychodze poza mape
//         args = new String[]{"forward",};
//         assertEquals("Pos: (2,3), Dir: Polnoc", animalWalk(args).toString());
//         args = new String[]{"forward","f", "f", "forward", "forward"};
//         assertEquals("Pos: (2,4), Dir: Polnoc", animalWalk(args).toString());

//         //czy chodze do tylu i nie wychodze poza mape
//         args = new String[]{"backward",};
//         assertEquals("Pos: (2,1), Dir: Polnoc", animalWalk(args).toString());
//         args = new String[]{"backward","b", "b", "backward", "backward"};
//         assertEquals("Pos: (2,0), Dir: Polnoc", animalWalk(args).toString());

//         //czy sie obracam w prawo i nie wychodze z prawej poza mape
//         args = new String[]{"right", "r"};
//         assertEquals("Pos: (2,2), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"right", "r", "forward"};
//         assertEquals("Pos: (2,1), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"right", "forward", "f", "f", "f", "f"};
//         assertEquals("Pos: (4,2), Dir: Wschod", animalWalk(args).toString());

//         //czy sie obracam w lewo i nie wychodze z lewej poza mape
//         args = new String[]{"left", "l"};
//         assertEquals("Pos: (2,2), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"left", "l", "forward"};
//         assertEquals("Pos: (2,1), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"left", "forward", "f", "f", "f", "f"};
//         assertEquals("Pos: (0,2), Dir: Zachod", animalWalk(args).toString());

//         //kombinacje wszystkiego
//         args = new String[]{"f", "forward", "l", "b", "left"};
//         assertEquals("Pos: (3,4), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"b", "left", "b", "backward", "r", "right", "forward", "right", "forward", "f"};
//         assertEquals("Pos: (4,0), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"r", "right", "right", "forward", "backward", "l", "forward", "b", "b", "b", "b", "b", "right"};
//         assertEquals("Pos: (2,4), Dir: Zachod", animalWalk(args).toString());

//         //kombinacje wszystkiego, tylko ze teraz z niepoprawnymi danymi
//         args = new String[]{"f", "forward","braykuv", "l", "b", "kdj", "a","left"};
//         assertEquals("Pos: (3,4), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"b", "left", "b", "hyilcbvyl", "backward", "r", "right", "k","forward", "rrr", "right", "forward", "f"};
//         assertEquals("Pos: (4,0), Dir: Poludnie", animalWalk(args).toString());
//         args = new String[]{"no tak","r", "ojaaaa","right", "right", "forward", "backward", "l", "PO to moj ulubiony przedmiot","forward", "b", "b", "b", "ok","b", "b", "right"};
//         assertEquals("Pos: (2,4), Dir: Zachod", animalWalk(args).toString());
    
//     }

    
// }
