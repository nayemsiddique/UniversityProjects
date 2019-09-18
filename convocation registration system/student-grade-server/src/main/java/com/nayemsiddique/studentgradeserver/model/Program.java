package com.nayemsiddique.studentgradeserver.model;



import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


    @NoArgsConstructor
    @Entity
    public class Program {


        private ProgramPrimaryKey programPrimaryKey;

        private double minimumCGPA;
        private int minimumCredit;




        public Program(ProgramPrimaryKey programPrimaryKey, double minimumCGPA, int minimumCredit) {
            this.programPrimaryKey = programPrimaryKey;
            this.minimumCGPA = minimumCGPA;
            this.minimumCredit = minimumCredit;
        }


        @Id
        public ProgramPrimaryKey getProgramPrimaryKey() {
            return programPrimaryKey;
        }

        public void setProgramPrimaryKey(ProgramPrimaryKey programPrimaryKey) {
            this.programPrimaryKey = programPrimaryKey;
        }

        public double getMinimumCGPA() {
           return minimumCGPA;
       }

       public void setMinimumCGPA(double minimumCGPA) {
           this.minimumCGPA = minimumCGPA;
       }

       public int getMinimumCredit() {
           return minimumCredit;
       }

       public void setMinimumCredit(int minimumCredit) {
           this.minimumCredit = minimumCredit;
       }

       @Override
       public String toString() {
           return "Program{" +
                   "programPrimaryKey=" + programPrimaryKey +
                   ", minimumCGPA=" + minimumCGPA +
                   ", minimumCredit=" + minimumCredit +

                   '}';
       }
       //        @Id
//        private String getLabel(){
//            return ProgramTitle.CSE +"."+semesterID;
//        }

    }
