package cart.model.entity;

import lombok.AllArgsConstructor;

import java.sql.Date;

import lombok.*;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Integer orderId;
	private Integer userId;
	private Date orderDate;
}
