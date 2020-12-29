package pushBox;

import org.junit.jupiter.api.Test;

import java.io.*;

public class LoadMap {
    private int[][] map = new int[20][20];
    public int[][] getMap() {
        return map;
    }

    public LoadMap(int level) {
        File file=new File("maps/"+level+".map");
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            for (int i=0; i<20; i++){
                String line = bufferedReader.readLine();
                byte[] bytes = line.getBytes();

                for (int j=0; j<20; j++) {
                    map[i][j] = bytes[j] - 48;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
