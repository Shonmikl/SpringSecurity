package com.mikhailegorov.spring.security.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//Вся логика работы Spring MVC построена вокруг DispatcherServlet,
// который принимает и обрабатывает все HTTP-запросы (из UI) и ответы на них
//AbstractAnnotationConfigDispatcherServletInitializer
// который предоставляет возможность настроить 2 контекста: AppConfig и WebConfig
public class MyWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
//Первое, на что следует обратить внимание,
// это то, что любой класс, который расширяет
// AbstractAnnotationConfigDispatcherServletInitializer
// будет использоваться для настройки DispatcherServlet и контекста приложения.

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    @Override
    //Метод getServletMappings() определяет путь, по которому будет сопоставлен DispatcherServlet
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}