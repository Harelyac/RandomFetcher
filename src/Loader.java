import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;


public class Loader {
    private static Post [] posts;
    private static Comment [] comments;
    private static Album [] albums;
    private static Photo [] photos;
    private static Todo [] todos;
    private static User [] users;


    public static HashMap<Integer, ArrayList<Post>> postsPerUser = new HashMap<>();
    public static HashMap<Integer, ArrayList<Album>> albumsPerUser = new HashMap<>();
    public static HashMap<Integer, ArrayList<Todo>> todosPerUser = new HashMap<>();
    public static HashMap<Integer, ArrayList<Photo>> photosPerAlbum = new HashMap<>();
    public static HashMap<Integer, ArrayList<Comment>> commentPerPosts = new HashMap<>();
    public static HashMap<Integer, User> userById = new HashMap<>();


    public static void LoadAll() {
           HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts" )).build();
           client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                   .thenApply(HttpResponse::body) // gets response and output body
                   .thenApply(Loader::parser) // gets body and output parsing result
                   .join();

           HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/comments" )).build();
           client.sendAsync(request1, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // gets response and output body
                .thenApply(Loader::parser1) // gets body and output parsing result
                .join();

            HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums" )).build();
            client.sendAsync(request2, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // gets response and output body
                .thenApply(Loader::parser2) // gets body and output parsing result
                .join();


            HttpRequest request3 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/photos" )).build();
            client.sendAsync(request3, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // gets response and output body
                .thenApply(Loader::parser3) // gets body and output parsing result
                .join();

            HttpRequest request4 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/todos" )).build();
            client.sendAsync(request4, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // gets response and output body
                .thenApply(Loader::parser4) // gets body and output parsing result
                .join();

            HttpRequest request5 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/users" )).build();
            client.sendAsync(request5, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // gets response and output body
                .thenApply(Loader::parser5) // gets body and output parsing result
                .join();
    }

    private static String parser(String responseBody){
        Gson gson = new Gson();
        posts = gson.fromJson(responseBody, Post[].class); // takes array of json objects and convert to array of java objects

        // shuffle the results to appropriate hashmap
        for(Post post : posts){
            postsPerUser.putIfAbsent(post.getUserId(), new ArrayList<Post>());
            postsPerUser.get(post.getUserId()).add(post);
        }
        return "";
    }

    private static String parser1(String responseBody){
        Gson gson = new Gson();
        comments = gson.fromJson(responseBody, Comment[].class); // takes array of json objects and convert to array of java objects

        // shuffle the results to appropriate hashmap
        for(Comment comment : comments){
            commentPerPosts.putIfAbsent(comment.getPostId(), new ArrayList<Comment>());
            commentPerPosts.get(comment.getPostId()).add(comment);
        }
        return "";
    }

    private static String parser2(String responseBody){
        Gson gson = new Gson();
        albums = gson.fromJson(responseBody, Album[].class); // takes array of json objects and convert to array of java objects

        for(Album album : albums){
            albumsPerUser.putIfAbsent(album.getUserId(), new ArrayList<Album>());
            albumsPerUser.get(album.getUserId()).add(album);
        }
        return "";
    }

    private static String parser3(String responseBody){
        Gson gson = new Gson();
        photos = gson.fromJson(responseBody, Photo[].class); // takes array of json objects and convert to array of java objects

        for(Photo photo : photos){
            photosPerAlbum.putIfAbsent(photo.getAlbumId(), new ArrayList<Photo>());
            photosPerAlbum.get(photo.getAlbumId()).add(photo);
        }
        return "";
    }

    private static String parser4(String responseBody){
        Gson gson = new Gson();
        todos = gson.fromJson(responseBody, Todo[].class); // takes array of json objects and convert to array of java objects

        for(Todo todo : todos){
            todosPerUser.putIfAbsent(todo.getUserId(), new ArrayList<Todo>());
            todosPerUser.get(todo.getUserId()).add(todo);
        }
        return "";
    }

    private static String parser5(String responseBody){
        Gson gson = new Gson();
        users = gson.fromJson(responseBody, User[].class); // takes array of json objects and convert to array of java objects

        for(User user : users){
            userById.put(user.getId(), user);
        }
        return "";
    }
}
