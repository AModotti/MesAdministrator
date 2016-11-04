package bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TableSerialization {
	
	private static String fileName ="grid.ser";

	public static Object deserialize() {
		
		FileInputStream fis = null;
		Object obj = null;
		
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois;
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return obj;
		
	}

	public static void serialize(Object object) {
		
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
