/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionthreeb;

/**
 *
 * @author nayem
 */
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nayem
 */
public class Main {
    public static void main(String Args[]){
        new Main();
    }

    public Main() {
        List<student> studentList= new ArrayList<>();
        List<course>courseListA = new ArrayList<>() ,courseListB= new ArrayList<>();  
         student A= new student(121l,"bob",LocalDate.of(1995, Month.DECEMBER, 21),3.11,courseListA);
         A.getRegisteredCourseList().add(new course("CSE1011",""));
         A.getRegisteredCourseList().add(new course("CSE1021",""));
         A.getRegisteredCourseList().add(new course("MATH1024",""));
         A.getRegisteredCourseList().add(new course("CSE1011",""));
         A.getRegisteredCourseList().add(new course("Math1034",""));
         A.getRegisteredCourseList().add(new course("CSE1033",""));
         studentList.add(A);
         student B= new student(121l,"bob",LocalDate.of(1995, Month.DECEMBER, 21),3.11,courseListB);
         
         B.getRegisteredCourseList().add(new course("CSE1011",""));
         B.getRegisteredCourseList().add(new course("ENG1001",""));
         B.getRegisteredCourseList().add(new course("MATH1024",""));
         B.getRegisteredCourseList().add(new course("MATH1024",""));
         B.getRegisteredCourseList().add(new course("Math1033",""));
         B.getRegisteredCourseList().add(new course("MATH1024",""));
        
        
        studentList.add(B);
        
        
        //Collections.sort(studentList, new studentComparator());
        //studentComparator
       studentList.forEach(action-> {
            List<String> collect = action.getRegisteredCourseList().stream().map(course ->course.getCode()).distinct().sorted().collect(Collectors.toList());
         //   action.getRegisteredCourseList().clear();
          //  action.getRegisteredCourseList().add(collect.toArray());
          collect.forEach(System.out::println);
           System.out.println("\n");
            
});
      // collect.forEach(System.out::println);
      
//      
//      for(int i=0;i<studentList.size();i++){
//            System.out.println(studentList.get(i));
//            
//        }
      
      
    }
}
