// SynonymHandler
/****************************************************************
 SynonymHandler handles information about synonyms for various
 words.
 The synonym data can be read from a file and handled in various
 ways. These data consists of several lines, where each line begins
 with a word, and this word is followed with a number of synonyms.
 The synonym line for a given word can be obtained. It is possible
 to add a synonym line, and to remove the synonym line for a given
 word. Also a synonym for a particular word can be added, or
 removed. The synonym data can be sorted. Lastly, the data can be
 written to a given file.
 Author: Fadil Galjic
 ****************************************************************/
import java.io.*; // FileReader, BufferedReader, PrintWriter, IOException

class SynonymHandler {

    // readSynonymData reads the synonym data from a given file
    public static String[] readSynonymData(String synonymFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null) {
            numberOfLines++;
            synonymLine = reader.readLine();
        }
        reader.close();

        String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
        for (int i = 0; i < numberOfLines; i++)
            synonymData[i] = reader.readLine();
        reader.close();
        return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given file
    public static void writeSynonymData(String[] synonymData, String synonymFile) throws IOException {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();
    }

    private static int synonymLineIndex(String[] synonymData, String word) throws IllegalArgumentException {
        int delimiterIndex;
        String w;
        int i = 0;
        boolean wordFound = false;
        while (!wordFound && i < synonymData.length) {
            delimiterIndex = synonymData[i].indexOf('|');
            w = synonymData[i].substring(0, delimiterIndex).trim();
            if (w.equalsIgnoreCase(word))
                wordFound = true;
            else
                i++;
        }
        if (!wordFound)
            throw new IllegalArgumentException(word + " not present");
        return i;
    }

    public static String getSynonymLine(String[] synonymData, String word) throws IllegalArgumentException {
        int index = synonymLineIndex(synonymData, word);
        return synonymData[index];
    }

    public static String[] addSynonymLine(String[] synonymData, String synonymLine) {
        String[] synData = new String[synonymData.length + 1];
        for (int i = 0; i < synonymData.length; i++)
            synData[i] = synonymData[i];
        synData[synData.length - 1] = synonymLine;
        return synData;
    }

    public static String[] removeSynonymLine(String[] synonymData, String word) throws IllegalArgumentException {
        int lineIndex = synonymLineIndex(synonymData, word);
        String[] synData = new String[synonymData.length - 1];
        for (int i = 0; i < lineIndex; i++)
            synData[i] = synonymData[i];
        for (int i = lineIndex; i < synData.length; i++)
            synData[i] = synonymData[i + 1];
        return synData;
    }

    private static String[] getSynonyms(String synonymLine) {
        String[] parts = synonymLine.split("\\|");
        if (parts.length < 2) return new String[0];
        String[] synonyms = parts[1].split(",");
        for (int i = 0; i < synonyms.length; i++)
            synonyms[i] = synonyms[i].trim();
        return synonyms;
    }

    public static void addSynonym(String[] synonymData, String word, String synonym) throws IllegalArgumentException {
        int lineIndex = synonymLineIndex(synonymData, word);
        synonymData[lineIndex] += ", " + synonym;
    }

    public static void removeSynonym(String[] synonymData, String word, String synonym)
            throws IllegalArgumentException, IllegalStateException {
        int lineIndex = synonymLineIndex(synonymData, word);
        String[] synonyms = getSynonyms(synonymData[lineIndex]);
        if (synonyms.length == 1)
            throw new IllegalStateException(word + " only has one synonym!");

        int wordIndex = -1;
        for (int i = 0; i < synonyms.length; i++) {
            if (synonyms[i].equalsIgnoreCase(synonym)) {
                wordIndex = i;
                break;
            }
        }
        if (wordIndex == -1)
            throw new IllegalArgumentException("the synonym " + synonym + " could not be found");

        String newLine = word + " |";
        boolean first = true;
        for (int i = 0; i < synonyms.length; i++) {
            if (i != wordIndex) {
                if (!first) newLine += ", ";
                newLine += synonyms[i];
                first = false;
            }
        }
        synonymData[lineIndex] = newLine;
    }

    private static void sortIgnoreCase(String[] strings) {
        // Selection sort
        for (int i = 0; i < strings.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // swap
            String temp = strings[i];
            strings[i] = strings[minIndex];
            strings[minIndex] = temp;
        }
    }

    private static String sortSynonymLine(String synonymLine) {
        String[] parts = synonymLine.split("\\|");
        if (parts.length < 2) return synonymLine;
        String word = parts[0].trim();
        String[] synonyms = getSynonyms(synonymLine);
        sortIgnoreCase(synonyms);
        String sortedLine = word + " | " + String.join(", ", synonyms);
        return sortedLine;
    }

    public static void sortSynonymData(String[] synonymData) {
        // Sort each line
        for (int i = 0; i < synonymData.length; i++)
            synonymData[i] = sortSynonymLine(synonymData[i]);
        // Sort the lines by the first word
        sortIgnoreCase(synonymData);
    }
}
