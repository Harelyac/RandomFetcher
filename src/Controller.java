/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    public List<Todo> getToDosSummaryPerUser(int userId){
        ArrayList<Todo> userCompletedToDo = new ArrayList<>();
        for(Todo todo : Loader.todosPerUser.get(userId)){
            if(todo.getCompleted()){
                userCompletedToDo.add(todo);
            }
        }
        return userCompletedToDo;
    }

    public Map<Integer,List<Todo>> getToDosSummary(){
        HashMap<Integer ,List<Todo>> toDoSummary = new HashMap<>();
        for(Integer userId : Loader.userById.keySet()){
            toDoSummary.put(userId, getToDosSummaryPerUser(userId));
        }
        return toDoSummary;
    }


    public Map<Integer,Integer> getPostsSummary(int userId){
        HashMap<Integer , Integer> postsSummary = new HashMap<>();
        for(Post post : Loader.postsPerUser.get(userId)) {
            int commentSize = Loader.commentPerPosts.get(post.getId()).size();
            if (commentSize > 0) {
                postsSummary.put(post.getId(), commentSize);
            }
        }
        return postsSummary;
    }

    public ArrayList<Album> getAlbumsByThreshold(int userId, int threshold){
        ArrayList<Album> specialAlbums = new ArrayList<>();
        for(Album album : Loader.albumsPerUser.get(userId)){
            if (Loader.photosPerAlbum.get(album.getId()).size() > threshold){
                specialAlbums.add(album);
            }
        }
        return specialAlbums;
    }
}
