package com.example.kosta.fileexam02;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by kosta on 2017-04-25.
 */

public class FileManager {
    private static final String FILE_NAME = "data.txt";

    private Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    public void save(HashMap<String, String> saveData) {
        if(saveData == null || saveData.isEmpty()) {
            return;
        }

        ObjectOutputStream fout = null;

        try {
            fout = new ObjectOutputStream(context.openFileOutput(FILE_NAME, context.MODE_PRIVATE));
            fout.writeObject(saveData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fout != null) fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> load() {
        ObjectInputStream fin = null;

        HashMap<String, String> loadData = null;

        try {
            fin = new ObjectInputStream(context.openFileInput(FILE_NAME));

            loadData = (HashMap<String, String>)fin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fin != null) fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return loadData;
    }
}
