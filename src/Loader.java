import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Loader {
    private static Post [] posts;
    private static Comment [] comments;
    private static Album [] albums;
    private static Photo [] photos;
    private static Todo [] todos;
    private static User [] users;

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
        for (int i = 0; i < posts.length; i++){
            System.out.println(posts[i]);
        }
        return "";
    }

    private static String parser1(String responseBody){
        Gson gson = new Gson();
        comments = gson.fromJson(responseBody, Comment[].class); // takes array of json objects and convert to array of java objects
        for (int i = 0; i < comments.length; i++){
            System.out.println(comments[i]);
        }
        return "";
    }

    private static String parser2(String responseBody){
        Gson gson = new Gson();
        albums = gson.fromJson(responseBody, Album[].class); // takes array of json objects and convert to array of java objects
        for (int i = 0; i < albums.length; i++){
            System.out.println(albums[i]);
        }
        return "";
    }

    private static String parser3(String responseBody){
        Gson gson = new Gson();
        photos = gson.fromJson(responseBody, Photo[].class); // takes array of json objects and convert to array of java objects
        for (int i = 0; i < photos.length; i++){
            System.out.println(photos[i]);
        }
        return "";
    }

    private static String parser4(String responseBody){
        Gson gson = new Gson();
        todos = gson.fromJson(responseBody, Todo[].class); // takes array of json objects and convert to array of java objects
        for (int i = 0; i < todos.length; i++){
            System.out.println(todos[i]);
        }
        return "";
    }

    private static String parser5(String responseBody){
        Gson gson = new Gson();
        users = gson.fromJson(responseBody, User[].class); // takes array of json objects and convert to array of java objects
        for (int i = 0; i < users.length; i++){
            System.out.println(users[i].toString());
        }
        return "";
    }
}
