package cn.st.xml.jdom;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;
import org.junit.Test;

public class JdomTest {
	@Test
	public void traditional() throws Exception {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(this.getClass().getClassLoader()
				.getResourceAsStream("hd.xml"));
		Element root = doc.getRootElement();
		List list = root.getChildren("disk");
		for (int i = 0; i < list.size(); i++) {
			Element element = (Element) list.get(i);
			String name = element.getAttributeValue("name");
			String capacity = element.getChildText("capacity");
			String directories = element.getChildText("directories");
			String files = element.getChildText("files");
			System.out.println("磁盘信息:");
			System.out.println("分区盘符:" + name);
			System.out.println("分区容量:" + capacity);
			System.out.println("目录数:" + directories);
			System.out.println("文件数:" + files);
			System.out.println("-----------------------------------");
		}
	}
	@Test
	public void xpath() throws Exception {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(this.getClass().getClassLoader()
				.getResourceAsStream("hd.xml"));
		Element root = doc.getRootElement();
		List list = XPath.selectNodes(root, "/HD/disk");
		for (int i = 0; i < list.size(); i++) {
			Element disk_element = (Element) list.get(i);
			String name = disk_element.getAttributeValue("name");
			String capacity = ((Text) XPath.selectSingleNode(disk_element,
					"//disk[@name='" + name + "']/capacity/text()"))
					.getTextNormalize();
			String directories = ((Text) XPath.selectSingleNode(disk_element,
					"//disk[@name='" + name + "']/directories/text()"))
					.getTextNormalize();
			String files = ((Text) XPath.selectSingleNode(disk_element,
					"//disk[@name='" + name + "']/files/text()"))
					.getTextNormalize();
			System.out.println("磁盘信息:");
			System.out.println("分区盘符:" + name);
			System.out.println("分区容量:" + capacity);
			System.out.println("目录数:" + directories);
			System.out.println("文件数:" + files);
			System.out.println("-----------------------------------");
		}
	}

}
