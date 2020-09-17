/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.*;


public class Main {
    public static void main(String args []){

        // request data from endpoint and parse and shuffle it into data structures
        Loader loader = new Loader();
        loader.LoadAll();

        // make queries to data
        Controller controller = new Controller();

        // print completed to do for given user id
        for(Todo todo : controller.getToDosSummaryPerUser(1)){
             System.out.println(todo);
        }


         //print completed to do for all users
         for(Map.Entry<Integer, List<Todo>> pair : controller.getToDosSummary().entrySet()){
             System.out.println(pair);
         }

         //print number of comments on each post for given user id (only more than zero)
         for(Map.Entry<Integer, Integer> pair : controller.getPostsSummary(2).entrySet()){
             System.out.println(pair);
         }

         // print all albums of a specific user that contains more photos than a given threshold
         for (Album album : controller.getAlbumsByThreshold(1, 49)){
             System.out.println(album);
         }

    }
}
