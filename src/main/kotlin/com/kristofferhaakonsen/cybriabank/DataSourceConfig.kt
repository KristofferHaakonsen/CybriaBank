import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary


@ConfigurationProperties("spring.datasource")
data class LocalDatabaseProperties(
    val jdbcUrl: String,
    val username: String,
    val password: String,
    val ownerUsername: String,
    val ownerPassword: String
)

@Configuration
class DataSourceConfigLocal(databaseProperties: LocalDatabaseProperties) {
    private val jdbcUrl = databaseProperties.jdbcUrl
    private val username = databaseProperties.username
    private val password = databaseProperties.password
    private val ownerUsername = databaseProperties.ownerUsername
    private val ownerPassword = databaseProperties.ownerPassword


    @Bean
    @Primary
    @DependsOn("migrationDataSource")
    fun dataSource() = HikariDataSource(
        HikariConfig().also {
            it.jdbcUrl = jdbcUrl
            it.username = username
            it.password = password

        }
    )

    @Bean("migrationDataSource")
    @Qualifier("migrationDataSource")
    @FlywayDataSource
    fun migrationDataSource() = HikariDataSource(
        HikariConfig().also {
            it.jdbcUrl = jdbcUrl
            it.username = ownerUsername
            it.password = ownerPassword
        }
    )

}
