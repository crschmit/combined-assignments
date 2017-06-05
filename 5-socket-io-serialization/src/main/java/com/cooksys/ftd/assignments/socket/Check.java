/**
 * 
 */
package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

/**
 * @author ftd-8
 *
 */
public class Check {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student s;
		Config c;
		File sx;
		File cx;
		FileInputStream sxin;
		FileInputStream cxin;
		JAXBContext ctx;
		
/*		try {
			sx =  new File("config/student.xml");
			sxin = new FileInputStream(sx);
			ctx = JAXBContext.newInstance(Student.class);
			Unmarshaller u = ctx.createUnmarshaller();
			s = (Student) u.unmarshal(sxin);
			
			System.out.print("Student: " + s.getLastName() + ", " + s.getFirstName() + "\n"
							+ "\tFavorite Language: " + s.getFavoriteLanguage() + "\n"
							+ "\tFavorite Programming Paradigm: " + s.getFavoriteParadigm() + "\n"
							+ "\tFavorite IDE: " + s.getFavoriteIDE() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			cx =  new File("config/config.xml");
			cxin = new FileInputStream(cx);
			ctx = JAXBContext.newInstance(Config.class);
			Unmarshaller u = ctx.createUnmarshaller();
			c = (Config) u.unmarshal(cxin);
			
			System.out.print("Config:\n"
						   + "\tLocal Config:\n" 
						   + "\t\tPort: " + c.getLocal().getPort() + "\n"
						   + "\tRemote Config:\n"
						   + "\t\tHost: " + c.getRemote().getHost() + "\n"
						   + "\t\tPort: " + c.getRemote().getPort() + "\n"
						   + "\tStudent File Path: " + c.getStudentFilePath() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		try {
			sx =  new File("config/student.xml");
			sxin = new FileInputStream(sx);
			//cx =  new File("config/config.xml");
			//cxin = new FileInputStream(cx);
			//ctx = JAXBContext.newInstance(Student.class, Config.class);
			ctx = Utils.createJAXBContext();
			Unmarshaller u = ctx.createUnmarshaller();
			
			//c = (Config) u.unmarshal(cxin);
			c = Utils.loadConfig("config/config.xml", ctx);
			System.out.print("Config:\n"
						   + "\tLocal Config:\n" 
						   + "\t\tPort: " + c.getLocal().getPort() + "\n"
						   + "\tRemote Config:\n"
						   + "\t\tHost: " + c.getRemote().getHost() + "\n"
						   + "\t\tPort: " + c.getRemote().getPort() + "\n"
						   + "\tStudent File Path: " + c.getStudentFilePath() + "\n");
			
			s = (Student) u.unmarshal(sxin);
			System.out.print("Student: " + s.getLastName() + ", " + s.getFirstName() + "\n"
					+ "\tFavorite Language: " + s.getFavoriteLanguage() + "\n"
					+ "\tFavorite Programming Paradigm: " + s.getFavoriteParadigm() + "\n"
					+ "\tFavorite IDE: " + s.getFavoriteIDE() + "\n");

			sxin.close();
			//cxin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
