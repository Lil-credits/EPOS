package io.epos.portal_api.api.user;

import io.epos.portal_api.api.educationModule.EducationModuleRepository;
import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.domain.User;
import io.epos.portal_api.util.LogUtils;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.SqlTemplate;

import java.util.Collections;
import java.util.NoSuchElementException;

public class UserRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
  private static final String SQL_SELECT_USER_BY_ID =
    "SELECT id, name, did, issuancekey FROM Users WHERE id = #{id}";

  private static final RowMapper<User> USER_ROW_MAPPER = row -> new User(row.getInteger("id"), row.getString("name"), row.getString("did"), row.getJsonObject("issuancekey"));
  public Future<User> getUser(SqlConnection connection, int id) {
    return SqlTemplate
      .forQuery(connection, SQL_SELECT_USER_BY_ID)
      .execute(Collections.singletonMap("id", id))
      .map(rowSet -> {
        final RowIterator<Row> iterator = rowSet.iterator();
        if (iterator.hasNext()) {
          Row row = iterator.next();
          return USER_ROW_MAPPER.map(row);
        } else {
          throw new NoSuchElementException(LogUtils.NO_USER_WITH_ID_MESSAGE.buildMessage(id));
        }
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read user by id", SQL_SELECT_USER_BY_ID)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read user by id", throwable.getMessage())));
  }
}
