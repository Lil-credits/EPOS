package io.epos.portal_api.api.service;

import io.epos.portal_api.api.model.Book;
import io.epos.portal_api.api.model.BookGetAllResponse;
import io.epos.portal_api.api.model.BookGetByIdResponse;
import io.epos.portal_api.api.repository.BookRepository;
import io.epos.portal_api.api.repository.EducationModuleRepository;
import io.epos.portal_api.util.LogUtils;
import io.epos.portal_api.util.QueryUtils;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Pool;

import java.util.List;
import java.util.stream.Collectors;

public class EducationModuleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

  private final Pool dbClient;
  private final EducationModuleRepository educationModuleRepository;

  public EducationModuleService(Pool dbClient,
                                EducationModuleRepository educationModuleRepository) {
    this.dbClient = dbClient;
    this.educationModuleRepository = educationModuleRepository;
  }
}
