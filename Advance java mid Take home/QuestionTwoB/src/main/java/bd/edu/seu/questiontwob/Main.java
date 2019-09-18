/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questiontwob;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author nayem
 */
public class Main {
    public static void main(String Args[]){
        new Main();
    }

    public Main() {
        List<course>courseList = new ArrayList<>();
        courseList.add(new course("cse3011","bg"));
        courseList.add(new course("cse3013","math"));
         courseList.add(new course("cse3111","math2"));
         courseList.add(new course("cse3016","cs"));
        
        List<student> studentList= Arrays.asList(
        new student(121l,"bob",LocalDate.of(1995, Month.DECEMBER, 21),3.11,courseList),
        new student(122l,"mac",LocalDate.of(1991, Month.AUGUST, 12),3.12, courseList),
        new student(123l,"joe",LocalDate.of(1991, Month.AUGUST, 12),3.22,courseList ),
        new student(125l,"bery",LocalDate.of(1996, Month.APRIL, 8),3.58, courseList )
        );
        
        
      //Collections.sort(studentList, new studentComparator());
      
      
      
      //studentComparator
      
      Collections.sort(studentList,( o1, o2)-> {
          if(o1.getDob().equals(o2.getDob())){
            return (int)(o2.getCgpa()*100 - o1.getCgpa()*100);
        }
        else{
            return o2.getDob().compareTo(o1.getDob());
        }
      });
      
      
      
      for(int i=0;i<studentList.size();i++){
            System.out.println(studentList.get(i));
            
        }
      
      
    }
}
