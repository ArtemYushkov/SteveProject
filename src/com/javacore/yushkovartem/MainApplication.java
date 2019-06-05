package com.javacore.yushkovartem;

import com.javacore.yushkovartem.dbservice.DBApplication;
import com.javacore.yushkovartem.dbservice.misc.XMLDocumentHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

public class MainApplication {

    public static final String APP_NAME = "Steve";
    public static final String VERSION = "0.0.0.1";
    public static final String AUTHOR = "Yushkov Artem";

    public static Map<String, Enum> applications = new HashMap<>();

    public static void main(String[] args) {

        try {
            startApplications();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void startApplications() {
        try {
            Utils.readXMLDocument("main/config.xml", new XMLDocumentHandler() {
                @Override
                public void handleDocument(Document document) {
                    try {
                        NodeList apps = document.getElementsByTagName("application");
                        for (int i = 0, len = apps.getLength(); i < len; i++) {
                            Element el = (Element) (apps.item(i));
                            Class cls = Class.forName(el.getAttribute("class"));
                            Enum appInstance = Enum.valueOf(cls, "INSTANCE");
                            applications.put(el.getAttribute("name"), appInstance);
                            int port = Integer.valueOf(el.getAttribute("port"));
                            cls.getDeclaredMethod("start", int.class).invoke(appInstance, port);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
