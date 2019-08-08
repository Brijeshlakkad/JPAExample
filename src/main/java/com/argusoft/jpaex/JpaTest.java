package com.argusoft.jpaex;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaTest {

    private static EntityManager em;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("MessagePU");
        em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 4) {
            System.out.println("Choose option: ");
            System.out.println("1. Enter an message");
            System.out.println("2. Show an message");
            System.out.println("3. Show all message details");
            System.out.println("4. Exit");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    createMessage(sc);
                    break;
                case 2:
                    showMessage(sc);
                    break;
                case 3:
                    showMessages(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private static void createMessage(Scanner sc) {
        System.out.println("Enter a message: ");
        String text = sc.next();
        em.getTransaction().begin();
        HelloWorld msg = new HelloWorld(text);
        em.persist(msg);
        em.getTransaction().commit();
    }

    private static void showMessage(Scanner sc) {
        System.out.println("Enter message id:  ");
        int id = sc.nextInt();
        em.getTransaction().begin();
        HelloWorld msg = em.find(HelloWorld.class, id);
        em.getTransaction().commit();
        System.out.println(msg);
    }

    private static void showMessages(Scanner sc) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM HelloWorld e");
        em.getTransaction().commit();
        List<HelloWorld> msgList = query.getResultList();
        for(HelloWorld msg : msgList){
            System.out.println(msg);
        }
    }
}
