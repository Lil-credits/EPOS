package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.domain.EducationModule;
import io.epos.portal_api.domain.EducationModuleVersion;
import io.epos.portal_api.util.LogUtils;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.SqlTemplate;

import java.util.*;

public class EducationModuleRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(EducationModuleRepository.class);
  private static final String SQL_INSERT_EDUCATION_MODULE = "INSERT INTO EducationModule (Name, ImageUrl, TeamID)" +
    "VALUES (#{name}, #{imageurl}, #{teamid}) RETURNING id";
  private static final String SQL_INSERT_EDUCATION_MODULE_VERSION =
    "INSERT INTO EducationModuleVersion (version, description, attributes, requiredachievements, skills, educationmoduleid, status)" +
      "VALUES (#{version}, #{description}, #{attributes}, #{requiredAchievements}, #{skills}, #{educationModuleID}, #{status}) RETURNING id";

  private static final String SQL_SELECT_EDUCATION_MODULE_BY_ID =
    "SELECT em.id as edu_id, em.name, em.teamid, em.imageurl, " +
      "ev.id AS version_id, ev.version AS version, ev.description AS version_description, " +
      "ev.attributes AS version_attributes, ev.requiredachievements AS version_required_achievements, " +
      "ev.skills AS version_skills, ev.status AS version_status " +
      "FROM EducationModule em " +
      "LEFT JOIN EducationModuleVersion ev ON em.id = ev.EducationModuleID " +
      "WHERE em.id = #{id}";
  private static final String SQL_SELECT_ALL_EDUCATION_MODULES =     "SELECT em.id as edu_id, em.name, em.teamid, em.imageurl, " +
    "ev.id AS version_id, ev.version AS version, ev.description AS version_description, " +
    "ev.attributes AS version_attributes, ev.requiredachievements AS version_required_achievements, " +
    "ev.skills AS version_skills, ev.status AS version_status " +
    "FROM EducationModule em " +
    "LEFT JOIN EducationModuleVersion ev ON em.id = ev.EducationModuleID " +
    "ORDER BY em.id DESC LIMIT #{limit} OFFSET #{offset} ;";
  private static final String SQL_COUNT_EDUCATION_MODULES = "SELECT COUNT(*) AS total FROM EducationModule;";

  private static final RowMapper<EducationModule> EDUCATION_MODULE_ROW_MAPPER = row -> {
    EducationModule educationModule = new EducationModule();
    EducationModuleVersion educationModuleVersion = new EducationModuleVersion();
    educationModule.setId(row.getInteger("edu_id"));
    educationModule.setName(row.getString("edu_name"));
    educationModule.setImageUrl(row.getString("edu_imageurl"));
    educationModule.setTeamId(row.getInteger("edu_teamid"));

    return educationModule;
  };
  public Future<EducationModule> getEducationModule(SqlConnection connection, int id) {
    return SqlTemplate
      .forQuery(connection, SQL_SELECT_EDUCATION_MODULE_BY_ID)
      .execute(Collections.singletonMap("id", id))
      .map(rowSet -> {
        final RowIterator<Row> iterator = rowSet.iterator();
        if (iterator.hasNext()) {
          Row row = iterator.next();
          EducationModule educationModule = mapRowToEducationModule(row);
          educationModule.getEducationModuleVersions().add(mapRowToEducationModuleVersion(row));

          while (iterator.hasNext()) {
            educationModule.getEducationModuleVersions().add(mapRowToEducationModuleVersion(iterator.next()));
          }
          return educationModule;
        } else {
          throw new NoSuchElementException(LogUtils.NO_BOOK_WITH_ID_MESSAGE.buildMessage(id));
        }
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read book by id", SQL_SELECT_EDUCATION_MODULE_BY_ID)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read book by id", throwable.getMessage())));
  }
  public Future<List<EducationModule>> getAllEducationModules(SqlConnection connection, int limit, int offset) {
    return SqlTemplate
      .forQuery(connection, SQL_SELECT_ALL_EDUCATION_MODULES)
      .execute(Map.of("limit", limit, "offset", offset))
      .map(rowSet -> {
        final Map<Integer, EducationModule> moduleMap = new HashMap<>();
        rowSet.forEach(row -> {
          int moduleId = row.getInteger("edu_id");
          EducationModule educationModule;
          if (moduleMap.containsKey(moduleId)) {
            educationModule = moduleMap.get(moduleId);
          } else {
            educationModule = mapRowToEducationModule(row);
            moduleMap.put(moduleId, educationModule);
          }
          if (row.getInteger("version_id") != null) {
            EducationModuleVersion version = mapRowToEducationModuleVersion(row);
            educationModule.getEducationModuleVersions().add(version);
          }
        });
        return (List<EducationModule>) new ArrayList<>(moduleMap.values());
      })
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Read all education modules")))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Read all education modules", throwable.getMessage())));
  }

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

  public Future<Integer> count(SqlConnection connection) {
    final RowMapper<Integer> ROW_MAPPER = row -> row.getInteger("total");

    return SqlTemplate
      .forQuery(connection, SQL_COUNT_EDUCATION_MODULES)
      .mapTo(ROW_MAPPER)
      .execute(Collections.emptyMap())
      .map(rowSet -> rowSet.iterator().next())
      .onSuccess(success -> LOGGER.info(LogUtils.REGULAR_CALL_SUCCESS_MESSAGE.buildMessage("Count education modules", SQL_COUNT_EDUCATION_MODULES)))
      .onFailure(throwable -> LOGGER.error(LogUtils.REGULAR_CALL_ERROR_MESSAGE.buildMessage("Count education module", throwable.getMessage())));
  }
  private static EducationModule mapRowToEducationModule(Row row) {
    EducationModule educationModule = new EducationModule();
    educationModule.setId(row.getInteger("edu_id"));
    educationModule.setName(row.getString("name"));
    educationModule.setImageUrl(row.getString("imageurl"));
    educationModule.setTeamId(row.getInteger("teamid"));
    educationModule.setEducationModuleVersions(new ArrayList<>());
    return educationModule;
  }

  private static EducationModuleVersion mapRowToEducationModuleVersion(Row row) {
    EducationModuleVersion educationModuleVersion = new EducationModuleVersion();
    educationModuleVersion.setId(row.getInteger("version_id"));
    educationModuleVersion.setVersion(row.getInteger("version"));
    educationModuleVersion.setDescription(row.getString("version_description"));
    educationModuleVersion.setAttributes(row.getJsonObject("version_attributes"));
    educationModuleVersion.setRequiredAchievements(row.getJsonArray("version_required_achievements"));
    educationModuleVersion.setSkills(row.getJsonArray("version_skills"));
    educationModuleVersion.setEducationModuleID(row.getInteger("edu_id"));
    educationModuleVersion.setStatus(row.getString("version_status"));
    return educationModuleVersion;
  }
}
