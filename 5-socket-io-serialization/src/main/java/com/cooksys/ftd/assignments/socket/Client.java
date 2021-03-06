package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     */
    public static void main(String[] args) {
    	JAXBContext ctx;
    	Config cfg;
    	Socket server;
    	InputStream in;
    	Unmarshaller u;
    	Student s;
    	
    	try{
    		ctx = Utils.createJAXBContext();
    		cfg = Utils.loadConfig("config/config.xml", ctx);
    		server = new Socket(cfg.getRemote().getHost(), cfg.getRemote().getPort());
    		in = server.getInputStream();
    		u = ctx.createUnmarshaller();
    		s = (Student) u.unmarshal(in);
    		System.out.print("Student: " + s.getLastName() + ", " + s.getFirstName() + "\n"
					+ "\tFavorite Language: " + s.getFavoriteLanguage() + "\n"
					+ "\tFavorite Programming Paradigm: " + s.getFavoriteParadigm() + "\n"
					+ "\tFavorite IDE: " + s.getFavoriteIDE() + "\n");
    		server.close();
    		in.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
