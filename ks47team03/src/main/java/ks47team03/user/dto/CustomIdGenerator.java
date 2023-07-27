package ks47team03.user.dto;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Work;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        final String[] id = new String[1];
        String prefix = "community_now_board_code";

        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet rs = statement.executeQuery("SELECT COUNT(community_now_board_code) as code FROM community_now_board")) {
                        if (rs.next()) {
                            int count = rs.getInt("code");
                            id[0] = prefix + new Integer(count + 1).toString();
                            System.out.println("Generated Id: " + id[0]);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return id[0];
    }
}