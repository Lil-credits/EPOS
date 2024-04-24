package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.LogUtils;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.templates.SqlTemplate;

import java.util.*;

public class EducationModuleRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(EducationModuleRepository.class);

  private static final String SQL_INSERT_EDUCATION_MODULE = "INSERT INTO EducationModule (Name, ImageUrl, TeamID)" +
    "VALUES (#{Name}, #{ImageUrl}, #{TeamID}) RETURNING id";

  private static final String SQL_INSERT_EDUCATION_MODULE_VERSION =
    "INSERT INTO EducationModuleVersion (version, description, attributes, requiredachievements, skills, educationmoduleid, status)" +
      "VALUES (#{version}, #{description}, #{attributes}, #{requiredAchievements}, #{skills}, #{educationModuleID}, #{status}) RETURNING id";

  public Future<EducationModule> insertEducationModule(SqlConnection connection,
                                        EducationModule educationModule) {
    return SqlTemplate
      .forUpdate(connection, SQL_INSERT_EDUCATION_MODULE)
      .mapFrom(EducationModule.class)
      .mapTo(EducationModule.class)
      .execute(educationModule)
      .map(rowSet -> {
        final RowIterator<EducationModule> iterator = rowSet.iterator();

        if (iterator.hasNext()) {
          educationModule.setId(iterator.next().getId());
          return educationModule;
        } else {
          throw new IllegalStateException(LogUtils.CANNOT_CREATE_EDUCATION_MODULE_MESSAGE.buildMessage());
        }
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Insert education module", SQL_INSERT_EDUCATION_MODULE)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Insert education module error", throwable.getMessage())));
  }

  public Future<EducationModuleVersion> insertEducationModuleVersion(SqlConnection connection, EducationModuleVersion educationModuleVersion) {
    return SqlTemplate
      .forUpdate(connection, SQL_INSERT_EDUCATION_MODULE_VERSION)
      .mapFrom(EducationModuleVersion.class)
      .mapTo(EducationModuleVersion.class)
      .execute(educationModuleVersion)
      .map(rowSet -> {
        final RowIterator<EducationModuleVersion> iterator = rowSet.iterator();

        if (iterator.hasNext()) {
          educationModuleVersion.setId(iterator.next().getId());
          return educationModuleVersion;
        } else {
          throw new IllegalStateException(LogUtils.CANNOT_CREATE_EDUCATION_MODULE_MESSAGE.buildMessage());
        }
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Insert education module version", SQL_INSERT_EDUCATION_MODULE_VERSION)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Insert education module version error", throwable.getMessage())));
  }
}
