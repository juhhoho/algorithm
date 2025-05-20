package implementation;

import java.util.*;

public class boj10814 {
    static class Person{
        int age, index;
        String name;
        public Person(int a, int i, String n){
            this.age = a;
            this.index = i;
            this.name = n;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Person> pq = new PriorityQueue<>((o1,o2)->{
           if (o1.age == o2.age){
               return o1.index - o2.index;
           }
           else return o1.age - o2.age;
        });

        for(int i = 0; i < n; i++){
            int age = sc.nextInt();
            String name = sc.next();
            pq.offer(new Person(age, i, name));
        }

        while(!pq.isEmpty()){
            Person p = pq.poll();
            System.out.println(p.age + " " + p.name);
        }
    }
}
