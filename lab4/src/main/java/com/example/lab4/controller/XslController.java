package com.example.lab4.controller;

import com.example.lab4.entity.Order;
import com.example.lab4.entity.Product;
import com.example.lab4.service.OrderService;
import com.example.lab4.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
@RequestMapping("/xsl")
public class XslController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_XML_VALUE)
    public ModelAndView getOrders() throws JsonProcessingException {
        Iterable<Order> list = orderService.findAll();
        return getModelAndView(list, "orderXSL");
    }

    @ResponseBody
    @GetMapping(path = "/products", produces = MediaType.APPLICATION_XML_VALUE)
    public ModelAndView getProducts() throws JsonProcessingException {
        Iterable<Product> list = productService.findAll();
        return getModelAndView(list, "productXSL");
    }

    public ModelAndView getModelAndView(Iterable<?> list, String viewName) throws JsonProcessingException {
        String str = new XmlMapper().writeValueAsString(list);
        ModelAndView mod = new ModelAndView(viewName);
        Source src = new StreamSource(new StringReader(str));
        mod.addObject("List", src);
        return mod;
    }
}
