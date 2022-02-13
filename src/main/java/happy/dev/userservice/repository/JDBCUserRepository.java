package happy.dev.userservice.repository;

import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JDBCUserRepository implements UserRepository{

    private final JdbcTemplate template;

    RowMapper<SearchedUser> usersRowMapper = ((rs, rowNum) ->
            new SearchedUser(rs.getString("email"),
                                rs.getString("create_at")
            )
    );


    String INSERT_MESSAGE_SQL = "insert into users(email, password) values (?, ?)" ;

    @Override
    public Long save(SignUpUser user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_MESSAGE_SQL, new String[]{"seq"});
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPwd());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public SearchedUser findById(Long id) {
        String sql = "select email, create_at from users where seq = " + id;
        log.debug("query : {}", sql);
        return template.queryForObject(sql, usersRowMapper);
    }

    @Override
    public List<SearchedUser> findAll() {
        String sql = "select email, create_at from users";
        log.debug("query : {}", sql);
        return template.query(sql, usersRowMapper);
    }
}
