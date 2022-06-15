package com.tom.authenticator.control;

import com.tom.authenticator.entity.User;
import com.tom.authenticator.entity.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate template;

    @Override
    public User findById(String id) {
        return template.query("select * from users where id=" + id, new UserRowMapper()).get(0);
    }

    @Override
    public void createUser(User user) {
        final String sql = "insert into users(id, firstname, lastname, username, email, password, birthDateTimestamp) values(:id, :firstName, :lastName, :username, :email, :password, :birthDateTimestamp)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("birthDateTimestamp", user.getBirthDateTimestamp());
        template.update(sql, param, holder);
    }

    @Override
    public void updateUser(User user) {
        final String sql = "update users set firstname=:firstName, lastname=:lastName, username=:username, email=:email, password=:password, birthDateTimestamp=:birthDateTimestamp where id=:id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("birthDateTimestamp", user.getBirthDateTimestamp());
        template.update(sql, param, holder);
    }

    @Override
    public void deleteUser(String id) {
        final String sql = "delete from users where id=:id";

        Map<String, String> map = new HashMap<>();
        map.put("id", id);

        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
