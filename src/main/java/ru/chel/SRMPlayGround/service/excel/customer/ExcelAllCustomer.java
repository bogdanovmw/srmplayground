package ru.chel.SRMPlayGround.service.excel.customer;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.model.Orders;
import ru.chel.SRMPlayGround.model.Product;
import ru.chel.SRMPlayGround.model.ProductList;
import ru.chel.SRMPlayGround.service.OrdersService;
import ru.chel.SRMPlayGround.service.ProductListService;
import ru.chel.SRMPlayGround.service.ProductService;
import ru.chel.SRMPlayGround.service.excel.GenerateInteger;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExcelAllCustomer {
    private OrdersService ordersService;
    private ProductListService productListService;
    private ProductService productService;

    public ExcelAllCustomer(OrdersService ordersService, ProductListService productListService, ProductService productService) {
        this.ordersService = ordersService;
        this.productListService = productListService;
        this.productService = productService;
    }

    public List<List<String>> getExcelAllCustomer(Long id){
        // Получить id заказов по маршруту
        List<Orders> orders = ordersService.findAllByDriverId(id);
        // Сортировка по позиции магазина
        Comparator<Orders> ordersComparator = Comparator.comparing(s -> s.getCustomers().getPosition());
        // Получить список id заказов в отсорт виде
        List<Long> idOrders = orders.stream().sorted(ordersComparator).map(Orders::getId).distinct().collect(Collectors.toList());

        // Получить список продуктов которые есть в заказах по маршруту
        List<ProductList> productLists = new ArrayList<>();
        for (Long idOrder : idOrders){
            productLists.addAll(productListService.findByOrderId(idOrder));
        }
        // Сортировка списка продуктов
        Comparator<ProductList> comparator = Comparator.comparing(s -> s.getProduct().getTypeProduct().getId());
        comparator = comparator.thenComparing(s -> s.getProduct().getId());
        // Получить ID продуктов объединенные и отсортированные
        List<Long> idProduct = productLists.stream().sorted(comparator).map(ProductList::getProduct).map(Product::getId).distinct().collect(Collectors.toList());

        List<List<String>> result = new ArrayList<>();
        List<String> driverList = new ArrayList<String>() {{
            add("Наименование");
            for (Long id : idOrders)
                add(ordersService.findById(id).getCustomers().getName());
            add("Итог");
        }};
        result.add(driverList);

        AtomicInteger i = new AtomicInteger();
        for (Long idProd : idProduct){
            Map<String, Double> valueRes = new HashMap<String, Double>() {{
                put("кор", 0.0);
                put("уп", 0.0);
                put("кг", 0.0);
                put("шт", 0.0);
                put("пл", 0.0);
                put("", 0.0);
            }};

            List<String> cell = new ArrayList<>();
            cell.add(productService.findById(idProd).getName());

            int index = 0;
            for (Long idOrder : idOrders){
                ProductList pL = productListService.findByOrdersIdAndProductId(idOrder, idProd);
                if (pL == null){
                    cell.add("-");
                } else{
                    cell.add(String.valueOf(pL.getCount()).replace(".0", "") + " " + pL.getType());
                    index++;

                    double aDouble = valueRes.get(pL.getType()) + pL.getCount();
                    DecimalFormat decimalFormat = new DecimalFormat("0.0");
                    valueRes.put(pL.getType(), Double.parseDouble(decimalFormat.format(aDouble).replace(",", ".")));
                }
            }

            Map<String, Double> collect = valueRes.entrySet().stream().filter(e -> e.getValue() != 0.0)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (collect.size() > 1){
                cell.add(String.valueOf(index));
            }else{
                String res = collect.entrySet().stream().map(e -> e.getValue() + " " + e.getKey()).collect(Collectors.joining());
                cell.add(res.replace(".0", ""));
            }

            result.add(cell);
        }

        return result;
    }
}
