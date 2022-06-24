package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource poolDataSource = new ComboPooledDataSource();
		poolDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura_zoccojulian?useTimeZone=true&serverTimeZone=UTC");
		poolDataSource.setUser("root");
		poolDataSource.setPassword("Mavi1722*");
		poolDataSource.setMaxPoolSize(10);
		
		this.dataSource = poolDataSource;
	}
	
	public Connection recuperaConexion() {
		
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
