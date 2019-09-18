/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionfourb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.util.Pair;

/**
 *
 * @author nayem
 */
class checkPasswordThreads implements Callable<List<Pair<String, String>>> {

    private final List<Pair<String, String>> passwordPair = new ArrayList<>();
    private final List<String> wordList;
    private final List<String> passwordList;
    private int start;
    private int end;

    public checkPasswordThreads(List<String> wordList, List<String> passwordList, int start, int end) {
        this.wordList = wordList;
        this.passwordList = passwordList;
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Pair<String, String>> call() throws Exception {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < passwordList.size(); j++) {
                if (md5.getMd5(wordList.get(i)).endsWith(passwordList.get(j))) {
                    passwordPair.add(new Pair(wordList.get(i), passwordList.get(j)));
                    break;
                }
            }
        }
        return passwordPair;
    }

}

public class QuestionFourB {

    List<String> wordList = new ArrayList<>();
    List<String> passwordList = new ArrayList<>();
    List<Pair<String, String>>passwordPair= new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new QuestionFourB();
    }

    public QuestionFourB() {
        
        
        loadFile();
        passwordPair.clear();
        long startTime = System.currentTimeMillis();
        parallel();
        long stopTime = System.currentTimeMillis();
        //print();
        passwordPair.clear();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);
        double p= (stopTime - startTime) / 1000.0;
        
        
        startTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(QuestionFourB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Noparallel();
        stopTime = System.currentTimeMillis();
        double np= (stopTime - startTime) / 1000.0;
        
        
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);
        double speedup= np/p;
        System.out.println("SpeedUp : "+speedup);
        
        
        
       

    }
    
    private void parallel(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService FixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
        int part = wordList.size() / availableProcessors;
        List<Future<List<Pair<String, String>>>> futureResultList = new ArrayList<>();
        
        for(int i=0;i<availableProcessors;i++){
            checkPasswordThreads chePasswordThreads=null;
           if(i!=availableProcessors-1) 
               chePasswordThreads=new checkPasswordThreads(wordList, passwordList, i*part, (i+1)*part);
           else
                chePasswordThreads=new checkPasswordThreads(wordList, passwordList, i*part, wordList.size());
            Future<List<Pair<String, String>>> submit = FixedThreadPool.submit(chePasswordThreads);
            futureResultList.add(submit);
        }
        FixedThreadPool.shutdown();
        List<Pair<String, String>>passwordPair= new ArrayList<>();
        
        for(int i=0;i<futureResultList.size();i++){
            try {
               passwordPair = futureResultList.get(i).get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(QuestionFourB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   private void Noparallel(){
      
       for(int i=0;i<wordList.size();i++){
           for(int j=0;j<passwordList.size();j++){
               if (md5.getMd5(wordList.get(i)).endsWith(passwordList.get(j))) {
                    passwordPair.add(new Pair(wordList.get(i), passwordList.get(j)));
                    break;
                }
           }
       }
       
   }
   private void print(){
       for(int i=0;i<passwordPair.size();i++){
            System.out.println("pasword: "+passwordPair.get(i).getKey()+" Hash: "+passwordPair.get(i).getValue());
        }
   }

    private void loadFile() {
        try {
            wordList = Files.lines(Paths.get("words.txt")).collect(Collectors.toList());
            passwordList = Files.lines(Paths.get("password.txt")).collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(QuestionFourB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
