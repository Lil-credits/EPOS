import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.epos"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.5.7"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "io.epos.portal_api.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  // https://mvnrepository.com/artifact/io.smallrye.reactive/smallrye-mutiny-vertx-core
  implementation("io.smallrye.reactive:smallrye-mutiny-vertx-core:3.12.0")
  // https://mvnrepository.com/artifact/io.smallrye.reactive/smallrye-mutiny-vertx-web
  implementation("io.smallrye.reactive:smallrye-mutiny-vertx-web:3.12.0")
  // https://mvnrepository.com/artifact/io.smallrye.reactive/smallrye-mutiny-vertx-web-client
  implementation("io.smallrye.reactive:smallrye-mutiny-vertx-web-client:3.12.0")
  // https://mvnrepository.com/artifact/io.smallrye.reactive/smallrye-mutiny-vertx-pg-client
  implementation("io.smallrye.reactive:smallrye-mutiny-vertx-pg-client:3.12.0")
// https://mvnrepository.com/artifact/org.hibernate.reactive/hibernate-reactive-core
  implementation("org.hibernate.reactive:hibernate-reactive-core:2.3.0.Final")
  // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
  implementation("ch.qos.logback:logback-classic:1.5.6")
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
  implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
  // https://mvnrepository.com/artifact/org.testcontainers/postgresql
  implementation("org.testcontainers:postgresql:1.19.8")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
