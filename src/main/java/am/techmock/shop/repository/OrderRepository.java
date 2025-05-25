package am.techmock.shop.repository;

import am.techmock.shop.model.OrderContact;
import am.techmock.shop.model.Product;
import am.techmock.shop.model.order.Order;
import am.techmock.shop.model.order.OrderItem;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

	private final NamedParameterJdbcTemplate queryTemplate;

	public OrderRepository(DataSource dataSource) {
		queryTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Order> list() {
		String sql = "SELECT o.id as order_id, o.price, o.done, " +
				"oc.id as contact_id, oc.name, oc.phone, oc.address " +
				"FROM shop_order o " +
				"JOIN order_contact oc ON o.contact_id = oc.id";

		List<Order> orders = new ArrayList<>();
		Map<Integer, Order> orderMap = new HashMap<>();

		queryTemplate.query(sql, (rs, rowNum) -> {
			int orderId = rs.getInt("order_id");

			if (!orderMap.containsKey(orderId)) {
				OrderContact contact = new OrderContact(
						rs.getString("name"),
						rs.getString("phone"),
						rs.getString("address")
				);

				Order order = new Order(
						orderId,
						new ArrayList<>(),
						contact,
						rs.getInt("price"),
						rs.getBoolean("done")
				);

				orderMap.put(orderId, order);
				orders.add(order);
			}

			return null;
		});

		// Fetch order items for each order
		for (Order order : orders) {
			String itemsSql = "SELECT oi.quantity, p.id, p.title, p.image, p.description, p.price " +
					"FROM order_item oi " +
					"JOIN product p ON oi.product_id = p.id " +
					"WHERE oi.order_id = :orderId";

			List<OrderItem> items = queryTemplate.query(
					itemsSql,
					new MapSqlParameterSource("orderId", order.id()),
					(rs, rowNum) -> {
						Product product = new Product(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("image"),
								rs.getString("description"),
								rs.getInt("price")
						);

						return new OrderItem(product, rs.getInt("quantity"));
					}
			);

			// Since Order is immutable, we need to create a new one with the items
			int index = orders.indexOf(order);
			orders.set(index, new Order(
					order.id(),
					items,
					order.contact(),
					order.price(),
					order.done()
			));
		}

		return orders;
	}

	public void add(Order order) {
		// Insert contact first
		String contactSql = "INSERT INTO order_contact (name, phone, address) " +
				"VALUES (:name, :phone, :address)";

		MapSqlParameterSource contactParams = new MapSqlParameterSource()
				.addValue("name", order.contact().name())
				.addValue("phone", order.contact().phone())
				.addValue("address", order.contact().address());

		KeyHolder contactKeyHolder = new GeneratedKeyHolder();
		queryTemplate.update(contactSql, contactParams, contactKeyHolder);
		int contactId = contactKeyHolder.getKey().intValue();

		// Insert order
		String orderSql = "INSERT INTO shop_order (contact_id, price, done) " +
				"VALUES (:contactId, :price, :done)";

		MapSqlParameterSource orderParams = new MapSqlParameterSource()
				.addValue("contactId", contactId)
				.addValue("price", order.price())
				.addValue("done", false);

		KeyHolder orderKeyHolder = new GeneratedKeyHolder();
		queryTemplate.update(orderSql, orderParams, orderKeyHolder);
		int orderId = orderKeyHolder.getKey().intValue();

		// Insert order items
		String itemSql = "INSERT INTO order_item (order_id, product_id, quantity) " +
				"VALUES (:orderId, :productId, :quantity)";

		for (OrderItem item : order.items()) {
			MapSqlParameterSource itemParams = new MapSqlParameterSource()
					.addValue("orderId", orderId)
					.addValue("productId", item.product().id())
					.addValue("quantity", item.quantity());

			queryTemplate.update(itemSql, itemParams);
		}
	}

	public void markAsDone(int id) {
		String sql = "UPDATE shop_order SET done = true WHERE id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		queryTemplate.update(sql, params);
	}

	public void remove(int id) {
		// Due to foreign key constraints with ON DELETE CASCADE,
		// deleting the order will automatically delete related order items
		String sql = "DELETE FROM shop_order WHERE id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		queryTemplate.update(sql, params);
	}
}
