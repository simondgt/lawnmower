package org.lawnmower;

import java.util.List;
import java.util.function.BiPredicate;

public class Story {

    private final BiPredicate<Long, Long> surfaceTester;
    private final List<LawnmowerProgram> programs;

    public Story(BiPredicate<Long, Long> surfaceTester, List<LawnmowerProgram> programs){

        this.surfaceTester= surfaceTester;
        this.programs = programs;

    }


    public void run(){

        for(LawnmowerProgram program : programs){

            Lawnmower lm = new Lawnmower(program.getInitX(), program.getInitY(), program.getInitDirection(), surfaceTester);
            while(program.hasNext()){

                lm.executeCommand(program.next());

            }

            System.out.println(lm.getX()+" "+lm.getY()+" " + lm.getDirection());

        }

    }




}
