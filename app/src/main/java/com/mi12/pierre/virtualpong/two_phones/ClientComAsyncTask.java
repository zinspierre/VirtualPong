package com.mi12.pierre.virtualpong.two_phones;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple server socket that accepts connection and writes some data on
 * the stream.
 */
public class ClientComAsyncTask extends AsyncTask<Void, GamePositions, String> {

    private DrawActivityClient.GameView gameView;
    private FileOutputStream fstream;

    public ClientComAsyncTask (Context context, DrawActivityClient.GameView gameView) {
        this.gameView = gameView;
        File dir = new File (Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/echantillonnage");

        dir.mkdirs();
        File file = new File(dir, "client.txt");
        try {
            fstream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        ServerSocket s = null;
        ObjectInputStream ois = null;
        Socket soc = null;
        try {
            s = new ServerSocket(8989);
            soc = s.accept();
            ois = new ObjectInputStream(soc.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long[] data = new long[1000];
        int i = 0;
        while (i < 1000)
        {
            try {
                GamePositions gp = (GamePositions) ois.readObject();
//                writeFile(Long.toString(System.nanoTime()) + "\n");
                publishProgress((GamePositions) ois.readObject());
                data[i++] = System.currentTimeMillis();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(false){break;}
        }

        for(int j = 0; j < 1000; ++j){
            writeFile(Long.toString(data[j]) + "\n");
        }

        try{
            s.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
    @Override
    protected void onProgressUpdate(GamePositions... progress) {
        super.onProgressUpdate(progress);
        if(this.gameView != null) {
            this.gameView.setPositions(progress[0]);
        }
    }

    public void writeFile(String d)
    {
        try {
            fstream.write(d.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

