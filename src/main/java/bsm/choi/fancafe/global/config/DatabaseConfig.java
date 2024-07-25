package bsm.choi.fancafe.global.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // 프로시저 삭제
            statement.execute("DROP PROCEDURE IF EXISTS create_index_if_not_exists");

            // 프로시저 생성
            String createProcedure = "CREATE PROCEDURE create_index_if_not_exists() " +
                    "BEGIN " +
                    "DECLARE idx_count INT DEFAULT 0; " +
                    "SELECT COUNT(*) INTO idx_count FROM INFORMATION_SCHEMA.STATISTICS " +
                    "WHERE table_schema = DATABASE() AND table_name = 'board' AND index_name = 'idx_content'; " +
                    "IF idx_count = 0 THEN " +
                    "SET @create_index_sql = 'CREATE INDEX idx_content ON board (content(1000))'; " +
                    "PREPARE stmt FROM @create_index_sql; " +
                    "EXECUTE stmt; " +
                    "DEALLOCATE PREPARE stmt; " +
                    "END IF; " +
                    "END";
            statement.execute(createProcedure);

            // 프로시저 호출
            statement.execute("CALL create_index_if_not_exists()");

            // 프로시저 삭제
            statement.execute("DROP PROCEDURE IF EXISTS create_index_if_not_exists");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
