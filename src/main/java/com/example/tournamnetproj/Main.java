package com.example.tournamnetproj;
import java.io.*;

public class Main {
    public static Tournament[] tournaments;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        tournaments = loadTournaments();
        HelloApplication.main(args);
        saveTournaments();
    }

    // a method to save the tournaments objects to a file named "tournaments.bin"
    private static void saveTournaments() {
        try {
            // Open an output stream to save the tournaments to the file
            FileOutputStream fos = new FileOutputStream("tournaments.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write the number of tournaments to the file
            oos.writeInt(tournaments.length);

            // Write each tournament to the file
            for (Tournament tournament : tournaments) {
                oos.writeObject(tournament);
            }

            // Close the output streams
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // a method to load the tournaments objects from a file named "tournaments.bin"
    public static Tournament[] loadTournaments() throws IOException, ClassNotFoundException {
        // Open an input stream to read from the file
        FileInputStream fis = new FileInputStream("tournaments.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Read the number of tournaments from the file
        int numTournaments = ois.readInt();

        // Create an array to hold the tournaments
        Tournament[] tournaments = new Tournament[numTournaments];

        // Read each tournament from the file and store it in the array
        for (int i = 0; i < numTournaments; i++) {
            Tournament tournament = (Tournament) ois.readObject();
            if (tournament instanceof Elimination) {
                Elimination elimination = (Elimination) tournament;
                tournaments[i] = elimination;
            } else if (tournament instanceof RoundRobin) {
                RoundRobin roundRobin = (RoundRobin) tournament;
                tournaments[i] = roundRobin;
            } else {
                tournaments[i] = tournament;
            }
        }

        // Close the input streams
        ois.close();
        fis.close();

        // Return the array of tournaments
        return tournaments;
    }



}

