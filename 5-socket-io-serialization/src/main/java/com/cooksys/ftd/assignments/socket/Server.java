package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     * @throws JAXBException 
     * @throws IOException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException, IOException {
    	File sx =  new File(studentFilePath);
		FileInputStream sxin = new FileInputStream(sx);
		Unmarshaller u = jaxb.createUnmarshaller();
		Student s = (Student) u.unmarshal(sxin);
		sxin.close();
		return s;
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     */
    public static void main(String[] args) {
        // TODO
    }
}
