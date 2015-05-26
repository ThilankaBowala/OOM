package middleware;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 *
 * @author 2012cs024
 */
public class BusinessLogicLayer {

    static String portNumber;
    static String methodName;

    static void readXML() {
        try {

            File fXmlFile = new File("/ServerData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList methodList = doc.getElementsByTagName("method");
            int numberOfServers = methodList.getLength();

            for (int temp = 0; temp < numberOfServers; temp++) {
                Node nNode = methodList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element serverDetails = (Element) nNode;
                    if (serverDetails.getAttribute("id").equals(methodName)) {
                        portNumber = serverDetails.getElementsByTagName("portNumber").item(0).getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public static void main(String argv[]) {
    }
}
