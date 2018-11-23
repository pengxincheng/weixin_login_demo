package com.pxc.weixin_login_demo.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/23
 * @Time 14:18
 */
public class XmlUtil {

    public static <T> T xmlToBean(String xml, Class<T> c) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T bean = (T) unmarshaller.unmarshal(new StringReader(xml));
        return bean;
    }

    public static String beanToXml(Object bean) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);
        StringWriter xmlWriter = new StringWriter();
        marshaller.marshal(bean, xmlWriter);
        return xmlWriter.getBuffer().toString();
    }

    public static String beanToXml(Object bean, String encoding) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);
        marshaller.setProperty("jaxb.encoding", encoding);
        marshaller.setProperty("jaxb.fragment", true);
        StringWriter xmlWriter = new StringWriter();
        xmlWriter.append("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>\n");
        marshaller.marshal(bean, xmlWriter);
        return xmlWriter.getBuffer().toString();
    }
}
