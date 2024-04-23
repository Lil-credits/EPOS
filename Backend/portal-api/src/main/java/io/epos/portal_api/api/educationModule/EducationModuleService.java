package io.epos.portal_api.api.educationModule;

import io.epos.portal_api.api.book.BookService;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.sqlclient.Pool;

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
