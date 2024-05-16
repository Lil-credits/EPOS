package io.epos.portal_api.api.microCredential;

import io.epos.portal_api.domain.IssuedMicroCredential;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class MicroCredentialRepository {
    public Future<IssuedMicroCredential> insertIssuedMicroCredential(IssuedMicroCredential issuedMicroCredential) {
        return Future.succeededFuture(issuedMicroCredential);
    }

}
