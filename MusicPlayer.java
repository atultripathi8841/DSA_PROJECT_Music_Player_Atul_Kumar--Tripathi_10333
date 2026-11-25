
import java.util.Scanner;
import java.util.Stack;

class MusicPlayerData {

    class Song {
        String name;
        Song next, prev;

        Song(String name) {
            this.name = name;
        }
    }

    class TreeNode {
        String name;
        TreeNode left, right;

        TreeNode(String name) {
            this.name = name;
        }
    }

    Song head = null;
    Song tail = null;
    Song current = null;
    Song circularCurrent = null;

    Stack<String> history = new Stack<>();
    TreeNode root = null;

    void addSong(String name) {

        Song newSong = new Song(name);

        if (head == null) {
            head = tail = newSong;

            newSong.next = head;
            newSong.prev = head;

            current = head;
            circularCurrent = head;
        } else {
            newSong.prev = tail;
            newSong.next = head;

            tail.next = newSong;
            head.prev = newSong;

            tail = newSong;
        }

        root = insertBST(root, name);

        System.out.println("Song added successfully!");
    }

    void displayPlaylist() {

        if (head == null) {
            System.out.println("Playlist is empty! Add songs first.");
            return;
        }

        System.out.println("\n--- MUSIC PLAYLIST ---");

        Song temp = head;

        do {
            System.out.println(temp.name);
            temp = temp.next;
        } while (temp != head);
    }

    void playSong() {

        if (current == null) {
            System.out.println("No songs available!");
            return;
        }

        System.out.println("Now Playing: " + current.name);

        history.push(current.name);
    }

    void nextSong() {

        if (current == null) {
            System.out.println("No songs available!");
            return;
        }

        current = current.next;
        playSong();
    }

    void previousSong() {

        if (current == null) {
            System.out.println("No songs available!");
            return;
        }

        current = current.prev;
        playSong();
    }

    void repeatSong() {

        if (circularCurrent == null) {
            System.out.println("No songs available!");
            return;
        }

        System.out.println("Repeat Playing: " + circularCurrent.name);

        history.push(circularCurrent.name);

        circularCurrent = circularCurrent.next;
    }

    void showHistory() {

        if (history.empty()) {
            System.out.println("No songs played yet!");
            return;
        }

        System.out.println("\n--- RECENTLY PLAYED SONGS ---");

        Stack<String> temp = new Stack<>();
        temp.addAll(history);

        while (!temp.empty()) {
            System.out.println(temp.pop());
        }
    }

    TreeNode insertBST(TreeNode node, String name) {

        if (node == null) {
            return new TreeNode(name);
        }

        int result = name.compareToIgnoreCase(node.name);

        if (result < 0) {
            node.left = insertBST(node.left, name);
        } else if (result > 0) {
            node.right = insertBST(node.right, name);
        }

        return node;
    }

    boolean searchBST(TreeNode node, String name) {

        if (node == null) {
            return false;
        }

        int result = name.compareToIgnoreCase(node.name);

        if (result == 0) {
            return true;
        }

        if (result < 0) {
            return searchBST(node.left, name);
        }

        return searchBST(node.right, name);
    }

    void searchSong(Scanner sc) {

        System.out.print("Enter song name: ");
        String name = sc.nextLine();

        if (searchBST(root, name)) {
            System.out.println("Song found!");
        } else {
            System.out.println("Song not found!");
        }
    }

    void displayBST(TreeNode node) {

        if (node == null) {
            return;
        }

        displayBST(node.left);
        System.out.println(node.name);
        displayBST(node.right);
    }

    void displaySorted() {

        if (root == null) {
            System.out.println("No songs available!");
            return;
        }

        System.out.println("\n--- SONGS IN ALPHABETICAL ORDER ---");

        displayBST(root);
    }
}
public class MusicPlayer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MusicPlayerData player = new MusicPlayerData();

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n========== MUSIC PLAYER ==========");
            System.out.println("1. Add Song");
            System.out.println("2. Display Playlist");
            System.out.println("3. Play Song");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Circular Repeat");
            System.out.println("7. Recently Played Songs");
            System.out.println("8. Search Song (BST)");
            System.out.println("9. Display Sorted Songs");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            // INPUT HANDLING
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");

                if (sc.hasNextLine()) {
                    sc.nextLine();
                }

                continue;
            }

            choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter song name: ");
                    String name = sc.nextLine();

                    if (!name.trim().isEmpty()) {
                        player.addSong(name);
                    } else {
                        System.out.println("Song name cannot be empty!");
                    }
                    break;

                case 2:
                    player.displayPlaylist();
                    break;

                case 3:
                    player.playSong();
                    break;

                case 4:
                    player.nextSong();
                    break;

                case 5:
                    player.previousSong();
                    break;

                case 6:
                    player.repeatSong();
                    break;

                case 7:
                    player.showHistory();
                    break;

                case 8:
                    player.searchSong(sc);
                    break;

                case 9:
                    player.displaySorted();
                    break;

                case 0:
                    System.out.println("Thank you for using Music Player!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}



// OUTPUT

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 1
// Enter song name: Believer
// Song added successfully!

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 2

// --- MUSIC PLAYLIST ---
// Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 3
// Now Playing: Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 4
// Now Playing: Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 5
// Now Playing: Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 6
// Repeat Playing: Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 7

// --- RECENTLY PLAYED SONGS ---
// Believer
// Believer
// Believer
// Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 8
// Enter song name: Believer
// Song found!

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 9

// --- SONGS IN ALPHABETICAL ORDER ---
// Believer

// ========== MUSIC PLAYER ==========
// 1. Add Song
// 2. Display Playlist
// 3. Play Song
// 4. Next Song
// 5. Previous Song
// 6. Circular Repeat
// 7. Recently Played Songs
// 8. Search Song (BST)
// 9. Display Sorted Songs
// 0. Exit

// Enter your choice: 0
// Thank you for using Music Player!

