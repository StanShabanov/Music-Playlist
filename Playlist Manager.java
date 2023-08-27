//Stan Shabanov
//1/20/2023
//Music playlist
import java.util.*;
import java.io.*;

public class MusicPlaylist {
    //This is the main method that runs the program. It initializes a Scanner object, 
    //a Queue object, and a Stack object, and prompts the user to enter a choice from a
    // menu of options until they choose to quit.
    //Parameters: The main method takes in an array of String objects called args, 
    //but it does not use them in this program.
    //Returns: The main method does not return anything.
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Queue<String> followingSong = new LinkedList<>();
        Stack<String> history = new Stack<>();
        System.out.println("Welcome to the CSE 122 Music Playlist!");
        String input = printing(console);

        while(!input.equalsIgnoreCase("Q")){
            if(input.equalsIgnoreCase("A")){
                addSongs(console, followingSong, history);
            }
            else if(input.equalsIgnoreCase("P")){
                playSongs(history, followingSong);
            }
            else if(input.equalsIgnoreCase("Pr")){
                printHistory(history);
            }
            else if(input.equalsIgnoreCase("C")){
                clearHistory(history);
            }
            else if(input.equalsIgnoreCase("D")){
                deleteHistory(history, console);
                System.out.println();
            }
            input = printing(console);
        }
    }
    //Purpose: This method prints a menu of options to the console, prompts the user
    // to enter a choice, and returns the choice in uppercase format.
    //Parameters: The printing method takes in a Scanner object called scan that is used 
    //to read the user's input.
    //Returns: The printing method returns a String object representing the user's choice 
    //in uppercase format.
    private static String printing (Scanner scan){
        System.out.println("(A) Add song");
        System.out.println("(P) Play song");
        System.out.println("(Pr) Print history");
        System.out.println("(C) Clear history");
        System.out.println("(D) Delete from history");
        System.out.println("(Q) Quit");
        System.out.println();
        System.out.print("Enter your choice: ");
        String input = scan.nextLine();
        return input.toUpperCase();
    }
    //Purpose: This method adds a song to the end of a queue and to the top of a stack.
    //Parameters: The addSongs method takes in a Scanner object called scan that is
    // used to read the user's input, a Queue object called followingSong that represents the playlist, 
    //and a Stack object called history that represents the history of played songs.
    //Returns: The addSongs method does not return anything.
    public static void addSongs (Scanner scan, Queue<String> followingSong, Stack<String> history){
        System.out.print("Enter song name: ");
        String input = scan.nextLine();
        followingSong.add(input);
        history.push(input);
        System.out.println("Successfully added " + input );
        System.out.println();
        System.out.println();
    }
    /*  Purpose: This method plays the next song in the playlist by removing it 
        from the front of the queue and adding it to the top of the history stack.
        Parameters: The playSongs method takes in a Stack object called history that 
        represents the history of played songs, and a Queue object called followingSong that represents the playlist.
        Returns: The playSongs method does not return anything.
    */
    public static void playSongs(Stack<String> history, Queue<String> followingSong){
        if(followingSong.isEmpty()){
            throw new IllegalStateException();
        }
        String concurrent = followingSong.remove();
        history.push(concurrent);
        System.out.println("Playing song: " + concurrent);
        System.out.println();
        System.out.println();
    }
    /*
    Purpose: This method prints the history of played songs in reverse order (i.e., from most recent to least recent).
    Parameters: The printHistory method takes in a Stack object called history that represents the history of played songs.
    Returns: The printHistory method does not return anything.
    */
    public static void printHistory(Stack<String> history){
        if(history.isEmpty()){
            throw new IllegalStateException();
        }
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < history.size(); i++){
            String concurrent = history.pop();
            stack.add(concurrent);
            System.out.println("    " + concurrent);
        }
        System.out.println();
        System.out.println();
        while(!stack.isEmpty()){
            history.push(stack.pop());
        }
    }
    /*
    Purpose: This method removes all songs from the history stack.
    Parameters: The clearHistory method takes in a Stack object called history that 
    represents the history of played songs.
    Returns: The clearHistory method does not return anything.
    */
    public static void clearHistory(Stack<String> history){
        while(!history.isEmpty()){
            history.pop();
        }
    }
    /*
    Purpose: This method removes a specified number of songs from the history stack, 
    either from the top (most recent) or from the bottom (least recent) of the stack.
    Parameters: The deleteHistory method takes in a Stack object called history that represents 
    the history of played songs, and a Scanner object called scan that is used to read the user's input.
    Returns: The deleteHistory method does not return anything.
    */
    public static void deleteHistory(Stack<String> history, Scanner scan){
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        System.out.print("Enter number of songs to delete: ");
        String input = scan.nextLine();
        int current = Integer.parseInt(input);
        int size = Math.abs(current);
        if(history.size() < size){
            throw new IllegalArgumentException();
        }
        if(current > 0){
                history.pop();
                System.out.println(history);
            
        } else {
            Stack<String> playList = new Stack<>();
            while(!history.isEmpty()){
                if(history.size() > size){
                    playList.push(history.pop());
                } else{
                    history.pop();
                }
            }
            while(!playList.isEmpty()){
                history.push(playList.pop());
            }
        }
    }    
}
