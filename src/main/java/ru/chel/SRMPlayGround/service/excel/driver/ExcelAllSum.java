package ru.chel.SRMPlayGround.service.excel.driver;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.model.ProductList;
import ru.chel.SRMPlayGround.service.DriverService;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.ProductListService;
import ru.chel.SRMPlayGround.service.ProductService;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExcelAllSum {
    private OrdersService ordersService;
    private DriverService driverService;
    private ProductListService productListService;
    private ProductService productService;

    public ExcelAllSum(OrdersService ordersService, DriverService driverService, ProductListService productListService, ProductService productService) {
        this.ordersService = ordersService;
        this.driverService = driverService;
        this.productListService = productListService;
        this.productService = productService;
    }

    public List<List<String>> getExcelAllSum(){
        Comparator<Driver> comparatorDriver = Comparator.comparing(Driver::getId);
        List<Driver> drivers = driverService.findAll().stream()
                .sorted(comparatorDriver)
                .filter(e -> !e.getOrders().isEmpty())
                .collect(Collectors.toList());


        List<ProductList> productLists = productListService.findAll();
        Comparator<ProductList> comparator = Comparator.comparing(s -> s.getProduct().getTypeProduct().getId());
        comparator = comparator.thenComparing(s -> s.getProduct().getId());
        List<Long> idProduct = productLists.stream().sorted(comparator).map(ProductList::getProduct).map(Product::getId).distinct().collect(Collectors.toList());

        List<List<String>> result = new ArrayList<>();
        List<String> driverList = new ArrayList<String>(){{
            add("Наименование");
            drivers.stream().map(Driver::getName).forEach(this::add);
            add("Итог");
        }};
        result.add(driverList);

        for (Long idProd : idProduct){
            List<String> listSum = new ArrayList<>();

            Product product = productService.findById(idProd);
            listSum.add(product.getName());

            for (Driver driver : drivers){
                Map<String, Double> valueRes = new HashMap<String, Double>() {{
                    put("", 0.0);
                    put("кг", 0.0);
                    put("пл", 0.0);
                    put("кор", 0.0);
                    put("уп", 0.0);
                    put("шт", 0.0);
                }};

                int index = 0;
                for (Orders order : driver.getOrders()){
                    for (ProductList pL : order.getListProd()){
                        if (pL.getProduct().getId().equals(idProd)) {
                            index++;
                            double aDouble = valueRes.get(pL.getType()) + pL.getCount();
                            DecimalFormat decimalFormat = new DecimalFormat("0.0");
                            valueRes.put(pL.getType(), Double.parseDouble(decimalFormat.format(aDouble).replace(",", ".")));
                        }
                    }
                }
                Map<String, Double> collect = valueRes.entrySet().stream().filter(e -> e.getValue() != 0.0)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                if (collect.size() > 1){
                    listSum.add(String.valueOf(index));
                } else if(collect.size() == 0){
                    listSum.add("-");
                } else{
                    String res = collect.entrySet().stream().map(e -> e.getValue() + " " + e.getKey()).collect(Collectors.joining());
                    listSum.add(res.replace(".0", ""));
                }
            }

            listSum.add("");
            result.add(listSum);
        }
        return result;
    }
}
