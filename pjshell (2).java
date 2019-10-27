import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class pjshell {

  public static void main(String[] args) {

    /* Run the program until the user types Ctrl+D */
    try {
        Scanner input_scanner = new Scanner(System.in);
        System.out.println("Enter the command(s). Separate multiple commands with a semicolon (;). ");
        String user_input = input_scanner.nextLine();

        /* While the user types a carriage return, then nothing should happen and the prompt should be printed again */
        while(user_input != null && user_input.isEmpty()) {
          System.out.println("Enter the command(s). Separate multiple commands with a semicolon (;). ");
          user_input = input_scanner.nextLine();
        }

        /* Array of lists containing the command and its arguments */
        String[] list = user_input.split(";");

        for(int i=0; i<list.length; i++) {
          /* List containing the command and its arguments */
          List<String> command = new ArrayList<>();

          String string = list[i];
          Scanner command_scanner = new Scanner(string);
          while(command_scanner.hasNext()) {
            command.add(command_scanner.next());
          }

          String working_directory = System.getProperty("user.dir");

          try {
            ProcessBuilder pb = new ProcessBuilder(command);
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader ebr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            String errorline;
            /* The stdout stream of the created process is captured and printed to the screen */
            while((line=br.readLine()) != null) {
              System.out.println(line);
            }

            /* The stderr stream of the created process is captured and printed to the screen */
            while((errorline=ebr.readLine()) != null) {
              System.out.println(errorline);
            }
          } catch (Exception ex) {
              System.out.println("Invalid command(s)/argument(s). ");
          }
        }

    } catch (NoSuchElementException no_such_element_exception) {
        /* This should be printed out when the user types Ctrl+D */
        System.out.println("Have a good day.");
    }
  }
}
