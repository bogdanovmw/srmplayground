package ru.chel.SRMPlayGround.dto.orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import ru.chel.SRMPlayGround.model.Customer;
import ru.chel.SRMPlayGround.model.Driver;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class OrderListDto{
    private Long id;

    @JsonProperty("driver")
    private Driver drivers;

    @JsonProperty("customer")
    private Customer customers;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Yekaterinburg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private List<ProductListDtoResponse> list;

    public String getDrivers() {
        return drivers.getName();
    }

    public String getCustomers() {
        return customers.getName();
    }

}
