/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args []){

        // request data from endpoint and parse it into data structure
        Loader loader = new Loader();
        loader.LoadAll();


        public List<Todo> getToDosSummaryPerUser(int userId){
            Loader.todosPerUser.get(userId)
                    .stream()
                    .filter(todo -> !todo.getCompleted())
                    .collect(Collectors.toList());
        }

        public Map<Integer,List<Todo>> getToDosSummary(){
            HashMap<Integer ,List<Todo>> toDoSummary = null;
            for(Integer userId : Loader.userById.keySet()){
                toDoSummary.put(userId, getToDosSummaryPerUser(userId));
            }

            return toDoSummary;
        }


        public ArrayList<String> getPostsSummary(int userId){
            ArrayList<String> emailsOnPosts = new ArrayList<>();
            for(Post post : Loader.postsPerUser.get(userId)){
                for(Comment comment : Loader.commentPerPosts.get(post.getId())){
                    emailsOnPosts.add(comment.getEmail());
                }
            }
            return emailsOnPosts;

        }

        public ArrayList<Album> getAlbumsByThreshold(int userId, int thrshold){
            ArrayList<Album> specialAlbums = new ArrayList<>();
            for(Album album : Loader.albumsPerUser.get(userId)){
                if (Loader.photosPerAlbum.get(album.getId()).size() > thrshold){
                    specialAlbums.add(album);
                }
            }
        }

    }
}
