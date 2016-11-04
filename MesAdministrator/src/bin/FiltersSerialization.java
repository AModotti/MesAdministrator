package bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FiltersSerialization {
	
	private static String fileName ="filters.ser";

	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Object> deserialize() {
		
		FileInputStream fis = null;
		HashMap<Integer, Object> obj = null;
		
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois;
			ois = new ObjectInputStream(fis);
			obj =  (HashMap<Integer, Object>) ois.readObject();
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

	public static void serialize(HashMap<Integer, Object> filters) {
		
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(fos);
			oos.writeObject(filters);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
