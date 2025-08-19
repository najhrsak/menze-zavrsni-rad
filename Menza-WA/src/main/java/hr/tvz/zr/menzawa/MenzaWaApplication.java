package hr.tvz.zr.menzawa;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MenzaWaApplication {


    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer(args).start();
        SpringApplication.run(MenzaWaApplication.class, args);
        server.stop();
    }



}
