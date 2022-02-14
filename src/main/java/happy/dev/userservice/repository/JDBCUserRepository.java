package happy.dev.userservice.repository;

import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;
import happy.dev.userservice.exception.NoUserDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JDBCUserRepository implements UserRepository{


    public static final String SELECT_MESSAGE_SQL = "select email, login_count, last_login_at, create_at from users";
    public static final String INSERT_MESSAGE_SQL = "insert into users(email, passwd) values (?, ?)" ;
    private final JdbcTemplate template;


    RowMapper<SearchedUser> usersRowMapper = ((rs, rowNum) -> {
        return new SearchedUser(rs.getString("email"),
                getToLocalDateTime(rs, "create_at"));
    });

    private LocalDateTime getToLocalDateTime(ResultSet rs, String data) throws SQLException {
        return rs.getTimestamp(data).toLocalDateTime();
    }

    private boolean LastLoginIsNull(ResultSet rs) throws SQLException {
        return rs.getTimestamp("last_login_at") == null;
    }


    @Override
    public Long save(SignUpUser user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_MESSAGE_SQL, new String[]{"seq"});
            ps.setString(1, user.getPrincipal());
            ps.setString(2, user.getCredentials());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public SearchedUser findById(Long id) {
        String sql = SELECT_MESSAGE_SQL +" where seq ="+ id;
        log.debug("query : {}", sql);
        try {
            return template.queryForObject(sql, usersRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.debug("{}", e.getMessage());
            throw new NoUserDataException("찾는 사용자가 존재하지 않습니다.");
        }
    }

    @Override
    public List<SearchedUser> findAll() {
        String sql = SELECT_MESSAGE_SQL;
        log.debug("query : {}", sql);
        List<SearchedUser> userList = template.query(sql, usersRowMapper);
        if (userList.isEmpty()) {
            throw new NoUserDataException("사용자가 존재하지 않습니다.");
        }
        return userList;
    }

    @Override
    public boolean findByEmail(String email) {
        String sql =  "select count(*) from users where email = '" + email + "'";
        log.debug("query : {}", sql);
        Integer integer = template.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        log.debug("already email : {}", integer);
        return Objects.requireNonNull(integer).equals(1);
    }
}
