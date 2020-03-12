package com.floriankleewein.einstiegsbeispiel;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient extends Thread{

    private String inputFromUser;
    private String responseServer;

    public static void main(String[] args){
    }

    TCPClient(String inputFromUser){
        this.inputFromUser = inputFromUser;
    }

    @Override
    public void run(){

        try {

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes( inputFromUser + '\n');
            responseServer = inFromServer.readLine();
            //Log.d("TAG", responseServer);
            clientSocket.close();
        }
        catch(IOException e){
            Log.e("TAG", "Client-Error occured");
        }
    }

    protected String getResponseServer(){
        return responseServer;
    }
}
