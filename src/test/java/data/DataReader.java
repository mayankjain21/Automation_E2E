package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		/*
		 * String filePath = System.getProperty("user.dir") + File.separator + "src" +
		 * File.separator + "test" + File.separator + "java" + File.separator + "data" +
		 * File.separator + "PurchaseOrder.json";
		 */
		/*
		 * String filePath = System.getProperty("user.dir") + File.separator + "src" +
		 * File.separator + "test" + File.separator + "java" + File.separator + "data" +
		 * File.separator + "PurchaseOrder.json";
		 * 
		 * String fileContent = FileUtils.readFileToString(new File(filePath), "UTF-8");
		 */

		 String jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		 
		 ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
         
	});
		 return data;
	}
}

