package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();

        assertThrows(Exception.class, () -> {
            controller.request();
        });
    }

    static class Controller {
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        // 서비스에서 처리를 못하기 때문에 throws 선언 필요
        public void logic() throws ConnectException, SQLException {
            repository.call();
            networkClient.call();
        }

    }

    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패"); // 체크 예외
        }

    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("ex"); // 체크 예외
        }

    }
}
