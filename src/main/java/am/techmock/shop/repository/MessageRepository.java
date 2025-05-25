package am.techmock.shop.repository;

import am.techmock.shop.model.Message;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MessageRepository {

	private final NamedParameterJdbcTemplate queryTemplate;

	public MessageRepository(DataSource dataSource) {
		queryTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Message> list() {
		String sql = "SELECT id, name, email, message, seen FROM message";

		return queryTemplate.query(sql, (rs, rowNum) -> new Message(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("message"),
				rs.getBoolean("seen")
		));
	}

	public void add(Message message) {
		String sql = "INSERT INTO message (name, email, message, seen) VALUES (:name, :email, :message, :seen)";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", message.name())
				.addValue("email", message.email())
				.addValue("message", message.message())
				.addValue("seen", false);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		queryTemplate.update(sql, params, keyHolder);
	}

	public void remove(int id) {
		String sql = "DELETE FROM message WHERE id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		queryTemplate.update(sql, params);
	}

	public void markAsRead(int id) {
		String sql = "UPDATE message SET seen = true WHERE id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		queryTemplate.update(sql, params);
	}

	public void markAsUnread(int id) {
		String sql = "UPDATE message SET seen = false WHERE id = :id";

		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		queryTemplate.update(sql, params);
	}
}
