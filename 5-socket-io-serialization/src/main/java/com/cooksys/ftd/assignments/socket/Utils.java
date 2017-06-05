package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws JAXBException 
     */
    public static JAXBContext createJAXBContext() throws JAXBException {
        return JAXBContext.newInstance(Student.class, Config.class);
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     * @throws JAXBException 
     * @throws IOException 
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) throws JAXBException, IOException {
    	File cx =  new File("config/config.xml");
		FileInputStream cxin = new FileInputStream(cx);
		//JAXBContext ctx = Utils.createJAXBContext();
		Unmarshaller u = jaxb.createUnmarshaller();
		Config c = (Config) u.unmarshal(cxin);
		cxin.close();
		return c;
    }
}
