import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatBot {
    private static final String BOT_NAME = "Jarvis";
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        showWelcomeMessage();
        String userName = getUserName();
        startChatLoop(userName);
    }
    
    private static void showWelcomeMessage() {
        System.out.println("""
            🤖 ****************************************************
            🤖 Hello! I'm %s, your personal assistant.
            🤖 I can help with various tasks - type 'help' to see options.
            🤖 ****************************************************
            """.formatted(BOT_NAME));
    }
    
    private static String getUserName() {
        System.out.print("👉 What should I call you? ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("⚠️ Please enter your name: ");
            name = scanner.nextLine().trim();
        }
        System.out.printf("\nNice to meet you, %s! \n\n", name);
        return name;
    }
    
    private static void startChatLoop(String userName) {
        boolean running = true;
        
        while (running) {
            System.out.print("[" + userName + "] > ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.isEmpty()) {
                continue;
            }
            
            try {
                running = processInput(input, userName);
            } catch (Exception e) {
                System.out.println("⚠️ Oops! Something went wrong: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static boolean processInput(String input, String userName) {
        if (input.contains("time")) {
            showCurrentTime();
        } 
        else if (input.contains("date")) {
            showCurrentDate();
        } 
        else if (input.contains("joke")) {
            tellJoke();
        } 
        else if (input.contains("weather")) {
            System.out.println("☀️ I'm just a simple bot - try checking a weather website!");
        }
        else if (input.contains("how are you") || input.contains("how's it going")) {
            System.out.println("💻 I'm just code, but running smoothly! How about you?");
        } 
        else if (input.contains("help")) {
            showHelpMenu();
        } 
        else if (input.contains("bye") || input.contains("exit")) {
            System.out.printf("👋 Goodbye, %s! See you soon.\n", userName);
            return false;
        } 
        else {
            System.out.println("🤔 I'm not sure how to respond to that. Try 'help' for options.");
        }
        return true;
    }
    
    private static void showCurrentTime() {
        System.out.println("⏰ Current time: " + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
    
    private static void showCurrentDate() {
        System.out.println("📅 Today's date: " + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")));
    }
    
    private static void tellJoke() {
        String[] jokes = {
            "Why do Java developers wear glasses? Because they don't C#!",
            "How many programmers does it take to change a light bulb? None, that's a hardware problem!",
            "Why was the JavaScript developer sad? Because he didn't know how to 'null' his feelings."
        };
        System.out.println("😂 " + jokes[(int)(Math.random() * jokes.length)]);
    }
    
    private static void showHelpMenu() {
        System.out.println("""
            \n📋 HELP MENU:
            ------------------------------
            time      - Show current time
            date      - Show today's date
            joke      - Tell a random joke
            weather   - Get weather info
            how are you - Check my status
            help      - Show this menu
            bye/exit  - End conversation
            ------------------------------
            """);
    }
}