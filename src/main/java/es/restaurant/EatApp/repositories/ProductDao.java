package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.ProductPriority;

public class ProductDao extends Dao {

	private static ProductDao dao;
	public static final String TABLE_PRODUCTS = "products";

	public static ProductDao getProductDao() {
		if(dao == null) {
			dao = new ProductDao();
		}
		return dao;
	}

	private ProductDao() {
		super();
	}

	private RowMapper<Product> buildProduct() {
		return new RowMapper<Product>() {
			public Product mapRow(ResultSet result, int rowNum) throws SQLException {
				Product product = new Product(result.getInt("id"),
											  result.getString("name"),
											  result.getString("description"),
											  result.getDouble("price"),
											  result.getInt("priority"));
				return product;
			}
		};
	}

	public String selectAllProducts() {
		return selectAllFrom(TABLE_PRODUCTS);
	}
	public List<Product> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildProduct());
	}

	public List<Product> getProducts() {
		return executeQuery(selectAllProducts());
	}

	public Product getProductById(int id) {
		return findProduct(id);
	}

	public Product findProduct(Product product) {
		return findProduct(product.getId());
	}

	public Product findProduct(int id) {
		List<Product> i = executeQuery(selectAllProducts() + where("id=" + id));
		if(i.size() == 0)
			return null;
		else
			return i.get(0);
	}

	public boolean insert(Product product) {
		String sql = insertInto(TABLE_PRODUCTS, "(name, description, price, priority) VALUES (?,?,?,?)");
		return (this.db.getJdbcTemplate().update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getPriority().getTypeOrdinal()) == 1);
	}
	
	public boolean insertWithId(Product product) {
		String sql = insertInto(TABLE_PRODUCTS, "(id, name, description, price, priority) VALUES (?,?,?,?,?)");
		return (this.db.getJdbcTemplate().update(sql, product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getPriority().getTypeOrdinal()) == 1);
	}
	
	public boolean deleteProduct(int productId) {
		String sql = delete(TABLE_PRODUCTS, where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, productId) == 1);
	}

	public boolean updateName(Product product, String newValue) {
		String sql = update(TABLE_PRODUCTS, "name=?" + where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, newValue, product.getId()) == 1);
	}

	public boolean updateDescription(Product product, String newValue) {
		String sql = update(TABLE_PRODUCTS, "description=?" + where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, newValue, product.getId()) == 1);
	}

	public boolean updatePrice(Product product, double newValue) {
		String sql = update(TABLE_PRODUCTS, "price=?" + where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, newValue, product.getId()) == 1);
	}

	public boolean updatePriority(Product product, ProductPriority newValue) {
		String sql = update(TABLE_PRODUCTS, "priority=?" + where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, newValue.getTypeOrdinal(), product.getId()) == 1);
	}

	public boolean updateProduct(Product product) {
		String sql = update(TABLE_PRODUCTS, "name=?, description=?, price=?, priority=?" + where("id=?"));
		return (this.db.getJdbcTemplate().update(sql, product.getName(),
													  product.getDescription(),
													  product.getPrice(),
													  product.getPriority().getTypeOrdinal(),
													  product.getId()) == 1);
	}

	public Product getProductByNameAndDescription(String productName, String productDescription) {
		String sql = selectAllFrom(TABLE_PRODUCTS) + where ("name = \"" + productName + "\""+ and ("description = \"" + productDescription + "\""));
		List<Product> products = executeQuery(sql);
		return products.isEmpty() ? null : products.get(0);
	}
}
