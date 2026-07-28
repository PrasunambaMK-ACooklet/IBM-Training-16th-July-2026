package com.SpringFw;
import org.springframework.beans.factory.BeanFactory; // deprecated
import org.springframework.beans.factory.xml.XmlBeanFactory;// deprecated
import org.springframework.core.io.ClassPathResource;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
@SuppressWarnings("deprecation") // XmlBeanFactory is deprecated but fine for demo
public class App
{
    public static void main( String[] args ){
        //here we are creating object in a spring container (Beans)
        BeanFactory factory = new XmlBeanFactory(new ClassPathResources("bean-factory-demo.xml"));
        Employee employee = (Employee) factory.getBean("Employee");
        System.out.println( employee );
    }
}