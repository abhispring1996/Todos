package com.example.revision;

import java.util.ArrayList;
import java.util.List;

public class Concurrency {

    int i=1;
    public static void main(String[] args) {
        new Concurrency().printNumsWithDiffThreadStrategy();
    }

    /**
     * To print 0-10 using on thread , 10-40 using 4 threads and 40-50 using one thread
     */
    public void printNumsWithDiffThreadStrategy(){

        while(i<=10){
            printItem(i);
            i++;
        }

        List<Thread> threadList = new ArrayList<>();

        for(int j=0;j<4;j++){

         threadList.add(new Thread(() -> {
             while(i<=40){
                  printItem(i);
                  i++;
             }
         }));
        }

        threadList.forEach(thread -> thread.start());

        while(i<50){
            printItem(i);
            i++;
        }

    }

    public synchronized void printItem(int index){
        System.out.println(Thread.currentThread().getName()+" : index : "+index);
    }


}
