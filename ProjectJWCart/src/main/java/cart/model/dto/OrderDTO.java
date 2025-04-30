package cart.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cart.model.entity.OrderItem;
import lombok.Data;

@Data
public class OrderDTO {
	private Integer orderId;
	private Integer userId;
	private Date orderDate;
	
	// 集合該筆訂單的所有的訂單項目
	private List<OrderItemDTO> items = new ArrayList<>();
}
