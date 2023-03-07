package br.com.uol.games.hero.UTIL;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Util {
	
	public static List<String> jsonResponse(BufferedReader reader) throws IOException {

		StringBuilder sb = new StringBuilder();
		String line;
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String jsonString = sb.toString();
		jsonObject = new JSONObject(jsonString);
		jsonArray = jsonObject.getJSONArray("vingadores");

		List<String> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			list.add(jsonArray.getJSONObject(i).get("codinome").toString());
		}
		reader.close();
		return list;
	}
	
	public static List<String> xmlResponse(BufferedReader reader) throws Exception{
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(reader));
		NodeList heros = doc.getElementsByTagName("codinome");
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < heros.getLength(); i++) {
			list.add(heros.item(i).getTextContent());
		}
		reader.close();
		
		return list;
	}
	
}













