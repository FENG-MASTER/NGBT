package com.fengmaster.ngbt.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by Feng-master on 2017/8/31.
 * 测试
 */
public class Test {

    public static class Box{
        public int w;
        public int r;

        public Box(int w) {
            this.w = w;
        }

        public int random(int sum){
            return new Random().nextInt(sum/10+w);
        }
    }

    public static void main(String args[]){

        List<Box> list=new ArrayList<>();

        Random random=new Random();

        for (int i=0;i<20;i++){

            list.add(new Box(random.nextInt(2000)));

        }
        System.out.println("------------------------");
        System.out.println("");
        list.forEach(new Consumer<Box>() {
            @Override
            public void accept(Box box) {
                System.out.print(box.w+",");
            }
        });
        System.out.println("");
        System.out.println("------------------------");

        int sum=0;

        for (Box box : list) {
            sum+=box.w;
        }




        for (int i=0;i<100;i++){
            System.out.println("------------------------");
            System.out.println("");

            int finalSum = sum;
            list.sort(Comparator.comparingInt(o -> o.random(finalSum)));

            list.forEach(new Consumer<Box>() {
                @Override
                public void accept(Box box) {
                    System.out.print(box.w+",");
                }
            });

            System.out.println("");
            System.out.println("------------------------");


        }


    }
}
