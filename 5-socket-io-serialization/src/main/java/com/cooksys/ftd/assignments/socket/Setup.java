/**
 * 
 */
package com.cooksys.ftd.assignments.socket;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

/**
 * @author ftd-8
 *
 */
public class Setup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Set up student.xml
		Student s = new Student();
		s.setFirstName("Christian");
		s.setLastName("Schmitt");
		s.setFavoriteLanguage("Scheme");
		s.setFavoriteParadigm("Functional");
		s.setFavoriteIDE("EMACS");
		
		// set up config.xml
		LocalConfig lc = new LocalConfig();
		lc.setPort(8080);
		
		RemoteConfig rc = new RemoteConfig();
		rc.setHost("0.0.0.0");
		rc.setPort(8080);
		
		Config cfg = new Config();
		cfg.setLocal(lc);
		cfg.setRemote(rc);
		cfg.setStudentFilePath("config/student.xml");
		
		JAXBContext ctx;
		File studentXML;
		File configXML;
		FileOutputStream so;
		FileOutputStream co;
		try {
			ctx = JAXBContext.newInstance(Student.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(s, System.out);
			studentXML = new File("config/student.xml");
			so = new FileOutputStream(studentXML);
			m.marshal(s, so);
			so.close();
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		
		try {
			ctx = JAXBContext.newInstance(Config.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(cfg, System.out);
			configXML = new File("config/config.xml");
			co = new FileOutputStream(configXML);
			m.marshal(cfg, co);			
			co.close();
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
